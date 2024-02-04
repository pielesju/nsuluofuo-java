package org.cofezuwo.nsuluofuo.graphics.tiles;

import org.cofezuwo.nsuluofuo.graphics.ATG;
import org.cofezuwo.nsuluofuo.graphics.Animation;

import java.awt.*;

public class AnimatedTile extends Tile {

    private Animation tileAnimation;

    public AnimatedTile(int id, Image[] texture, int width, int height, boolean solid) {
        super(id, width, height, solid);
        this.tileAnimation = new Animation(100, texture);
    }

    @Override
    public void update() {
        this.tileAnimation.update();
    }

    @Override
    public void render(ATG g, int x, int y) {
        g.drawImage(tileAnimation.getCurrentFrame(), x, y, width, height);
    }

}
