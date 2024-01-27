package org.cofezuwo.nsuluofuo.modes;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JTextField;

import org.cofezuwo.nsuluofuo.graphics.Assets;
import org.cofezuwo.nsuluofuo.graphics.Text;
import org.cofezuwo.nsuluofuo.input.MouseManager;
import org.cofezuwo.nsuluofuo.main.Game;
import org.cofezuwo.nsuluofuo.ui.ClickListener;
import org.cofezuwo.nsuluofuo.ui.GUIImageButton;
import org.cofezuwo.nsuluofuo.ui.GUIManager;

public class MenuMode extends Mode {
	
	private GUIManager guiManager;
	private JTextField textField;
	private JPanel contentPane;
	
	public MenuMode() {
		super();
		textField = new JTextField();
		guiManager = GUIManager.getInstance();
		guiManager.clear();
		MouseManager.getInstance().setGUIManager(guiManager);
		
		guiManager.addObject(new GUIImageButton(256, 158, 128, 64, Assets.start_btn, new ClickListener(){
		
	
			@Override
			public void onClick() {
				MouseManager.getInstance().setGUIManager(null);
				Mode.setMode(Game.getInstance().gameMode);
				
			}}));
		
		guiManager.addObject(new GUIImageButton(256, 258, 128, 64, Assets.close_button, new ClickListener(){
			
			
			@Override
			public void onClick() {
				MouseManager.getInstance().setGUIManager(null);
				System.exit(0);
				
			}}));
		
		guiManager.addObject(new GUIImageButton(256, 358, 128, 64, Assets.settings_button, new ClickListener(){
			
			
			@Override
			public void onClick() {
				Mode.setMode(Game.getInstance().settingsMode);
				guiManager.clear();
			}}));
	}

	

	@Override
	public void update() {
		guiManager.update();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 640, 480);
		g.drawImage(Assets.ivo, 0, 0, null);
		g.drawImage(Assets.thc, 0, 400, null);
		Text.drawString(g, "NSULUOFUO", 320, 100, true, Color.WHITE, Assets.head);
		guiManager.render(g);
		
	}



	public GUIManager getGuiManager() {
		return guiManager;
	}



	public void setGuiManager(GUIManager guiManager) {
		this.guiManager = guiManager;
	}
	
	
}
