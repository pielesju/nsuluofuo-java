package org.cofezuwo.nsuluofuo.graphics;

import org.cofezuwo.nsuluofuo.entities.Entity;
import org.cofezuwo.nsuluofuo.graphics.tiles.Tile;
import org.cofezuwo.nsuluofuo.main.Game;
import org.cofezuwo.nsuluofuo.worlds.World;

public class GameCamera {

	private static GameCamera instance;

	private int xOffset;
	private int yOffset;

	public void move(int xAmt, int yAmt) {
		setxOffset(getxOffset() + xAmt);
		setyOffset(getyOffset() + yAmt);
		checkBlankSpace();
	}

	public int getxOffset() {
		return xOffset;
	}

	public void setxOffset(int xOffset) {
		this.xOffset = xOffset;
	}

	public int getyOffset() {
		return yOffset;
	}

	public void setyOffset(int yOffset) {
		this.yOffset = yOffset;
	}

	public void centerOnEntity(Entity e) {
		setxOffset(e.getX() - Game.getInstance().getWidth() / 2 + e.getWidth() / 2);
		setyOffset(e.getY() - Game.getInstance().getHeight() / 2 + e.getHeight() / 2);
		checkBlankSpace();
	}
	
	public void checkBlankSpace(){
		if(getxOffset() < 0) setxOffset(0);

		if(getxOffset() > World.getInstance().getWidth() * Tile.TILEWIDTH - Game.getInstance().getWidth()){
			setxOffset(World.getInstance().getWidth() * Tile.TILEWIDTH - Game.getInstance().getWidth());
		}

		if(getyOffset() < 0) setyOffset(0);

		if(getyOffset() > World.getInstance().getHeight() * Tile.TILEHEIGHT - Game.getInstance().getHeight()){
			setyOffset(World.getInstance().getHeight() * Tile.TILEHEIGHT - Game.getInstance().getHeight());
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
