package org.cofezuwo.nsuluofuo.graphics;

import org.cofezuwo.nsuluofuo.entities.Entity;
import org.cofezuwo.nsuluofuo.graphics.tiles.Tile;
import org.cofezuwo.nsuluofuo.main.Handler;

public class GameCamera {

	private float xOffset;
	private float yOffset;

	public void move(float xAmt, float yAmt) {
		setxOffset(getxOffset() + xAmt);
		setyOffset(getyOffset() + yAmt);
		checkBlankSpace();
	}

	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}

	public void centerOnEntity(Entity e) {
		setxOffset(e.getX() - Handler.getInstance().getWidth() / 2 + e.getWidth() / 2);
		setyOffset(e.getY() - Handler.getInstance().getHeight() / 2 + e.getHeight() / 2);
		checkBlankSpace();
	}
	
	public void checkBlankSpace(){
		if(getxOffset() < 0) setxOffset(0);

		if(getxOffset() > Handler.getInstance().getWorld().getWidth() * Tile.TILEWIDTH - Handler.getInstance().getWidth()){
			setxOffset(Handler.getInstance().getWorld().getWidth() * Tile.TILEWIDTH - Handler.getInstance().getWidth());
		}

		if(getyOffset() < 0) setyOffset(0);

		if(getyOffset() > Handler.getInstance().getWorld().getHeight() * Tile.TILEHEIGHT - Handler.getInstance().getHeight()){
			setyOffset(Handler.getInstance().getWorld().getHeight() * Tile.TILEHEIGHT - Handler.getInstance().getHeight());
		}
	}

	public GameCamera(float xOffset, float yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;

	}

}
