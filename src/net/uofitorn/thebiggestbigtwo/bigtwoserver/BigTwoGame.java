package net.uofitorn.thebiggestbigtwo.bigtwoserver;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import net.uofitorn.thebiggestbigtwo.common.Card;
import net.uofitorn.thebiggestbigtwo.common.Hand;
import net.uofitorn.thebiggestbigtwo.common.NetworkMessage;
import net.uofitorn.thebiggestbigtwo.common.Play;

public class BigTwoGame {
	
	private ArrayList<Socket> clientsAttachedSockets = new ArrayList<Socket>();
	private ArrayList<ObjectOutputStream> clientsAttachedOutputs = new ArrayList<ObjectOutputStream>();
	private int clients = 0;
	private Hand[] hands = new Hand[4];
	private int firstPlayer;
	private int currentPlayer;
	private int ownerOfPlay;
	private boolean started = false;
	private Play currentPlay;

	public BigTwoGame() {
		Deck deck = new Deck();
		for (int i = 0; i < 4; i++) {
			hands[i] = new Hand();
			for (int j = 0; j < 13; j++) {
				hands[i].addCard(deck.drawFromDeck());
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
		System.out.println("Client connected. Client: " + clients);
		if (clients == 2) {
			startGame();
		}
	}
	
	public void processPlay(int player, Play play) {
		System.out.println("Player " + player + " made a play: " + play.getFriendlyName());
		currentPlay = play;
		for (int i = 0; i < clients; i++) {
			if (player == i) continue;
			System.out.println("Sending play to player: " + i);
			NetworkMessage message = new NetworkMessage(NetworkMessage.PLAY);
			message.setPlay(play);
			sendMessage(clientsAttachedOutputs.get(i), message);
		}
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
			
			message = new NetworkMessage(NetworkMessage.HAND);
			message.setHand(hands[i]);
			sendMessage(clientsAttachedOutputs.get(i), message);
		}
	}
	
	private void determineStartingPlayer(int players) {
		Card player1 = hands[0].getLowest();
		Card player2 = hands[1].getLowest();
		System.out.println("Player 1: " + player1.toString());
		System.out.println("Player 2: " + player2.toString());
		if (player1.compareTo(player2) == 1) {
			firstPlayer = 1;
			currentPlayer = 1;
			System.out.println("Setting to player 1");
		} else {
			System.out.println("Setting to player 0");
			firstPlayer = 0;
			currentPlayer = 0;
		}
	}
	
}
