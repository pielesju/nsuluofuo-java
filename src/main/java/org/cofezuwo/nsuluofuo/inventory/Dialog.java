package org.cofezuwo.nsuluofuo.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import org.cofezuwo.nsuluofuo.graphics.Assets;
import org.cofezuwo.nsuluofuo.graphics.Text;
import org.cofezuwo.nsuluofuo.main.Handler;


public class Dialog {
	
	public static String[] text;

	public static String name;
	public static BufferedImage portrait;
	private static boolean active;

	public Dialog() {
		super();
		
	}
	
	public void update() {
		if(!active) {
			return;
		}
		if(Handler.getInstance().getKeyManager().keyJustPressed(KeyEvent.VK_SPACE)) {
			active = ! active;
		}
	}
	
	public static void render(Graphics g) {
		if(!active) {
			return;
		}
		g.setColor(new Color(255,255,255,200));
		g.fillRoundRect(20, 300, 600, 160, 20, 20);
		Text.drawString(g, Dialog.name, 90, 445, true, Color.BLACK, Assets.smallText);

		int textYPos = 320;

		for(int i = 0; i < 6; i++) {
			Text.drawString(g, Dialog.text[i], 395, textYPos, true, Color.BLACK, Assets.smallText);
			textYPos += 20;
		}

		g.drawImage(portrait, 30, 310, null);
	}

	public static boolean isActive() {
		return active;
	}

	public static void setActive(boolean active) {
		Dialog.active = active;
	}
	
	
	

}