package com.skula.pingouins.services;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.skula.pingouins.R;
import com.skula.pingouins.constants.Cnst;
import com.skula.pingouins.models.Player;

public class Drawer {
	private GameEngine engine;
	private Paint paint;
	private Resources res;
	private String message;

	public Drawer(Resources res, GameEngine engine) {
		this.engine = engine;
		this.res = res;
		this.paint = new Paint();
		paint.setColor(Color.RED);
		paint.setTextSize(40f);
		paint.setStyle(Paint.Style.STROKE);
		this.message="prout";
	}

	public void draw(Canvas c) {
		drawTiles(c);
		drawPlayer(c, new Rect(0+12,0,100+12, 100), R.drawable.auk_blue);
		drawKeys(c);
		c.drawText(message, 1050, 700, paint);
		//drawPlayers(c);
		//drawScore(c);
	}
	
	private void drawKeys(Canvas c) {
		int dx=0;
		int dy = 28;
		int x = Cnst.X0_TILES;
		int y = Cnst.Y0_TILES;
		Rect rect = null;
		for (int j = 0; j < Cnst.ROW_COUNT; j++) {
			int cpt = Cnst.COLUMN_COUNT;
			x=0;
			if (j % 2 != 0) {
				x += 125 / 2;
				cpt--;
			}
			for (int i = 0; i < cpt; i++) {
				rect = new Rect(x,y+15,x+125, y+115-15);
				c.drawRect(rect, paint);
				x += 125-dx;
			}
			y += 115-dy;
		}
	}

	private void drawTiles(Canvas c) {
		int dx=0;
		int dy = 28;
		int x = Cnst.X0_TILES;
		int y = Cnst.Y0_TILES;
		Rect rect = null;
		for (int j = 0; j < Cnst.ROW_COUNT; j++) {
			int cpt = Cnst.COLUMN_COUNT;
			x=0;
			if (j % 2 != 0) {
				x += 125 / 2;
				cpt--;
			}
			for (int i = 0; i < cpt; i++) {
				rect = new Rect(x,y,x+125, y+115);
				switch (engine.getTile(i, j).getFishCount()) {
				case 1:
					drawAuk(c, rect, R.drawable.nfish1);
					break;
				case 2:
					drawAuk(c, rect, R.drawable.nfish2);
					break;
				case 3:
					drawAuk(c, rect, R.drawable.nfish3);
					break;
				default:
					break;
				}
				x += 125-dx;
			}
			y += 115-dy;
		}
	}

	private void drawPlayers(Canvas c) {
		Rect rect = null;
		for (int i = 0; i < engine.getnPlayers(); i++) {
			for (int j = 0; j < engine.getnAuks(); j++) {
				
			}
		}
	}

	private void drawScore(Canvas c) {
		for (Player p: engine.getScore()) {

		}
	}
	
	private void drawAuk(Canvas canvas, Rect rect, int id) {
		canvas.drawBitmap(getPict(id), new Rect(0,0, Cnst.TILE_WIDHT, Cnst.TILE_HIGHT), rect, null);
	}
	
	private void drawPlayer(Canvas canvas, Rect rect, int id) {
		canvas.drawBitmap(getPict(id), new Rect(0,0, Cnst.PLAYER_WIDTH, Cnst.PLAYER_HIGHT), rect, null);
	}

	private Bitmap getPict(int id) {
		return BitmapFactory.decodeStream(res.openRawResource(id));
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
