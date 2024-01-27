package org.cofezuwo.nsuluofuo.utils;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Utils {

	private Utils() {

	}

	public static String loadFileAsString(String path) {
		StringBuilder builder = new StringBuilder();

		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getFileFromResourceAsStream(path), StandardCharsets.UTF_8));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				builder.append(line + "\n");
			}

			bufferedReader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return builder.toString();
	}

	public static int parseInt(String number) {
		try {
			return Integer.parseInt(number);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static InputStream getFileFromResourceAsStream(String fileName) {
		ClassLoader classLoader = Utils.class.getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(fileName);

		if(null == inputStream) {
			throw new IllegalArgumentException("file not found: " + fileName);
		} else {
			return inputStream;
		}
	}
}
