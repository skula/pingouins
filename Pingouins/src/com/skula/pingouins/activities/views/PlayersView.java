package com.skula.pingouins.activities.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import com.skula.pingouins.R;

public class PlayersView extends View {
	private Resources res;
	private Paint paint;
	private String message;

	public PlayersView(Context context) {
		super(context);
		res = context.getResources();
		paint = new Paint();
		message = "plop";
	}

	@Override
	public void draw(Canvas canvas) {
		paint.setColor(Color.rgb(63, 170, 215));
		canvas.drawRect(new Rect(0, 0, 1280, 800), paint);
		drawTitle(canvas);
		drawButtons(canvas);
		
		paint.setColor(Color.RED);
		paint.setTextSize(22f);
		canvas.drawText(message, 50, 50, paint);

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
			int n = getCountPlayers(x,y);
			if(n>0){
				message = "nombre de joueurs: " +n;
			}else{
				message = "NULL";
			}
			invalidate();
			break;
		}
		return true;
	}
	
	private int getCountPlayers(int x, int y){
		int x0 = 515;
		int dy = 100;
		int y0 = 350;
		int TITLE_WIDTH = 250;
		int TITLE_HEIGHT = 80;
		
		Rect r = new Rect(x0, y0, x0 + TITLE_WIDTH, y0+ TITLE_HEIGHT);
		if(r.contains(x,y)){
			return 2;
		}
		
		y0+=dy;
		r = new Rect(x0, y0, x0 + TITLE_WIDTH, y0+ TITLE_HEIGHT);
		if(r.contains(x,y)){
			return 3;
		}
		
		y0+=dy;
		r = new Rect(x0, y0, x0 + TITLE_WIDTH, y0+ TITLE_HEIGHT);
		if(r.contains(x,y)){
			return 4;
		}
		
		return 0;
	}

	private void drawTitle(Canvas canvas) {
		canvas.drawBitmap(getPict(R.drawable.title), new Rect(0, 0, 500, 161),
				new Rect(335, 50, 945, 281), null);
	}

	private void drawButtons(Canvas canvas) {
		int x0 = 515;
		int dy = 100;
		int y0 = 350;
		int TITLE_WIDTH = 250;
		int TITLE_HEIGHT = 80;
		// 2 joueurs
		canvas.drawBitmap(getPict(R.drawable.twoplayers), new Rect(0, 0, 250,
				TITLE_HEIGHT), new Rect(x0, y0, x0 + TITLE_WIDTH, y0
				+ TITLE_HEIGHT), null);
		// 3 joueurs
		y0 += dy;
		canvas.drawBitmap(getPict(R.drawable.threeplayers), new Rect(0, 0, 250,
				TITLE_HEIGHT), new Rect(x0, y0, x0 + TITLE_WIDTH, y0
				+ TITLE_HEIGHT), null);
		// 4 joueurs
		y0 += dy;
		canvas.drawBitmap(getPict(R.drawable.fourplayers), new Rect(0, 0, 250,
				TITLE_HEIGHT), new Rect(x0, y0, x0 + TITLE_WIDTH, y0
				+ TITLE_HEIGHT), null);
	}

	private Bitmap getPict(int id) {
		return BitmapFactory.decodeStream(res.openRawResource(id));
	}
}
