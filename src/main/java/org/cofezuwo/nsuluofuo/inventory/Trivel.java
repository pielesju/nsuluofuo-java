package org.cofezuwo.nsuluofuo.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import lombok.Getter;
import lombok.Setter;
import org.cofezuwo.nsuluofuo.graphics.Assets;
import org.cofezuwo.nsuluofuo.graphics.Text;
import org.cofezuwo.nsuluofuo.input.KeyManager;
import org.cofezuwo.nsuluofuo.story.QuestManager;
import org.cofezuwo.nsuluofuo.worlds.World;

public class Trivel {

	@Getter
	@Setter
	private boolean active;

	private byte page; // 0 - 3

	
	public Trivel(){
		this.page = 0; // 0 - 3
	}
	
	public void update(){
		if (!active) {
			return;
		}
		
		if (KeyManager.getInstance().keyJustPressed(KeyEvent.VK_E)) {
			
			active = !active;
			this.page = 0;
			
		}
		
		if(KeyManager.getInstance().keyJustPressed(KeyEvent.VK_RIGHT)){
			active = !active;
			World.getInstance().getEntityManager().getPlayer().getInfo().setActive(true);
		}

		if(KeyManager.getInstance().keyJustPressed(KeyEvent.VK_PAGE_DOWN)) {
			if(this.page == 3) {
				this.page = 0;
			} else {
				this.page++;
			}
		}

		if(KeyManager.getInstance().keyJustPressed(KeyEvent.VK_PAGE_UP)) {
			if(this.page == 0) {
				this.page = 3;
			} else {
				this.page--;
			}
		}
		
	}
	
	public void render(Graphics g) {
		if (!active) return;

		renderTrivelBackground(g);

		switch(page) {
			case 0: renderTrivelPage0(g); break;
			case 1: renderTrivelPage1(g); break;
			case 2: renderTrivelPage2(g); break;
			case 3: renderTrivelPage3(g); break;
			default: renderTrivelPage0(g);
		}

		Text.drawString(g, "" + (this.page + 1), 600, 442, false, Color.BLACK, Assets.smallText);
	}

	private void renderTrivelPage0(Graphics g) {
		Text.drawString(g, "The Trivle", 175, 50, true, Color.BLACK, Assets.text);
		Text.drawString(g, "Hello World", 175, 70, true, Color.BLACK, Assets.smallText);
	}

	private void renderTrivelPage1(Graphics g) {
		Text.drawString(g, "Active Quests", 175, 50, true, Color.BLACK, Assets.text);

		String[] quests = World.getInstance().getQuestManager().getActiveQuests().split("\n");

		int y = 70;
		for(String quest : quests) {
			Text.drawString(g, quest, 175, y, false, Color.BLACK, Assets.smallText);
			y += 20;
		}

	}

	private void renderTrivelPage2(Graphics g) {
		Text.drawString(g, "Not Available Quests", 175, 50, true, Color.BLACK, Assets.text);

		String[] quests = World.getInstance().getQuestManager().getNotAvailableQuests().split("\n");

		int y = 70;
		for(String quest : quests) {
			Text.drawString(g, quest, 175, y, false, Color.BLACK, Assets.smallText);
			y += 20;
		}
	}

	private void renderTrivelPage3(Graphics g) {
		Text.drawString(g, "Completed Quests", 175, 50, true, Color.BLACK, Assets.text);

		String[] quests = World.getInstance().getQuestManager().getDoneQuests().split("\n");

		int y = 70;
		for(String quest : quests) {
			Text.drawString(g, quest, 175, y, false, Color.BLACK, Assets.smallText);
			y += 20;
		}
	}

	private void renderTrivelBackground(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRoundRect(20, 20, 600, 440, 5, 5);
		g.setColor(new Color(245,245,220));
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
	}
}
