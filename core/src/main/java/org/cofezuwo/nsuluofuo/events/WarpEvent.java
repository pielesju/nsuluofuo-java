package org.cofezuwo.nsuluofuo.events;

import org.cofezuwo.nsuluofuo.graphics.ATG;
import org.cofezuwo.nsuluofuo.worlds.World;

public class WarpEvent extends Event {

    private int x;
    private int y;
    private int x2;
    private int y2;

    public void update() {
        if(World.getInstance().getEntityManager().getPlayer().getX() == x &&
                World.getInstance().getEntityManager().getPlayer().getY() == y
        ) {
            World.getInstance().getEntityManager().getPlayer().setX(x2);
            World.getInstance().getEntityManager().getPlayer().setX(y2);
        }
    }

    public void render(ATG g) {

    }

    public WarpEvent(int x, int y, int x2, int y2) {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
    }
}
