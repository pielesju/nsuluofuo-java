package org.cofezuwo.nsuluofuo.main;

import lombok.Getter;
import lombok.Setter;
import org.cofezuwo.nsuluofuo.graphics.*;
import org.cofezuwo.nsuluofuo.input.KeyManager;

import java.awt.image.BufferStrategy;

public class Main implements Runnable {


	private static Main instance;

	public static Main getInstance() {
		if(null == instance) {
			instance = new Main("NSULUOFUO", 640, 480);
		}

		return instance;
	}

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

	private Game game;

	private BufferStrategy bufferStrategy;

	private static String graphicsBackend;

	private static String startMode;



	private Main(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
	}

	private static void handleArgument(String arg) {
		String argv[] = arg.split("=");
		switch(argv[0]) {
			case "-PgraphicsBackend": graphicsBackend = argv[1]; break;
			case "-PstartMode": startMode = argv[1]; break;
		}
	}

	public static void main(String[] args) {
		for(String arg : args) {
			if(!arg.contains("=")) continue;
			handleArgument(arg);
		}

		Main.getInstance().start();
	}

	private void initialize() {
		switch(graphicsBackend) {
			case "AWT": this.g = new ATGAWT(this.title, this.width, this.height); break;
			case "OGL": this.g = new ATGGL(this.title, this.width, this.height); break;
			case "SDL": this.g = new ATGSDL(this.title, this.width, this.height); break;
		}

		Assets.initialize();
		game = game.getInstance();
		g.getDisplay().getCanvas().createBufferStrategy(2);
	}

	private void update() {
		KeyManager.getInstance().update();
		game.update();
	}

	private void render() {
		BufferStrategy bufferStrategy = g.getDisplay().getCanvas().getBufferStrategy();


		g.setGraphics(bufferStrategy.getDrawGraphics());
		g.clear();

		game.render(g);

		bufferStrategy.show();
		g.free();
	}

	public void run() {
		initialize();
		int fps = 120;
		double timePerUpdate = (double) SECOND / fps;
		double delta = 0;

		long now;
		long lastTime = System.nanoTime();
		long timer = 0;

		while (isRunning()) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerUpdate;
			timer += now - lastTime;
			lastTime = now;

			if (delta >= 1) {
				update();
				render();
				delta--;
			}

			if (timer >= SECOND) timer = 0;
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
