package org.cofezuwo.nsuluofuo.main;

import lombok.Getter;
import org.cofezuwo.nsuluofuo.graphics.AbstractTrivialGraphics;
import org.cofezuwo.nsuluofuo.graphics.Assets;
import org.cofezuwo.nsuluofuo.graphics.GameCamera;
import org.cofezuwo.nsuluofuo.inventory.Dialog;
import org.cofezuwo.nsuluofuo.worlds.World;

public class GameMode {


	private final World world;

	@Getter
	private final GameCamera gameCamera;


	public GameMode() {


		world = World.getInstance();

		this.gameCamera = GameCamera.getInstance();
		
	}

	public void update() {
		world.update();
	}

	public void render(AbstractTrivialGraphics g) {
		world.render(g);

		renderHealth(g, world.getEntityManager().getPlayer().getHealth());

		Dialog.render(g);
	}

	private void renderHealth(AbstractTrivialGraphics g, int health) {
		if(health <= 0) return;

		int fullHearts = health / 2;
		int halfHeart = health % 2;

		for (int i = 0; i < fullHearts; i++) {
			g.drawImage(Assets.heart, 5 + 16 * i, 5);
		}

		if (halfHeart == 1) {
			g.drawImage(Assets.hheart, 5 + 16 * fullHearts, 5);
		}
	}

}
