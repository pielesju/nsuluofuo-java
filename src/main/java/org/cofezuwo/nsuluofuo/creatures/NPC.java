package org.cofezuwo.nsuluofuo.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import org.cofezuwo.nsuluofuo.graphics.Assets;
import org.cofezuwo.nsuluofuo.graphics.GameCamera;
import org.cofezuwo.nsuluofuo.input.KeyManager;
import org.cofezuwo.nsuluofuo.inventory.Dialog;
import org.cofezuwo.nsuluofuo.worlds.World;

public class NPC extends Creature {


	protected String name;

	protected BufferedImage portrait;

	protected String[] text;

	protected Rectangle sbounds;
	private BufferedImage currentPosition = Assets.mNpcDown;

	public NPC(int x, int y, String name, String[] text) {
		super(x * 32, y * 32, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);
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
		if (KeyManager.getInstance().keyJustPressed(KeyEvent.VK_SPACE)
				&& World.getInstance().getEntityManager().getPlayer().getCollisionBounds(0, 0).intersects(sbounds)) {
			Dialog.setActive(!(Dialog.isActive()));
			Dialog.text = text;
			Dialog.name = name;

			if (World.getInstance().getEntityManager().getPlayer().getCurrentPosition() == World.getInstance()
					.getEntityManager().getPlayer().getAnimDown().getCurrentFrame()) {
				currentPosition = Assets.mNpcUp;
			} else if (World.getInstance().getEntityManager().getPlayer().getCurrentPosition() == World.getInstance()
					.getEntityManager().getPlayer().getAnimUp().getCurrentFrame()) {
				currentPosition = Assets.mNpcDown;
			} else if (World.getInstance().getEntityManager().getPlayer().getCurrentPosition() == World.getInstance()
					.getEntityManager().getPlayer().getAnimLeft().getCurrentFrame()) {
				currentPosition = Assets.mNpcRight;
			} else if (World.getInstance().getEntityManager().getPlayer().getCurrentPosition() == World.getInstance()
					.getEntityManager().getPlayer().getAnimRight().getCurrentFrame()) {
				currentPosition = Assets.mNpcLeft;
			}
		}
		

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(currentPosition, (int) (getX() - GameCamera.getInstance().getxOffset()),
				(int) (getY() - GameCamera.getInstance().getyOffset()), getWidth(), getHeight(), null);
	}
	
	

}
