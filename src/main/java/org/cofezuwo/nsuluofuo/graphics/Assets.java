package org.cofezuwo.nsuluofuo.graphics;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {

	private static final int width = 32, height = 32;
	public static BufferedImage grass, highGrass, tree, tree2,ganja, sGanja, rGanja, water, bridge, largeRock, rock, path, stone, stoneBright, gate,
			middleWall, mossyWall, mossyWallLeft, mossyWallRight, leftWall, rightWall, cliffDown, cliffUp, cliffLeft,
			cliffRight, leftUpperCorner, leftBottomCorner, rightUpperCorner, rightBottomCorner;

	public static BufferedImage playerDown, playerUp, playerLeft, playerRight;
	public static BufferedImage iMDown, iMUp, iMLeft, iMRight;
	public static BufferedImage mNpcUp, mNpcDown, mNpcLeft, mNpcRight, fNpcUp, fNpcDown, fNpcLeft, fNpcRight;
	
	public static BufferedImage heart, hheart;

	public static BufferedImage trivel, money, triviartefakt, dsword, msword, tsword, jibbet, monster;
	public static BufferedImage[] start_btn, close_button, settings_button, player_down, player_up, player_left,
			player_right, ivo_down, ivo_left, ivo_right, ivo_up, watera;
	public static Font head, text, smallText;
	public static BufferedImage background;
	public static BufferedImage gameO;
	public static BufferedImage ivo;
	public static BufferedImage thc;
	public static BufferedImage ivof;

	public static void initialize() {

		head = FontLoader.loadFont("/fonts/saoFont.ttf", 128);
		text = FontLoader.loadFont("/fonts/saoFont.ttf", 64);
		smallText = FontLoader.loadFont("/fonts/saoFont.ttf", 24);
		
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
		start_btn[0] = gui.getSubimage(0, 0, 4 * width, 2 * height);
		start_btn[1] = gui.getSubimage(0, 2 * height, 4 * width, 2 * height);

		close_button = new BufferedImage[2];
		close_button[0] = gui.getSubimage(0, 4 * height, 4 * width, 2 * height);
		close_button[1] = gui.getSubimage(0, 6 * height, 4 * width, 2 * height);

		settings_button = new BufferedImage[2];
		settings_button[0] = gui.getSubimage(0, 8 * height, 4 * width, 2 * height);
		settings_button[1] = gui.getSubimage(0, 10 * height, 4 * width, 2 * height);

		/* Animations */
		player_down = new BufferedImage[2];
		player_down[0] = player.getSubimage(0, 0, width, height);
		player_down[1] = player.getSubimage(2 * width, 0, width, height);

		ivo_down = new BufferedImage[2];
		ivo_down[0] = mal.getSubimage(0, 0, width, height);
		ivo_down[1] = mal.getSubimage(2 * width, 0, width, height);

		player_up = new BufferedImage[2];
		player_up[0] = player.getSubimage(0, 3 * height, width, height);
		player_up[1] = player.getSubimage(2 * width, 3 * height, width, height);
		
		ivo_up = new BufferedImage[2];
		ivo_up[0] = mal.getSubimage(0, 3 * height, width, height);
		ivo_up[1] = mal.getSubimage(2 * width, 3 * height, width, height);

		player_left = new BufferedImage[2];
		player_left[0] = player.getSubimage(0, height, width, height);
		player_left[1] = player.getSubimage(2 * width, height, width, height); 
		
		ivo_left = new BufferedImage[2];
		ivo_left[0] = mal.getSubimage(0, height, width, height);
		ivo_left[1] = mal.getSubimage(2 * width, height, width, height); 

		player_right = new BufferedImage[2];
		player_right[0] = player.getSubimage(0, 2 * height, width, height);
		player_right[1] = player.getSubimage(2 * width, 2 * height, width, height);
		
		ivo_right = new BufferedImage[2];
		ivo_right[0] = mal.getSubimage(0, 2 * height, width, height);
		ivo_right[1] = mal.getSubimage(2 * width, 2 * height, width, height);
		
		watera = new BufferedImage[32];
		watera[0] = aWater.getSubimage(0, 0, width, height);
		watera[1] = aWater.getSubimage(0, height, width, height);
		watera[2] = aWater.getSubimage(0, 2*height, width, height);
		watera[3] = aWater.getSubimage(0, 3*height, width, height);
		watera[4] = aWater.getSubimage(0, 4*height, width, height);
		watera[5] = aWater.getSubimage(0, 5*height, width, height);
		watera[6] = aWater.getSubimage(0, 6*height, width, height);
		watera[7] = aWater.getSubimage(0, 7*height, width, height);
		watera[8] = aWater.getSubimage(0, 8*height, width, height);
		watera[9] = aWater.getSubimage(0, 9*height, width, height);
		watera[10] = aWater.getSubimage(0, 10*height, width, height);
		watera[11] = aWater.getSubimage(0,11*height, width, height);
		watera[12] = aWater.getSubimage(0, 12*height, width, height);
		watera[13] = aWater.getSubimage(0, 13*height, width, height);
		watera[14] = aWater.getSubimage(0, 14*height, width, height);
		watera[15] = aWater.getSubimage(0, 15*height, width, height);
		watera[16] = aWater.getSubimage(0, 16*height, width, height);
		watera[17] = aWater.getSubimage(0, 17*height, width, height);
		watera[18] = aWater.getSubimage(0, 18*height, width, height);
		watera[19] = aWater.getSubimage(0, 19*height, width, height);
		watera[20] = aWater.getSubimage(0, 20*height, width, height);
		watera[21] = aWater.getSubimage(0, 21*height, width, height);
		watera[22] = aWater.getSubimage(0, 22*height, width, height);
		watera[23] = aWater.getSubimage(0, 23*height, width, height);
		watera[24] = aWater.getSubimage(0, 24*height, width, height);
		watera[25] = aWater.getSubimage(0, 25*height, width, height);
		watera[26] = aWater.getSubimage(0, 26*height, width, height);
		watera[27] = aWater.getSubimage(0, 27*height, width, height);
		watera[28] = aWater.getSubimage(0, 28*height, width, height);
		watera[29] = aWater.getSubimage(0, 29*height, width, height);
		watera[30] = aWater.getSubimage(0, 30*height, width, height);
		watera[31] = aWater.getSubimage(0, 31*height, width, height);

		/* Items */
		
		trivel = items.getSubimage(0, 0, width, height);
		money = items.getSubimage(0, height, width, height);
		triviartefakt = items.getSubimage(0, 2* height, width, height);
		dsword = items.getSubimage(width, 0, width, height);
		msword = items.getSubimage(width, height, width, height);
		tsword = items.getSubimage(width, 2 * height, width, height);
		jibbet = items.getSubimage(2* width, 0, width, height);
		monster = items.getSubimage(2* width, height, width, height);
		

		/* Entities */

		tree = terrain.getSubimage(2 * width, 0, width, 2 * height);
		tree2 = terrain.getSubimage(0, 4*height, width, 2*height);
		ganja = terrain.getSubimage(2*width, 2*height, width, 2*height);
		sGanja = terrain.getSubimage(width, 2*height, width, 2*height);
		rGanja = terrain.getSubimage(0, 2*height, width, 2*height);
		rock = terrain.getSubimage(3 * width, 0, width, height);
		largeRock = terrain.getSubimage(3 * width, 2 * height, width, 2 * height);

		/* Player */
		playerDown = player.getSubimage(width, 0, width, height);
		playerLeft = player.getSubimage(width, 1 * height, width, height);
		playerRight = player.getSubimage(width, 2 * height, width, height);
		playerUp = player.getSubimage(width, 3 * height, width, height);

		/* NPC's */

		// female
		fNpcDown = npc.getSubimage(0, 0, width, height);
		fNpcLeft = npc.getSubimage(0, height, width, height);
		fNpcRight = npc.getSubimage(0, 2 * height, width, height);
		fNpcUp = npc.getSubimage(0, 3 * height, width, height);

		// male
		mNpcDown = npc.getSubimage(width, 0, width, height);
		mNpcLeft = npc.getSubimage(width, height, width, height);
		mNpcRight = npc.getSubimage(width, 2 * height, width, height);
		mNpcUp = npc.getSubimage(width, 3 * height, width, height);
		
		// Ivo Malenica
		
		iMDown = mal.getSubimage(width, 0, width, height);
		iMLeft = mal.getSubimage(width, 1 * height, width, height);
		iMRight = mal.getSubimage(width, 2 * height, width, height);
		iMUp = mal.getSubimage(width, 3 * height, width, height);

		/* Tiles */
		grass = terrain.getSubimage(0, 0, width, height);
		highGrass = terrain.getSubimage(width, 0, width, height);
		water = terrain.getSubimage(0, height, width, height);
		bridge = terrain.getSubimage(0, height, width, height);
		path = terrain.getSubimage(width, height, width, height);
		stone = terrain.getSubimage(3 * width, height, width, height);
		stoneBright = terrain.getSubimage(7 * width, height, width, height);
		gate = terrain.getSubimage(7 * width, 2 * height, width, height);
		middleWall = terrain.getSubimage(6 * width, 0, width, height);
		cliffDown = terrain.getSubimage(6 * width, 2 * height, width, height);
		cliffUp = terrain.getSubimage(6 * width, height, width, height);
		cliffLeft = terrain.getSubimage(6 * width, 3 * height, width, height);
		cliffRight = terrain.getSubimage(6 * width, 4 * height, width, height);
		leftUpperCorner = terrain.getSubimage(4 * width, 0, width, height);
		leftBottomCorner = terrain.getSubimage(4 * width, height, width, height);
		rightUpperCorner = terrain.getSubimage(5 * width, 0, width, height);
		rightBottomCorner = terrain.getSubimage(5 * width, height, width, height);
		mossyWall = terrain.getSubimage(7 * width, 0, width, height);
		mossyWallLeft = terrain.getSubimage(4 * width, 4 * height, width, height);
		mossyWallRight = terrain.getSubimage(5 * width, 4 * height, width, height);
		leftWall = terrain.getSubimage(4 * width, 2 * height, width, height);
		rightWall = terrain.getSubimage(5 * width, 2 * height, width, height);

	}

}
