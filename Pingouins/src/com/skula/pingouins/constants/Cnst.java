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
	public static final int COLOR_BLACK = 3;
	
	public static int getPictureId(int color){
		switch (color) {
		case Cnst.COLOR_BLUE:
			return R.drawable.auk_blue;
		case Cnst.COLOR_GREEN:
			return R.drawable.auk_green;
		case Cnst.COLOR_RED:
			return R.drawable.auk_red;
		case Cnst.COLOR_BLACK:
			return R.drawable.auk_black;
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
		case Cnst.COLOR_BLACK:
			return "noir";
		default:
			return "null";
		}
	}
	
	public static final int TILE_WIDTH = 100;
	public static final int TILE_HIGHT = 92;
	public static final int PLAYER_HIGHT = 100;
	public static final int PLAYER_WIDTH = 80;
	
	public static final int X0_TILES = 0;
	public static final int Y0_TILES = 0;
}
