package org.cofezuwo.nsuluofuo.creatures;

import lombok.Getter;
import lombok.Setter;
import org.cofezuwo.nsuluofuo.entities.Entity;
import org.cofezuwo.nsuluofuo.graphics.tiles.Tile;
import org.cofezuwo.nsuluofuo.main.Game;

public abstract class Creature extends Entity {

	public static final int DEFAULT_SPEED = 1;
	public static final int DEFAULT_CREATURE_WIDTH = 32;
	public static final int DEFAULT_CREATURE_HEIGHT = 32;

	@Getter
	@Setter
	private int speed;

	@Getter
	@Setter
	private int xMove = 0;

	@Getter
	@Setter
	private int yMove = 0;


	public void moveX() {
		if (xMove > 0) {
			if(getX() >= (Game.getInstance().getWidth() * Tile.TILE_WIDTH- this.getWidth())) return;

			int tx = (getX() + xMove + bounds[0].x + bounds[0].width) / Tile.TILE_WIDTH;

			if (!collisionWithTile(tx, (getY() + bounds[0].y) / Tile.TILE_HEIGHT)
					&& !collisionWithTile(tx, (getY() + bounds[0].y + bounds[0].height) / Tile.TILE_HEIGHT)) {
				setX(getX() + getXMove());
			}


		} else if (xMove < 0) {

			if(getX() <= 0) return;

			int tx = (getX() + xMove + bounds[0].x) / Tile.TILE_WIDTH;

			if (!collisionWithTile(tx, (getY() + bounds[0].y) / Tile.TILE_HEIGHT)
					&& !collisionWithTile(tx, (int) (getY() + bounds[0].y + bounds[0].height) / Tile.TILE_HEIGHT)) {
				setX(getX() + getXMove());
			}

		}
	}

	public void moveY() {
		if (yMove < 0) {
			if(getY() <= 0) return;

			int ty = (getY() + yMove + bounds[0].y) / Tile.TILE_HEIGHT;

			if (!collisionWithTile((getX() + bounds[0].x) / Tile.TILE_WIDTH, ty)
					&& !collisionWithTile((getX() + bounds[0].x + bounds[0].width) / Tile.TILE_WIDTH, ty)) {
				setY(getY() + getYMove());
			}

		} else if (yMove > 0) {
			if(getY() >= (Game.getInstance().getHeight() * Tile.TILE_HEIGHT - this.getHeight() - 1)) return;

			int ty = (getY() + yMove + bounds[0].y + bounds[0].height) / Tile.TILE_HEIGHT;

			if (!collisionWithTile((getX() + bounds[0].x) / Tile.TILE_WIDTH, ty)
					&& !collisionWithTile((getX() + bounds[0].x + bounds[0].width) / Tile.TILE_WIDTH, ty)) {
				setY(getY() + getYMove());
			}
		}
	}

	private boolean collisionWithTile(int x, int y) {
		return Game.getInstance().getTile(x, y).isSolid();
	}

	protected Creature(int x, int y, int width, int height) {
		super(x, y, width, height);
		setSpeed(DEFAULT_SPEED);
	}

	public void move() {
		if (!checkEntityCollisions(xMove, 0)) moveX();
		if (!checkEntityCollisions(0, yMove)) moveY();
	}

	public void giveExp(int amount) {
		for (int i = 0; i < amount; i++) {
			Game.getInstance().getEntityManager().getPlayer()
					.setExp(Game.getInstance().getEntityManager().getPlayer().getExp() + 1);
			if (Game.getInstance().getEntityManager().getPlayer().getExp() >= Game.getInstance().getEntityManager()
					.getPlayer().getMaxExp()) {
				Game.getInstance().getEntityManager().getPlayer().setExp(0);
				Game.getInstance().getEntityManager().getPlayer()
						.setLevel(Game.getInstance().getEntityManager().getPlayer().getLevel() + 1);
				Game.getInstance().getEntityManager().getPlayer()
						.setMaxExp(Game.getInstance().getEntityManager().getPlayer().getMaxExp()
								+ (Game.getInstance().getEntityManager().getPlayer().getMaxExp() / 2));

			}
		}
	}

	public void path() {
		setXMove(0);
		setYMove(0);

		int width = 0;
		if (getWidth() > Game.getInstance().getEntityManager().getPlayer().getWidth()) {
			width = 32;
		}

		if (getX() + width < Game.getInstance().getEntityManager().getPlayer().getX()) {
			setXMove(getXMove() + getSpeed());
		} else if (getX() + width > Game.getInstance().getEntityManager().getPlayer().getX()) {
			setXMove(getXMove() - getSpeed());
		} else if (getY() + width < Game.getInstance().getEntityManager().getPlayer().getY()) {
			setYMove(getYMove() + getSpeed());
		} else if (getY() + width > Game.getInstance().getEntityManager().getPlayer().getY()) {
			setYMove(getY() - getSpeed());
		}

	}

}
