package org.cofezuwo.nsuluofuo.server;


import java.net.ServerSocket;
import java.net.Socket;

public class MpServer {
	private ServerSocket serversoc;
	private int port;
	private boolean on;
	private int clntCount = 0;
	public static boolean online = true;
	
	public MpServer(int pport){
		port = pport;
	}
	
	public void start(){
		try {
			
			serversoc = new ServerSocket(port);
			on = true;
			System.out.println("Server: Server started");
			
			Thread tron = new Thread(){
				public void run(){
					while(on && clntCount < 2){
						
						try {
							
							Socket clientsoc;
							clientsoc = serversoc.accept();
							
							clntCount ++;
							
							Thread tr = new Thread(new MpClientHandler(clientsoc));
							tr.start();
							
							System.out.println("Server: Client connected");
							online = true;
							
						} catch (Exception e) {
							e.printStackTrace();
							online = false;
						}
					}
				}
			};
			tron.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void stop(){
		try {
			
			serversoc.close();
			on = false;
			
			System.out.println("Server: Server closed");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}