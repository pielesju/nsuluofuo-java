package org.cofezuwo.nsuluofuo.statics;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import org.cofezuwo.nsuluofuo.creatures.Creature;
import org.cofezuwo.nsuluofuo.graphics.ATG;
import org.cofezuwo.nsuluofuo.graphics.Assets;
import org.cofezuwo.nsuluofuo.graphics.GameCamera;

public class Ganja extends StaticEntity{

	private Random random = new Random();
	private Image[] textures = new Image[3];
	private Image texture;

	public Ganja(int x, int y) {
		super(x, y, Creature.DEFAULT_CREATURE_WIDTH, 2* Creature.DEFAULT_CREATURE_HEIGHT);

		bounds[0].x = 15;
		bounds[0].y = 52;
		bounds[0].width = 2;
		bounds[0].height = 2;
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
	public void render(ATG g) {
		g.drawImage(texture, (int) (x - GameCamera.getInstance().getXOffset()), (int) (y - GameCamera.getInstance().getYOffset()));
	}

}
