package org.cofezuwo.nsuluofuo.graphics;

import java.awt.*;

import org.cofezuwo.nsuluofuo.input.KeyManager;

public class ATGAWT implements ATG {


    private Graphics graphics;

    private final DisplaySwing displaySwing;

    private final int width;
    private final int height;

    public void setGraphics(Object g) {
        this.graphics = (Graphics) g;
    }


    public ATGAWT(String windowTitle, int windowWidth, int windowHeight) {
        KeyManager keyManager = KeyManager.getInstance();

        this.width = windowWidth;
        this.height = windowHeight;

        displaySwing = new DisplaySwing(windowTitle, windowWidth, windowHeight);
        displaySwing.getFrame().addKeyListener(keyManager);
        displaySwing.getCanvas().setVisible(true);

    }

    public Graphics getGraphics() {
        return this.graphics;
    }

    public DisplaySwing getDisplay() {
        return this.displaySwing;
    }

    public void clear() {
        if(null != graphics) {
            graphics.clearRect(0, 0, this.width, this.height);
        }
    }

    public void free() {
        this.graphics.dispose();
    }

    public void setColor(Color color) {
        graphics.setColor(color);
    }

    public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight, Color color) {
        this.setColor(color);
        graphics.fillRoundRect(x, y, width , height , arcWidth , arcHeight );
    }

    public void fillRect(int x, int y, int width, int height, Color color) {
        this.setColor(color);
        graphics.fillRect(x , y , width , height );
    }

    public void drawImage(Image image, int x, int y) {
        graphics.drawImage(image, x, y,null);
    }

    public void drawImage(Image image, int x, int y, int width, int height) {
        graphics.drawImage(image, x , y , width , height ,null);
    }

    public void drawString(String text, int xPos, int yPos, boolean center, Color c, Font font){
        this.setColor(c);
        this.graphics.setFont(font);
        int x = xPos;
        int y = yPos;
        if(center){
            FontMetrics fm = graphics.getFontMetrics(font);
            x = xPos - fm.stringWidth(text) / 2;
            y = yPos -fm.getHeight() / 2 + fm.getAscent();
        }
        this.graphics.drawString(text, x, y);
    }

    public void drawLine(int x1, int y1, int x2, int y2, Color color) {
        this.setColor(color);
        graphics.drawLine(x1, y1, x2, y2);
    }
}
