package com.skula.pingouins.services;

import com.skula.pingouins.enums.Timeline;
import com.skula.pingouins.models.Auk;
import com.skula.pingouins.models.Board;
import com.skula.pingouins.models.Player;
import com.skula.pingouins.models.Tile;

public class GameEngine {
	private Timeline timeline;
	private int nPlayers;
	private int nAuks;
	private int pToken;
	private Board board;
	private Player[] players;

	private int leftToPosition;
	private int xSrc;
	private int ySrc;
	private int xDest;
	private int yDest;

	public static void main(String[] args) {
		GameEngine ge = new GameEngine(2);
		System.out.println("");
	}

	public GameEngine(int nPlayers) {
		this.nPlayers = nPlayers;
		this.setAuksCount();
		this.leftToPosition = 0;
		this.players = new Player[nPlayers];
		for (int i = 0; i < nPlayers; i++) {
			players[i] = new Player(nAuks, i);
		}

		// this.timeline = Timeline.CHOOSE_COLOR;
		this.timeline = Timeline.POSITIONING;
		this.pToken = 0;

		clearSrcPosition();
		clearDestPosition();
	}

	public boolean canProcess() {
		switch (timeline) {
		case POSITIONING:
			return xDest != -1 && yDest != -1;
		case MOVEMENT:
			return xSrc != -1 && ySrc != -1 && xDest != -1 && yDest != -1;
		case SCORE:
			return true;
		case END:
			return true;
		default:
			return false;
		}
	}

	public void process() {
		switch (timeline) {
		case POSITIONING:
			// boucler tant que tous les pingouins ne sont pas positionnes
			// correctement
			if (!board.isPositionable(xDest, yDest)) {
				clearSrcPosition();
				clearDestPosition();
				break;
			}

			players[pToken].getAuk(leftToPosition / nPlayers)
					.move(xDest, yDest);
			board.setTakenTile(xDest, yDest);
			leftToPosition++;
			nextPlayer();
			if (leftToPosition == nPlayers * nAuks) {
				timeline = Timeline.MOVEMENT;
				clearSrcPosition();
				clearDestPosition();
			}
			break;
		case MOVEMENT:
			// TODO: boucler tant que tous les pingouins ne sont pas bloques
			Auk auk = players[pToken].getAuk(xSrc, ySrc);
			if (auk == null || !board.canMove(xSrc, ySrc, xDest, yDest)) {
				clearSrcPosition();
				clearDestPosition();
				break;
			}

			auk.move(xDest, yDest);
			board.setTakenTile(xDest, yDest);
			players[pToken].fish(board.emptyTile(xSrc, ySrc));
			nextPlayer();
			if (isEndOfMatch()) {
				timeline = Timeline.SCORE;
			}
			break;
		case SCORE:
			// TODO: ajouter les poissons sous les pingouins
			for (int i = 0; i < nPlayers; i++) {
				for (int j = 0; j < nAuks; j++) {
					int ax = players[i].getAuk(j).getxPos();
					int ay = players[i].getAuk(j).getyPos();
					players[i].fish(board.emptyTile(ax, ay));
				}
			}
			// TODO: envoyer players[] a l'activit� de resultat
			// ...
			break;
		case END:
			// TODO: retourner sur ecran de selection du nombre de joueurs
			// ..
			break;
		default:
			break;
		}
	}

	private void setAuksCount() {
		if (nPlayers == 2) {
			nAuks = 4;
		} else if (nPlayers == 3) {
			nAuks = 3;
		} else {
			nAuks = 2;
		}
	}

	public boolean isEndOfMatch() {
		for (int i = 0; i < nPlayers; i++) {
			for (int j = 0; j < nAuks; j++) {
				if (!board.isBlocked(players[i].getAuk(j))) {
					return false;
				}
			}
		}
		return true;
	}

	public void nextPlayer() {
		boolean tmp = false;
		while (!tmp) {
			if (pToken == nPlayers - 1) {
				pToken = 0;
			} else {
				pToken++;
			}
			if (!board.isBlocked(players[pToken], nAuks)) {
				tmp = true;
			}
		}
	}

	public Tile getTile(int x, int y) {
		return board.getTile(x, y);
	}

	public void setSrcPos(int x, int y) {
		xSrc = x;
		ySrc = y;
	}

	public void setDestPos(int x, int y) {
		xDest = x;
		yDest = y;
	}

	public void clearSrcPosition() {
		xSrc = -1;
		ySrc = -1;
	}

	public void clearDestPosition() {
		xDest = -1;
		yDest = -1;
	}

	public boolean isSrcSelected() {
		return xSrc != -1 && ySrc != -1;
	}

	public boolean isDestSelected() {
		return xDest != -1 && yDest != -1;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Player[] getPlayers() {
		return players;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
	}

	public int getnPlayers() {
		return nPlayers;
	}

	public void setnPlayers(int nPlayers) {
		this.nPlayers = nPlayers;
	}

	public Timeline getTimeline() {
		return timeline;
	}

	public void setTimeline(Timeline timeline) {
		this.timeline = timeline;
	}

	public int getnAuks() {
		return nAuks;
	}

	public void setnAuks(int nAuks) {
		this.nAuks = nAuks;
	}
}
