package org.cofezuwo.nsuluofuo.inventory;

import org.cofezuwo.nsuluofuo.graphics.ATG;
import org.cofezuwo.nsuluofuo.graphics.Assets;

import java.awt.*;

public class Computer {

    public void update() {

    }

    public void render(ATG g) {
		g.fillRoundRect(10, 10, 620, 460, 10, 10, Color.BLACK);
		g.drawString("$ whoami" ,15, 30, false, Color.GREEN, Assets.vga);
		g.drawString("cofezuwo" ,15, 45, false, Color.GREEN, Assets.vga);
		g.drawString("pwd" ,15, 60, false, Color.GREEN, Assets.vga);
		g.drawString("/" ,15, 75, false, Color.GREEN, Assets.vga);
    }

}
