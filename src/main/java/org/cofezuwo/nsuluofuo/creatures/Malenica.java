package org.cofezuwo.nsuluofuo.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import org.cofezuwo.nsuluofuo.graphics.Assets;
import org.cofezuwo.nsuluofuo.inventory.Dialog;
import org.cofezuwo.nsuluofuo.main.Handler;

public class Malenica extends NPC {
	
	private BufferedImage currentPosition = Assets.iMDown;


	public Malenica(float x, float y, String[] text) {
		super(x, y, "Ivo Malenica", text);

		super.sbounds = new Rectangle((int) getX() - 5, (int) getY() - 5, getWidth() + 10, getHeight() + 10);

		super.portrait = Assets.ivof;
	}
	
	public void update() {
		if (getHealth() < 100000) {
			setHealth(100000);
		}
		if (Handler.getInstance().getKeyManager().keyJustPressed(KeyEvent.VK_SPACE)
				&& Handler.getInstance().getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(sbounds)) {
			Dialog.setActive(!(Dialog.isActive()));
			Dialog.text = text;
			Dialog.name = name;
			Dialog.portrait = portrait;
			if (Handler.getInstance().getWorld().getEntityManager().getPlayer().getCurrentPosition() == Handler.getInstance().getWorld()
					.getEntityManager().getPlayer().getAnimDown().getCurrentFrame()) {
				currentPosition = Assets.iMUp;
			} else if (Handler.getInstance().getWorld().getEntityManager().getPlayer().getCurrentPosition() == Handler.getInstance().getWorld()
					.getEntityManager().getPlayer().getAnimUp().getCurrentFrame()) {
				currentPosition = Assets.iMDown;
			} else if (Handler.getInstance().getWorld().getEntityManager().getPlayer().getCurrentPosition() == Handler.getInstance().getWorld()
					.getEntityManager().getPlayer().getAnimLeft().getCurrentFrame()) {
				currentPosition = Assets.iMRight;
			} else if (Handler.getInstance().getWorld().getEntityManager().getPlayer().getCurrentPosition() == Handler.getInstance().getWorld()
					.getEntityManager().getPlayer().getAnimRight().getCurrentFrame()) {
				currentPosition = Assets.iMLeft;
			}
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(currentPosition, (int) (getX() - Handler.getInstance().getGameCamera().getxOffset()),
				(int) (getY() - Handler.getInstance().getGameCamera().getyOffset()), getWidth(), getHeight(), null);
	}

}
