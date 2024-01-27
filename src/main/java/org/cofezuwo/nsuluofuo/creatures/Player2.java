package org.cofezuwo.nsuluofuo.creatures;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import org.cofezuwo.nsuluofuo.graphics.Animation;
import org.cofezuwo.nsuluofuo.graphics.Assets;
import org.cofezuwo.nsuluofuo.main.Handler;
import org.cofezuwo.nsuluofuo.multiplayer.MpConnection;
import org.cofezuwo.nsuluofuo.multiplayer.SimpleDualPlayer;
import org.cofezuwo.nsuluofuo.server.MpServer;

public class Player2 extends Creature {
	private BufferedImage currentPosition = Assets.playerDown;
	private Animation animDown, animUp, animLeft, animRight;

	public Player2(float x, float y) {
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
		float remoteX = ((float)SimpleDualPlayer.getPlayerR().getPositionX());
		float remoteY = ((float)SimpleDualPlayer.getPlayerR().getPositionY());
		
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
	public void render(Graphics g) {
		if(MpConnection.online){
			g.drawImage(getCurrentAnimationFrame(), (int) (getX() - Handler.getInstance().getGameCamera().getxOffset()),
					(int) (getY() - Handler.getInstance().getGameCamera().getyOffset()), getWidth(), getHeight(), null);
		}else{
			return;
		}
		

	}

	private BufferedImage getCurrentAnimationFrame() {
		if (Handler.getInstance().getKeyManager().isA()) {
			currentPosition = animLeft.getCurrentFrame();
		} else if (Handler.getInstance().getKeyManager().isD()) {
			currentPosition = animRight.getCurrentFrame();
		} else if (Handler.getInstance().getKeyManager().isW()) {
			currentPosition = animUp.getCurrentFrame();
		} else if (Handler.getInstance().getKeyManager().isS()) {
			currentPosition = animDown.getCurrentFrame();
		} else if (currentPosition == null) {

			currentPosition = Assets.playerDown;
		}

		return currentPosition;

	}

	private void getInput() {

		setXMove(0);
		setYMove(0);

		if (Handler.getInstance().getKeyManager().isW()) {
			setYMove(getYMove() - getSpeed());
		}
		if (Handler.getInstance().getKeyManager().isS()) {
			setYMove(getYMove() + getSpeed());
		}
		if (Handler.getInstance().getKeyManager().isA()) {
			setXMove(getXMove() - getSpeed());
		}
		if (Handler.getInstance().getKeyManager().isD()) {
			setXMove(getXMove() + getSpeed());
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

}
