package org.cofezuwo.nsuluofuo.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class GUIImageButton extends GUIObject{
	
	private BufferedImage[] images;
	private ClickListener clicker;
	
	public GUIImageButton(int x, int y, int width, int height, BufferedImage[] images, ClickListener clicker){
		super(x, y, width, height);
		this.images = images;
		this.clicker = clicker;
	}
	
	@Override
	public void update(){
		
	}
	
	@Override 
	public void render(Graphics g){
		if(hovering){
			g.drawImage(images[1], x, y, width, height, null);
		}else{
			g.drawImage(images[0], x, y, width, height, null);
		}
	}
	
	@Override
	public void onClick(){
		clicker.onClick();
	}

}
