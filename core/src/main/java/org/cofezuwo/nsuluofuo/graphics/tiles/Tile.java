package org.cofezuwo.nsuluofuo.graphics.tiles;

import lombok.Getter;
import org.cofezuwo.nsuluofuo.graphics.ATG;

import java.awt.Rectangle;
import java.awt.Image;

public class Tile {

	public static final int TILE_WIDTH = 32;
	public static final int TILE_HEIGHT = 32;

	@Getter
	private int id;
	private Image texture;
	private int width;
	private int height;

	@Getter
	private boolean solid;

	@Getter
	private Rectangle bounds;

	public Tile(int id, Image texture, int width, int height, boolean solid) {
		this.id = id;
		this.texture = texture;
		this.width = width;
		this.height = height;
		this.solid = solid;
		this.bounds = new Rectangle(0, 0, width, height);
	}

	public void update() {
		// not supported
	}

	public void render(ATG g, int x, int y) {
		g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT);
	}
}
