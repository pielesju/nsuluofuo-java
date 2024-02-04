package org.cofezuwo.nsuluofuo.statics;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import org.cofezuwo.nsuluofuo.creatures.Creature;
import org.cofezuwo.nsuluofuo.graphics.ATG;
import org.cofezuwo.nsuluofuo.graphics.Assets;
import org.cofezuwo.nsuluofuo.graphics.GameCamera;


public class Tree extends StaticEntity{
	

	private Random random = new Random();
	private Image[] textures = new Image[2];
	private Image texture;

	public Tree(int x, int y, int width, int height) {
		super(x, y, Creature.DEFAULT_CREATURE_WIDTH, 2* Creature.DEFAULT_CREATURE_HEIGHT);
		this.textures[0] = Assets.tree;
		this.textures[1] = Assets.tree2;
		texture = textures[random.nextInt(2)];
		bounds[0].x = 12;
		bounds[0].y = 50;
		bounds[0].width = 6;
		bounds[0].height = 6;
	}

	@Override
	public void update() {
		if(getHealth() < 10000){
			setHealth(10000);
		}
		
	}

	@Override
	public void render(ATG g) {
		g.drawImage(texture, (int) (this.x - GameCamera.getInstance().getXOffset()), (int) (y - GameCamera.getInstance().getYOffset()));
		
	}

}
