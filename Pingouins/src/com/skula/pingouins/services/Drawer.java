package com.skula.pingouins.services;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.skula.pingouins.R;
import com.skula.pingouins.constants.Cnst;
import com.skula.pingouins.constants.PictureLibrary;
import com.skula.pingouins.models.Auk;
import com.skula.pingouins.models.Player;

public class Drawer {
	private GameEngine engine;
	private PictureLibrary lib;
	private Paint paint;
	private Resources res;

	public Drawer(Resources res, GameEngine engine) {
		this.engine = engine;
		this.lib = new PictureLibrary(res);
		this.res = res;
		this.paint = new Paint();
		paint.setColor(Color.RED);
		paint.setTextSize(30f);
	}

	public void draw(Canvas c) {
		drawBackground(c);
		drawTiles(c);
		//drawKeys(c);
		if(engine.isSrcSelected()){
			drawSrcKeys(c);
		}
		paint.setColor(Color.GREEN);
		c.drawText(engine.getMessage(), 980, 680, paint);
		drawPlayers(c);
		drawScore(c);
	}
	
	private void drawSrcKeys(Canvas c) {
		int x0 = engine.getxSrc() * (125-5) + Cnst.X0_TILES;
		int y0 = engine.getySrc() * (115 - 50) + Cnst.Y0_TILES;
		if (engine.getySrc() % 2 != 0) {
			x0 += 125 / 2;
		}
		
		x0+=10;
		y0+=5;
		c.drawBitmap(lib.get(R.drawable.tilesel), new Rect(0, 0, 100, 98), new Rect(x0, y0, x0 + 100, y0 + 98), paint);
	}

	private void drawKeys(Canvas c) {		
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(Color.RED);
		int dx = 5;
		int dy = 50;
		int x = Cnst.X0_TILES;
		int y = Cnst.Y0_TILES;
		y += 10;
		Rect rect = null;
		for (int j = 0; j < Cnst.ROW_COUNT; j++) {
			int cpt = Cnst.COLUMN_COUNT;
			x = Cnst.X0_TILES;
			if (j % 2 != 0) {
				x += 125 / 2;
				cpt--;
			}
			for (int i = 0; i < cpt; i++) {
				rect = new Rect(x, y, x + 125-dx, y + 115-dy);
				c.drawRect(rect, paint);
				x += 125 - dx;
			}
			y += 115 - dy;
		}
		paint.setStyle(Paint.Style.FILL);
	}

	private void drawTiles(Canvas c) {
		int dx = 5;
		int dy = 50;
		int x = Cnst.X0_TILES;
		int y = Cnst.Y0_TILES;
		Rect rect = null;
		for (int j = 0; j < Cnst.ROW_COUNT; j++) {
			int cpt = Cnst.COLUMN_COUNT;
			x = Cnst.X0_TILES;
			if (j % 2 != 0) {
				x += 125 / 2;
				cpt--;
			}
			for (int i = 0; i < cpt; i++) {
				rect = new Rect(x, y, x + 125, y + 115);
				switch (engine.getTile(i, j).getFishCount()) {
				case 1:
					drawTile(c, rect, R.drawable.nfish1);
					break;
				case 2:
					drawTile(c, rect, R.drawable.nfish2);
					break;
				case 3:
					drawTile(c, rect, R.drawable.nfish3);
					break;
				default:
					break;
				}
				x += 125 - dx;
			}
			y += 115 - dy;
		}
	}

	private void drawPlayers(Canvas c) {
		Rect rect = null;
		for(Auk auk : engine.getAuksByOrder()){
			int x = auk.getxPos();
			int y = auk.getyPos();
			int x0 = x * (125-5) + Cnst.X0_TILES;
			int y0 = y * (115 - 50) + Cnst.Y0_TILES;
			if (y % 2 != 0) {
				x0 += 125 / 2;
			}
			x0 += 15;
			y0 -= 65;
			rect = new Rect(x0, y0, x0 + Cnst.PLAYER_WIDTH, y0 + Cnst.PLAYER_HIGHT);
			switch (auk.getColor()) {
			case Cnst.COLOR_BLUE:
				drawAuk(c, rect, R.drawable.aukblue);
				break;
			case Cnst.COLOR_GREEN:
				drawAuk(c, rect, R.drawable.aukgreen);
				break;
			case Cnst.COLOR_RED:
				drawAuk(c, rect, R.drawable.aukred);
				break;
			case Cnst.COLOR_PURPLE:
				drawAuk(c, rect, R.drawable.aukpurple);
				break;
			default:
				break;
			}
		}
	}
	
	/*private void drawPlayers(Canvas c) {
		Rect rect = null;
		for (int i = 0; i < engine.getnPlayers(); i++) {
			for (int j = 0; j < engine.getnAuks(); j++) {
				if (engine.getPlayers()[i].getAuk(j).isInGame()) {
					int x = engine.getPlayers()[i].getAuk(j).getxPos();
					int y = engine.getPlayers()[i].getAuk(j).getyPos();
					int x0 = x * (125-5) + Cnst.X0_TILES;
					int y0 = y * (115 - 50) + Cnst.Y0_TILES;
					if (y % 2 != 0) {
						x0 += 125 / 2;
					}

					x0 += 15;
					y0 -= 65;

					rect = new Rect(x0, y0, x0 + Cnst.PLAYER_WIDTH, y0
							+ Cnst.PLAYER_HIGHT);
					switch (engine.getPlayers()[i].getColor()) {
					case Cnst.COLOR_BLUE:
						drawAuk(c, rect, R.drawable.aukblue);
						break;
					case Cnst.COLOR_GREEN:
						drawAuk(c, rect, R.drawable.aukgreen);
						break;
					case Cnst.COLOR_RED:
						drawAuk(c, rect, R.drawable.aukred);
						break;
					case Cnst.COLOR_PURPLE:
						drawAuk(c, rect, R.drawable.aukpurple);
						break;
					default:
						break;
					}
				}
			}
		}
	}*/

	private void drawScore(Canvas c) {
		int x0 = 1140;
		int y0 = 150;
		int id = 0;
		for (Player p : engine.getScore()) {
			switch(p.getColor()){
			case Cnst.COLOR_BLUE:
				id = R.drawable.scoreblue;
				break;
			case Cnst.COLOR_GREEN:
				id = R.drawable.scoregreen;
				break;
			case Cnst.COLOR_RED:
				id = R.drawable.scorered;
				break;
			case Cnst.COLOR_PURPLE:
				id = R.drawable.scorepurple;
				break;
			default:
				break;
			}
			drawScore(c, new Rect(x0,y0,x0+150,y0+80), id);
			paint.setColor(Color.WHITE);
			c.drawText(p.getFishCount()+"", x0+18+70, y0+50, paint);
			y0+=90;
		}
	}

	private void drawBackground(Canvas canvas) {
		paint.setColor(Color.rgb(63, 170, 215));
		canvas.drawRect(new Rect(0,0,1280,800), paint);
	}
	
	private void drawTile(Canvas canvas, Rect rect, int id) {
		canvas.drawBitmap(lib.get(id), new Rect(0, 0, Cnst.TILE_WIDTH,
				Cnst.TILE_HIGHT), rect, null);
	}

	private void drawAuk(Canvas canvas, Rect rect, int id) {
		canvas.drawBitmap(lib.get(id), new Rect(0, 0, Cnst.PLAYER_WIDTH,
				Cnst.PLAYER_HIGHT), rect, null);
	}
	
	private void drawScore(Canvas canvas, Rect rect, int id) {
		canvas.drawBitmap(lib.get(id), new Rect(0, 0, 100,
				50), rect, null);
	}
}
