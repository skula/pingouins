package com.skula.pingouins.models;

public class Player implements Comparable<Player>{

	private int nAuks;
	private Auk[] auks;
	private int color;
	private int fishCount;
	private boolean blocked;

	/*public Player(int nAuks) {
		this.nAuks = nAuks;
		this.blocked = false;
		this.auks = new Auk[nAuks];
		for (int i = 0; i < nAuks; i++) {
			auks[i] = new Auk();
		}
	}*/

	public Player(int nAuks, int color) {
		this.nAuks = nAuks;
		this.auks = new Auk[nAuks];
		for(int i=0; i<nAuks; i++){
			auks[i]=new Auk(color);
		}
		this.color = color;
	}

	public void fish(int n) {
		fishCount += n;
	}

	public Auk getAuk(int x, int y) {
		for(int i = 0; i<nAuks; i++){
			if(auks[i].getxPos()==x && auks[i].getyPos()==y){
				return auks[i];
			}
		}
		return null;
	}
	
	public Auk getAuk(int i) {
		return auks[i];
	}

	public Auk[] getAuks() {
		return auks;
	}

	public void setAuks(Auk[] auks) {
		this.auks = auks;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getFishCount() {
		return fishCount;
	}

	public void setFishCount(int fishCount) {
		this.fishCount = fishCount;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	
	@Override
	public int compareTo(Player o) {
		if(o.getFishCount()>this.getFishCount()){
			return 1;
		}
		
		if(o.getFishCount()<this.getFishCount()){
			return -1;
		}
		
		return 0;
	}
}
