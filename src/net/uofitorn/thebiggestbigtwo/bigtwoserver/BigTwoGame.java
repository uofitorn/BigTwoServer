package net.uofitorn.thebiggestbigtwo.bigtwoserver;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import net.uofitorn.thebiggestbigtwo.common.Card;
import net.uofitorn.thebiggestbigtwo.common.Hand;
import net.uofitorn.thebiggestbigtwo.common.NetworkMessage;

public class BigTwoGame {
	
	private ArrayList<Socket> clientsAttachedSockets = new ArrayList<Socket>();
	private ArrayList<ObjectOutputStream> clientsAttachedOutputs = new ArrayList<ObjectOutputStream>();
	private int clients = 0;
	private Hand[] hands = new Hand[4];
	private int firstPlayer;
	private int currentPlayer;
	private int ownerOfPlay;
	private boolean started = false;

	public BigTwoGame() {
		Deck deck = new Deck();
		for (int i = 0; i < 4; i++) {
			hands[i] = new Hand();
			for (int j = 0; j < 13; j++) {
				Card c = deck.drawFromDeck();
				hands[i].addCard(c);
			}
		}
	}
	
	public void attachClient(Socket socket) {
		clientsAttachedSockets.add(socket);
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			clientsAttachedOutputs.add(out);
		} catch (IOException e) {
			e.printStackTrace();
		}

		clients++;
		if (clients == 1) {
			startGame();
		}
	}
	
	public void makePlay(int player, NetworkMessage message) {
		System.out.println("Player " + player + " made a play: " + message.getMessage());
	}
	
	private void sendMessage(ObjectOutputStream out, NetworkMessage message) {
		try {
			out.writeObject(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void startGame() {
		started = true;
		determineStartingPlayer(2);
		for (int i = 0; i < 2; i++) {
			System.out.println("Sending message to client: " + i);
			NetworkMessage message = new NetworkMessage(NetworkMessage.STARTING);
			message.setPlayerNumber(i);
			message.setPlayersTurn(firstPlayer);
			sendMessage(clientsAttachedOutputs.get(i), message);
		}
	}
	
	private void determineStartingPlayer(int players) {
		Card player1 = hands[0].getLowest();
		Card player2 = hands[1].getLowest();
		if (player1.compareTo(player2) == -1) {
			firstPlayer = 1;
			currentPlayer = 1;
		} else {
			firstPlayer = 0;
			currentPlayer = 0;
		}
	}
	
}
