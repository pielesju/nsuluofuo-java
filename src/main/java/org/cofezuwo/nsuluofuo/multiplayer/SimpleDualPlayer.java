package org.cofezuwo.nsuluofuo.multiplayer;

import org.cofezuwo.nsuluofuo.server.MpServer;

public class SimpleDualPlayer {
	
	private static MpServer server;
	private static MpConnection connection;
	private static MpCharacter playerL = new MpCharacter(0, 0, 0, "Player");
	private static MpCharacter playerR = new MpCharacter(0, 0, 0, "Player");
	
	private static int port;

	public static void startServer(int pport){
		port = pport;
		server = new MpServer(pport);
		server.start();
	}
	public static void stopServer(){
		server.stop();
	}
	public static void join(String phost, int pport){
		//playerL = new MpCharacter(0, 0, 0, "Player");
		connection = new MpConnection(phost, pport, playerL);
		connection.start();
	}
	public static void join(){
		//playerL = new MpCharacter(0, 0, 0, "Player");
		connection = new MpConnection("localhost", port, playerL);
		connection.start();
	}
	public static void disconnect(){
		connection.stop();
	}
	public static void update(){
		connection.setLocalPlayer(playerL);
		//connection.getLocalPlayer().setPositionX(playerL.getPositionX());
		//connection.getLocalPlayer().setPositionY(playerL.getPositionY());
		connection.update();
		playerR = MpConnection.getCharacter(1);
	}
	
	
	public static MpCharacter getPlayerL() {
		return playerL;
	}
	public static void setPlayerL(MpCharacter playerL) {
		SimpleDualPlayer.playerL = playerL;
	}
	public static MpCharacter getPlayerR() {
		return playerR;
	}
	public static void setPlayerR(MpCharacter playerR) {
		SimpleDualPlayer.playerR = playerR;
	}
	


}

				
		//conn.update(); daten vom lokalen speiler werden hochgeladen, allse anderern spieler werden runtergeladen und in clientliste gespeichert / au�erdem weist der server dem lokalen spieler seine id zu
		//System.out.println("Ausgabe: " + conn.getCharacter(2).getName()); name von spieler mit der id 2 ausgeben
		//System.out.println("Ausgabe 2: " + conn.getLocalPlayer().getId()); id vom lokalen spieler ausgeben