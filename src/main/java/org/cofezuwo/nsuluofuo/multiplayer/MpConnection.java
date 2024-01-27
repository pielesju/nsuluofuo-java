package org.cofezuwo.nsuluofuo.multiplayer;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class MpConnection {
	private Socket soc;
	private InputStream in;
	private OutputStream out;
	
	private String host;
	private int port;
	public static boolean online;
	private static double[] characterPosx = {0, 0};
	private static double[] characterPosy = {0, 0};
	private static int[] characterDir = {0, 0};
	private static String[] characterName = {"-", "-"};
	
	private MpCharacter localPlayer = new MpCharacter(0, 0, 0, "-");
	private boolean updatevar = false;
	
	public MpConnection(String phost, int pport, MpCharacter plocalPlayer){
		try {
			
			host = phost;
			port = pport;
			online = true;
			
		}  catch (Exception e) {
			e.printStackTrace();
			online = false;
		}
	}
	
	public void start(){
		try {
			
			soc = new Socket(host, port);
			
			in = soc.getInputStream();
			out = soc.getOutputStream();
			
			update2();
			online = true;
			System.out.println("Client: Connected to server");
			
		}  catch (Exception e) {
			e.printStackTrace();
			online = false;
		}
		
	}
	public void stop(){
		try {
			
			soc.close();
			System.out.println("Client: Socket closed");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(){
	
		characterPosx[0] = localPlayer.getPositionX();
		characterPosy[0] = localPlayer.getPositionY();
		characterDir[0] = localPlayer.getDir();
		characterName[0] = localPlayer.getName();
	}
	
	public void update2(){
		
		Thread trrecive = new Thread(){public void run(){
			try {
				
				ObjectInputStream in = new ObjectInputStream(soc.getInputStream());
				
				
				
				Timer timer = new Timer();
				TimerTask task = new TimerTask() {
					
					@Override
					public void run() {

						try {
							MpCharacter tmp = (MpCharacter)in.readObject();
							characterPosx[1] = tmp.getPositionX();
							characterPosy[1] = tmp.getPositionY();
							characterDir[1] = tmp.getDir();
							characterName[1] = tmp.getName();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}
				};
				timer.scheduleAtFixedRate(task, 50, 50);
				
				//this.sleep(100);
				//run();
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}};
		Thread trsend = new Thread(){public void run(){
			try {
				
				ObjectOutputStream out = new ObjectOutputStream(soc.getOutputStream());;
				
				

				Timer timer = new Timer();
				TimerTask task = new TimerTask() {
					
					@Override
					public void run() {

						try {
							MpCharacter tmp = new MpCharacter(characterPosx[0], characterPosy[0], characterDir[0], characterName[0]);
							out.writeObject(tmp);
							out.flush();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}
				};
				timer.scheduleAtFixedRate(task, 50, 50);
				
				//this.sleep(100);
				//run();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}};
		
		trrecive.start();
		trsend.start();
	}
	
	
	
	public static MpCharacter getCharacter(int index) {
		return new MpCharacter(characterPosx[index], characterPosy[index], characterDir[index], characterName[index]);
	}

	public MpCharacter getLocalPlayer() {
		return localPlayer;
	}

	public void setLocalPlayer(MpCharacter localPlayer) {
		this.localPlayer = localPlayer;
	}
	
}
