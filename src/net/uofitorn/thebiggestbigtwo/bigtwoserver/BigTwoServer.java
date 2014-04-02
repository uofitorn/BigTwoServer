package net.uofitorn.thebiggestbigtwo.bigtwoserver;

import java.io.IOException;
import java.net.ServerSocket;

public class BigTwoServer {

	public static void main(String[] args) {
		 
		int portNumber = 4444;
	    boolean listening = true;
	    BigTwoGame bigTwoGame = new BigTwoGame(1);
	    int client = 0;
	         
	    try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
	        while (listening) {
	            new BigTwoServerThread(serverSocket.accept(), bigTwoGame, client).start();
	            client++;
	        }
	    } catch (IOException e) {
	        System.err.println("Could not listen on port " + portNumber);
	        System.exit(-1);
	    }
	}
}
