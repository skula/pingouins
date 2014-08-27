package com.skula.pingouins.services;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.skula.pingouins.R;
import com.skula.pingouins.constants.Cnst;
import com.skula.pingouins.models.Player;

public class Drawer {
	private GameEngine engine;
	private Paint paint;
	private Resources res;

	public Drawer(Resources res, GameEngine engine) {
		this.engine = engine;
		this.res = res;
		this.paint = new Paint();
	}

	public void draw(Canvas c) {
		drawTiles(c);
		//drawPlayers(c);
		//drawScore(c);
	}

	public void drawTiles(Canvas c) {
		int x = Cnst.X0_TILES;
		int y = Cnst.Y0_TILES;
		Rect rect = null;
		for (int j = 0; j < Cnst.ROW_COUNT; j++) {
			int cpt = Cnst.COLUMN_COUNT;
			x=0;
			if (j % 2 != 0) {
				x += Cnst.TILE_SIZE / 2;
				cpt--;
			}
			for (int i = 0; i < cpt; i++) {
				rect = new Rect(x,y,x+Cnst.TILE_SIZE, y+Cnst.TILE_SIZE);
				switch (engine.getTile(i, j).getFishCount()) {
				case 1:
					drawBitmap(c, rect, R.drawable.nfish1);
					break;
				case 2:
					drawBitmap(c, rect, R.drawable.nfish2);
					break;
				case 3:
					drawBitmap(c, rect, R.drawable.nfish3);
					break;
				default:
					break;
				}
				x += Cnst.TILE_SIZE;
			}
			y += Cnst.TILE_SIZE;
		}
	}

	public void drawPlayers(Canvas c) {
		Rect rect = null;
		for (int i = 0; i < engine.getnPlayers(); i++) {
			for (int j = 0; j < engine.getnAuks(); j++) {
				
			}
		}
	}

	public void drawScore(Canvas c) {
		for (Player p: engine.getScore()) {

		}
	}
	
	private void drawBitmap(Canvas canvas, Rect rect, int id) {
		canvas.drawBitmap(getPict(id), new Rect(0,0, Cnst.TILE_SIZE, Cnst.TILE_SIZE), rect, null);
	}

	private Bitmap getPict(int id) {
		return BitmapFactory.decodeStream(res.openRawResource(id));
	}
}
