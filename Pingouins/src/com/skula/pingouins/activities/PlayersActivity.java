package com.skula.pingouins.activities;

import com.skula.pingouins.activities.views.PlayersView;

import android.app.Activity;
import android.os.Bundle;

public class PlayersActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(new PlayersView(this));
	}

}
