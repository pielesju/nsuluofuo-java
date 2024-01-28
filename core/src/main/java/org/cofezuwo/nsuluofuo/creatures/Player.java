package org.cofezuwo.nsuluofuo.creatures;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import lombok.Getter;
import lombok.Setter;
import org.cofezuwo.nsuluofuo.entities.Entity;
import org.cofezuwo.nsuluofuo.graphics.AbstractTrivialGraphics;
import org.cofezuwo.nsuluofuo.graphics.Animation;
import org.cofezuwo.nsuluofuo.graphics.Assets;
import org.cofezuwo.nsuluofuo.graphics.GameCamera;
import org.cofezuwo.nsuluofuo.input.KeyManager;
import org.cofezuwo.nsuluofuo.inventory.Dialog;
import org.cofezuwo.nsuluofuo.inventory.Inventory;
import org.cofezuwo.nsuluofuo.inventory.PlayerInfo;
import org.cofezuwo.nsuluofuo.inventory.Trivel;
import org.cofezuwo.nsuluofuo.item.Item;
import org.cofezuwo.nsuluofuo.worlds.World;

public class Player extends Creature {

	/* animations */
	@Getter
	@Setter
	private Animation animDown;
	@Getter
	@Setter
	private Animation animUp;
	@Getter
	@Setter
	private Animation animLeft;
	@Getter
	@Setter
	private Animation animRight;

	/* attack variables */
	@Getter
	@Setter
	private long lastAttackTimer;
	@Getter
	@Setter
	private long attackCooldown = 800;
	@Getter
	@Setter
	private long attackTimer = 800;

	/* current sprite */
	@Getter
	@Setter
	private BufferedImage currentPosition = Assets.playerDown;

	@Getter
	@Setter
	private int money = 2000;
	@Getter
	@Setter
	private int maxHealth;
	@Getter
	@Setter
	private int exp;

	private long startTime;

	private int circlesize;

	private boolean expanding = false;

	@Getter
	@Setter
	private int level;
	@Getter
	@Setter
	private int maxExp;
	@Getter
	@Setter
	private String name;
	@Getter
	@Setter
	private Item currentWeapon;
	@Getter
	@Setter
	private Inventory inventory;
	@Getter
	@Setter
	private PlayerInfo info;
	@Getter
	@Setter
	private Trivel trivel;
	@Getter
	@Setter
	private GameCamera cam;

	public Player(int x, int y) {
		super(x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

		info = new PlayerInfo();
		inventory = new Inventory();
		trivel = new Trivel();
		
		currentWeapon = new Item(null, null, 0, 0, 5, 0);
		bounds.x = 8;
		bounds.y = 16;
		bounds.width = 16;
		bounds.height = 16;

		animDown = new Animation(animDown.DEFAULT_SPEED, Assets.player_down);
		animUp = new Animation(animUp.DEFAULT_SPEED, Assets.player_up);
		animLeft = new Animation(animLeft.DEFAULT_SPEED, Assets.player_left);
		animRight = new Animation(animRight.DEFAULT_SPEED, Assets.player_right);

		setMaxExp(100);
		setExp(1);
		setMaxHealth(6);
		setHealth(getMaxHealth());
		setSpeed(DEFAULT_SPEED);
		setStrength(getCurrentWeapon().getAttack());
		setLevel(1);

		this.cam = GameCamera.getInstance();
	}

	@Override
	public void update() {
		
		getInput();
		move();
		animDown.update();
		animUp.update();
		animLeft.update();
		animRight.update();
		
		cam.centerOnEntity(this);
		checkAttack();
		checkEntity();

		info.update();
		inventory.update();
		trivel.update();
		
		//Positionsdaten den Multiplayerklassen geben
		//SimpleDualPlayer.getPlayerL().setPositionX(getX());
		//SimpleDualPlayer.getPlayerL().setPositionY(getY());
		//SimpleDualPlayer.getPlayerL().setName(getName());

	}

	public void checkAttack() {

		if (inventory.isActive() || info.isActive() || trivel.isActive() || Dialog.isActive()) {
			return;
		}

		Rectangle attackRectangle = new Rectangle((int) (getX() - 20), (int) (getY() - 20), (int) (getWidth()) + 40,
				(int) (getHeight() + 40));

		if (KeyManager.getInstance().keyJustPressed(KeyEvent.VK_A)) {
			for (Entity e : World.getInstance().getEntityManager().getEntities()) {
				if (e.equals(this)) continue;

				if (e.getCollisionBounds(0, 0).intersects(attackRectangle)) {
					e.hurt(getStrength());

					KeyManager.getInstance().update();
					return;
				}
				try {
					Thread.sleep(1);
				} catch (InterruptedException i) {
					i.printStackTrace();
				}
			}

		}

	}

	public void checkEntity() {

		if (inventory.isActive() || info.isActive() || trivel.isActive() || Dialog.isActive()) {
			return;
		}

		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();

		if (attackTimer < attackCooldown) return;

		Rectangle rect = new Rectangle(getX(), getY(), getWidth(), getHeight());

		attackTimer = 0;

		for (Entity e : World.getInstance().getEntityManager().getEntities()) {
			if (e.equals(this)) continue;

			if (e.getCollisionBounds(0, 0).intersects(rect)) {
				setHealth(getHealth() - e.getStrength());
				return;
			}
		}
	}

	private void getInput() {

		setXMove(0);
		setYMove(0);

		if (inventory.isActive() || info.isActive() || trivel.isActive()  || Dialog.isActive()) {
			return;
		}

		if (KeyManager.getInstance().isUp()) {
			setYMove(getYMove() - getSpeed());
		}
		if (KeyManager.getInstance().isDown()) {
			setYMove(getSpeed());
		}
		if (KeyManager.getInstance().isLeft()) {
			setXMove(getXMove() - getSpeed());
		}
		if (KeyManager.getInstance().isRight()) {
			setXMove(getSpeed());
		}

		if (KeyManager.getInstance().isSpace()) {
			setSpeed(DEFAULT_SPEED * 2);
			animDown.setSpeed(animDown.DEFAULT_SPEED / 2);
			animUp.setSpeed(animUp.DEFAULT_SPEED / 2);
			animLeft.setSpeed(animLeft.DEFAULT_SPEED / 2);
			animRight.setSpeed(animRight.DEFAULT_SPEED / 2);
		} else {
			setSpeed(DEFAULT_SPEED);
			animDown.setSpeed(animDown.DEFAULT_SPEED);
			animUp.setSpeed(animUp.DEFAULT_SPEED);
			animLeft.setSpeed(animLeft.DEFAULT_SPEED);
			animRight.setSpeed(animRight.DEFAULT_SPEED);
		}

	}

	@Override
	public void render(AbstractTrivialGraphics g) {
		// ATTACK CIRCLE
		/*if (KeyManager.getInstance().keyJustPressed(KeyEvent.VK_A)) {
			if(circlesize == 128) return;

			circlesize = 0;
			if (startTime == 0) {
				startTime = System.currentTimeMillis();
			}

			long currentTime = System.currentTimeMillis();
			long elapsedTime = currentTime - startTime;

			while (KeyManager.getInstance().keyPressed(KeyEvent.VK_A)) {
				int radius = (int) (128 * (double) elapsedTime / 2000);

				g.setColor(new Color(255, 255, 255, 100));
				g.fillOval(getX() - cam.getxOffset() - radius, getY() - cam.getyOffset() - radius, 2 * radius, 2 * radius);

				if(KeyManager.getInstance().keyReleased(KeyEvent.VK_A)) {
					break;
				}
			}

		}*/

		//startTime = 0;

		g.drawImage(getCurrentAnimationFrame(), getX() - cam.getxOffset(),
				getY() - cam.getyOffset(), getWidth(), getHeight());

		inventory.render(g);
		trivel.render(g);
		info.render(g);



	}



	private BufferedImage getCurrentAnimationFrame() {
		if (getXMove() < 0) {
			currentPosition = animLeft.getCurrentFrame();
		} else if (getXMove() > 0) {
			currentPosition = animRight.getCurrentFrame();
		} else if (getYMove() < 0) {
			currentPosition = animUp.getCurrentFrame();
		} else if (getYMove() > 0) {
			currentPosition = animDown.getCurrentFrame();
		}

		else if (currentPosition == null) {

			currentPosition = Assets.playerDown;
		}

		return currentPosition;

	}

	public void postRender(AbstractTrivialGraphics g) {
	
	}
}
