package org.cofezuwo.nsuluofuo.creatures;

import java.awt.image.BufferedImage;

import org.cofezuwo.nsuluofuo.graphics.AbstractTrivialGraphics;
import org.cofezuwo.nsuluofuo.graphics.Animation;
import org.cofezuwo.nsuluofuo.graphics.Assets;
import org.cofezuwo.nsuluofuo.graphics.GameCamera;
import org.cofezuwo.nsuluofuo.input.KeyManager;
import org.cofezuwo.nsuluofuo.multiplayer.MpConnection;
import org.cofezuwo.nsuluofuo.multiplayer.SimpleDualPlayer;

public class Player2 extends Creature {
	private BufferedImage currentPosition = Assets.playerDown;
	private Animation animDown, animUp, animLeft, animRight;
	private GameCamera cam;

	public Player2(int x, int y) {
		super(x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

		animDown = new Animation(animDown.DEFAULT_SPEED, Assets.player_down);
		animUp = new Animation(animUp.DEFAULT_SPEED, Assets.player_up);
		animLeft = new Animation(animLeft.DEFAULT_SPEED, Assets.player_left);
		animRight = new Animation(animRight.DEFAULT_SPEED, Assets.player_right);

		//collisionbox vor�bergehend auf null gemacht
		
//		bounds.x = 8;
//		bounds.y = 16;
//		bounds.width = 16;
//		bounds.height = 16;
		
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = 0;
		bounds.height = 0;

		this.cam = GameCamera.getInstance();

	}

	@Override
	public void update() {

		animDown.update();
		animUp.update();
		animLeft.update();
		animRight.update();

		//getInput();
		move();
		
		//Positionsdaten von Multiplayerklassen holen
		int remoteX = SimpleDualPlayer.getPlayerR().getPositionX();
		int remoteY = SimpleDualPlayer.getPlayerR().getPositionY();
		
		//System.out.println("x ist: " + getX() + " x soll: " + remoteX + " differenz: " + Math.abs(getX() - remoteX) + " mehr als default speed: " + (boolean)(Math.abs(getY() - remoteY) > DEFAULT_SPEED));
		//System.out.println("y ist: " + getY() + " y soll: " + remoteY + " differenz " + Math.abs(getY() - remoteY) + " mehr als default speed: " + (boolean)(Math.abs(getY() - remoteY) > DEFAULT_SPEED));
        
		if(Math.abs(getY() - remoteY) > DEFAULT_SPEED*2){
			
			if (getY() > remoteY) {
				setYMove(- getSpeed()*2);
				currentPosition = animUp.getCurrentFrame();
			}
			if (getY() < remoteY) {
				setYMove(+ getSpeed()*2);
				currentPosition = animDown.getCurrentFrame();
			}
		}
		else{
			setY(remoteY);
		}
		if(Math.abs(getX() - remoteX) > DEFAULT_SPEED*2){
			
			if (getX() > remoteX) {
				setXMove(- getSpeed()*2);
				currentPosition = animLeft.getCurrentFrame();
			}
			if (getX() < remoteX) {
				setXMove(+ getSpeed()*2);
				currentPosition = animRight.getCurrentFrame();
			}
		}
		else{
			setX(remoteX);
		}
		

	}

	@Override
	public void render(AbstractTrivialGraphics g) {
		if(MpConnection.online){
			g.drawImage(getCurrentAnimationFrame(), (int) (getX() - cam.getxOffset()),
					(int) (getY() - cam.getyOffset()), getWidth(), getHeight());
		}else{
			return;
		}
		

	}

	private BufferedImage getCurrentAnimationFrame() {
		if (KeyManager.getInstance().isA()) {
			currentPosition = animLeft.getCurrentFrame();
		} else if (KeyManager.getInstance().isD()) {
			currentPosition = animRight.getCurrentFrame();
		} else if (KeyManager.getInstance().isW()) {
			currentPosition = animUp.getCurrentFrame();
		} else if (KeyManager.getInstance().isS()) {
			currentPosition = animDown.getCurrentFrame();
		} else if (currentPosition == null) {

			currentPosition = Assets.playerDown;
		}

		return currentPosition;

	}

	private void getInput() {

		setXMove(0);
		setYMove(0);

		if (KeyManager.getInstance().isW()) {
			setYMove(getYMove() - getSpeed());
		}
		if (KeyManager.getInstance().isS()) {
			setYMove(getYMove() + getSpeed());
		}
		if (KeyManager.getInstance().isA()) {
			setXMove(getXMove() - getSpeed());
		}
		if (KeyManager.getInstance().isD()) {
			setXMove(getXMove() + getSpeed());
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

}
