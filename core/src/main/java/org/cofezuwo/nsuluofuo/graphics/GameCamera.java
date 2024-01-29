package org.cofezuwo.nsuluofuo.graphics;

import lombok.Getter;
import lombok.Setter;
import org.cofezuwo.nsuluofuo.entities.Entity;
import org.cofezuwo.nsuluofuo.graphics.tiles.Tile;
import org.cofezuwo.nsuluofuo.main.Game;
import org.cofezuwo.nsuluofuo.worlds.World;

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
		setXOffset(e.getX() - Game.getInstance().getWidth() / 2 + e.getWidth() / 2);
		setYOffset(e.getY() - Game.getInstance().getHeight() / 2 + e.getHeight() / 2);
		checkBlankSpace();
	}
	
	public void checkBlankSpace(){
		if(getXOffset() < 0) setXOffset(0);

		if(getXOffset() > World.getInstance().getWidth() * Tile.TILE_WIDTH - Game.getInstance().getWidth()){
			setXOffset(World.getInstance().getWidth() * Tile.TILE_WIDTH - Game.getInstance().getWidth());
		}

		if(getYOffset() < 0) setYOffset(0);

		if(getYOffset() > World.getInstance().getHeight() * Tile.TILE_HEIGHT - Game.getInstance().getHeight()){
			setYOffset(World.getInstance().getHeight() * Tile.TILE_HEIGHT - Game.getInstance().getHeight());
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
