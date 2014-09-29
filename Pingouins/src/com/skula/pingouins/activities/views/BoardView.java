package com.skula.pingouins.activities.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import com.skula.pingouins.constants.Cnst;
import com.skula.pingouins.services.Drawer;
import com.skula.pingouins.services.GameEngine;

public class BoardView extends View {
	private Drawer drawer;
	private GameEngine engine;
	private int xTile;
	private int yTile;

	public BoardView(Context context, int nPlayers) {
		super(context);
		this.engine = new GameEngine(nPlayers);
		this.drawer = new Drawer(context.getResources(), engine);
		this.xTile = -1;
		this.yTile = -1;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:

			break;
		case MotionEvent.ACTION_MOVE:

			break;
		case MotionEvent.ACTION_UP:
			if (getTile(x, y)) {
				if (!engine.isSrcSelected()) {
					engine.setSrcPos(xTile, yTile);
					try{
					if(engine.canProcess()){
						engine.process();
					}
					}catch(Exception e){
						e.getMessage();
					}
				} else {
					if (!engine.isDestSelected()) {
						engine.setDestPos(xTile, yTile);
						if(engine.canProcess()){
							engine.process();
						}
					}else{
						
					}
				}
			}else{
				
			}
			invalidate();
			break;
		}
		return true;
	}

	public boolean getTile(int x, int y) {
		int dx = 5;
		int dy = 50;
		int x0 = Cnst.X0_TILES;
		int y0 = Cnst.Y0_TILES;
		y0 += 10;
		Rect rect = null;
		for (int j = 0; j < Cnst.ROW_COUNT; j++) {
			int cpt = Cnst.COLUMN_COUNT;
			x0 = Cnst.X0_TILES;
			if (j % 2 != 0) {
				x0 += 125 / 2;
				cpt--;
			}
			for (int i = 0; i < cpt; i++) {
				rect = new Rect(x0, y0, x0 + 125-dx, y0 + 115-dy);
				if (rect.contains(x, y)) {
					//engine.setMessage("(" + i + "," + j + ")");
					xTile = i;
					yTile = j;
					return true;
				}
				x0 += 125 - dx;
			}
			y0 += 115 - dy;
		}
		//engine.setMessage("null");
		xTile = -1;
		yTile = -1;
		return false;
	}

	@Override
	public void draw(Canvas canvas) {
		drawer.draw(canvas);
	}
}
