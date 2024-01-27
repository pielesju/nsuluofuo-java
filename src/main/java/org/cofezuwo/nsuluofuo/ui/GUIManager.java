package org.cofezuwo.nsuluofuo.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class GUIManager {

	private static GUIManager instance;

	private GUIManager() {
		objects = new ArrayList<GUIObject>();
	}

	public static GUIManager getInstance() {
		if(null == instance) {
			instance = new GUIManager();
		}

		return instance;
	}
	private ArrayList<GUIObject> objects;
	

	
	public void update(){
		for(GUIObject o : objects){
			o.update();
		}
	}

	public void clear() {
		for(GUIObject o : objects) {
			objects.remove(o);
		}
	}
	
	public void render(Graphics g){
		for(GUIObject o : objects){
			o.render(g);
		}
	}
	
	public void onMouseMove(MouseEvent e){
		for(GUIObject o : objects){
			o.onMouseMove(e);
		}
	}
	
	public void onMouseRelease(MouseEvent e){
		for(GUIObject o : objects){
			o.onMouseRelease(e);
		}
	}
	
	public void addObject(GUIObject o){
		objects.add(o);
	}
	
	public void removeObject(GUIObject o){
		objects.remove(o);
	}

	
	public ArrayList<GUIObject> getObjects(){
		return objects;
	}
	
	public void setObjects(ArrayList<GUIObject> objects){
		this.objects = objects;
	}

}
