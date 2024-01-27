package org.cofezuwo.nsuluofuo.creatures;

import lombok.Getter;
import lombok.Setter;
import org.cofezuwo.nsuluofuo.entities.Entity;
import org.cofezuwo.nsuluofuo.graphics.tiles.Tile;
import org.cofezuwo.nsuluofuo.main.Handler;

public abstract class Creature extends Entity {

	public static final float DEFAULT_SPEED = 1.5f;
	public static final int DEFAULT_CREATURE_WIDTH = 32;
	public static final int DEFAULT_CREATURE_HEIGHT = 32;

	@Getter
	@Setter
	private float speed;

	@Getter
	@Setter
	private float xMove = 0;

	@Getter
	@Setter
	private float yMove = 0;


	public void moveX() {
		if (xMove > 0) {
			int tx = (int) ((getX() + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH);

			if (!collisionWithTile(tx, (int) ((getY() + bounds.y) / Tile.TILEHEIGHT))
					&& !collisionWithTile(tx, (int) ((getY() + bounds.y + bounds.height) / Tile.TILEHEIGHT))) {
				setX(getX() + getXMove());
			}
		} else if (xMove < 0) {
			int tx = (int) ((getX() + xMove + bounds.x) / Tile.TILEWIDTH);

			if (!collisionWithTile(tx, (int) ((getY() + bounds.y) / Tile.TILEHEIGHT))
					&& !collisionWithTile(tx, (int) (getY() + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				setX(getX() + getXMove());
			}
		}
	}

	public void moveY() {
		if (yMove < 0) {
			int ty = (int) (getY() + yMove + bounds.y) / Tile.TILEHEIGHT;

			if (!collisionWithTile((int) ((getX() + bounds.x) / Tile.TILEWIDTH), ty)
					&& !collisionWithTile((int) (getX() + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
				setY(getY() + getYMove());
			}
		} else if (yMove > 0) {
			int ty = ((int) (getY() + yMove + bounds.y + bounds.height)) / Tile.TILEHEIGHT;

			if (!collisionWithTile((int) ((getX() + bounds.x) / Tile.TILEWIDTH), ty)
					&& !collisionWithTile((int) ((getX() + bounds.x + bounds.width) / Tile.TILEWIDTH), ty)) {
				setY(getY() + getYMove());
			}

		}
	}

	private boolean collisionWithTile(int x, int y) {
		return Handler.getInstance().getWorld().getTile(x, y).isSolid();
	}

	public Creature(float x, float y, int width, int height) {
		super(x, y, width, height);
		setSpeed(DEFAULT_SPEED);
	}

	public void move() {
		if (!checkEntityCollisions(xMove, 0f)) moveX();
		if (!checkEntityCollisions(0f, yMove)) moveY();
	}

	public void giveExp(int amount) {
		for (int i = 0; i < amount; i++) {
			Handler.getInstance().getWorld().getEntityManager().getPlayer()
					.setExp(Handler.getInstance().getWorld().getEntityManager().getPlayer().getExp() + 1);
			if (Handler.getInstance().getWorld().getEntityManager().getPlayer().getExp() >= Handler.getInstance().getWorld().getEntityManager()
					.getPlayer().getMaxExp()) {
				Handler.getInstance().getWorld().getEntityManager().getPlayer().setExp(0);
				Handler.getInstance().getWorld().getEntityManager().getPlayer()
						.setLevel(Handler.getInstance().getWorld().getEntityManager().getPlayer().getLevel() + 1);
				Handler.getInstance().getWorld().getEntityManager().getPlayer()
						.setMaxExp(Handler.getInstance().getWorld().getEntityManager().getPlayer().getMaxExp()
								+ (Handler.getInstance().getWorld().getEntityManager().getPlayer().getMaxExp() / 2));

			}
		}
	}

	public void path() {
		setXMove(0.0f);
		setYMove(0.0f);

		int width = 0;
		if (getWidth() > Handler.getInstance().getWorld().getEntityManager().getPlayer().getWidth()) {
			width = 32;
		}

		if (getX() + width < Handler.getInstance().getWorld().getEntityManager().getPlayer().getX()) {
			setXMove(getXMove() + getSpeed());
		} else if (getX() + width > Handler.getInstance().getWorld().getEntityManager().getPlayer().getX()) {
			setXMove(getXMove() - getSpeed());
		} else if (getY() + width < Handler.getInstance().getWorld().getEntityManager().getPlayer().getY()) {
			setYMove(getYMove() + getSpeed());
		} else if (getY() + width > Handler.getInstance().getWorld().getEntityManager().getPlayer().getY()) {
			setYMove(getY() - getSpeed());
		}

	}

}
