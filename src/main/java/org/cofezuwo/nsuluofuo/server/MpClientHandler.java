package org.cofezuwo.nsuluofuo.server;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.cofezuwo.nsuluofuo.multiplayer.MpCharacter;

public class MpClientHandler implements Runnable{
	
	private Socket clientsoc;
	private static double[] characterPosx = {0, 0};
	private static double[] characterPosy = {0, 0};
	private static int[] characterDir = {0, 0};
	private static String[] characterName = {"-", "-"};
	private static int index = 0;
	
	public MpClientHandler (Socket pclientsoc) {
		clientsoc = pclientsoc;
	}

	@Override
	public void run() {
		try{

			index ++;
			final int thisIndex = index - 1;
			int oIndex = 0;
			switch (thisIndex){
				case 0: oIndex = 1;
				case 1: oIndex = 0;
			}
			final int foIndex = oIndex;

			Thread trrecive = new Thread(){public void run(){
				try {
					
					ObjectInputStream in = new ObjectInputStream(clientsoc.getInputStream());
					
					MpCharacter tmp = (MpCharacter)in.readObject();
					characterPosx[thisIndex] = tmp.getPositionX();
					characterPosy[thisIndex] = tmp.getPositionY();
					characterDir[thisIndex] = tmp.getDir();
					characterName[thisIndex] = tmp.getName();
					
					this.sleep(100);
					run();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}};
			Thread trsend = new Thread(){public void run(){
				try {
					
					ObjectOutputStream out = new ObjectOutputStream(clientsoc.getOutputStream());;
					
					MpCharacter tmp = new MpCharacter(characterPosx[foIndex], characterPosy[foIndex], characterDir[foIndex], characterName[foIndex]);
					out.writeObject(tmp);
					out.flush();
					
					this.sleep(100);
					run();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}};
			
			trrecive.start();
			trsend.start();
		
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
