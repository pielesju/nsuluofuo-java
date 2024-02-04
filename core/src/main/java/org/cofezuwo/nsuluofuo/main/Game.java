package org.cofezuwo.nsuluofuo.main;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.Setter;
import org.cofezuwo.nsuluofuo.GameNode;
import org.cofezuwo.nsuluofuo.audio.Audio;
import org.cofezuwo.nsuluofuo.creatures.Malenica;
import org.cofezuwo.nsuluofuo.creatures.NPC;
import org.cofezuwo.nsuluofuo.creatures.Player;
import org.cofezuwo.nsuluofuo.creatures.Player2;
import org.cofezuwo.nsuluofuo.entities.EntityManager;
import org.cofezuwo.nsuluofuo.environment.Weather;
import org.cofezuwo.nsuluofuo.events.EventManager;
import org.cofezuwo.nsuluofuo.events.WarpEvent;
import org.cofezuwo.nsuluofuo.graphics.ATG;
import org.cofezuwo.nsuluofuo.graphics.Assets;
import org.cofezuwo.nsuluofuo.graphics.GameCamera;
import org.cofezuwo.nsuluofuo.graphics.tiles.AnimatedTile;
import org.cofezuwo.nsuluofuo.graphics.tiles.Tile;
import org.cofezuwo.nsuluofuo.graphics.tiles.Tiles;
import org.cofezuwo.nsuluofuo.input.KeyManager;
import org.cofezuwo.nsuluofuo.inventory.Dialog;
import org.cofezuwo.nsuluofuo.item.ItemManager;
import org.cofezuwo.nsuluofuo.statics.Ganja;
import org.cofezuwo.nsuluofuo.statics.Gate1;
import org.cofezuwo.nsuluofuo.statics.Roof;
import org.cofezuwo.nsuluofuo.story.QuestManager;
import org.cofezuwo.nsuluofuo.utils.Utils;


public class Game implements GameNode {

	private static Game instance;

	@Getter @Setter
	private int width;
	@Getter @Setter
	private int height;
	private int spawnX;
	private int spawnY;
	private int spawnX2;
	private int spawnY2;

	private Weather weather;

	@Getter
	private String name;

	private Tile[][] groundTiles;
	private Tile[][] wallTiles;

	@Getter
	private final EntityManager entityManager;
	@Getter
	private final QuestManager questManager;

	private final EventManager eventManager;

	private AnimatedTile waterTile;
	private HUD hud;



	private ItemManager itemManager;
	private GameCamera cam;
	private Audio audio;

	private boolean showSpecs = false;

	private ATG g;

	public void update() {
		entityManager.update();
		questManager.update();
		eventManager.update();
		itemManager.update();
		waterTile.update();

		weather.update();

		//

		if(KeyManager.getInstance().keyJustPressed(KeyEvent.VK_F3)) {
			showSpecs = !showSpecs;
		}

		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				this.groundTiles[x][y].update();
			}
		}

		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				this.wallTiles[x][y].update();
			}
		}
	}

	private void renderMap() {
		this.g = g;

		int xOff = cam.getXOffset();
		int yOff = cam.getYOffset();


		int xStart = (0 >= cam.getXOffset() / 32) ? 0 : cam.getXOffset() / 32;
		int yStart = (0 >= cam.getYOffset() / 32) ? 0 : cam.getYOffset() / 32;
		int xEnd = (width <= (cam.getXOffset() + 640) / Tile.TILE_WIDTH + 1) ? width : (cam.getXOffset() + 640) / Tile.TILE_WIDTH + 1;
		int yEnd = (height <= (cam.getYOffset() + 480) / Tile.TILE_HEIGHT + 1) ? height : (cam.getYOffset() + 480) / Tile.TILE_HEIGHT + 1;

		renderWater(0, 0, 20 * 32, 20 * 32, xOff, yOff);

		renderLayer(this.groundTiles, yStart, yEnd, xStart, xEnd);

		waterTile.render(g, 1 * 32, 2 * 32);
		waterTile.render(g, 1 * 32, 3 * 32);

		renderLayer(this.wallTiles, yStart, yEnd, xStart, xEnd);
	}

	public void render(ATG g) {
		this.g = g;
		renderMap();

		itemManager.render(g);
		entityManager.render(g);
		eventManager.render(g);
		questManager.render(g);

		weather.render(g);
		//renderDayTimeColor(g);


		hud.renderHealth(g, entityManager.getPlayer().getHealth());

		Dialog.render(g);

		showSpecs();
	}



	private void showSpecs() {
		if(showSpecs) {
			String playerPos = String.format("PX=%d (%d) PY=%d (%d)", entityManager.getPlayer().getX(), entityManager.getPlayer().getX() / 32, entityManager.getPlayer().getY(), entityManager.getPlayer().getY() / 32);
			g.drawString(playerPos, 5, 50, false, Color.WHITE, Assets.vga);
			g.drawString("XOFF=" + GameCamera.getInstance().getXOffset() + " YOFF=" + GameCamera.getInstance().getYOffset(), 5, 70, false, Color.WHITE, Assets.vga);
		}
	}

	public void renderLayer(Tile[][] tiles, int yStart, int yEnd, int xStart, int xEnd) {
		for (int y = yStart; y < yEnd; y++) {
			for (int x = xStart; x < xEnd; x++) {
				//tiles[x][y].render(g, x, y);
				renderTile(tiles[x][y], x, y);
			}
		}
	}

	public void renderWater(int x, int y, int width, int height, int xOff, int yOff) {
		for(int i = y; i < height; i++) {
			for(int j = x; j < width; j++) {
				i = i * 32; // TODO - xOff
				j = j * 32; // not working

				waterTile.render(g, i, j);
			}
		}
	}

	public void renderTile(Tile tile, int x, int y) {
		x = x * Tile.TILE_WIDTH - cam.getXOffset();
		y = y * Tile.TILE_WIDTH - cam.getYOffset();

		tile.render(g, x, y);
	}

	public Tile getTile(int x, int y) {
		return wallTiles[x][y];
	}

	private void loadWorld(String path) {
		MapLoader mapLoader = new MapLoader();
		Map map = mapLoader.loadWorld(path);

		this.width = map.getWidth();
		this.height = map.getHeight();
		this.groundTiles = map.getGroundLayer();
		this.wallTiles = map.getWallLayer();


		audio.start();

		waterTile = new AnimatedTile(0, Assets.watera, 32, 32, false);


	}

	private void loadSaveFile(String path) {
		Gson gson = new Gson();
		String info = Utils.loadFileAsString("worlds/info.json");
		PlayerInfo playerInfo = gson.fromJson(info, PlayerInfo.class);

		spawnX = playerInfo.getPlayer1SpawnX();
		spawnY = playerInfo.getPlayer1SpawnY();
		spawnX2 = playerInfo.getPlayer2SpawnX();
		spawnY2 = playerInfo.getPlayer2SpawnY();
		name = playerInfo.getPlayer1Name();
	}

	private Game() {
		hud = new HUD();
		audio = new Audio();
		this.weather = new Weather();
		entityManager = new EntityManager(new Player(spawnX, spawnY), new Player2(spawnX2, spawnY2));
		questManager = new QuestManager();
		questManager.setActive(false);
		eventManager = new EventManager();
		cam = GameCamera.getInstance();
		itemManager = new ItemManager();
		eventManager.addEvent(new WarpEvent(10, 10, 20, 20));

		entityManager.addEntity(new Gate1(5, 5));
		entityManager.addEntity(new Roof(11, 9));

		addEntities();
		loadWorld("worlds/world.tmx");
		setSpawnPoints();

		entityManager.getPlayer().setX(5 * 32);
		entityManager.getPlayer().setY(5 * 32);
	}

	private void addEntities() {
		Gson gson = new Gson();
		List<TmpEntity> entities;
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
	}

	private void setSpawnPoints() {
		entityManager.getPlayer().setX(spawnX * entityManager.getPlayer().getWidth());
		entityManager.getPlayer().setY(spawnY * entityManager.getPlayer().getWidth());

		entityManager.getPlayer2().setX(spawnX2 * entityManager.getPlayer2().getWidth());
		entityManager.getPlayer2().setY(spawnY2 * entityManager.getPlayer2().getWidth());
	}

	public static Game getInstance() {
		if(null == instance) {
			instance = new Game();
		}

		return instance;
	}
}
