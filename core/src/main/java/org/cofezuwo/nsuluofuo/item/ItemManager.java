package org.cofezuwo.nsuluofuo.item;

import org.cofezuwo.nsuluofuo.graphics.ATG;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ItemManager {
	

	private List<Item> items;
	
	public ItemManager(){

		items = new ArrayList<>();
	}
	
	public void update(){
		for(Item item : items) {
			item.update();
			if(item.isPickedUp()) {
				items.remove(item);
			}
		}
	}
	
	public void render(ATG g){
		for(Item item : items){
			item.render(g);
		}
	}
	
	public void addItem(Item i){
		items.add(i);
	}


	

}
