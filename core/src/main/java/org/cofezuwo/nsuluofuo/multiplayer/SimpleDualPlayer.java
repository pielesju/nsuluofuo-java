package org.cofezuwo.nsuluofuo.multiplayer;

import lombok.Getter;
import lombok.Setter;
import org.cofezuwo.nsuluofuo.server.MpServer;

public class SimpleDualPlayer {
	
	private static MpServer server;
	private static MpConnection connection;
	@Getter
	@Setter
	private static MpCharacter playerL = new MpCharacter(0, 0, 0, "Player");

	@Getter
	@Setter
	private static MpCharacter playerR = new MpCharacter(0, 0, 0, "Player");
	
	private static int port;

	private SimpleDualPlayer() {

	}

	public static void startServer(int pport){
		port = pport;
		server = new MpServer(pport);
		server.start();
	}
	public static void stopServer(){
		server.stop();
	}

	public static void join(String phost, int pport){
		connection = new MpConnection(phost, pport, playerL);
		connection.start();
	}
	public static void join(){
		connection = new MpConnection("localhost", port, playerL);
		connection.start();
	}
	public static void disconnect(){
		connection.stop();
	}

	public static void update(){
		connection.setLocalPlayer(playerL);
		connection.update();
		playerR = MpConnection.getCharacter(1);
	}
}