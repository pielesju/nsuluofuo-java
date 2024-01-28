package org.cofezuwo.nsuluofuo.graphics;

import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Assets {

	private static final int WIDTH = 32;
	private static final int HEIGHT = 32;

	/* tiles */
	public static BufferedImage grass;
	public static BufferedImage highGrass;
	public static BufferedImage tree;
	public static BufferedImage tree2;
	public static BufferedImage ganja;
	public static BufferedImage sGanja;
	public static BufferedImage rGanja;
	public static BufferedImage water;
	public static BufferedImage bridge;
	public static BufferedImage largeRock;
	public static BufferedImage rock;
	public static BufferedImage path;
	public static BufferedImage stone;
	public static BufferedImage stoneBright;
	public static BufferedImage gate;
	public static BufferedImage middleWall;
	public static BufferedImage mossyWall;
	public static BufferedImage mossyWallLeft;
	public static BufferedImage mossyWallRight;
	public static BufferedImage leftWall;
	public static BufferedImage rightWall;
	public static BufferedImage cliffDown;
	public static BufferedImage cliffUp;
	public static BufferedImage cliffLeft;
	public static BufferedImage cliffRight;
	public static BufferedImage leftUpperCorner;
	public static BufferedImage leftBottomCorner;
	public static BufferedImage rightUpperCorner;
	public static BufferedImage rightBottomCorner;

	/* water tile animations */
	public static BufferedImage[] watera;

	/* player */
	public static BufferedImage playerDown;
	public static BufferedImage playerUp;
	public static BufferedImage playerLeft;
	public static BufferedImage playerRight;

	/* player animation */
	public static BufferedImage[] player_down;
	public static BufferedImage[] player_up;
	public static BufferedImage[] player_left;
	public static BufferedImage[] player_right;

	/* malenica */
	public static BufferedImage iMDown;
	public static BufferedImage iMUp;
	public static BufferedImage iMLeft;
	public static BufferedImage iMRight;

	/* malenica animations */
	public static BufferedImage[] ivo_down;
	public static BufferedImage[] ivo_left;
	public static BufferedImage[] ivo_right;
	public static BufferedImage[] ivo_up;

	/* male npc */
	public static BufferedImage mNpcUp;
	public static BufferedImage mNpcDown;
	public static BufferedImage mNpcLeft;
	public static BufferedImage mNpcRight;

	/* female npc */
	public static BufferedImage fNpcUp;
	public static BufferedImage fNpcDown;
	public static BufferedImage fNpcLeft;
	public static BufferedImage fNpcRight;

	/* health indicator icons */
	public static BufferedImage heart;
	public static BufferedImage hheart;

	/* items */
	public static BufferedImage trivel;
	public static BufferedImage money;
	public static BufferedImage triviartefakt;
	public static BufferedImage dsword;
	public static BufferedImage msword;
	public static BufferedImage tsword;
	public static BufferedImage jibbet;
	public static BufferedImage monster;

	/* gui animated buttons */
	public static BufferedImage[] start_btn;
	public static BufferedImage[] close_button;
	public static BufferedImage[] settings_button;

	/* fonts */
	public static Font head;
	public static Font text;
	public static Font smallText;

	/* large pictures */
	public static BufferedImage background;
	public static BufferedImage gameO;
	public static BufferedImage ivo;
	public static BufferedImage thc;
	public static BufferedImage ivof;

	public static final String SAO_FONT_FILE = "fonts/saoFont.ttf";

	private Assets() {

	}

	public static void initialize() {



		head = FontLoader.loadFont(SAO_FONT_FILE, 128);
		text = FontLoader.loadFont(SAO_FONT_FILE, 64);
		smallText = FontLoader.loadFont(SAO_FONT_FILE, 24);

		BufferedImage player = ImageLoader.loadImage("/textures/Player.png");
		BufferedImage npc = ImageLoader.loadImage("/textures/NPC.png");
		BufferedImage terrain = ImageLoader.loadImage("/textures/terrain.png");
		BufferedImage items = ImageLoader.loadImage("/textures/Items.png");
		BufferedImage gui = ImageLoader.loadImage("/textures/GUI.png");
		BufferedImage uli = ImageLoader.loadImage("/textures/uli.jpg");
		BufferedImage thct = ImageLoader.loadImage("/textures/THC Schriftzug.png");
		BufferedImage health = ImageLoader.loadImage("/textures/health.png");
		BufferedImage mal = ImageLoader.loadImage("/textures/Cofezuwo.png");
		BufferedImage aWater = ImageLoader.loadImage("/textures/Water.png");
		BufferedImage ivoM = ImageLoader.loadImage("/textures/mmm.jpg");

		/* big pictures */

		ivo = uli.getSubimage(0, 0, 640, 480);
		thc = thct.getSubimage(0,0, 100, 80);
		ivof = ivoM.getSubimage(0, 0, 120, 120);

		/* Health*/

		heart = health.getSubimage(0, 0, 16, 16);
		hheart = health.getSubimage(16,0,16,16);

		/* GUI */
		start_btn = new BufferedImage[2];
		start_btn[0] = gui.getSubimage(0, 0, 4 * WIDTH, 2 * HEIGHT);
		start_btn[1] = gui.getSubimage(0, 2 * HEIGHT, 4 * WIDTH, 2 * HEIGHT);

		close_button = new BufferedImage[2];
		close_button[0] = gui.getSubimage(0, 4 * HEIGHT, 4 * WIDTH, 2 * HEIGHT);
		close_button[1] = gui.getSubimage(0, 6 * HEIGHT, 4 * WIDTH, 2 * HEIGHT);

		settings_button = new BufferedImage[2];
		settings_button[0] = gui.getSubimage(0, 8 * HEIGHT, 4 * WIDTH, 2 * HEIGHT);
		settings_button[1] = gui.getSubimage(0, 10 * HEIGHT, 4 * WIDTH, 2 * HEIGHT);

		/* Animations */
		player_down = new BufferedImage[2];
		player_down[0] = player.getSubimage(0, 0, WIDTH, HEIGHT);
		player_down[1] = player.getSubimage(2 * WIDTH, 0, WIDTH, HEIGHT);

		ivo_down = new BufferedImage[2];
		ivo_down[0] = mal.getSubimage(0, 0, WIDTH, HEIGHT);
		ivo_down[1] = mal.getSubimage(2 * WIDTH, 0, WIDTH, HEIGHT);

		player_up = new BufferedImage[2];
		player_up[0] = player.getSubimage(0, 3 * HEIGHT, WIDTH, HEIGHT);
		player_up[1] = player.getSubimage(2 * WIDTH, 3 * HEIGHT, WIDTH, HEIGHT);

		ivo_up = new BufferedImage[2];
		ivo_up[0] = mal.getSubimage(0, 3 * HEIGHT, WIDTH, HEIGHT);
		ivo_up[1] = mal.getSubimage(2 * WIDTH, 3 * HEIGHT, WIDTH, HEIGHT);

		player_left = new BufferedImage[2];
		player_left[0] = player.getSubimage(0, HEIGHT, WIDTH, HEIGHT);
		player_left[1] = player.getSubimage(2 * WIDTH, HEIGHT, WIDTH, HEIGHT);

		ivo_left = new BufferedImage[2];
		ivo_left[0] = mal.getSubimage(0, HEIGHT, WIDTH, HEIGHT);
		ivo_left[1] = mal.getSubimage(2 * WIDTH, HEIGHT, WIDTH, HEIGHT);

		player_right = new BufferedImage[2];
		player_right[0] = player.getSubimage(0, 2 * HEIGHT, WIDTH, HEIGHT);
		player_right[1] = player.getSubimage(2 * WIDTH, 2 * HEIGHT, WIDTH, HEIGHT);

		ivo_right = new BufferedImage[2];
		ivo_right[0] = mal.getSubimage(0, 2 * HEIGHT, WIDTH, HEIGHT);
		ivo_right[1] = mal.getSubimage(2 * WIDTH, 2 * HEIGHT, WIDTH, HEIGHT);

		watera = new BufferedImage[32];
		watera[0] = aWater.getSubimage(0, 0, WIDTH, HEIGHT);
		watera[1] = aWater.getSubimage(0, HEIGHT, WIDTH, HEIGHT);
		watera[2] = aWater.getSubimage(0, 2*HEIGHT, WIDTH, HEIGHT);
		watera[3] = aWater.getSubimage(0, 3*HEIGHT, WIDTH, HEIGHT);
		watera[4] = aWater.getSubimage(0, 4*HEIGHT, WIDTH, HEIGHT);
		watera[5] = aWater.getSubimage(0, 5*HEIGHT, WIDTH, HEIGHT);
		watera[6] = aWater.getSubimage(0, 6*HEIGHT, WIDTH, HEIGHT);
		watera[7] = aWater.getSubimage(0, 7*HEIGHT, WIDTH, HEIGHT);
		watera[8] = aWater.getSubimage(0, 8*HEIGHT, WIDTH, HEIGHT);
		watera[9] = aWater.getSubimage(0, 9*HEIGHT, WIDTH, HEIGHT);
		watera[10] = aWater.getSubimage(0, 10*HEIGHT, WIDTH, HEIGHT);
		watera[11] = aWater.getSubimage(0,11*HEIGHT, WIDTH, HEIGHT);
		watera[12] = aWater.getSubimage(0, 12*HEIGHT, WIDTH, HEIGHT);
		watera[13] = aWater.getSubimage(0, 13*HEIGHT, WIDTH, HEIGHT);
		watera[14] = aWater.getSubimage(0, 14*HEIGHT, WIDTH, HEIGHT);
		watera[15] = aWater.getSubimage(0, 15*HEIGHT, WIDTH, HEIGHT);
		watera[16] = aWater.getSubimage(0, 16*HEIGHT, WIDTH, HEIGHT);
		watera[17] = aWater.getSubimage(0, 17*HEIGHT, WIDTH, HEIGHT);
		watera[18] = aWater.getSubimage(0, 18*HEIGHT, WIDTH, HEIGHT);
		watera[19] = aWater.getSubimage(0, 19*HEIGHT, WIDTH, HEIGHT);
		watera[20] = aWater.getSubimage(0, 20*HEIGHT, WIDTH, HEIGHT);
		watera[21] = aWater.getSubimage(0, 21*HEIGHT, WIDTH, HEIGHT);
		watera[22] = aWater.getSubimage(0, 22*HEIGHT, WIDTH, HEIGHT);
		watera[23] = aWater.getSubimage(0, 23*HEIGHT, WIDTH, HEIGHT);
		watera[24] = aWater.getSubimage(0, 24*HEIGHT, WIDTH, HEIGHT);
		watera[25] = aWater.getSubimage(0, 25*HEIGHT, WIDTH, HEIGHT);
		watera[26] = aWater.getSubimage(0, 26*HEIGHT, WIDTH, HEIGHT);
		watera[27] = aWater.getSubimage(0, 27*HEIGHT, WIDTH, HEIGHT);
		watera[28] = aWater.getSubimage(0, 28*HEIGHT, WIDTH, HEIGHT);
		watera[29] = aWater.getSubimage(0, 29*HEIGHT, WIDTH, HEIGHT);
		watera[30] = aWater.getSubimage(0, 30*HEIGHT, WIDTH, HEIGHT);
		watera[31] = aWater.getSubimage(0, 31*HEIGHT, WIDTH, HEIGHT);

		/* Items */

		trivel = items.getSubimage(0, 0, WIDTH, HEIGHT);
		money = items.getSubimage(0, HEIGHT, WIDTH, HEIGHT);
		triviartefakt = items.getSubimage(0, 2* HEIGHT, WIDTH, HEIGHT);
		dsword = items.getSubimage(WIDTH, 0, WIDTH, HEIGHT);
		msword = items.getSubimage(WIDTH, HEIGHT, WIDTH, HEIGHT);
		tsword = items.getSubimage(WIDTH, 2 * HEIGHT, WIDTH, HEIGHT);
		jibbet = items.getSubimage(2* WIDTH, 0, WIDTH, HEIGHT);
		monster = items.getSubimage(2* WIDTH, HEIGHT, WIDTH, HEIGHT);


		/* Entities */

		tree = terrain.getSubimage(2 * WIDTH, 0, WIDTH, 2 * HEIGHT);
		tree2 = terrain.getSubimage(0, 4*HEIGHT, WIDTH, 2*HEIGHT);
		ganja = terrain.getSubimage(2*WIDTH, 2*HEIGHT, WIDTH, 2*HEIGHT);
		sGanja = terrain.getSubimage(WIDTH, 2*HEIGHT, WIDTH, 2*HEIGHT);
		rGanja = terrain.getSubimage(0, 2*HEIGHT, WIDTH, 2*HEIGHT);
		rock = terrain.getSubimage(3 * WIDTH, 0, WIDTH, HEIGHT);
		largeRock = terrain.getSubimage(3 * WIDTH, 2 * HEIGHT, WIDTH, 2 * HEIGHT);

		/* Player */
		playerDown = player.getSubimage(WIDTH, 0, WIDTH, HEIGHT);
		playerLeft = player.getSubimage(WIDTH, 1 * HEIGHT, WIDTH, HEIGHT);
		playerRight = player.getSubimage(WIDTH, 2 * HEIGHT, WIDTH, HEIGHT);
		playerUp = player.getSubimage(WIDTH, 3 * HEIGHT, WIDTH, HEIGHT);

		/* NPC's */

		// female
		fNpcDown = npc.getSubimage(0, 0, WIDTH, HEIGHT);
		fNpcLeft = npc.getSubimage(0, HEIGHT, WIDTH, HEIGHT);
		fNpcRight = npc.getSubimage(0, 2 * HEIGHT, WIDTH, HEIGHT);
		fNpcUp = npc.getSubimage(0, 3 * HEIGHT, WIDTH, HEIGHT);

		// male
		mNpcDown = npc.getSubimage(WIDTH, 0, WIDTH, HEIGHT);
		mNpcLeft = npc.getSubimage(WIDTH, HEIGHT, WIDTH, HEIGHT);
		mNpcRight = npc.getSubimage(WIDTH, 2 * HEIGHT, WIDTH, HEIGHT);
		mNpcUp = npc.getSubimage(WIDTH, 3 * HEIGHT, WIDTH, HEIGHT);

		// Ivo Malenica

		iMDown = mal.getSubimage(WIDTH, 0, WIDTH, HEIGHT);
		iMLeft = mal.getSubimage(WIDTH, 1 * HEIGHT, WIDTH, HEIGHT);
		iMRight = mal.getSubimage(WIDTH, 2 * HEIGHT, WIDTH, HEIGHT);
		iMUp = mal.getSubimage(WIDTH, 3 * HEIGHT, WIDTH, HEIGHT);

		/* Tiles */
		grass = terrain.getSubimage(0, 0, WIDTH, HEIGHT);
		highGrass = terrain.getSubimage(WIDTH, 0, WIDTH, HEIGHT);
		water = terrain.getSubimage(0, HEIGHT, WIDTH, HEIGHT);
		bridge = terrain.getSubimage(0, HEIGHT, WIDTH, HEIGHT);
		path = terrain.getSubimage(WIDTH, HEIGHT, WIDTH, HEIGHT);
		stone = terrain.getSubimage(3 * WIDTH, HEIGHT, WIDTH, HEIGHT);
		stoneBright = terrain.getSubimage(7 * WIDTH, HEIGHT, WIDTH, HEIGHT);
		gate = terrain.getSubimage(7 * WIDTH, 2 * HEIGHT, WIDTH, HEIGHT);
		middleWall = terrain.getSubimage(6 * WIDTH, 0, WIDTH, HEIGHT);
		cliffDown = terrain.getSubimage(6 * WIDTH, 2 * HEIGHT, WIDTH, HEIGHT);
		cliffUp = terrain.getSubimage(6 * WIDTH, HEIGHT, WIDTH, HEIGHT);
		cliffLeft = terrain.getSubimage(6 * WIDTH, 3 * HEIGHT, WIDTH, HEIGHT);
		cliffRight = terrain.getSubimage(6 * WIDTH, 4 * HEIGHT, WIDTH, HEIGHT);
		leftUpperCorner = terrain.getSubimage(4 * WIDTH, 0, WIDTH, HEIGHT);
		leftBottomCorner = terrain.getSubimage(4 * WIDTH, HEIGHT, WIDTH, HEIGHT);
		rightUpperCorner = terrain.getSubimage(5 * WIDTH, 0, WIDTH, HEIGHT);
		rightBottomCorner = terrain.getSubimage(5 * WIDTH, HEIGHT, WIDTH, HEIGHT);
		mossyWall = terrain.getSubimage(7 * WIDTH, 0, WIDTH, HEIGHT);
		mossyWallLeft = terrain.getSubimage(4 * WIDTH, 4 * HEIGHT, WIDTH, HEIGHT);
		mossyWallRight = terrain.getSubimage(5 * WIDTH, 4 * HEIGHT, WIDTH, HEIGHT);
		leftWall = terrain.getSubimage(4 * WIDTH, 2 * HEIGHT, WIDTH, HEIGHT);
		rightWall = terrain.getSubimage(5 * WIDTH, 2 * HEIGHT, WIDTH, HEIGHT);

	}

}