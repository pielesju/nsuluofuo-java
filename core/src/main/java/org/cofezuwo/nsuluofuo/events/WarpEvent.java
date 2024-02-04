package org.cofezuwo.nsuluofuo.events;

import org.cofezuwo.nsuluofuo.graphics.ATG;
import org.cofezuwo.nsuluofuo.graphics.GameCamera;
import org.cofezuwo.nsuluofuo.main.Game;

import java.awt.*;

public class WarpEvent extends Event {

    private int x;
    private int y;
    private int x2;
    private int y2;

    public void update() {
        if
        (
            Game.getInstance().getEntityManager().getPlayer().getX() >= x &&
            Game.getInstance().getEntityManager().getPlayer().getY() <= x + 32 &&
                    Game.getInstance().getEntityManager().getPlayer().getX() >= y &&
                    Game.getInstance().getEntityManager().getPlayer().getY() <= y + 32

        )
        {
            Game.getInstance().getEntityManager().getPlayer().setX(x2);
            Game.getInstance().getEntityManager().getPlayer().setX(y2);
        }
    }

    public void render(ATG g) {
        g.fillRect(x - GameCamera.getInstance().getXOffset(), y - GameCamera.getInstance().getYOffset(), 32, 32, Color.RED);
    }

    public WarpEvent(int x, int y, int x2, int y2) {
        this.x = x * 32;
        this.y = y * 32;
        this.x2 = x2 * 32;
        this.y2 = y2 * 32;
    }
}
