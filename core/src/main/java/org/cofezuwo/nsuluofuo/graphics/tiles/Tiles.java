package org.cofezuwo.nsuluofuo.graphics.tiles;

import org.cofezuwo.nsuluofuo.graphics.Assets;

public class Tiles {

    public static final Tile[] tiles = {
        new Tile(1, Assets.grass, 32, 32, false),
            new Tile(2, Assets.highGrass, 32, 32, false),
            new Tile(17, Assets.watera[0], 32, 32, true),
            new Tile(18, Assets.path, 32, 32, false),
            new Tile(20, Assets.stone, 32 ,32, true),
            new Tile(5, Assets.leftUpperCorner, 32, 32, true),
            new Tile(7, Assets.rightUpperCorner, 32, 32, true),
            new Tile(7, Assets.middleWall, 32, 32, true),
            new Tile(22, Assets.rightBottomCorner, 32, 32, true),
            new Tile(11, Assets.cliffDown, 32, 32, true),
            new Tile(11, Assets.cliffUp, 32, 32, true),
            new Tile(12, Assets.cliffLeft, 32, 32, true),
            new Tile(13, Assets.cliffRight, 32, 32, true),
            new Tile(14, Assets.bridge, 32, 32, false),
            new Tile(15, Assets.mossyWall, 32, 32, true),
            new Tile(68, Assets.mossyWallLeft, 32, 32, true),
            new Tile(69, Assets.mossyWallRight, 32, 32, true),
            new Tile(52,Assets.leftWall, 32, 32, true),
            new Tile(53, Assets.rightWall, 32, 32, true),
            new Tile(20, Assets.stoneBright, 32, 32, true),
            new Tile(21, Assets.gate, 32, 32, false),
            new Tile(21, Assets.leftBottomCorner, 32, 32, true),
            new Tile(22, Assets.rightBottomCorner, 32, 32, true),
    };

    private Tiles() {

    }
}
