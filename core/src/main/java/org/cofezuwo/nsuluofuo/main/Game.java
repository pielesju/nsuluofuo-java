package org.cofezuwo.nsuluofuo.main;

import lombok.Getter;
import lombok.Setter;
import org.cofezuwo.nsuluofuo.graphics.ATG;
import org.cofezuwo.nsuluofuo.graphics.ATGAWT;
import org.cofezuwo.nsuluofuo.graphics.Assets;
import org.cofezuwo.nsuluofuo.input.KeyManager;

import java.awt.image.BufferStrategy;

public class Game implements Runnable {

	private static Game instance;

	private static final long SECOND = 1000000000;

	private Thread thread;
	private final String title;

	@Getter
	@Setter
	private int width;

	@Getter
	@Setter
	private int height;

	@Getter
	@Setter
	private boolean running;

	private ATG g;

	private GameMode gameMode;



	private Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;

	}

	public static void main(String[] args) {
		Game.getInstance().start();
	}

	public static Game getInstance() {
		if(null == instance) {
			instance = new Game("NSULUOFUO", 640, 480);
		}

		return instance;
	}

	private void initialize() {

		this.g = new ATGAWT(this.title, this.width, this.height);

		Assets.initialize();


		gameMode = new GameMode();
	}

	private void update() {
		KeyManager.getInstance().update();
		gameMode.update();
	}

	private void render() {
  		BufferStrategy bufferStrategy = g.getDisplay().getCanvas().getBufferStrategy();
		if (bufferStrategy == null) {
			g.getDisplay().getCanvas().createBufferStrategy(2);
			return;
		}

		g.setGraphics(bufferStrategy.getDrawGraphics());
		g.clear();

		gameMode.render(g);

		bufferStrategy.show();
		g.free();
	}

	public void run() {
  		int ticks;
		initialize();
		int fps = 120;
		double timePerUpdate = (double) SECOND / fps;
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

			if (timer >= SECOND) {
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
