package org.cofezuwo.nsuluofuo.entities;

import java.util.*;
import org.cofezuwo.nsuluofuo.creatures.Player;
import org.cofezuwo.nsuluofuo.creatures.Player2;
import org.cofezuwo.nsuluofuo.graphics.ATG;
import org.cofezuwo.nsuluofuo.statics.Roof;

public class EntityManager {
	private Player player;
	private Player2 player2;
	private List<Entity> entities;

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

//		Collections.sort(entities, (e1, e2) -> Integer.compare(e1.getY() + e1.getHeight(), e2.getY() + e2.getHeight()));
		Collections.sort(entities, (e1, e2) -> {
			// Wenn beide Entitäten vom Typ "Roof" sind, dann sind sie gleich
			if (e1 instanceof Roof && e2 instanceof Roof) {
				return 0;
			}

			// Wenn e1 vom Typ "Roof" ist, platzieren Sie es später (größerer Index)
			if (e1 instanceof Roof) {
				return 1;
			}

			// Wenn e2 vom Typ "Roof" ist, platzieren Sie es später (kleinerer Index)
			if (e2 instanceof Roof) {
				return -1;
			}

			// Andernfalls vergleichen Sie die Y-Werte wie zuvor
			return Integer.compare(e1.getY() + e1.getHeight(), e2.getY() + e2.getHeight());
		});
	}

	public void render(ATG g) {
		for (Entity e : entities)
			e.render(g);
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
