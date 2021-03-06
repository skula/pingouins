package com.skula.pingouins.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.content.Intent;

import com.skula.pingouins.activities.BoardActivity;
import com.skula.pingouins.activities.PlayersActivity;
import com.skula.pingouins.constants.Cnst;
import com.skula.pingouins.enums.Timeline;
import com.skula.pingouins.models.Auk;
import com.skula.pingouins.models.Board;
import com.skula.pingouins.models.Player;
import com.skula.pingouins.models.Tile;

public class GameEngine {
	private Context context;
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

	private String message;

	public GameEngine(int nPlayers, Context context) {
		this.context = context;
		this.message = "";
		this.board = new Board();
		this.board.shuffleTiles();
		//this.board.mockTiles();

		this.nPlayers = nPlayers;
		if (nPlayers == 2) {
			nAuks = 4;
		} else if (nPlayers == 3) {
			nAuks = 3;
		} else {
			nAuks = 2;
		}
		this.leftToPosition = 0;
		this.players = new Player[nPlayers];
		for (int i = 0; i < nPlayers; i++) {
			players[i] = new Player(nAuks, i);
		}
		this.pToken = 0;

		//this.timeline = Timeline.CHOOSE_COLOR;
		this.timeline = Timeline.POSITIONING;

		clearSrcPosition();
		clearDestPosition();
		
		setMessage();
	}
	
	public void setMessage(){
		String color = Cnst.getPictureLabel(players[pToken].getColor());
		
		switch (timeline) {
		case POSITIONING:
			message = "Joueur " + color + " place";
			break;
		case MOVEMENT:
			message = "Joueur " + color + " deplace";
			break;
		default:
			break;
		}
	}

	public boolean canProcess() {
		switch (timeline) {
		case POSITIONING:
			if(!isSrcSelected()){
				return false;
			}
			
			if (!board.isPositionable(xSrc, ySrc)) {
				clearSrcPosition();
				clearDestPosition();
				return false;
			}
			return true;
		case MOVEMENT:
			if(!isDestSelected()){
				return false;
			}
			Auk auk = players[pToken].getAuk(xSrc, ySrc);
			if (auk == null || !board.canMove(xSrc, ySrc, xDest, yDest)) {
				clearSrcPosition();
				clearDestPosition();
				return false;
			}
			return true;
		case SCORE:
			return true;
		case END:
			return true;
		default:
			return false;
		}
	}

	public void process() {
		Auk auk = null;
		switch (timeline) {
		case POSITIONING:
			int n = leftToPosition / nPlayers;
			auk = players[pToken].getAuk(n);
			auk.move(xSrc, ySrc);
			auk.setInGame(true);
			board.setTakenTile(xSrc, ySrc);
			leftToPosition++;
			nextPlayer();
			
			if (leftToPosition == nPlayers * nAuks) {
				timeline = Timeline.MOVEMENT;
			}
			
			clearSrcPosition();
			clearDestPosition();
			setMessage();
			break;
		case MOVEMENT:
			auk = players[pToken].getAuk(xSrc, ySrc);
			auk.move(xDest, yDest);
			board.setTakenTile(xDest, yDest);
			players[pToken].fish(board.emptyTile(xSrc, ySrc));
			
			if (isEndOfMatch()) {
				timeline = Timeline.END;
				message="Cliquer pour demarrer une nouvelle partie";
			}else{
				nextPlayer();
				setMessage();
			}
			
			clearSrcPosition();
			clearDestPosition();
			break;
		case SCORE:
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
			Intent intent = new Intent(context, PlayersActivity.class);
			context.startActivity(intent);
			break;
		default:
			break;
		}
	}

	private boolean isEndOfMatch() {
		for (int i = 0; i < nPlayers; i++) {
			/*for (int j = 0; j < nAuks; j++) {
				if (!board.isBlocked(players[i].getAuk(j))) {
					return false;
				}
			}*/
			if (!board.isBlocked(players[i], nAuks)) {
				return false;
			}
		}
		return true;
	}

	private void nextPlayer() {
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

	public List<Player> getScore() {
		List<Player> res = new ArrayList<Player>();
		for (int i = 0; i < nPlayers; i++) {
			res.add(players[i]);
		}
		Collections.sort(res);
		return res;
	}
	
	public List<Auk> getAuksByOrder() {
		List<Auk> res = new ArrayList<Auk>();
		for(int i=0; i<nPlayers; i++){
			for(int j=0; j< nAuks; j++){
				if (getPlayers()[i].getAuk(j).isInGame()) {
					res.add(players[i].getAuk(j));
				}
			}
		}
		Collections.sort(res);
		return res;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getxSrc() {
		return xSrc;
	}

	public int getySrc() {
		return ySrc;
	}

	public int getxDest() {
		return xDest;
	}

	public int getyDest() {
		return yDest;
	}
}
