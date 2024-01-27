package org.cofezuwo.nsuluofuo.gui;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Display {

	private JFrame frame;
	private Canvas canvas;
	private BufferedImage icon;
	private String title;
	private int width, height;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Display(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;

		createDisplay();
	}

	private void createDisplay() {
		
		frame = new JFrame(getTitle());
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/textures/Icon.png"));
		frame.setSize(getWidth(), getHeight());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(getWidth(), getHeight()));
		canvas.setMaximumSize(new Dimension(getWidth(), getHeight()));
		canvas.setMinimumSize(new Dimension(getWidth(), getHeight()));
		canvas.setFocusable(false);

		frame.add(canvas);
		frame.pack();
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	public JFrame getFrame() {
		return frame;
	}

}
