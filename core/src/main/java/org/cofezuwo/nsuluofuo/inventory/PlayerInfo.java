/*
	cofezuwo.org Nsuluofuo Java Edition
	Copyright (C) 2017 - 2024 Julian Pieles
 */

package org.cofezuwo.nsuluofuo.inventory;

import java.awt.Color;
import java.awt.event.KeyEvent;
import org.cofezuwo.nsuluofuo.graphics.ATG;
import org.cofezuwo.nsuluofuo.graphics.Assets;
import org.cofezuwo.nsuluofuo.graphics.GameCamera;
import org.cofezuwo.nsuluofuo.input.KeyManager;
import org.cofezuwo.nsuluofuo.main.Game;
import org.cofezuwo.nsuluofuo.worlds.World;

public class PlayerInfo {

	private boolean active = false;
	private boolean itemScreen;
	private boolean trivel;
	private Color color;

	public PlayerInfo() {
		color = new Color(255, 255, 255, 200);
	}

	public void update() {
		
		
		
		
		if (KeyManager.getInstance().keyJustPressed(KeyEvent.VK_E)) {
			System.out.println("E");
			if(Dialog.isActive()) {
				return;
			}
			active = !active;
		}
		
		if (!active) {
			return;
		}
		
		if(KeyManager.getInstance().keyJustPressed(KeyEvent.VK_RIGHT)){
			active = !active;
			World.getInstance().getEntityManager().getPlayer().getInventory().setActive(true);
		}
		
		if(KeyManager.getInstance().keyJustPressed(KeyEvent.VK_LEFT)){
			active = !active;
			World.getInstance().getEntityManager().getPlayer().getTrivel().setActive(true);
		}

	}

	public void render(ATG g) {
		System.out.println("E");
		if (!active) {
			return;
		}

		g.fillRoundRect(20, 20, Game.getInstance().getWidth() - 40, Game.getInstance().getHeight() - 40, 40, 40, color);
		g.fillRect(40, 80, Game.getInstance().getWidth() - 80, 2, Color.gray);
		g.drawString( ""+World.getInstance().getName(), 320, 60, true, Color.gray, Assets.text);
		g.fillRect(304, 466, 8, 8, Color.BLACK);
		g.fillRect(316, 466, 8, 8, Color.BLACK);
		g.fillRect(328, 466, 8, 8, Color.BLACK);
		g.drawString( "HP:", 320, 98, true, Color.gray, Assets.smallText);

		int healthPercentage =
				World.getInstance().getEntityManager().getPlayer().getMaxHealth() /
						World.getInstance().getEntityManager().getPlayer().getHealth();
		g.fillRect(340, 95, 260, 10, Color.gray);
		g.fillRect(340, 95, (int) (260 / healthPercentage), 10, Color.GREEN);
		g.fillRect(305, 467, 6, 6, Color.WHITE);
		g.fillRect(317, 467, 6, 6, Color.BLUE);
		g.fillRect(329, 467, 6, 6, Color.WHITE);
		g.fillRect(39, 86, 252, 252, Color.BLACK);
		g.fillRect(40, 87, 250, 250, Color.GREEN);

		g.fillRect(
				(World.getInstance().getEntityManager().getPlayer().getX() + GameCamera.getInstance().getXOffset())/128 + 39,
				(World.getInstance().getEntityManager().getPlayer().getY() + GameCamera.getInstance().getYOffset())/128 + 49+20+17, 6, 6, Color.BLACK);

		g.fillRect(
				(World.getInstance().getEntityManager().getPlayer().getX() + GameCamera.getInstance().getXOffset())/128 + 40,
				(World.getInstance().getEntityManager().getPlayer().getY() + GameCamera.getInstance().getYOffset())/128 + 50+20 +17, 4, 4, Color.WHITE);
		
		
		
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isItemScreen() {
		return itemScreen;
	}

	public void setItemScreen(boolean itemScreen) {
		this.itemScreen = itemScreen;
	}

}
