package org.cofezuwo.nsuluofuo.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import lombok.Getter;
import lombok.Setter;
import org.cofezuwo.nsuluofuo.worlds.World;

public abstract class Entity {

	public static final int DEFAULT_HEALTH = 10;

	@Getter
	@Setter
	protected int x;

	@Getter
	@Setter
	protected int y;

	@Getter
	@Setter
	private int health;

	@Getter
	@Setter
	private int maxHealth;

	@Getter
	@Setter
	private int strength;

	@Getter
	@Setter
	private int width;

	@Getter
	@Setter
	private int height;

	@Getter
	@Setter
	private boolean active = true;

	protected Rectangle bounds;
	protected Rectangle dbounds;

	protected Entity(int x, int y, int width, int height) {
		setMaxHealth(DEFAULT_HEALTH);
		setHealth(getMaxHealth());
		this.x = x*32;
		this.y = y*32;
		this.width = width;
		this.height = height;

		bounds = new Rectangle(0, 0, width, height);
		dbounds = new Rectangle(getX() + bounds.x, getY() + bounds.y, width, height);
	}

	public abstract void update();

	public abstract void render(Graphics g);

	public void hurt(int amount) {
		setHealth(getHealth() - amount);
		if (getHealth() <= 0) {
			setActive(false);
			die();
		}
	}

	public void die() {
		setActive(false);
	}

	public boolean checkEntityCollisions(int xOffset, int yOffset) {
		for (Entity e : World.getInstance().getEntityManager().getEntities()) {
			if (e.equals(this)) continue;
			if (e.getCollisionBounds(0, 0).intersects(getCollisionBounds(xOffset, yOffset))) return true;
		}

		return false;
	}

	public Rectangle getCollisionBounds(int xOffset, int yOffset) {
		return new Rectangle((int) (getX() + bounds.x + xOffset), (int) (getY() + bounds.y + yOffset), bounds.width,
				bounds.height);
	}


	
	

}
