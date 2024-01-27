package org.cofezuwo.nsuluofuo.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import org.cofezuwo.nsuluofuo.graphics.Assets;
import org.cofezuwo.nsuluofuo.inventory.Dialog;
import org.cofezuwo.nsuluofuo.main.Handler;

public class NPC extends Creature {


	protected String name;

	protected BufferedImage portrait;

	protected String[] text;

	private float x;

	private float y;

	protected Rectangle sbounds;
	private BufferedImage currentPosition = Assets.mNpcDown;

	public NPC(float x, float y, String name, String[] text) {
		super(x, y, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);
		this.x = x*32;
		this.y = y*32;
		bounds.x = 8;
		bounds.y = 16;
		bounds.width = 16;
		bounds.height = 16;
		sbounds = new Rectangle((int) getX() - 5, (int) getY() - 5, getWidth() + 10, getHeight() + 10);
		this.name = name;
		this.text = text;
		setHealth(100000);

	}

	@Override
	public void update() {
		if (getHealth() < 100000) {
			setHealth(100000);
		}
		if (Handler.getInstance().getKeyManager().keyJustPressed(KeyEvent.VK_SPACE)
				&& Handler.getInstance().getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(sbounds)) {
			Dialog.setActive(!(Dialog.isActive()));
			Dialog.text = text;
			Dialog.name = name;

			if (Handler.getInstance().getWorld().getEntityManager().getPlayer().getCurrentPosition() == Handler.getInstance().getWorld()
					.getEntityManager().getPlayer().getAnimDown().getCurrentFrame()) {
				currentPosition = Assets.mNpcUp;
			} else if (Handler.getInstance().getWorld().getEntityManager().getPlayer().getCurrentPosition() == Handler.getInstance().getWorld()
					.getEntityManager().getPlayer().getAnimUp().getCurrentFrame()) {
				currentPosition = Assets.mNpcDown;
			} else if (Handler.getInstance().getWorld().getEntityManager().getPlayer().getCurrentPosition() == Handler.getInstance().getWorld()
					.getEntityManager().getPlayer().getAnimLeft().getCurrentFrame()) {
				currentPosition = Assets.mNpcRight;
			} else if (Handler.getInstance().getWorld().getEntityManager().getPlayer().getCurrentPosition() == Handler.getInstance().getWorld()
					.getEntityManager().getPlayer().getAnimRight().getCurrentFrame()) {
				currentPosition = Assets.mNpcLeft;
			}
		}
		

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(currentPosition, (int) (getX() - Handler.getInstance().getGameCamera().getxOffset()),
				(int) (getY() - Handler.getInstance().getGameCamera().getyOffset()), getWidth(), getHeight(), null);
	}
	
	

}
