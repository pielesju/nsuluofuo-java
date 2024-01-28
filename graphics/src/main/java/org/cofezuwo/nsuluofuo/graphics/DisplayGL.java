package org.cofezuwo.nsuluofuo.graphics;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class DisplayGL implements Display {

    @Getter
    private JFrame frame;

    private GLCanvas glCanvas;

    private final String title;
    private final int width;
    private final int height;

    public DisplayGL(String title, int width, int height) {


        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }

    public void createDisplay() {
        GLProfile profile = GLProfile.getDefault();
        GLCapabilities capabilities = new GLCapabilities(profile);
        GLCanvas gcanvas = new GLCanvas(capabilities);

        frame = new JFrame(this.title);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/textures/Icon.png"));
        frame.setSize(this.width, this.height);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        this.glCanvas = gcanvas;

        frame.getContentPane().add(glCanvas);
        frame.pack();
    }

    @Override
    public Canvas getCanvas() {
        return this.glCanvas;
    }
}