package org.cofezuwo.nsuluofuo.story;

import org.cofezuwo.nsuluofuo.item.Item;

public class Quest {
	
	private boolean done;
	private boolean active = false;
	private Item item;
	private int reward;
	private String name;
	
	public Quest(){
		
	}
	
	public void update(){
		if(!isActive() || isDone()){
			return;
		}else{
			
		}
	}
	
	public void render(){
		if(!isActive() || isDone()){
			return;
		}else{
			
		}
	}

	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	public Item getItem() {
		return item;
	}
	public int getReward() {
		return reward;
	}
	public String getName() {
		return name;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public void setReward(int reward) {
		this.reward = reward;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

}
