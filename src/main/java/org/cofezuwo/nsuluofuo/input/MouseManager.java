package org.cofezuwo.nsuluofuo.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import org.cofezuwo.nsuluofuo.ui.GUIManager;

public class MouseManager implements MouseListener, MouseMotionListener{

	private static MouseManager instance;

	private boolean leftPressed;
	private boolean rightPressed;
	private int mouseX, mouseY;
	private GUIManager guiManager;
	
	private MouseManager(){
		
	}

	public static MouseManager getInstance() {
		if(null == instance) {
			instance = new MouseManager();
		}

		return instance;
	}
	
	public void setGUIManager(GUIManager guiManager){
		this.guiManager = guiManager;
	}
	
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// not supported
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		setMouseX(e.getX());
		setMouseY(e.getY());
		if(guiManager != null){
			guiManager.onMouseMove(e);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// not supported
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// not supported
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// not supported
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1){
			setLeftPressed(true);
		} else if(e.getButton() == MouseEvent.BUTTON3){
			setRightPressed(true);
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1){
			setLeftPressed(false);
		} else if(e.getButton() == MouseEvent.BUTTON3){
			setRightPressed(false);
		}
		if(guiManager != null){
			guiManager.onMouseRelease(e);
		}
		
		
	}

	public boolean isLeftPressed() {
		return leftPressed;
	}

	public boolean isRightPressed() {
		return rightPressed;
	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

	public void setLeftPressed(boolean leftPressed) {
		this.leftPressed = leftPressed;
	}

	public void setRightPressed(boolean rightPressed) {
		this.rightPressed = rightPressed;
	}

	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}

	public void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}
	
	
	
	

}
