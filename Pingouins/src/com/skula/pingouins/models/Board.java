package com.skula.pingouins.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.skula.pingouins.constants.Cnst;
import com.skula.pingouins.enums.Direction;

public class Board {
	private Tile[][] tiles;

	public Board() {
		this.tiles = new Tile[Cnst.ROW_COUNT][Cnst.COLUMN_COUNT];
	}

	public boolean isBlocked(Player player, int nAuks) {
		for (int i = 0; i < nAuks; i++) {
			if (!isBlocked(player.getAuk(i))) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isBlocked(Auk auk) {
		int x = auk.getxPos();
		int y = auk.getyPos();
		int cpt = 0;

		if (y - 1 >= 0) {
			cpt += tiles[x][y - 1] != null && tiles[x][y - 1].isSafe() ? tiles[x][y - 1].getFishCount() : 0; // haut
		}

		if (x + 1 < Cnst.COLUMN_COUNT) {
			cpt += tiles[x + 1][y] != null && tiles[x+1][y].isSafe() ? tiles[x + 1][y].getFishCount() : 0;
		}

		if (x - 1 >= 0) {
			cpt += tiles[x - 1][y] != null && tiles[x-1][y].isSafe() ? tiles[x - 1][y].getFishCount() : 0; // gauche
		}

		if (y + 1 < Cnst.ROW_COUNT) {
			cpt += tiles[x][y + 1] != null && tiles[x][y + 1].isSafe() ? tiles[x][y + 1].getFishCount() : 0; // bas
		}

		if (y % 2 != 0) { // ligne de 7 tuiles
			if (x + 1 < Cnst.COLUMN_COUNT && y - 1 >= 0) {
				cpt += tiles[x + 1][y - 1] != null && tiles[x+1][y - 1].isSafe() ? tiles[x + 1][y - 1]
						.getFishCount() : 0; // haut droit
			}

			if (x + 1 < Cnst.COLUMN_COUNT && y + 1 < Cnst.ROW_COUNT) {
				cpt += tiles[x + 1][y + 1] != null && tiles[x+1][y + 1].isSafe() ? tiles[x + 1][y + 1]
						.getFishCount() : 0; // bas droite
			}
		} else {
			if (x - 1 >= 0 && y - 1 >= 0) {
				cpt = tiles[x - 1][y - 1] != null && tiles[x-1][y - 1].isSafe() ? tiles[x - 1][y - 1]
						.getFishCount() : 0; // haut gauche
			}

			if (x - 1 >= 0 && y + 1 < Cnst.ROW_COUNT) {
				cpt += tiles[x - 1][y + 1] != null && tiles[x-1][y + 1].isSafe() ? tiles[x - 1][y + 1]
						.getFishCount() : 0; // bas gauche
			}
		}

		return cpt == 0;
	}

	public void mockTiles() {
		for (int j = 0; j < Cnst.COLUMN_COUNT; j++) {
			for (int i = 0; i < Cnst.ROW_COUNT; i++) {
				if (i % 2 != 0 && j == Cnst.COLUMN_COUNT - 1) {

				} else {
					tiles[j][i] = new Tile(0);
				}
			}
		}

		tiles[4][1].setFishCount(1);
	}

	public boolean isPositionable(int x, int y) {
		return tiles[x][y].isOneFish() && !tiles[x][y].isTaken();
	}

	public boolean canMove(int x0, int y0, int x1, int y1) {
		return isReachable(x0, y0, x1, y1) && isCollision(x0, y0, x1, y1);
	}
	
	private boolean isReachable(int x0, int y0, int x1, int y1) {
		Direction dir = Direction.getDirection(x0, y0, x1, y1);
		boolean tmp = true;
		if (dir.isForbidden()) {
			return false;
		}
		if (dir.equals(Direction.EAST) || dir.equals(Direction.WEST)) {
			return true;
		}

		if (y0 % 2 != 0) { // ligne de 7 tuiles
			switch (dir) {
			case NORTH_EAST:
				for (int j = y0 - 1, i = x0 + 1; j >= 0
						&& i < Cnst.COLUMN_COUNT && tiles[i][j] != null; j--) {
					if (x1 == i && y1 == j) {
						return true;
					}
					if (!tmp) {
						i++;
					}
					tmp = !tmp;
				}
				break;
			case NORTH_WEST:
				for (int i = x0, j = y0 - 1; j >= 0 && i >= 0; j--) {
					if (x1 == i && y1 == j) {
						return true;
					}
					if (tmp) {
						i--;
					}
					tmp = !tmp;
				}
				break;
			case SOUTH_EAST:
				for (int i = x0 + 1, j = y0 + 1; j < Cnst.ROW_COUNT
						&& i < Cnst.COLUMN_COUNT && tiles[i][j] != null; j++) {
					if (x1 == i && y1 == j) {
						return true;
					}
					if (!tmp) {
						i++;
					}
					tmp = !tmp;
				}
				break;
			case SOUTH_WEST:
				for (int i = x0, j = y0 + 1; j < Cnst.ROW_COUNT && i >= 0; j++) {
					if (x1 == i && y1 == j) {
						return true;
					}
					if (tmp) {
						i--;
					}
					tmp = !tmp;
				}
				break;
			default:
				break;
			}
		} else { // ligne de 8 tuiles
			switch (dir) {
			case NORTH_EAST:
				for (int j = y0 - 1, i = x0; j >= 0 && i < Cnst.COLUMN_COUNT
						&& tiles[i][j] != null; j--) {
					if (i == x1 && j == y1) {
						return true;
					}
					if (tmp) {
						i++;
					}
					tmp = !tmp;
				}
				break;
			case NORTH_WEST:
				for (int j = y0 - 1, i = x0 - 1; j >= 0 && i >= 0; j--) {
					if (x1 == i && y1 == j) {
						return true;
					}
					if (!tmp) {
						i--;
					}
					tmp = !tmp;
				}
				break;
			case SOUTH_EAST:
				for (int i = x0, j = y0 + 1; j < Cnst.ROW_COUNT
						&& i < Cnst.COLUMN_COUNT && tiles[i][j] != null; j++) {
					if (x1 == i && y1 == j) {
						return true;
					}
					if (tmp) {
						i++;
					}
					tmp = !tmp;
				}
				break;
			case SOUTH_WEST:
				for (int i = x0 - 1, j = y0 + 1; j < Cnst.ROW_COUNT && i >= 0; j++) {
					if (x1 == i && y1 == j) {
						return true;
					}
					if (!tmp) {
						i--;
					}
					tmp = !tmp;
				}
				break;
			default:
				break;
			}
		}

		return false;
	}

	public boolean isCollision(int x0, int y0, int x1, int y1) {
		int dx = x1 - x0;
		int dy = y1 - y0;
		boolean tmp = true;

		if (y0 % 2 != 0) { // ligne de 7 tuiles
			switch (Direction.getDirection(x0, y0, x1, y1)) {
			case NORTH_EAST: // OK
				for (int j = y0 - 1, i = x0 + 1; j >= y0 + dy; j--) {
					if (!tiles[i][j].isSafe()) {
						return false;
					}
					if (!tmp) {
						i++;
					}
					tmp = !tmp;
				}
				break;
			case NORTH_WEST: // OK
				for (int i = x0, j = y0 - 1; j >= y0 + dy; j--) {
					if (!tiles[i][j].isSafe()) {
						return false;
					}
					if (tmp) {
						i--;
					}
					tmp = !tmp;
				}
				break;
			case SOUTH_EAST: // OK
				for (int i = x0 + 1, j = y0 + 1; j <= y0 + dy; j++) {
					if (!tiles[i][j].isSafe()) {
						return false;
					}
					if (!tmp) {
						i++;
					}
					tmp = !tmp;
				}
				break;
			case SOUTH_WEST: // OK
				for (int i = x0, j = y0 + 1; j <= y0 + dy; j++) {
					if (!tiles[i][j].isSafe()) {
						return false;
					}
					if (tmp) {
						i--;
					}
					tmp = !tmp;
				}
				break;
			case EAST: // OK
				for (int i = x0 + 1; i <= x0 + dx; i++) {
					if (!tiles[i][y0].isSafe()) {
						return false;
					}
				}
				break;
			case WEST: // OK
				for (int i = x0 - 1; i >= x0 + dx; i--) {
					if (!tiles[i][y0].isSafe()) {
						return false;
					}
				}
				break;
			default:
				break;
			}
		} else {
			switch (Direction.getDirection(x0, y0, x1, y1)) {
			case NORTH_EAST: // OK
				for (int j = y0 - 1, i = x0; j >= y0 + dy; j--) {
					if (!tiles[i][j].isSafe()) {
						return false;
					}
					if (tmp) {
						i++;
					}
					tmp = !tmp;
				}
				break;
			case NORTH_WEST: // OK
				for (int j = y0 - 1, i = x0 - 1; j >= y0 + dy; j--) {
					if (!tiles[i][j].isSafe()) {
						return false;
					}
					if (!tmp) {
						i--;
					}
					tmp = !tmp;
				}
				break;
			case SOUTH_EAST: // OK
				for (int i = x0, j = y0 + 1; j <= y0 + dy; j++) {
					if (!tiles[i][j].isSafe()) {
						return false;
					}
					if (tmp) {
						i++;
					}
					tmp = !tmp;
				}
				break;
			case SOUTH_WEST: // OK
				for (int i = x0 - 1, j = y0 + 1; j <= y0 + dy; j++) {
					if (!tiles[i][j].isSafe()) {
						return false;
					}
					if (!tmp) {
						i--;
					}
					tmp = !tmp;
				}
				break;
			case EAST: // OK
				for (int i = x0 + 1; i <= x0 + dx; i++) {
					if (!tiles[i][y0].isSafe()) {
						return false;
					}
				}
				break;
			case WEST: // OK
				for (int i = x0 - 1; i >= x0 + dx; i--) {
					if (!tiles[i][y0].isSafe()) {
						return false;
					}
				}
				break;
			default:
				break;
			}
		}
		return true;
	}

	public void shuffleTiles() {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < Cnst.THREE_FISH_TILE_COUNT; i++) {
			list.add(3);
		}
		for (int i = 0; i < Cnst.TWO_FISH_TILE_COUNT; i++) {
			list.add(2);
		}
		for (int i = 0; i < Cnst.ONE_FISH_TILE_COUNT; i++) {
			list.add(1);
		}

		long seed = System.nanoTime();
		Collections.shuffle(list, new Random(seed));
		seed = System.nanoTime();

		int cpt = 0;
		for (int j = 0; j < Cnst.COLUMN_COUNT; j++) {
			for (int i = 0; i < Cnst.ROW_COUNT; i++) {
				if (i % 2 != 0 && j == Cnst.COLUMN_COUNT - 1) {

				} else {
					tiles[j][i] = new Tile(list.get(cpt));
					cpt++;
				}
			}
		}
	}

	public void show() {
		System.out.println("**** DAMIER ****");
		for (int i = 0; i < Cnst.ROW_COUNT; i++) {
			for (int j = 0; j < Cnst.COLUMN_COUNT; j++) {
				if (i % 2 != 0 && j == 0) {
					System.out.print(" ");
				}

				if (i % 2 != 0 && j == Cnst.COLUMN_COUNT - 1) {

				} else {
					System.out.print(tiles[j][i].getFishCount() + " ");
				}
			}
			System.out.println();
		}
		System.out.println("****************");
	}

	public Tile getTile(int x, int y) {
		return tiles[x][y];
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}

	public void setTakenTile(int x, int y) {
		tiles[x][y].setTaken(true);
	}

	public int emptyTile(int x, int y) {
		return tiles[x][y].empty();
	}
}
