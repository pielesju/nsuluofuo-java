package org.cofezuwo.nsuluofuo.modes;

import java.awt.Graphics;


public abstract class Mode {

	private static Mode currentMode = null;

	public static void setMode(Mode mode) {
		currentMode = mode;
	}

	public static Mode getMode() {
		return currentMode;
	}

	public abstract void update();

	public abstract void render(Graphics g);

}
