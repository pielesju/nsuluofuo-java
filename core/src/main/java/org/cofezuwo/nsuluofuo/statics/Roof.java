package org.cofezuwo.nsuluofuo.statics;

import org.cofezuwo.nsuluofuo.creatures.Creature;
import org.cofezuwo.nsuluofuo.creatures.Player;
import org.cofezuwo.nsuluofuo.graphics.ATG;
import org.cofezuwo.nsuluofuo.graphics.Assets;
import org.cofezuwo.nsuluofuo.graphics.GameCamera;
import org.cofezuwo.nsuluofuo.main.Game;

import java.awt.*;
import java.util.Random;

public class Roof extends StaticEntity{

	private Rectangle space;
	private boolean visible;

	public Roof(int x, int y) {
		super(x, y, 6 * Creature.DEFAULT_CREATURE_WIDTH, 8 * Creature.DEFAULT_CREATURE_HEIGHT);

		bounds[0].x = 0;
		bounds[0].y = 0;
		bounds[0].width = 0;
		bounds[0].height = 0;

		space = new Rectangle(x - GameCamera.getInstance().getXOffset(), y - GameCamera.getInstance().getYOffset(), 192, 256);
		visible = true;

	}

	@Override
	public void update() {
		/*Rectangle cb1 = Game.getInstance().getEntityManager().getPlayer().getCollisionBounds(GameCamera.getInstance().getXOffset(), GameCamera.getInstance().getYOffset())[0];
		Rectangle cb2 = Game.getInstance().getEntityManager().getPlayer().getCollisionBounds(0, 0)[0];

		System.out.printf("OFFSET: %d %d %d %d\n", cb1.x / 32, cb1.y / 32, cb1.width, cb1.width);
		System.out.printf("0: %d %d %d %d\n", cb2.x / 32, cb2.y / 32, cb2.width, cb2.width);

		if(Game.getInstance().getEntityManager().getPlayer().getCollisionBounds(GameCamera.getInstance().getXOffset(), GameCamera.getInstance().getYOffset())[0].intersects(this.space)) {
			this.visible = false;
		} else {
			this.visible = true;
		}*/
		Player p = Game.getInstance().getEntityManager().getPlayer();

		if
		(
				p.getX() >= this.x &&
						p.getY() >= this.y &&
						p.getX() <= (this.x + this.getWidth() - 32) &&
						p.getY() <= (this.y + this.getHeight() - 32)
		)
		{
			this.visible = false;
		} else {
			this.visible = true;
		}
	}

	@Override
	public void render(ATG g) {
		if(!visible) return;
		g.drawImage(Assets.roof, (int) (x - GameCamera.getInstance().getXOffset()), (int) (y - GameCamera.getInstance().getYOffset()));
	}

}
