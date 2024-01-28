package org.cofezuwo.nsuluofuo.worlds;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.Setter;
import org.cofezuwo.nsuluofuo.creatures.Malenica;
import org.cofezuwo.nsuluofuo.creatures.NPC;
import org.cofezuwo.nsuluofuo.creatures.Player;
import org.cofezuwo.nsuluofuo.creatures.Player2;
import org.cofezuwo.nsuluofuo.entities.EntityManager;
import org.cofezuwo.nsuluofuo.graphics.ATG;
import org.cofezuwo.nsuluofuo.graphics.GameCamera;
import org.cofezuwo.nsuluofuo.graphics.tiles.Tile;
import org.cofezuwo.nsuluofuo.graphics.tiles.Tiles;
import org.cofezuwo.nsuluofuo.item.ItemManager;
import org.cofezuwo.nsuluofuo.statics.Ganja;
import org.cofezuwo.nsuluofuo.statics.Tree;
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
	private int[][] groundTiles;
	private int[][] wallTiles;
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

	private ATG g;

	public void update() {
		entityManager.update();
		questManager.update();
		itemManager.update();
	}

	public void render(ATG g) {
		this.g = g;



		int xStart = Math.max(0, cam.getxOffset() / 32);
		int yStart = Math.max(0, cam.getyOffset() / 32);
		int xEnd = Math.min(width, (cam.getxOffset() + 640) / Tile.TILEWIDTH + 1);
		int yEnd = Math.min(height, (cam.getyOffset() + 480) / Tile.TILEHEIGHT + 1);

		/* render ground Layer */
		renderLayer(this.groundTiles, yStart, yEnd, xStart, xEnd);

		/* render wall Layer */
		renderLayer(this.wallTiles, yStart, yEnd, xStart, xEnd);

		itemManager.render(g);
		entityManager.render(g);
		questManager.render(g);

		g.fillRect(0, 0, 640, 480, daycolor);
	}

	public void renderLayer(int[][] tiles, int yStart, int yEnd, int xStart, int xEnd) {
		for (int y = yStart; y < yEnd; y++) {
			for (int x = xStart; x < xEnd; x++) {
				renderTile(tiles[x][y], x, y);
			}
		}
	}

	public void renderTile(int tile, int x, int y) {
		x = x * Tile.TILEWIDTH - cam.getxOffset();
		y = y * Tile.TILEWIDTH - cam.getyOffset();

		Tiles.tiles[tile].render(g, x, y);
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
		return Tiles.tiles[wallTiles[x][y]];
	}

	private void loadWorld(String path) {
		MapLoader mapLoader = new MapLoader();
		Map map = mapLoader.loadWorld(path);

		this.width = map.getWidth();
		this.height = map.getHeight();
		this.groundTiles = map.getGroundLayer();
		this.wallTiles = map.getWallLayer();

		Gson gson = new Gson();
		String info = Utils.loadFileAsString("worlds/info.json");
		PlayerInfo playerInfo = gson.fromJson(info, PlayerInfo.class);

		spawnX = playerInfo.getPlayer1SpawnX();
		spawnY = playerInfo.getPlayer1SpawnY();
		spawnX2 = playerInfo.getPlayer2SpawnX();
		spawnY2 = playerInfo.getPlayer2SpawnY();
		name = playerInfo.getPlayer1Name();
		name2 = playerInfo.getPlayer2Name();
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

		Gson gson = new Gson();
		List<TmpEntity> entities = new ArrayList<>();
		String entitiesFile = Utils.loadFileAsString("worlds/entities.json");

		entities = gson.fromJson(entitiesFile, new TypeToken<List<TmpEntity>>() {}.getType());

		for(TmpEntity entity : entities) {
			if(entity.getType() == TmpEntity.EntityType.GANJA) {
				entityManager.addEntity(new Ganja(entity.getX(), entity.getY()));
			}
			if(entity.getType() == TmpEntity.EntityType.NPC) {
				entityManager.addEntity(new NPC(entity.getX(), entity.getY(), entity.getName(), entity.getText()));
			}
			if(entity.getType() == TmpEntity.EntityType.MALENICA) {
				entityManager.addEntity(new Malenica(entity.getX(), entity.getY(), entity.getText()));
			}
		}

		entityManager.addEntity(new Tree(4, 4, 16, 32));

		
		loadWorld("worlds/world.tmx");
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
