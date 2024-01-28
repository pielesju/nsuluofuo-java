package org.cofezuwo.nsuluofuo.creatures;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.Image;

import org.cofezuwo.nsuluofuo.graphics.ATG;
import org.cofezuwo.nsuluofuo.graphics.Assets;
import org.cofezuwo.nsuluofuo.graphics.GameCamera;
import org.cofezuwo.nsuluofuo.input.KeyManager;
import org.cofezuwo.nsuluofuo.inventory.Dialog;
import org.cofezuwo.nsuluofuo.worlds.World;

public class Malenica extends NPC {
	
	private Image currentPosition = Assets.iMDown;


	public Malenica(int x, int y, String[] text) {
		super(x, y, "Ivo Malenica", text);

		super.sbounds = new Rectangle((int) getX() - 5, (int) getY() - 5, getWidth() + 10, getHeight() + 10);

		super.portrait = Assets.ivof;
	}
	
	public void update() {
		if (getHealth() < 100000) {
			setHealth(100000);
		}
		if (KeyManager.getInstance().keyJustPressed(KeyEvent.VK_SPACE)
				&& World.getInstance().getEntityManager().getPlayer().getCollisionBounds(0, 0).intersects(sbounds)) {
			Dialog.setActive(!(Dialog.isActive()));
			Dialog.text = text;
			Dialog.name = name;
			Dialog.portrait = portrait;
			if (World.getInstance().getEntityManager().getPlayer().getCurrentPosition() == World.getInstance()
					.getEntityManager().getPlayer().getAnimDown().getCurrentFrame()) {
				currentPosition = Assets.iMUp;
			} else if (World.getInstance().getEntityManager().getPlayer().getCurrentPosition() == World.getInstance()
					.getEntityManager().getPlayer().getAnimUp().getCurrentFrame()) {
				currentPosition = Assets.iMDown;
			} else if (World.getInstance().getEntityManager().getPlayer().getCurrentPosition() == World.getInstance()
					.getEntityManager().getPlayer().getAnimLeft().getCurrentFrame()) {
				currentPosition = Assets.iMRight;
			} else if (World.getInstance().getEntityManager().getPlayer().getCurrentPosition() == World.getInstance()
					.getEntityManager().getPlayer().getAnimRight().getCurrentFrame()) {
				currentPosition = Assets.iMLeft;
			}
		}
	}
	
	public void render(ATG g) {
		g.drawImage(currentPosition, getX() - GameCamera.getInstance().getxOffset(),
				getY() - GameCamera.getInstance().getyOffset(), getWidth(), getHeight());
	}

}
