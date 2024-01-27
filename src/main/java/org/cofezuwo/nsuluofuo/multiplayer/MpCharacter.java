package org.cofezuwo.nsuluofuo.multiplayer;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MpCharacter implements Serializable{
	
	private int positionX;
	private int positionY;
	private int dir;
	private int id;
	private String name;

	public MpCharacter(int positionX, int positionY, int dir, String name){
		this.positionX = positionX;
		this.positionY = positionY;
		this.dir = dir;
		this.name = name;
	}
}
