package org.cofezuwo.nsuluofuo.graphics.tiles;

import org.cofezuwo.nsuluofuo.graphics.ATG;

public class VoidTile extends Tile {

    public VoidTile(int id, int width, int height, boolean solid) {
        super(id, width, height, solid);
    }

    public void update() {
        // not supported
    }

    public void render(ATG g, int x, int y) {
        //not supported
    }
}
