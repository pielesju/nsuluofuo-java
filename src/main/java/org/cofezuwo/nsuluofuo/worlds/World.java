package org.cofezuwo.nsuluofuo.worlds;

import java.awt.Color;
import java.awt.Graphics;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import org.cofezuwo.nsuluofuo.creatures.Malenica;
import org.cofezuwo.nsuluofuo.creatures.NPC;
import org.cofezuwo.nsuluofuo.creatures.Player;
import org.cofezuwo.nsuluofuo.creatures.Player2;
import org.cofezuwo.nsuluofuo.entities.EntityManager;
import org.cofezuwo.nsuluofuo.graphics.GameCamera;
import org.cofezuwo.nsuluofuo.graphics.tiles.Tile;
import org.cofezuwo.nsuluofuo.graphics.tiles.Tiles;
import org.cofezuwo.nsuluofuo.item.ItemManager;
import org.cofezuwo.nsuluofuo.main.Game;
import org.cofezuwo.nsuluofuo.statics.Ganja;
import org.cofezuwo.nsuluofuo.story.QuestManager;
import org.cofezuwo.nsuluofuo.utils.Utils;


public class World {

	private static World instance;

	@Getter @Setter
	private int width;
	@Getter @Setter
	private int height;
	private int spawnX;
	private int spawnY;
	private int spawnX2;
	private int spawnY2;

	@Getter
	private String name;
	private String name2;
	private int[][] tiles;
	@Getter
	private EntityManager entityManager;
	@Getter
	private QuestManager questManager;
	private Color daycolor;
	private Date currentDate = new Date();
	private SimpleDateFormat sdf = new SimpleDateFormat("HH");
	private String formattedDate = sdf.format(currentDate);
	private int time = Integer.parseInt(formattedDate);
	private ItemManager itemManager;
	private GameCamera cam;

	public void update() {
		entityManager.update();
		questManager.update();
		itemManager.update();
	}

	public void render(Graphics g) {



		int xStart = Math.max(0, cam.getxOffset() / 32);
		int yStart = Math.max(0, cam.getyOffset() / 32);
		int xEnd = Math.min(width, (cam.getxOffset() + 640) / Tile.TILEWIDTH + 1);
		int yEnd = Math.min(height, (cam.getyOffset() + 480) / Tile.TILEHEIGHT + 1);

		for (int y = yStart; y < yEnd; y++) {
			for (int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g,
						x * Tile.TILEWIDTH - cam.getxOffset(),
						y * Tile.TILEHEIGHT - cam.getyOffset());
			}
		}

		itemManager.render(g);
		entityManager.render(g);
		questManager.render(g);
		g.setColor(daycolor);
		g.fillRect(0, 0, 640, 480);
	}

	public Tile getTile(int x, int y) {

		// ????
		/*if (x < 0 || y < 0 || x >= width || y >= height) {
			return Tiles.tiles[0];
		}*/

		//Tile t =
		/*if (t == null) {
			return Tiles.tiles[0];
		}*/
		return Tiles.tiles[tiles[x][y]];
	}

	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		spawnX2 = Utils.parseInt(tokens[4]);
		spawnY2 = Utils.parseInt(tokens[5]);
		name = tokens[6];
		name2 = tokens[7];
	
		tiles = new int[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles[x][y] = Integer.parseInt(tokens[(x + y * width) + 8]);
			}
		}
	}

	private World() {
		if((time >= 17  &&  time <= 20) || (time >= 6 && time  <= 8)) {
			daycolor = new Color(255,0,0,75);
		}else if(time>= 20 || time<=6) {
			daycolor = new Color(0,0,255,100);
		}else if(time>= 6 && time<=8) {
			daycolor = new Color(255,0,140,100);
		}else{
			daycolor = new Color(0,0,0,0);
		}
		entityManager = new EntityManager(new Player(spawnX, spawnY), new Player2(spawnX2, spawnY2));
		questManager = new QuestManager();
		questManager.setActive(false);
		cam = GameCamera.getInstance();

		itemManager = new ItemManager();

		entityManager.addEntity(new Ganja(12, 12, width, height));

		entityManager.addEntity(new NPC(6, 3, "Pieles mit der Trivel", new String[] {"Ich hasse dich, du dreckiger " +
				"Hurensohn",
				"Du Spasst", "For Real... Realtalk jetzt",
				"Du geisteskranke Psychoschlampe",
				"Jo es ist Pieles mit der Trivel",
				"Wham Wham.. like Every beat, every line...",
				"Ab jetzt nicht mehr Lenzkirch, sondern Mechernich"}));
		entityManager.addEntity(new Malenica(4, 4,  new String[] {"Konzept klar?",
				"Ihr mit eurem bekloppten Trivialismus","und eurer schei� App",
				"Mir kommts so vor als w�r das alles nur ein",
				"RIESIGER Witz f�r euch",
				"Ich hasse dich nicht",
				"Ich bin nur ma�los entt�uscht von dir"}));
		
		
		loadWorld("worlds/world1.txt");
		entityManager.getPlayer().setX(spawnX * entityManager.getPlayer().getWidth());
		entityManager.getPlayer().setY(spawnY * entityManager.getPlayer().getWidth());
		
		entityManager.getPlayer2().setX(spawnX2 * entityManager.getPlayer2().getWidth());
		entityManager.getPlayer2().setY(spawnY2 * entityManager.getPlayer2().getWidth());


		

		
		
		
	}

	public static World getInstance() {
		if(null == instance) {
			instance = new World();
		}

		return instance;
	}
}
