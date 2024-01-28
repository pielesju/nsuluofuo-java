package org.cofezuwo.nsuluofuo.graphics;

import javax.swing.*;
import java.awt.*;

public interface Display {

    void createDisplay();

    Canvas getCanvas();

    JFrame getFrame();
}
