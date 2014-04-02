package net.uofitorn.bigtwoserver;

import java.io.IOException;
import java.io.PrintWriter;
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
	
	public void makePlay(int player, String play) {
		System.out.println("Player " + player + " made a play: " + play);
		broadcastMessage(play);
	}
	
	private void broadcastMessage(String message) {
		for (Socket s : clientsAttached) {
			try {
				PrintWriter out = new PrintWriter(s.getOutputStream(), true);
				out.println(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
