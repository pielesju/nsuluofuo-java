package org.cofezuwo.nsuluofuo.graphics;

import java.awt.*;

public interface ATG {

    Graphics getGraphics();

    Display getDisplay();

    void setGraphics(Object g);

    void clear();

    void free();

    void setColor(Color color);

    void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight, Color color);

    void fillRect(int x, int y, int width, int height, Color color);

    void drawImage(Image image, int x, int y);

    void drawImage(Image image, int x, int y, int width, int height);

    void drawString(String text, int xPos, int yPos, boolean center, Color c, Font font);

    void drawLine(int x1, int y1, int x2, int y2, Color color);
}
