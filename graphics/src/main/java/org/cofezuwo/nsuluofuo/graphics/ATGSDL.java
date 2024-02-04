package org.cofezuwo.nsuluofuo.graphics;

import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.awt.TextRenderer;
import io.github.libsdl4j.api.render.SDL_Renderer;
import org.cofezuwo.nsuluofuo.input.KeyManager;

import java.awt.*;

import static io.github.libsdl4j.api.Sdl.SDL_Quit;
import static io.github.libsdl4j.api.render.SdlRender.SDL_RenderClear;
import static io.github.libsdl4j.api.render.SdlRender.SDL_SetRenderDrawColor;

public class ATGSDL implements ATG, GLEventListener {


    private SDL_Renderer graphics;

    private final Display displaySDL;

    private final int width;
    private final int height;

    private GLAutoDrawable glAutoDrawable;
    private TextRenderer textRenderer;

    public void setGraphics(Object g) {
        this.graphics = (SDL_Renderer) g;
    }


    public ATGSDL(String windowTitle, int windowWidth, int windowHeight) {
        KeyManager keyManager = KeyManager.getInstance();

        this.width = windowWidth;
        this.height = windowHeight;

        displaySDL = new DisplaySDL(this, windowTitle, windowWidth, windowHeight);
        displaySDL.getFrame().addKeyListener(keyManager);
        displaySDL.getCanvas().setVisible(true);

        GLCanvas canvas = (GLCanvas) displaySDL.getCanvas();
        canvas.addGLEventListener(this);
        canvas.requestFocus();

    }

    public Graphics getGraphics() {
        return null; //return this.graphics;
    }

    public Display getDisplay() {
        return this.displaySDL;
    }

    public void clear() {
        if(null != graphics) {
            setColor(Color.WHITE);
            SDL_RenderClear(graphics);
        }
    }

    public void free() {
        SDL_Quit();
    }

    public void setColor(Color color) {
        SDL_SetRenderDrawColor(graphics, (byte) color.getRed(), (byte) color.getGreen(), (byte) color.getBlue(), (byte) color.getAlpha());
    }

    public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight, Color color) {

    }

    public void fillRect(int x, int y, int width, int height, Color color) {
        this.setColor(color);
        //SDL_FillRect()
    }

    public void drawImage(Image image, int x, int y) {
        //graphics.drawImage(image, x, y,null);
    }

    public void drawImage(Image image, int x, int y, int width, int height) {
        //graphics.drawImage(image, x , y , width , height ,null);
    }

    public void drawString(String text, int xPos, int yPos, boolean center, Color c, Font font){
        /*this.setColor(c);
        this.graphics.setFont(font);
        int x = xPos;
        int y = yPos;
        if(center){
            FontMetrics fm = graphics.getFontMetrics(font);
            x = xPos - fm.stringWidth(text) / 2;
            y = yPos -fm.getHeight() / 2 + fm.getAscent();
        }
        this.graphics.drawString(text, x, y);*/
    }

    public void drawLine(int x1, int y1, int x2, int y2, Color color) {
        this.setColor(color);
        //graphics.drawLine(x1, y1, x2, y2);
    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }
}
