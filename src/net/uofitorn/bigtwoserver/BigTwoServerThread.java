package net.uofitorn.bigtwoserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class BigTwoServerThread extends Thread {

	private Socket socket = null;
	private BigTwoGame bigTwoGame;
	private int client;
	
	public BigTwoServerThread(Socket socket, BigTwoGame bigTwoGame, int client) {
        super("BigTwoServerThread");
        this.socket = socket;
        this.bigTwoGame = bigTwoGame;
        this.client = client;
    }
	
	public void run() {
		 
        try (
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
        	bigTwoGame.attachClient(socket);
            String inputLine, outputLine;
            
            while ((inputLine = in.readLine()) != null) {
                bigTwoGame.makePlay(client, inputLine);
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
