package com.skula.pingouins.models;

public class Auk {
	private int xPos;
	private int yPos;
	private boolean inGame;

	public static void main(String[] args) {

	}

	public Auk() {
		this.inGame = false;
	}

	public Auk(int xPos, int yPos) {
		this.inGame = false;
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public void move(int x, int y) {
		xPos = x;
		yPos = y;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public boolean isInGame() {
		return inGame;
	}

	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}
	
	
}
