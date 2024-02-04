package org.cofezuwo.nsuluofuo.main;

import lombok.Getter;
import lombok.Setter;
import org.cofezuwo.nsuluofuo.GameNode;
import org.cofezuwo.nsuluofuo.graphics.ATG;
import org.cofezuwo.nsuluofuo.graphics.Assets;
import org.cofezuwo.nsuluofuo.graphics.GameCamera;
import org.cofezuwo.nsuluofuo.graphics.tiles.AnimatedTile;
import org.cofezuwo.nsuluofuo.graphics.tiles.Tile;
import org.cofezuwo.nsuluofuo.graphics.tiles.Tiles;

public class Map implements GameNode {

    @Getter
    @Setter
    private int width;
    @Getter
    @Setter
    private int height;
    @Getter
    @Setter
    private Tile[][] groundLayer;
    @Getter
    @Setter
    private Tile[][] wallLayer;
    private AnimatedTile waterTile;

    private ATG g;

    public Map(int width, int height, Tile[][] groundLayer, Tile[][] wallLayer) {
        this.width = width;
        this.height = height;
        this.groundLayer = groundLayer;
        this.wallLayer = wallLayer;

        waterTile = new AnimatedTile(0, Assets.watera, 32, 32, false);

        for(int y = 0; y < groundLayer.length; y++) {
            for(int x = 0; x < 32; x++) {
                System.out.println(groundLayer[x][y]);
            }
        }
    }

    /**
     *
     */
    @Override
    public void update() {
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                this.groundLayer[x][y].update();
            }
        }

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                this.wallLayer[x][y].update();
            }
        }
    }

    /**
     * @param g
     */
    @Override
    public void render(ATG g) {
        this.g = g;

        GameCamera cam = GameCamera.getInstance();

        int xOff = GameCamera.getInstance().getXOffset();
        int yOff = GameCamera.getInstance().getYOffset();


        int xStart = (0 >= cam.getXOffset() / 32) ? 0 : cam.getXOffset() / 32;
        int yStart = (0 >= cam.getYOffset() / 32) ? 0 : cam.getYOffset() / 32;
        int xEnd = (width <= (cam.getXOffset() + 640) / Tile.TILE_WIDTH + 1) ? width : (cam.getXOffset() + 640) / Tile.TILE_WIDTH + 1;
        int yEnd = (height <= (cam.getYOffset() + 480) / Tile.TILE_HEIGHT + 1) ? height : (cam.getYOffset() + 480) / Tile.TILE_HEIGHT + 1;

        //renderWater( 0, 0, 20, 20, xOff, yOff);

        System.out.println("RENDER_GROUNDLAYER");

        renderLayer(this.groundLayer, yStart, yEnd, xStart, xEnd);

        System.out.println(this.groundLayer[0].length);

        //waterTile.render(g, 1 * 32, 2 * 32);
        //waterTile.render(g, 0, 1);

        renderLayer(this.wallLayer, yStart, yEnd, xStart, xEnd);
    }

    public void renderLayer(Tile[][] tiles, int yStart, int yEnd, int xStart, int xEnd) {
        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                renderTile(tiles[x][y], x, y);
            }
        }
    }

    public void renderWater(int x, int y, int width, int height, int xOff, int yOff) {
        for(int i = y; i < height; i++) {
            for(int j = x; j < width; j++) {
                //i *= 32; // TODO - xOff
                //j *= 32; // not working

                waterTile.render(g, j * 32, i * 32);
            }
        }
    }

    public void renderTile(Tile tile, int x, int y) {
        x = x * Tile.TILE_WIDTH - GameCamera.getInstance().getXOffset();
        y = y * Tile.TILE_WIDTH - GameCamera.getInstance().getYOffset();

        tile.render(g, x, y);
    }
}
