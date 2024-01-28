package org.cofezuwo.nsuluofuo.creatures;

import lombok.Getter;
import lombok.Setter;
import org.cofezuwo.nsuluofuo.entities.Entity;
import org.cofezuwo.nsuluofuo.graphics.tiles.Tile;
import org.cofezuwo.nsuluofuo.worlds.World;

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
			if(getX() >= (World.getInstance().getWidth() * Tile.TILEWIDTH- this.getWidth())) return;

			int tx = (getX() + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;

			if (!collisionWithTile(tx, (getY() + bounds.y) / Tile.TILEHEIGHT)
					&& !collisionWithTile(tx, (getY() + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				setX(getX() + getXMove());
			}


		} else if (xMove < 0) {

			if(getX() <= 0) return;

			int tx = (getX() + xMove + bounds.x) / Tile.TILEWIDTH;

			if (!collisionWithTile(tx, (getY() + bounds.y) / Tile.TILEHEIGHT)
					&& !collisionWithTile(tx, (int) (getY() + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				setX(getX() + getXMove());
			}

		}
	}

	public void moveY() {
		if (yMove < 0) {
			if(getY() <= 0) return;

			int ty = (getY() + yMove + bounds.y) / Tile.TILEHEIGHT;

			if (!collisionWithTile((getX() + bounds.x) / Tile.TILEWIDTH, ty)
					&& !collisionWithTile((getX() + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
				setY(getY() + getYMove());
			}

		} else if (yMove > 0) {
			if(getY() >= (World.getInstance().getHeight() * Tile.TILEHEIGHT - this.getHeight() - 1)) return;

			int ty = (getY() + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;

			if (!collisionWithTile((getX() + bounds.x) / Tile.TILEWIDTH, ty)
					&& !collisionWithTile((getX() + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
				setY(getY() + getYMove());
			}
		}
	}

	private boolean collisionWithTile(int x, int y) {
		return World.getInstance().getTile(x, y).isSolid();
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
			World.getInstance().getEntityManager().getPlayer()
					.setExp(World.getInstance().getEntityManager().getPlayer().getExp() + 1);
			if (World.getInstance().getEntityManager().getPlayer().getExp() >= World.getInstance().getEntityManager()
					.getPlayer().getMaxExp()) {
				World.getInstance().getEntityManager().getPlayer().setExp(0);
				World.getInstance().getEntityManager().getPlayer()
						.setLevel(World.getInstance().getEntityManager().getPlayer().getLevel() + 1);
				World.getInstance().getEntityManager().getPlayer()
						.setMaxExp(World.getInstance().getEntityManager().getPlayer().getMaxExp()
								+ (World.getInstance().getEntityManager().getPlayer().getMaxExp() / 2));

			}
		}
	}

	public void path() {
		setXMove(0);
		setYMove(0);

		int width = 0;
		if (getWidth() > World.getInstance().getEntityManager().getPlayer().getWidth()) {
			width = 32;
		}

		if (getX() + width < World.getInstance().getEntityManager().getPlayer().getX()) {
			setXMove(getXMove() + getSpeed());
		} else if (getX() + width > World.getInstance().getEntityManager().getPlayer().getX()) {
			setXMove(getXMove() - getSpeed());
		} else if (getY() + width < World.getInstance().getEntityManager().getPlayer().getY()) {
			setYMove(getYMove() + getSpeed());
		} else if (getY() + width > World.getInstance().getEntityManager().getPlayer().getY()) {
			setYMove(getY() - getSpeed());
		}

	}

}
