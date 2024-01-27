package org.cofezuwo.nsuluofuo.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.cofezuwo.nsuluofuo.graphics.Assets;
import org.cofezuwo.nsuluofuo.graphics.GameCamera;
import org.cofezuwo.nsuluofuo.graphics.Text;
import org.cofezuwo.nsuluofuo.input.KeyManager;
import org.cofezuwo.nsuluofuo.main.Game;
import org.cofezuwo.nsuluofuo.worlds.World;

public class PlayerInfo {

	private boolean active = false;
	private boolean itemScreen;
	private boolean trivel;
	private Color color;
	private Date currentDate = new Date();
	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	private String formattedDate = sdf.format(currentDate);

	public PlayerInfo() {
		color = new Color(255, 255, 255, 200);
	}

	public void update() {
		
		
		
		
		if (KeyManager.getInstance().keyJustPressed(KeyEvent.VK_E)) {
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

	@SuppressWarnings("deprecation")
	public void render(Graphics g) {
		if (!active) {
			return;
		}
		g.setColor(color);
		g.fillRoundRect(20, 20, Game.getInstance().getWidth() - 40, Game.getInstance().getHeight() - 40, 40, 40);
		g.setColor(Color.gray);
		g.fillRect(40, 80, Game.getInstance().getWidth() - 80, 2);
		Text.drawString(g, ""+World.getInstance().getName(), 320, 60, true, Color.gray, Assets.text);
		g.setColor(Color.BLACK);
		g.fillRect(304, 466, 8, 8);
		
		g.fillRect(316, 466, 8, 8);
		
		g.fillRect(328, 466, 8, 8);
		
		Text.drawString(g, "HP:", 320, 98, true, Color.gray, Assets.smallText);
		g.setColor(Color.gray);
		int f = World.getInstance().getEntityManager().getPlayer().getMaxHealth() / World.getInstance().getEntityManager().getPlayer().getHealth();
		g.fillRect(340, 95, 260, 10);
		g.setColor(Color.GREEN);
		g.fillRect(340, 95, (int)(260/f), 10);
		
		g.setColor(Color.WHITE);
		g.fillRect(305, 467, 6, 6);
		g.setColor(Color.BLUE);
		g.fillRect(317, 467, 6, 6);
		g.setColor(Color.WHITE);
		g.fillRect(329, 467, 6, 6);
		g.setColor(Color.BLACK);
		g.fillRect(39, 86, 252, 252);
		g.setColor(Color.GREEN);
		g.fillRect(40, 87, 250, 250);
		g.setColor(Color.BLACK);
		g.fillRect((int)((World.getInstance().getEntityManager().getPlayer().getX() + GameCamera.getInstance().getxOffset())/128 + 39), (int) ((World.getInstance().getEntityManager().getPlayer().getY() + GameCamera.getInstance().getyOffset())/128 + 49)+20+17, 6, 6);
		g.setColor(Color.WHITE);
		g.fillRect((int)((World.getInstance().getEntityManager().getPlayer().getX() + GameCamera.getInstance().getxOffset())/128 + 40), (int) ((World.getInstance().getEntityManager().getPlayer().getY() + GameCamera.getInstance().getyOffset())/128 + 50)+20 +17, 4, 4);
		
		
		
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
