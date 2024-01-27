package org.cofezuwo.nsuluofuo.story;

import java.awt.Graphics;

import org.cofezuwo.nsuluofuo.item.Item;
import org.cofezuwo.nsuluofuo.item.Trivel;
import org.cofezuwo.nsuluofuo.main.Handler;

public class Beginning extends Quest{

	private Item trivel;

	public Beginning() {
		super();
		setName("The Trivel");
		setActive(true);
		setDone(false);
		trivel =  new Trivel();
	}
	
	public void update(){
		
			if(Handler.getInstance().getWorld().getEntityManager().getPlayer().getX() > 200 && Handler.getInstance().getWorld().getEntityManager().getPlayer().getX() > 200){
				setActive(false);
				setDone(true);
			}
		
		
	}
	
	public void render(Graphics g){
		
	}

}
