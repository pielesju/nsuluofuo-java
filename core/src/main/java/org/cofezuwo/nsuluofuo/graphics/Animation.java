package org.cofezuwo.nsuluofuo.graphics;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public class Animation {

	@Getter
	@Setter
	private int speed;
	private int index;
	public static final int DEFAULT_SPEED = 300;
	private long lastTime;
	private long timer;
	private final Image[] frames;
	
	public Animation(int speed, Image[] frames){
		this.speed = speed;
		this.frames = frames;
		index = 0;
		timer = 0;
		lastTime = System.currentTimeMillis();
	}
	
	public void update(){
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(timer > speed){
			index++;
			timer = 0;
			if(index >= frames.length){
				index = 0;
			}
		}
	}
	
	public Image getCurrentFrame(){
		return frames[index];
	}
}