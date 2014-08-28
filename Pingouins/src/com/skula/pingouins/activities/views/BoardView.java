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

	public BoardView(Context context) {
		super(context);
		this.engine = new GameEngine(2);
		this.drawer = new Drawer(context.getResources(), engine);
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
			getTile(x, y);
			invalidate();
			break;
		}
		return true;
	}

	public void getTile(int x, int y) {
		int dx = 0;
		int dy = 28;
		int x0 = Cnst.X0_TILES;
		int y0 = Cnst.Y0_TILES;
		Rect rect = null;
		for (int j = 0; j < Cnst.ROW_COUNT; j++) {
			int cpt = Cnst.COLUMN_COUNT;
			x0 = 0;
			if (j % 2 != 0) {
				x0 += 125 / 2;
				cpt--;
			}
			for (int i = 0; i < cpt; i++) {
				rect = new Rect(x0, y0 + 15, x0 + 125, y0 + 115 - 15);
				if (rect.contains(x, y)) {
					drawer.setMessage("(" + i + "," + j + ")");
					return;
				}
				x0 += 125 - dx;
			}
			y0 += 115 - dy;
		}
		drawer.setMessage("caca");
	}

	@Override
	public void draw(Canvas canvas) {
		drawer.draw(canvas);
	}
}
