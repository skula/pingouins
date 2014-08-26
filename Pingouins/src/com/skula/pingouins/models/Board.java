package com.skula.pingouins.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.skula.pingouins.constants.Cnst;
import com.skula.pingouins.enums.Direction;

public class Board {
	private Tile[][] tiles;

	public static void main(String[] args) {
		Board board = new Board();
		board.mockTiles();
		board.show();

		System.out.println(board.isBlocked(new Auk(5, 2)));
	}

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
		cpt += y - 1 >= 0 && tiles[x][y - 1] != null ? tiles[x][y - 1]
				.getFishCount() : 0; // haut
		cpt += x + 1 < Cnst.COLUMN_COUNT && tiles[x + 1][y] != null ? tiles[x + 1][y]
				.getFishCount() : 0; // droite
		cpt += x - 1 >= 0 && tiles[x - 1][y] != null ? tiles[x - 1][y]
				.getFishCount() : 0; // gauche
		cpt += y + 1 < Cnst.ROW_COUNT && tiles[x][y + 1] != null ? tiles[x][y + 1]
				.getFishCount() : 0; // bas

		if (y % 2 != 0) { // ligne de 7 tuiles
			cpt += x + 1 < Cnst.COLUMN_COUNT && y - 1 >= 0
					&& tiles[x + 1][y - 1] != null ? tiles[x + 1][y - 1]
					.getFishCount() : 0; // haut droit

			cpt += x + 1 < Cnst.COLUMN_COUNT && y + 1 < Cnst.ROW_COUNT
					&& tiles[x + 1][y + 1] != null ? tiles[x + 1][y + 1]
					.getFishCount() : 0; // bas droite
		} else {
			cpt = x - 1 >= 0 && y - 1 >= 0 && tiles[x - 1][y - 1] != null ? tiles[x - 1][y - 1]
					.getFishCount() : 0; // haut gauche
			cpt += x - 1 >= 0 && y + 1 < Cnst.ROW_COUNT
					&& tiles[x - 1][y + 1] != null ? tiles[x - 1][y + 1]
					.getFishCount() : 0; // bas gauche
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
		if (!isReachable(x0, y0, x1, y1)) {
			return false;
		} else {
			return isCollision(x0, y0, x1, y1);
		}
	}

	private boolean isReachable(int x0, int y0, int x1, int y1) {
		tiles[x0][y0].setFishCount(8);
		tiles[x1][y1].setFishCount(5);
		Direction dir = Direction.getDirection(x0, y0, x1, y1);
		System.out.println(dir);
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
					tiles[i][j].setFishCount(6);
					System.out.println("i=" + i + ", j=" + j);
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
					tiles[i][j].setFishCount(6);
					System.out.println("i=" + i + ", j=" + j);
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
					tiles[i][j].setFishCount(6);
					System.out.println("i=" + i + ", j=" + j);
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
					tiles[i][j].setFishCount(6);
					System.out.println("i=" + i + ", j=" + j);
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
					tiles[i][j].setFishCount(6);
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
					tiles[i][j].setFishCount(6);
					System.out.println("i=" + i + ", j=" + j);
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
					tiles[i][j].setFishCount(6);
					System.out.println("i=" + i + ", j=" + j);
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
					tiles[i][j].setFishCount(6);
					System.out.println("i=" + i + ", j=" + j);
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

	private boolean isCollision(int x0, int y0, int x1, int y1) {
		int dx = x1 - x0;
		int dy = y1 - y0;
		tiles[x0][y0].setFishCount(8);
		tiles[x1][y1].setFishCount(5);
		Direction dir = Direction.getDirection(x0, y0, x1, y1);

		if (dir.equals(Direction.EAST)) {
			for (int i = x0 + 1; i <= x0 + dx; i++) {
				if (!tiles[i][y0].isSafe()) {
					return false;
				}
				tiles[i][y0].setFishCount(6);
			}
			return true;
		}
		if (dir.equals(Direction.WEST)) {
			for (int i = x0 - 1; i >= x0 + dx; i--) {
				if (!tiles[i][y0].isSafe()) {
					return false;
				}
				tiles[i][y0].setFishCount(6);
			}
			return true;
		}

		boolean tmp = true;
		boolean incr = true;
		int xInc = 0;
		int yInc = 0;
		switch (dir) {
		case NORTH_EAST:
			tmp = y0 % 2 == 0;
			incr = true;
			xInc = y0 % 2 != 0 ? 1 : 0;
			yInc = -1;
			break;
		case NORTH_WEST:
			tmp = y0 % 2 != 0;
			incr = false;
			xInc = y0 % 2 != 0 ? 0 : -1;
			yInc = -1;
			break;
		case SOUTH_EAST:
			tmp = y0 % 2 == 0;
			incr = true;
			xInc = y0 % 2 != 0 ? 1 : 0;
			yInc = 1;
			break;
		case SOUTH_WEST:
			tmp = y0 % 2 != 0;
			incr = false;
			xInc = y0 % 2 != 0 ? 0 : -1;
			yInc = 1;
			break;
		default:
			break;
		}

		if (dir.equals(Direction.NORTH_EAST)
				|| dir.equals(Direction.NORTH_WEST)) {
			for (int j = y0 + yInc, i = x0 + xInc; j >= y0 + dy; j--) {
				if (!tiles[i][y0].isSafe()) {
					return false;
				}
				tiles[i][j].setFishCount(6);
				if (tmp) {
					i = incr ? i + 1 : i - 1;
				}
				tmp = !tmp;
			}
		} else {
			for (int i = x0 + xInc, j = y0 + yInc; j <= y0 + dy; j++) {
				if (!tiles[i][y0].isSafe()) {
					return false;
				}
				tiles[i][j].setFishCount(6);
				if (tmp) {
					i = incr ? i + 1 : i - 1;
				}
				tmp = !tmp;
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

	public boolean canMove2(int x0, int y0, int x1, int y1) {
		int dx = x1 - x0;
		int dy = y1 - y0;
		boolean tmp = true;
		tiles[x0][y0].setFishCount(8);
		tiles[x1][y1].setFishCount(5);

		System.out.println("x0=" + x0 + ", y0=" + y0);
		System.out.println("dx=" + dx + ", dy=" + dy);

		if (y0 % 2 != 0) { // ligne de 7 tuiles
			switch (Direction.getDirection(x0, y0, x1, y1)) {
			case NORTH_EAST: // OK
				for (int j = y0 - 1, i = x0 + 1; j >= y0 + dy; j--) {
					tiles[i][j].setFishCount(6);
					System.out.println("i=" + i + ", j=" + j);
					if (!tmp) {
						i++;
					}
					tmp = !tmp;
				}
				break;
			case NORTH_WEST: // OK
				for (int i = x0, j = y0 - 1; j >= y0 + dy; j--) {
					tiles[i][j].setFishCount(6);
					System.out.println("i=" + i + ", j=" + j);
					if (tmp) {
						i--;
					}
					tmp = !tmp;
				}
				break;
			case SOUTH_EAST: // OK
				for (int i = x0 + 1, j = y0 + 1; j <= y0 + dy; j++) {
					tiles[i][j].setFishCount(6);
					System.out.println("i=" + i + ", j=" + j);
					if (!tmp) {
						i++;
					}
					tmp = !tmp;
				}
				break;
			case SOUTH_WEST: // OK
				for (int i = x0, j = y0 + 1; j <= y0 + dy; j++) {
					tiles[i][j].setFishCount(6);
					System.out.println("i=" + i + ", j=" + j);
					if (tmp) {
						i--;
					}
					tmp = !tmp;
				}
				break;
			case EAST: // OK
				for (int i = x0 + 1; i <= x0 + dx; i++) {
					tiles[i][y0].setFishCount(6);
					System.out.println("i=" + i + ", j=" + y0);
				}
				break;
			case WEST: // OK
				for (int i = x0 - 1; i >= x0 + dx; i--) {
					tiles[i][y0].setFishCount(6);
					System.out.println("i=" + i + ", j=" + y0);
				}
				break;
			default:
				break;
			}
		} else {
			switch (Direction.getDirection(x0, y0, x1, y1)) {
			case NORTH_EAST: // OK
				for (int j = y0 - 1, i = x0; j >= y0 + dy; j--) {
					tiles[i][j].setFishCount(6);
					System.out.println("i=" + i + ", j=" + j);
					if (tmp) {
						i++;
					}
					tmp = !tmp;
				}
				break;
			case NORTH_WEST: // OK
				for (int j = y0 - 1, i = x0 - 1; j >= y0 + dy; j--) {
					tiles[i][j].setFishCount(6);
					System.out.println("i=" + i + ", j=" + j);
					if (!tmp) {
						i--;
					}
					tmp = !tmp;
				}
				break;
			case SOUTH_EAST: // OK
				for (int i = x0, j = y0 + 1; j <= y0 + dy; j++) {
					tiles[i][j].setFishCount(6);
					System.out.println("i=" + i + ", j=" + j);
					if (tmp) {
						i++;
					}
					tmp = !tmp;
				}
				break;
			case SOUTH_WEST: // OK
				for (int i = x0 - 1, j = y0 + 1; j <= y0 + dy; j++) {
					tiles[i][j].setFishCount(6);
					System.out.println("i=" + i + ", j=" + j);
					if (!tmp) {
						i--;
					}
					tmp = !tmp;
				}
				break;
			case EAST: // OK
				for (int i = x0 + 1; i <= x0 + dx; i++) {
					tiles[i][y0].setFishCount(6);
					System.out.println("i=" + i + ", j=" + y0);
				}
				break;
			case WEST: // OK
				for (int i = x0 - 1; i >= x0 + dx; i--) {
					tiles[i][y0].setFishCount(6);
					System.out.println("i=" + i + ", j=" + y0);
				}
				break;
			default:
				break;
			}
		}
		return true;
	}
}
