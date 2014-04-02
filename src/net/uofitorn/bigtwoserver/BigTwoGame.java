package net.uofitorn.bigtwoserver;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class BigTwoGame {
	
	private ArrayList<Socket> clientsAttached = new ArrayList<Socket>();
	private int x;
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public BigTwoGame(int x) {
		this.x = x;
	}
	
	public void attachClient(Socket socket) {
		clientsAttached.add(socket);
	}
	
	public void makePlay(int player, NetworkMessage message) {
		System.out.println("Player " + player + " made a play: " + message.getMessage());
		broadcastMessage(message);
	}
	
	private void broadcastMessage(NetworkMessage message) {
		for (Socket s : clientsAttached) {
			try {
				ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
				out.writeObject(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
