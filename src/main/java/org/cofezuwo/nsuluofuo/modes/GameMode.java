package org.cofezuwo.nsuluofuo.modes;

import java.awt.Color;
import java.awt.Graphics;

import org.cofezuwo.nsuluofuo.graphics.Assets;
import org.cofezuwo.nsuluofuo.input.KeyManager;
import org.cofezuwo.nsuluofuo.inventory.Dialog;
import org.cofezuwo.nsuluofuo.main.Handler;
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
		keyManager = new KeyManager();
		world = new World("/worlds/world1.txt");
		Handler.getInstance().setWorld(world);
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
		switch((Handler.getInstance().getWorld().getEntityManager().getPlayer().getHealth())) {
		case 0: return;
		case 1: g.drawImage(Assets.hheart, 5, 5, null);
		break;
		case 2: g.drawImage(Assets.heart, 5, 5, null);
		break;
		case 3: case 5: case 7: case 9: case 11: case 13: case 15: case 17: case 19: for(int i = 0; i < ((Handler.getInstance().getWorld().getEntityManager().getPlayer().getHealth() -1) /2 ); i++) {
			g.drawImage(Assets.heart, 5 + 16*i, 5, null);
		}
			g.drawImage(Assets.hheart, 5+16*((Handler.getInstance().getWorld().getEntityManager().getPlayer().getHealth()-1)/2), 5, null);
		break;
		case 4: case 6: case 8: case 10: case 12: case 14: case 16: case 18: case 20: for(int i = 0; i < Handler.getInstance().getWorld().getEntityManager().getPlayer().getHealth() / 2; i++) {
			g.drawImage(Assets.heart, 5 + 16*i, 5, null);
		}
		break;
		}
		Dialog.render(g);
		Handler.getInstance().getWorld().getEntityManager().getPlayer().getTrivel().render(g);
		Handler.getInstance().getWorld().getEntityManager().getPlayer().getInventory().render(g);
		Handler.getInstance().getWorld().getEntityManager().getPlayer().getInfo().render(g);
	
	}

}
