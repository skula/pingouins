package com.skula.pingouins.constants;

import java.util.HashMap;
import java.util.Map;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.skula.pingouins.R;

public class PictureLibrary{
	private Map<Integer, Bitmap> map;
	
	public PictureLibrary(Resources res){
		this.map = new HashMap<Integer, Bitmap>();
		this.map.put(R.drawable.aukblue, 		BitmapFactory.decodeResource(res, R.drawable.aukblue));
		this.map.put(R.drawable.aukgreen, 		BitmapFactory.decodeResource(res, R.drawable.aukgreen));
		this.map.put(R.drawable.aukpurple, 		BitmapFactory.decodeResource(res, R.drawable.aukpurple));
		this.map.put(R.drawable.aukred, 		BitmapFactory.decodeResource(res, R.drawable.aukred));
		this.map.put(R.drawable.nfish1, 		BitmapFactory.decodeResource(res, R.drawable.nfish1));
		this.map.put(R.drawable.nfish2, 		BitmapFactory.decodeResource(res, R.drawable.nfish2));
		this.map.put(R.drawable.nfish3, 		BitmapFactory.decodeResource(res, R.drawable.nfish3));
		this.map.put(R.drawable.score, 			BitmapFactory.decodeResource(res, R.drawable.score));
		this.map.put(R.drawable.scoreblue, 		BitmapFactory.decodeResource(res, R.drawable.scoreblue));
		this.map.put(R.drawable.scoregreen, 	BitmapFactory.decodeResource(res, R.drawable.scoregreen));
		this.map.put(R.drawable.scorepurple, 	BitmapFactory.decodeResource(res, R.drawable.scorepurple));
		this.map.put(R.drawable.scorered, 		BitmapFactory.decodeResource(res, R.drawable.scorered));
		this.map.put(R.drawable.tilesel, 		BitmapFactory.decodeResource(res, R.drawable.tilesel));
	}
		
	public Bitmap get(int id){
		return map.get(id);
	}	
}
