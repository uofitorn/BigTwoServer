package net.uofitorn.thebiggestbigtwo.bigtwoserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class BigTwoServer {

	public static void main(String[] args) {
		 
		int portNumber = 4444;
	    boolean listening = true;
	    ArrayList<BigTwoGame> bigTwoGames = new ArrayList<BigTwoGame>();
	    int gameNumber = 0;
	    int client = 0;
	         
	    try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
	        while (listening) {
	        	if (client % 2 == 0) {
	        		BigTwoGame bigTwoGame = new BigTwoGame();
	        		bigTwoGames.add(bigTwoGame);
	        		gameNumber++;
	        	}
	            new BigTwoServerThread(serverSocket.accept(), bigTwoGames.get(gameNumber - 1), client).start();
	            client++;
	        }
	    } catch (IOException e) {
	        System.err.println("Could not listen on port " + portNumber);
	        System.exit(-1);
	    }
	}
}
