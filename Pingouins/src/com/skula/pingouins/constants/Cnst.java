package com.skula.pingouins.constants;

import com.skula.pingouins.R;

public class Cnst {
	public static final int ROW_COUNT = 8;
	public static final int COLUMN_COUNT = 8;

	public static final int ONE_FISH_TILE_COUNT = 30;
	public static final int TWO_FISH_TILE_COUNT = 20;
	public static final int THREE_FISH_TILE_COUNT = 10;

	public static final int COLOR_RED = 0;
	public static final int COLOR_GREEN = 1;
	public static final int COLOR_BLUE = 2;
	public static final int COLOR_PURPLE = 3;
	
	public static int getPictureId(int color){
		switch (color) {
		case Cnst.COLOR_BLUE:
			return R.drawable.aukblue;
		case Cnst.COLOR_GREEN:
			return R.drawable.aukgreen;
		case Cnst.COLOR_RED:
			return R.drawable.aukred;
		case Cnst.COLOR_PURPLE:
			return R.drawable.aukpurple;
		default:
			return -1;
		}
	}
	
	public static String getPictureLabel(int color){
		switch (color) {
		case Cnst.COLOR_BLUE:
			return "bleu";
		case Cnst.COLOR_GREEN:
			return "vert";
		case Cnst.COLOR_RED:
			return "rouge";
		case Cnst.COLOR_PURPLE:
			return "violet";
		default:
			return "null";
		}
	}
	
	public static final int TILE_WIDTH = 100;
	public static final int TILE_HIGHT = 98;

	public static final int PLAYER_WIDTH = 100;
	public static final int PLAYER_HIGHT = 130;
	
	public static final int X0_TILES = 75;
	public static final int Y0_TILES = 75;
}
