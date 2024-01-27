package org.cofezuwo.nsuluofuo.main;

import java.awt.Color;

import lombok.Getter;
import lombok.Setter;
import org.cofezuwo.nsuluofuo.graphics.GameCamera;
import org.cofezuwo.nsuluofuo.input.KeyManager;
import org.cofezuwo.nsuluofuo.input.MouseManager;
import org.cofezuwo.nsuluofuo.modes.Mode;
import org.cofezuwo.nsuluofuo.worlds.World;

public class Handler {

	private static Handler instance;

	private Handler() {

	}

	public static Handler getInstance() {
		if(null == instance) {
			instance = new Handler();
		}

		return instance;
	}

	@Getter
	@Setter
	private Game game;

	@Getter
	@Setter
	private World world;

	@Getter
	@Setter
	private Mode mode;

	@Getter
	@Setter
	private Color color;

	public int getWidth() {
		return game.getWidth();
	}

	public int getHeight() {
		return game.getHeight();
	}

	public GameCamera getGameCamera() {
		return game.getGameCamera();
	}

	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}
	
	public MouseManager getMouseManager(){
		return game.getMouseManager();
	}

}
