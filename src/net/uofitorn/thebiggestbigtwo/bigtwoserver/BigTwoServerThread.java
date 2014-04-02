package net.uofitorn.thebiggestbigtwo.bigtwoserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import net.uofitorn.thebiggestbigtwo.common.NetworkMessage;

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
        	ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

        ) {
        	bigTwoGame.attachClient(socket);
            String inputLine, outputLine;
            NetworkMessage message;
            
            while ((message = (NetworkMessage) in.readObject()) != null) {
                //bigTwoGame.makePlay(client, message);
            }
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
