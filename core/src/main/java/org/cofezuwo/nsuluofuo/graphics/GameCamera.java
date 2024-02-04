package org.cofezuwo.nsuluofuo.graphics;

import lombok.Getter;
import lombok.Setter;
import org.cofezuwo.nsuluofuo.entities.Entity;
import org.cofezuwo.nsuluofuo.graphics.tiles.Tile;
import org.cofezuwo.nsuluofuo.main.Main;
import org.cofezuwo.nsuluofuo.main.Game;

public class GameCamera {

	/*static {
		System.load("/home/julian/nsuluofuo-java/core/target/classes/game_camera.so");
	}*/



	private static GameCamera instance;

	@Getter
	@Setter
	private int xOffset;
	@Getter
	@Setter
	private int yOffset;

	public void move(int xAmt, int yAmt) {
		setXOffset(getXOffset() + xAmt);
		setYOffset(getYOffset() + yAmt);
		checkBlankSpace();
	}

	public void centerOnEntity(Entity e) {
		setXOffset(e.getX() - Main.getInstance().getWidth() / 2 + e.getWidth() / 2);
		setYOffset(e.getY() - Main.getInstance().getHeight() / 2 + e.getHeight() / 2);
		checkBlankSpace();
	}
	
	public void checkBlankSpace(){
		if(getXOffset() < 0) setXOffset(0);

		if(getXOffset() > Game.getInstance().getWidth() * Tile.TILE_WIDTH - Main.getInstance().getWidth()){
			setXOffset(Game.getInstance().getWidth() * Tile.TILE_WIDTH - Main.getInstance().getWidth());
		}

		if(getYOffset() < 0) setYOffset(0);

		if(getYOffset() > Game.getInstance().getHeight() * Tile.TILE_HEIGHT - Main.getInstance().getHeight()){
			setYOffset(Game.getInstance().getHeight() * Tile.TILE_HEIGHT - Main.getInstance().getHeight());
		}
	}

	private GameCamera() {
		this.xOffset = 0;
		this.yOffset = 0;
	}

	public static GameCamera getInstance() {
		if(null == instance) {
			instance = new GameCamera();
		}

		return instance;
	}

}
