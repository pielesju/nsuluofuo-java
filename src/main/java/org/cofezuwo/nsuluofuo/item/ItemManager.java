package org.cofezuwo.nsuluofuo.item;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;


public class ItemManager {
	

	private ArrayList<Item> items;
	
	public ItemManager(){

		items = new ArrayList<Item>();
	}
	
	public void update(){
		Iterator<Item> it = items.iterator();
		while(it.hasNext()){
			Item i = it.next();
			i.update();
			if(i.isPickedUp()){
				it.remove();
			}
		}
	}
	
	public void render(Graphics g){
		for(Item i: items){
			i.render(g);
		}
	}
	
	public void addItem(Item i){
		items.add(i);
	}


	

}
