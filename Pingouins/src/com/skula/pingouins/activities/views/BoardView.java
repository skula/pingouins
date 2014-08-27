package com.skula.pingouins.activities.views;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import com.skula.pingouins.services.Drawer;
import com.skula.pingouins.services.GameEngine;

public class BoardView extends View{
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

			break;
		}
		invalidate();
		return true;
	}
	
	@Override
	public void draw(Canvas canvas) {
		drawer.draw(canvas);
	}
}
