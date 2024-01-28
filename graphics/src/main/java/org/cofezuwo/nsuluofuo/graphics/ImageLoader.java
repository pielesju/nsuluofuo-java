package org.cofezuwo.nsuluofuo.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageLoader {

	public static BufferedImage loadImage(String path) {
		try {
			InputStream stream = ImageLoader.class.getResourceAsStream(path);

			if (stream == null) {
				throw new IOException("Image file not found: " + path);
			}

			return ImageIO.read(stream);

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		return null;
	}

	private ImageLoader() {

	}

}
