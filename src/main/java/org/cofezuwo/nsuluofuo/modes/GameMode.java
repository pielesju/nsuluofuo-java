package org.cofezuwo.nsuluofuo.modes;

import java.awt.Color;
import java.awt.Graphics;

import org.cofezuwo.nsuluofuo.graphics.Assets;
import org.cofezuwo.nsuluofuo.input.KeyManager;
import org.cofezuwo.nsuluofuo.inventory.Dialog;
import org.cofezuwo.nsuluofuo.ui.GUIManager;
import org.cofezuwo.nsuluofuo.worlds.World;

public class GameMode extends Mode {


	private World world;

	private KeyManager keyManager;
	private String[] text;
	private String name;
	private GUIManager guiManager;
	private MenuMode menumode;
	private Color daycolor;

	public GameMode() {
		super();
		guiManager = GUIManager.getInstance();
		keyManager = KeyManager.getInstance();
		world = World.getInstance();
		text = Dialog.text;
		name = Dialog.name;
		
		
	}

	@Override
	public void update() {
		world.update();
	}

	@Override
	public void render(Graphics g) {
		world.render(g);

		renderHealth(g, world.getEntityManager().getPlayer().getHealth());

		Dialog.render(g);
		//world.getEntityManager().getPlayer().getTrivel().render(g);
		//world.getEntityManager().getPlayer().getInventory().render(g);
		//world.getEntityManager().getPlayer().getInfo().render(g);
	
	}

	private void renderHealth(Graphics g, int health) {
		if(health <= 0) return;

		int fullHearts = health / 2;
		int halfHeart = health % 2;

		for (int i = 0; i < fullHearts; i++) {
			g.drawImage(Assets.heart, 5 + 16 * i, 5, null);
		}

		if (halfHeart == 1) {
			g.drawImage(Assets.hheart, 5 + 16 * fullHearts, 5, null);
		}
	}

}
