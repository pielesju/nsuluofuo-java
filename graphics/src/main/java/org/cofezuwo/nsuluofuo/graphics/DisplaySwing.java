package org.cofezuwo.nsuluofuo.graphics;

import lombok.Getter;

import java.awt.*;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class DisplaySwing implements Display {

	@Getter
	private JFrame frame;

	@Getter
	private Canvas canvas;

	private final String title;
	private final int width;
	private final int height;

	public DisplaySwing(String title, int width, int height) {


		this.title = title;
		this.width = width;
		this.height = height;

		createDisplay();
	}

	public void createDisplay() {
		frame = new JFrame(this.title);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/textures/Icon.png"));
		frame.setSize(this.width, this.height);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(this.width, this.height));
		canvas.setMaximumSize(new Dimension(this.width, this.height));
		canvas.setMinimumSize(new Dimension(this.width, this.height));
		canvas.setFocusable(false);

		frame.add(canvas);
		frame.pack();
	}
}