package org.cofezuwo.nsuluofuo.graphics;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class FontLoader {

	public static Font loadFont(String path, int size) {

		try {
			InputStream stream = FontLoader.class.getClassLoader().getResourceAsStream(path);

			if (stream == null) {
				throw new IOException("Font file not found: " + path);
			}

			Font font = Font.createFont(Font.TRUETYPE_FONT, stream);
			return font.deriveFont(Font.PLAIN, size);

		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		return null;
	}

	private FontLoader() {

	}

}
