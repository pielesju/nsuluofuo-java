package org.cofezuwo.nsuluofuo.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.cofezuwo.nsuluofuo.graphics.Assets;
import org.cofezuwo.nsuluofuo.graphics.Text;
import org.cofezuwo.nsuluofuo.main.Handler;

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
		
		
		
		
		if (Handler.getInstance().getKeyManager().keyJustPressed(KeyEvent.VK_E)) {
			if(Dialog.isActive()) {
				return;
			}
			active = !active;
			
		}
		
		if (!active) {
			return;
		}
		
		if(Handler.getInstance().getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT)){
			active = !active;
			Handler.getInstance().getWorld().getEntityManager().getPlayer().getInventory().setActive(true);
		}
		
		if(Handler.getInstance().getKeyManager().keyJustPressed(KeyEvent.VK_LEFT)){
			active = !active;
			Handler.getInstance().getWorld().getEntityManager().getPlayer().getTrivel().setActive(true);
		}

	}

	@SuppressWarnings("deprecation")
	public void render(Graphics g) {
		if (!active) {
			return;
		}
		g.setColor(color);
		g.fillRoundRect(20, 20, Handler.getInstance().getGame().getWidth() - 40, Handler.getInstance().getGame().getHeight() - 40, 40, 40);
		g.setColor(Color.gray);
		g.fillRect(40, 80, Handler.getInstance().getGame().getWidth() - 80, 2);
		Text.drawString(g, ""+Handler.getInstance().getWorld().getName(), 320, 60, true, Color.gray, Assets.text);
		g.setColor(Color.BLACK);
		g.fillRect(304, 466, 8, 8);
		
		g.fillRect(316, 466, 8, 8);
		
		g.fillRect(328, 466, 8, 8);
		
		Text.drawString(g, "HP:", 320, 98, true, Color.gray, Assets.smallText);
		g.setColor(Color.gray);
		double f = Handler.getInstance().getWorld().getEntityManager().getPlayer().getMaxHealth() / Handler.getInstance().getWorld().getEntityManager().getPlayer().getHealth();
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
		g.fillRect((int)((Handler.getInstance().getWorld().getEntityManager().getPlayer().getX() + Handler.getInstance().getGameCamera().getxOffset())/128 + 39), (int) ((Handler.getInstance().getWorld().getEntityManager().getPlayer().getY() + Handler.getInstance().getGameCamera().getyOffset())/128 + 49)+20+17, 6, 6);
		g.setColor(Color.WHITE);
		g.fillRect((int)((Handler.getInstance().getWorld().getEntityManager().getPlayer().getX() + Handler.getInstance().getGameCamera().getxOffset())/128 + 40), (int) ((Handler.getInstance().getWorld().getEntityManager().getPlayer().getY() + Handler.getInstance().getGameCamera().getyOffset())/128 + 50)+20 +17, 4, 4);
		
		
		
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
