package com.skula.pingouins.models;

public class Auk implements Comparable<Auk>{
	private int xPos;
	private int yPos;
	private boolean inGame;
	private int color;

	public Auk(int color) {
		this.inGame = false;
		this.color = color;
	}

	public Auk(int xPos, int yPos, int color) {
		this.inGame = false;
		this.xPos = xPos;
		this.yPos = yPos;
		this.color = color;
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
	
	public int getColor() {
		return color;
	}
	
	public int compareTo(Auk o) {
		if(o.getyPos()>this.getyPos()){
			return -1;
		}else{
			if(o.getyPos()<this.getyPos()){
				return 1;
			}else{
				if(o.getxPos()>this.getxPos()){
					return -1;
				}else{
					return 1;
				}
			}
		}
	}
}
