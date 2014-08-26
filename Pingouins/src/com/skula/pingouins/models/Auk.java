package com.skula.pingouins.models;

public class Auk {
	private int xPos;
	private int yPos;

	public static void main(String[] args) {

	}

	public Auk() {
	}

	public Auk(int xPos, int yPos) {
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
}
