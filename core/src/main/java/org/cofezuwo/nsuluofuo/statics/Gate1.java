package org.cofezuwo.nsuluofuo.statics;

import org.cofezuwo.nsuluofuo.creatures.Creature;
import org.cofezuwo.nsuluofuo.graphics.ATG;
import org.cofezuwo.nsuluofuo.graphics.Assets;
import org.cofezuwo.nsuluofuo.graphics.GameCamera;

import java.awt.*;
import java.util.Random;

public class Gate1 extends StaticEntity{

	public Gate1(int x, int y) {
		super(x, y, 3 * Creature.DEFAULT_CREATURE_WIDTH, 2* Creature.DEFAULT_CREATURE_HEIGHT);

		bounds[0].x = 0;
		bounds[0].y = 52;
		bounds[0].width = 6;
		bounds[0].height = 12;

		bounds[1] = new Rectangle();

		bounds[1].x = 90;
		bounds[1].y = 52;
		bounds[1].width = 6;
		bounds[1].height = 12;
	}

	@Override
	public void update() {
		if(getHealth() < 10000){
			setHealth(10000);
		}
		
	}

	@Override
	public void render(ATG g) {
		g.drawImage(Assets.gate1, x - GameCamera.getInstance().getXOffset(), y - GameCamera.getInstance().getYOffset());
	}

}
