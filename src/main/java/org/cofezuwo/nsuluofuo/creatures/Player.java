package org.cofezuwo.nsuluofuo.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import org.cofezuwo.nsuluofuo.entities.Entity;
import org.cofezuwo.nsuluofuo.graphics.Animation;
import org.cofezuwo.nsuluofuo.graphics.Assets;
import org.cofezuwo.nsuluofuo.inventory.Dialog;
import org.cofezuwo.nsuluofuo.inventory.Inventory;
import org.cofezuwo.nsuluofuo.inventory.PlayerInfo;
import org.cofezuwo.nsuluofuo.inventory.Trivel;
import org.cofezuwo.nsuluofuo.item.Item;
import org.cofezuwo.nsuluofuo.main.Handler;
import org.cofezuwo.nsuluofuo.multiplayer.SimpleDualPlayer;

public class Player extends Creature {

	private Animation animDown, animUp, animLeft, animRight;
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
	private BufferedImage currentPosition = Assets.playerDown;
	private int money = 2000;
	private int maxHealth;
	private int exp;
	private int level;
	private int maxExp;
	private String name;
	private Item currentWeapon;
	private Inventory inventory;
	private PlayerInfo info;
	private Trivel trivel;

	public Player(float x, float y) {
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

	}

	@Override
	public void update() {
		
		getInput();
		move();
		animDown.update();
		animUp.update();
		animLeft.update();
		animRight.update();
		
		Handler.getInstance().getGameCamera().centerOnEntity(this);
		checkAttack();
		checkEntity();

		info.update();
		inventory.update();
		trivel.update();
		
		//Positionsdaten den Multiplayerklassen geben
		SimpleDualPlayer.getPlayerL().setPositionX(getX());
		SimpleDualPlayer.getPlayerL().setPositionY(getY());
		SimpleDualPlayer.getPlayerL().setName(getName());

	}

	public void checkAttack() {

		if (inventory.isActive() || info.isActive() || trivel.isActive() || Dialog.isActive()) {
			return;
		}

		Rectangle attackRectangle = new Rectangle((int) (getX() - 20), (int) (getY() - 20), (int) (getWidth()) + 40,
				(int) (getHeight() + 40));

		if (Handler.getInstance().getKeyManager().keyJustPressed(KeyEvent.VK_A)) {

			System.out.println("A");
			for (Entity e : Handler.getInstance().getWorld().getEntityManager().getEntities()) {
				if (e.equals(this)) {
					continue;
				}
				if (e.getCollisionBounds(0, 0).intersects(attackRectangle)) {
					e.hurt(getStrength());

					Handler.getInstance().getKeyManager().update();
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
		if (attackTimer < attackCooldown) {
			return;
		}
		Rectangle rect = new Rectangle((int) (getX()), (int) (getY()), (int) (getWidth()), (int) (getHeight()));

		attackTimer = 0;

		for (Entity e : Handler.getInstance().getWorld().getEntityManager().getEntities()) {
			if (e.equals(this)) {
				continue;
			}
			if (e.getCollisionBounds(0, 0).intersects(rect)) {
				setHealth(getHealth() - e.getStrength());
				System.out.println("AAAAAAAA");
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

		if (Handler.getInstance().getKeyManager().isUp()) {
			setYMove(getYMove() - getSpeed());
		}
		if (Handler.getInstance().getKeyManager().isDown()) {
			setYMove(getSpeed());
		}
		if (Handler.getInstance().getKeyManager().isLeft()) {
			setXMove(getXMove() - getSpeed());
		}
		if (Handler.getInstance().getKeyManager().isRight()) {
			setXMove(getSpeed());
		}

		if (Handler.getInstance().getKeyManager().isSpace()) {
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
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (getX() - Handler.getInstance().getGameCamera().getxOffset()),
				(int) (getY() - Handler.getInstance().getGameCamera().getyOffset()), getWidth(), getHeight(), null);

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

	public void postRender(Graphics g) {
	
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
 	
	public PlayerInfo getInfo() {
		return info;
	}

	public void setInfo(PlayerInfo info) {
		this.info = info;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getExp() {
		return exp;
	}

	public int getMaxExp() {
		return maxExp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public void setMaxExp(int maxExp) {
		this.maxExp = maxExp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public BufferedImage getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(BufferedImage currentPosition) {
		this.currentPosition = currentPosition;
	}

	public Animation getAnimDown() {
		return animDown;
	}

	public Animation getAnimUp() {
		return animUp;
	}

	public Animation getAnimLeft() {
		return animLeft;
	}

	public Animation getAnimRight() {
		return animRight;
	}

	public void setAnimDown(Animation animDown) {
		this.animDown = animDown;
	}

	public void setAnimUp(Animation animUp) {
		this.animUp = animUp;
	}

	public void setAnimLeft(Animation animLeft) {
		this.animLeft = animLeft;
	}

	public void setAnimRight(Animation animRight) {
		this.animRight = animRight;
	}

	public Item getCurrentWeapon() {
		return currentWeapon;
	}

	public void setCurrentWeapon(Item currentWeapon) {
		this.currentWeapon = currentWeapon;
	}

	public Trivel getTrivel() {
		return trivel;
	}

	public void setTrivel(Trivel trivel) {
		this.trivel = trivel;
	}

}
