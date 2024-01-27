package org.cofezuwo.nsuluofuo.statics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import org.cofezuwo.nsuluofuo.creatures.Creature;
import org.cofezuwo.nsuluofuo.graphics.Assets;
import org.cofezuwo.nsuluofuo.graphics.GameCamera;

public class Ganja extends StaticEntity{

	private Random random = new Random();
	private BufferedImage[] textures = new BufferedImage[3];
	private BufferedImage texture;

	public Ganja(int x, int y, int width, int height) {
		super(x, y, Creature.DEFAULT_CREATURE_WIDTH, 2* Creature.DEFAULT_CREATURE_HEIGHT);

		bounds.x = 15;
		bounds.y = 52;
		bounds.width = 2;
		bounds.height = 2;
		this.textures[0] = Assets.ganja;
		this.textures[1] = Assets.sGanja;
		this.textures[2] = Assets.rGanja;
		texture = textures[random.nextInt(3)];
		
	}

	@Override
	public void update() {
		if(getHealth() < 10000){
			setHealth(10000);
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(texture, (int) (x - GameCamera.getInstance().getxOffset()), (int) (y - GameCamera.getInstance().getyOffset()), null);
	}

}
