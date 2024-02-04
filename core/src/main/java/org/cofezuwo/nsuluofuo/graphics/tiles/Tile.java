package org.cofezuwo.nsuluofuo.graphics.tiles;

import lombok.Getter;
import lombok.Setter;
import org.cofezuwo.nsuluofuo.graphics.ATG;

import java.awt.Rectangle;
import java.awt.Image;

public class Tile {

	public static final int TILE_WIDTH = 32;
	public static final int TILE_HEIGHT = 32;

	@Getter
	protected int id;

	@Getter @Setter
	private Image texture;
	protected int width;
	protected int height;

	@Getter
	protected boolean solid;

	@Getter
	protected Rectangle bounds;

	public Tile(int id, Image texture, int width, int height, boolean solid) {
		this.id = id;
		this.texture = texture;
		this.width = width;
		this.height = height;
		this.solid = solid;
		this.bounds = new Rectangle(0, 0, width, height);
	}

	// without image
	public Tile(int id, int width, int height, boolean solid) {
		this.id= id;
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
