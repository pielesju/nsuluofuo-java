package org.cofezuwo.nsuluofuo.main;

import java.awt.Graphics;

import java.awt.image.BufferStrategy;

import lombok.Getter;
import lombok.Setter;
import org.cofezuwo.nsuluofuo.graphics.Assets;
import org.cofezuwo.nsuluofuo.graphics.GameCamera;
import org.cofezuwo.nsuluofuo.gui.Display;
import org.cofezuwo.nsuluofuo.input.KeyManager;
import org.cofezuwo.nsuluofuo.input.MouseManager;
import org.cofezuwo.nsuluofuo.modes.GameMode;
import org.cofezuwo.nsuluofuo.modes.MenuMode;
import org.cofezuwo.nsuluofuo.modes.Mode;
import org.cofezuwo.nsuluofuo.modes.SettingsMode;
import org.cofezuwo.nsuluofuo.multiplayer.SimpleDualPlayer;

public class Game implements Runnable {

	private Display display;

	private Thread thread;
	private String title;

	@Getter
	@Setter
	private int width;

	@Getter
	@Setter
	private int height;
	private boolean running;
	private BufferStrategy bufferStrategy;
	private Graphics g;

	@Getter
	private KeyManager keyManager;
	private int ticks;

	// States

	public Mode gameMode;
	public Mode menuMode;
	public Mode settingsMode;
	public Mode dialogMode;


	@Getter
	private MouseManager mouseManager;

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	@Getter
	private GameCamera gameCamera;

	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();

	}

	private void initialize() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.initialize();

		Handler.getInstance().setGame(this);

		gameCamera = new GameCamera( 0, 0);
		gameMode = new GameMode();
		menuMode = new MenuMode();
		settingsMode = new SettingsMode();
		
		Mode.setMode(menuMode);
		
		//Server beitreten
		///SimpleDualPlayer.join(Launcher.address, 1442);
	}

	private void update() {
		keyManager.update();
		if (Mode.getMode() != null) {
			Mode.getMode().update();
		}

		//SimpleDualPlayer.update();
	}

	private void render() {
		bufferStrategy = display.getCanvas().getBufferStrategy();
		if (bufferStrategy == null) {
			display.getCanvas().createBufferStrategy(16);
			return;
		}
		g = bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, width, height);

		if (Mode.getMode() != null) {
			Mode.getMode().render(g);
		}

		bufferStrategy.show();
		g.dispose();

	}

	public void run() {
		initialize();
		int fps = 60;
		double timePerUpdate = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		ticks = 0;

		while (isRunning()) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerUpdate;
			timer += now - lastTime;
			lastTime = now;

			if (delta >= 1) {
				update();
				render();
				ticks++;
				delta--;
			}

			if (timer >= 1000000000) {
				ticks = 0;
				timer = 0;
			}
		}
		try {
			stop();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public synchronized void start() {
		if (isRunning()) return;

		setRunning(true);

		thread = new Thread(this);
		thread.start();
	}

	private synchronized void stop() throws InterruptedException {
		if (!isRunning()) return;

		setRunning(false);
		thread.join();
	}
}