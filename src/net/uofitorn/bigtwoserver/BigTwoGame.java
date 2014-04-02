package net.uofitorn.bigtwoserver;

public class BigTwoGame {
	
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
	
	public void makePlay(int player, String play) {
		System.out.println("Player " + player + " made a play: " + play);
	}
}
