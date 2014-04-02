package net.uofitorn.thebiggestbigtwo.bigtwoserver;

import java.util.ArrayList;
import java.util.Collections;

import net.uofitorn.thebiggestbigtwo.common.Card;

public class Deck {

	private ArrayList<Card> cards;
	
	public Deck() {
		cards = new ArrayList<Card>();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				cards.add(new Card(i, j));
			}
		}
		Collections.shuffle(cards);
	}
	
	public Card drawFromDeck() {
		return cards.remove(0);
	}
	
	public int getTotalCards() {
		return cards.size();
	}
}
