package org.cofezuwo.nsuluofuo.creatures;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.Image;

import org.cofezuwo.nsuluofuo.graphics.ATG;
import org.cofezuwo.nsuluofuo.graphics.Assets;
import org.cofezuwo.nsuluofuo.graphics.GameCamera;
import org.cofezuwo.nsuluofuo.input.KeyManager;
import org.cofezuwo.nsuluofuo.inventory.Dialog;
import org.cofezuwo.nsuluofuo.main.Game;

public class NPC extends Creature {


	protected String name;

	protected Image portrait;

	protected String[] text;

	protected Rectangle sbounds;
	private Image currentPosition = Assets.mNpcDown;

	public NPC(int x, int y, String name, String[] text) {
		super(x , y, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);
		bounds[0].x = 8;
		bounds[0].y = 16;
		bounds[0].width = 16;
		bounds[0].height = 16;
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
				&& Game.getInstance().getEntityManager().getPlayer().getCollisionBounds(0, 0)[0].intersects(sbounds)) {
			Dialog.setActive(!(Dialog.isActive()));
			Dialog.text = text;
			Dialog.name = name;

			if (Game.getInstance().getEntityManager().getPlayer().getCurrentPosition() == Game.getInstance()
					.getEntityManager().getPlayer().getAnimDown().getCurrentFrame()) {
				currentPosition = Assets.mNpcUp;
			} else if (Game.getInstance().getEntityManager().getPlayer().getCurrentPosition() == Game.getInstance()
					.getEntityManager().getPlayer().getAnimUp().getCurrentFrame()) {
				currentPosition = Assets.mNpcDown;
			} else if (Game.getInstance().getEntityManager().getPlayer().getCurrentPosition() == Game.getInstance()
					.getEntityManager().getPlayer().getAnimLeft().getCurrentFrame()) {
				currentPosition = Assets.mNpcRight;
			} else if (Game.getInstance().getEntityManager().getPlayer().getCurrentPosition() == Game.getInstance()
					.getEntityManager().getPlayer().getAnimRight().getCurrentFrame()) {
				currentPosition = Assets.mNpcLeft;
			}
		}
		

	}

	@Override
	public void render(ATG g) {
		g.drawImage(currentPosition, getX() - GameCamera.getInstance().getXOffset(),
				getY() - GameCamera.getInstance().getYOffset(), getWidth(), getHeight());
	}
	
	

}
