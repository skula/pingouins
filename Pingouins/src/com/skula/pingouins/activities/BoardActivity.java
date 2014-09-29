package com.skula.pingouins.activities;

import android.app.Activity;
import android.os.Bundle;

import com.skula.pingouins.activities.views.BoardView;
import com.skula.pingouins.constants.Cnst;

public class BoardActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundle bundle = getIntent().getExtras();
		String nPlayers = bundle.getString(Cnst.BUNDLE_NAME_PLAYERS_COUNT);
		
		setContentView(new BoardView(this, Integer.valueOf(nPlayers)));
	}

}
