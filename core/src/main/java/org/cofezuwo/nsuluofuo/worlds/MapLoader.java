package org.cofezuwo.nsuluofuo.worlds;

import org.cofezuwo.nsuluofuo.utils.Utils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
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

            int[][] groundTiles = new int[width][height];
            int[][] wallTiles   = new int[width][height];

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

    private int[][] buildTileMap(Element layer, int width, int height) {
        NodeList dataNodes = layer.getElementsByTagName("data");
        int[][] tiles = new int[width][height];

        if (dataNodes.getLength() > 0) {
            Element dataElement = (Element) dataNodes.item(0);

            String[] tileData = dataElement.getTextContent().replaceAll("\\s+", "").trim().split(",");
            for (int i = 0; i < tileData.length; i++) {

                int tileId = Integer.parseInt(tileData[i]);
                int x = i % width;
                int y = i / width;
                tiles[x][y] = tileId;
            }
        }

        return tiles;
    }
}
