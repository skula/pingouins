package com.skula.pingouins.enums;

public enum Direction {
	NORTH, NORTH_EAST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, WEST, NORTH_WEST, NONE;

	public static Direction getDirection(int x0, int y0, int x1, int y1) {
		if (x0 > x1) {
			if (y0 < y1) {
				return Direction.SOUTH_WEST;
			} else {
				if (y0 > y1) {
					return Direction.NORTH_WEST;
				} else {
					return Direction.WEST;
				}
			}
		} else if (x0 < x1) {
			if (y0 < y1) {
				return Direction.SOUTH_EAST;
			} else {
				if (y0 > y1) {
					return Direction.NORTH_EAST;
				} else {
					return Direction.EAST;
				}
			}
		} else {
			int dy = Math.abs(y0 - y1);
			if (dy == 0) {
				return Direction.NONE;
			}

			if (dy > 1) {
				if (y0 > y1) {
					return Direction.NORTH;
				} else {
					return Direction.SOUTH;
				}
			} else {
				if (y0 % 2 != 0) { // si ligne a 7 tuiles
					if (y0 > y1) {
						return Direction.NORTH_WEST;
					} else {
						return Direction.SOUTH_WEST;
					}
				} else {// si ligne a 8 tuiles
					if (y0 > y1) {
						return Direction.NORTH_EAST;
					} else {
						return Direction.SOUTH_EAST;
					}
				}
			}
		}
	}

	public boolean isForbidden() {
		return this.equals(NORTH) || this.equals(SOUTH) || this.equals(NONE);
	}

}
