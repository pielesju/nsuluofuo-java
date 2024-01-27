package org.cofezuwo.nsuluofuo.graphics.tiles;

import org.cofezuwo.nsuluofuo.graphics.Assets;

public class Tiles {

    public static final Tile[] tiles = {
        new Tile(0, Assets.grass, 32, 32, false),
            new Tile(1, Assets.highGrass, 32, 32, false),
            new Tile(2, Assets.watera[0], 32, 32, true),
            new Tile(3, Assets.path, 32, 32, false),
            new Tile(4, Assets.stone, 32 ,32, true),
            new Tile(5, Assets.middleWall, 32, 32, true),
            new Tile(6, Assets.leftUpperCorner, 32, 32, true),
            new Tile(7, Assets.leftBottomCorner, 32, 32, true),
            new Tile(8, Assets.rightUpperCorner, 32, 32, true),
            new Tile(9, Assets.rightBottomCorner, 32, 32, true),
            new Tile(10, Assets.cliffDown, 32, 32, true),
            new Tile(11, Assets.cliffUp, 32, 32, true),
            new Tile(12, Assets.cliffLeft, 32, 32, true),
            new Tile(13, Assets.cliffRight, 32, 32, true),
            new Tile(14, Assets.bridge, 32, 32, false),
            new Tile(15, Assets.mossyWall, 32, 32, true),
            new Tile(16, Assets.mossyWallLeft, 32, 32, true),
            new Tile(17, Assets.mossyWallRight, 32, 32, true),
            new Tile(18,Assets.leftWall, 32, 32, true),
            new Tile(19, Assets.rightWall, 32, 32, true),
            new Tile(20, Assets.stoneBright, 32, 32, true),
            new Tile(21, Assets.gate, 32, 32, false)
    };
}
