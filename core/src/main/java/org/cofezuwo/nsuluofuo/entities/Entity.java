package org.cofezuwo.nsuluofuo.entities;

import java.awt.Rectangle;

import lombok.Getter;
import lombok.Setter;
import org.cofezuwo.nsuluofuo.creatures.Player;
import org.cofezuwo.nsuluofuo.graphics.ATG;
import org.cofezuwo.nsuluofuo.main.Game;

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

	protected Rectangle[] bounds;
	protected Rectangle dbounds;

	protected Entity(int x, int y, int width, int height) {
		setMaxHealth(DEFAULT_HEALTH);
		setHealth(getMaxHealth());
		this.x = x*32;
		this.y = y*32;
		this.width = width;
		this.height = height;

		bounds = new Rectangle[4];
		bounds[0] = new Rectangle(0, 0, width, height);
		dbounds = new Rectangle(getX() + bounds[0].x, getY() + bounds[0].y, width, height);
	}

	public abstract void update();

	public abstract void render(ATG g);

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
		for (Entity e : Game.getInstance().getEntityManager().getEntities()) {
			if (e.equals(this) || e.getClass().equals(Player.class)) continue;

			Rectangle[] c1 = e.getCollisionBounds(0, 0);
			Rectangle[] c2 = getCollisionBounds(xOffset, yOffset);

			for(int i = 0; i < bounds.length; i++) {
				if(null == c1[i] || null == c2[i]) continue;
				if(c1[i].intersects(c2[i])) return true;
			}
		}

		return false;
	}

	public Rectangle[] getCollisionBounds(int xOffset, int yOffset) {
		Rectangle[] rects = new Rectangle[4];
		for(int i = 0; i < bounds.length; i++) {
			if(null == bounds[i]) continue;
			rects[i] = new Rectangle(
					x + bounds[i].x + xOffset,
					getY() + bounds[i].y + yOffset,
					bounds[i].width,
					bounds[i].height);
		}

		return rects;
	}


	
	

}
