package com.skula.pingouins.models;

public class Tile {
	private int fishCount;
	private boolean taken;

	public Tile() {
		this.taken = false;
	}

	public Tile(int fishCount) {
		this.fishCount = fishCount;
		this.taken = false;
	}

	public boolean isSafe() {
		return !taken && fishCount > 0;
	}

	public boolean isOneFish() {
		return fishCount == 1;
	}

	public int empty() {
		int tmp = fishCount;
		fishCount = 0;
		taken = false;
		return tmp;
	}

	public int getFishCount() {
		return fishCount;
	}

	public void setFishCount(int fishCount) {
		this.fishCount = fishCount;
	}

	public boolean isTaken() {
		return taken;
	}

	public void setTaken(boolean occupied) {
		this.taken = occupied;
	}
}
