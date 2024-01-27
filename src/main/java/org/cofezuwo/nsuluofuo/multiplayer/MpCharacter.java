package org.cofezuwo.nsuluofuo.multiplayer;

import java.io.Serializable;

public class MpCharacter implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2608720197915917032L;
	
	private double positionX;
	private double positionY;
	private int dir;
	private int id;
	private String name = "";

	public MpCharacter(double ppositionX, double ppositionY, int pdir){
		
		positionX = ppositionX;
		positionY = ppositionY;
		dir = pdir;
	}
	public MpCharacter(double ppositionX, double ppositionY, int pdir, String pname){
		
		positionX = ppositionX;
		positionY = ppositionY;
		dir = pdir;
		name = pname;
	}

	public int getId() {
		return id;
	}

	public void setId(int pid) {
		this.id = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String pname) {
		this.name = pname;
	}

	public double getPositionX() {
		return positionX;
	}

	public void setPositionX(double ppositionX) {
		this.positionX = ppositionX;
	}

	public double getPositionY() {
		return positionY;
	}

	public void setPositionY(double ppositionY) {
		this.positionY = ppositionY;
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int pdir) {
		this.dir = pdir;
	}
}
