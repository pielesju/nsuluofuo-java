package org.cofezuwo.nsuluofuo.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import lombok.Getter;
import org.cofezuwo.nsuluofuo.creatures.NPC;
import org.cofezuwo.nsuluofuo.creatures.Player;
import org.cofezuwo.nsuluofuo.creatures.Player2;

public class EntityManager {
	private Player player;
	private Player2 player2;
	private List<Entity> entities;
	private Comparator<Entity> renderSorter = new Comparator<Entity>() {
		public int compare(Entity a, Entity b) {
			if (a.getY() + a.getHeight() < b.getY() + b.getHeight()) {
				return -1;
			}
			return 1;
		}
	};

	public EntityManager(Player player, Player2 player2) {
		this.player = player;
		this.player2 = player2;
		entities = new ArrayList<>();
		addEntity(player);
		addEntity(player2);
		
	}

	public void update() {
		for(Entity e: entities) {
			e.update();
		}

		entities.sort(renderSorter);
	}

	public void render(Graphics g) {
		for (Entity e : entities) {
			e.render(g);
		}

		player.postRender(g);
		
	}

	public void addEntity(Entity e) {
		entities.add(e);
	}



	public Player getPlayer() {
		return player;
	}

	public List<Entity> getEntities() {
		return entities;
	}



	public void setPlayer(Player player) {
		this.player = player;
	}

	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}

	public Player2 getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player2 player2) {
		this.player2 = player2;
	}
	
	

}
