package org.cofezuwo.nsuluofuo.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public abstract class GUIObject {
	
	protected int x,y, width, height;
	protected Rectangle bounds;
	protected boolean hovering = false;
	
	public GUIObject(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		bounds = new Rectangle(x,y,width,height);
	}
	
	public abstract void update();
	
	public abstract void render(Graphics g);
	
	public abstract void onClick();
	
	public void onMouseMove(MouseEvent e){
		if(bounds.contains(e.getX(), e.getY())){
			hovering = true;
		}else{
			hovering = false;
		}
	}
	
	public void onMouseRelease(MouseEvent e){
		if(hovering){
			onClick();
		}
	}
}
