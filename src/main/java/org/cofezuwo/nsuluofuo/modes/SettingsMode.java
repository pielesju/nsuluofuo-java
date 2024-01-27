package org.cofezuwo.nsuluofuo.modes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import org.cofezuwo.nsuluofuo.graphics.Assets;
import org.cofezuwo.nsuluofuo.graphics.Text;
import org.cofezuwo.nsuluofuo.input.KeyManager;
import org.cofezuwo.nsuluofuo.main.Game;


public class SettingsMode extends Mode{
	
	public SettingsMode() {
		super();
		
		
		
	}

	@Override
	public void update() {
		if(KeyManager.getInstance().keyJustPressed(KeyEvent.VK_ESCAPE)){
			Mode.setMode(Game.getInstance().menuMode);
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 640, 480);
		Text.drawString(g, "Settings", 320, 50, true, Color.BLACK, Assets.text);
		
	}

}
