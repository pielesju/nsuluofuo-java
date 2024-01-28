package org.cofezuwo.nsuluofuo.worlds;

import lombok.Getter;

public class Map {

    @Getter
    private int width;
    @Getter
    private int height;
    @Getter
    private int[][] groundLayer;
    @Getter
    private int[][] wallLayer;

    public Map(int width, int height, int[][] groundLayer, int[][] wallLayer) {
        this.width = width;
        this.height = height;
        this.groundLayer = groundLayer;
        this.wallLayer = wallLayer;
    }
}
