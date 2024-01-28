package org.cofezuwo.nsuluofuo.inventory;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.Image;

import org.cofezuwo.nsuluofuo.graphics.ATG;
import org.cofezuwo.nsuluofuo.graphics.Assets;
import org.cofezuwo.nsuluofuo.input.KeyManager;


public class Dialog {
	
	public static String[] text;

	public static String name;
	public static Image portrait;
	private static boolean active;

	public Dialog() {
		super();
		
	}
	
	public void update() {
		if(!active) {
			return;
		}
		if(KeyManager.getInstance().keyJustPressed(KeyEvent.VK_SPACE)) {
			active = ! active;
		}
	}
	
	public static void render(ATG g) {
		if(!active) {
			return;
		}

		g.fillRoundRect(20, 300, 600, 160, 20, 20, new Color(255,255,255,200));
		g.drawString(Dialog.name, 90, 445, true, Color.BLACK, Assets.smallText);

		int textYPos = 320;

		for(int i = 0; i < 6; i++) {
			g.drawString(Dialog.text[i], 395, textYPos, true, Color.BLACK, Assets.smallText);
			textYPos += 20;
		}

		g.drawImage(portrait, 30, 310);
	}

	public static boolean isActive() {
		return active;
	}

	public static void setActive(boolean active) {
		Dialog.active = active;
	}
	
	
	

}
