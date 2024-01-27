package org.cofezuwo.nsuluofuo.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import org.cofezuwo.nsuluofuo.graphics.Assets;
import org.cofezuwo.nsuluofuo.graphics.Text;
import org.cofezuwo.nsuluofuo.main.Handler;
import org.cofezuwo.nsuluofuo.story.QuestManager;

public class Trivel {

	private Graphics g;
	private boolean active;
	private Color trivel;
	private QuestManager qm;
	
	public Trivel(){

		trivel = new Color(245,245,220);
		qm = new QuestManager();
	}
	
	public void update(){
		if (!active) {
			return;
		}
		
		if (Handler.getInstance().getKeyManager().keyJustPressed(KeyEvent.VK_E)) {
			
			active = !active;
			
		}
		
		if(Handler.getInstance().getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT)){
			active = !active;
			Handler.getInstance().getWorld().getEntityManager().getPlayer().getInfo().setActive(true);
		}
		
	}
	
	public void render(Graphics g){
		if (!active) {
			return;
		}
		g.setColor(Color.BLUE);
		g.fillRoundRect(20, 20, 600, 440,5,5);
		g.setColor(trivel);
		g.fillRoundRect(30, 30, 580, 420, 5, 5);
		g.setColor(Color.BLACK);
		g.drawLine(319, 30, 319, 450);
		
		g.setColor(Color.BLACK);
		g.fillRect(304, 466, 8, 8);
		
		g.fillRect(316, 466, 8, 8);
		
		g.fillRect(328, 466, 8, 8);
		
		g.setColor(Color.BLUE);
		g.fillRect(305, 467, 6, 6);
		g.setColor(Color.WHITE);
		g.fillRect(317, 467, 6, 6);
		g.setColor(Color.WHITE);
		g.fillRect(329, 467, 6, 6);
		Text.drawString(g, " " + qm.getCurrentQuest(), 50, 50, false, Color.BLACK, Assets.smallText);
		
		
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	

}
