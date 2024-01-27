package org.cofezuwo.nsuluofuo.graphics.tiles;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Tile {

	public static final int TILEWIDTH = 32;
	public static final int TILEHEIGHT = 32;

	private int id;
	private BufferedImage texture;
	private int width;
	private int height;
	private boolean solid;
	private Rectangle bounds;

	public Tile(int id, BufferedImage texture, int width, int height, boolean solid) {
		this.id = id;
		this.texture = texture;
		this.width = width;
		this.height = height;
		this.solid = solid;
		this.bounds = new Rectangle(0, 0, width, height);
	}

	public void update() {

	}

	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}

	public boolean isSolid() {
		return this.solid;
	}

	public int getId() {
		return id;
	}

	public Rectangle getBounds() {
		return bounds;
	}
}
