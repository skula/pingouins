package com.skula.pingouins.activities;

import android.app.Activity;
import android.os.Bundle;

import com.skula.pingouins.activities.views.BoardView;

public class BoardActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(new BoardView(this));
	}

}
