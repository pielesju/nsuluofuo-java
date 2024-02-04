package org.cofezuwo.nsuluofuo.main;

import org.cofezuwo.nsuluofuo.graphics.Assets;
import org.cofezuwo.nsuluofuo.graphics.tiles.AnimatedTile;
import org.cofezuwo.nsuluofuo.graphics.tiles.Tile;
import org.cofezuwo.nsuluofuo.graphics.tiles.Tiles;
import org.cofezuwo.nsuluofuo.utils.Utils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

public class MapLoader {

    public MapLoader() {

    }



    public Map loadWorld(String path) {
        Map map = null;

        try {

            InputStream inputStream = Utils.getFileFromResourceAsStream(path);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputStream);
            doc.getDocumentElement().normalize();

            Element mapElement = (Element) doc.getElementsByTagName("map").item(0);

            int width = Integer.parseInt(mapElement.getAttribute("width"));
            int height = Integer.parseInt(mapElement.getAttribute("height"));

            Tile[][] groundTiles = new Tile[width][height];
            Tile[][] wallTiles   = new Tile[width][height];

            Element groundLayer = findLayerByName(doc, "ground");
            if(null != groundLayer) {
                groundTiles = buildTileMap(groundLayer, width, height);
            }

            Element wallLayer = findLayerByName(doc, "walls");
            if(null != groundLayer) {
                wallTiles = buildTileMap(wallLayer, width, height);
            }

            map = new Map(width, height, groundTiles, wallTiles);
        } catch(Exception e) {
            System.out.println("LOADING MAP FAILED");
            e.printStackTrace();
        }

        return map;
    }

    private Element findLayerByName(Document doc, String name) {
        NodeList layerNodes = doc.getElementsByTagName("layer");
        for (int i = 0; i < layerNodes.getLength(); i++) {
            Element layerElement = (Element) layerNodes.item(i);
            String layerName = layerElement.getAttribute("name");
            if (layerName.equals(name)) {
                return layerElement;
            }
        }
        return null;
    }

    private Tile[][] buildTileMap(Element layer, int width, int height) {
        NodeList dataNodes = layer.getElementsByTagName("data");
        int[][] tilesL = new int[width][height];

        if (dataNodes.getLength() > 0) {
            Element dataElement = (Element) dataNodes.item(0);

            String[] tileData = dataElement.getTextContent().replaceAll("\\s+", "").trim().split(",");
            for (int i = 0; i < tileData.length; i++) {

                int tileId = Integer.parseInt(tileData[i]);
                int x = i % width;
                int y = i / width;
                tilesL[x][y] = tileId;
            }
        }

        Tile[][] tt = new Tile[width][height];

        for(int y = 0; y < width; y++) {
            for(int x = 0; x < height; x++) {
                int tileNr = tilesL[x][y];
                Tile tile = Tiles.tiles[tileNr];
                tt[x][y] = tile;

                System.out.println(tt[x][y].getId());
            }
        }

        return tt;
    }
}