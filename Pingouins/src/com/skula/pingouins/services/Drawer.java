package com.skula.pingouins.services;

import com.skula.pingouins.constants.Cnst;

public class Drawer {

	private GameEngine engine;

	public Drawer(GameEngine engine) {
		this.engine = engine;
	}

	public void draw() {
		drawTile();
		drawPlayer();
		drawScore();
	}

	public void drawTile() {
		int x = 0;
		int y = 0;
		for (int j = 0; j < Cnst.ROW_COUNT; j++) {
			int cpt = Cnst.COLUMN_COUNT;
			if (j % 2 != 0) {
				x += Cnst.TILE_SIZE / 2;
				cpt--;
			}
			for (int i = 0; i < cpt; i++) {
				switch (engine.getTile(i, j).getFishCount()) {
				case 0:
					break;
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				default:
					break;
				}
				x += Cnst.TILE_SIZE;
			}
			y += Cnst.TILE_SIZE;
		}
	}

	public void drawPlayer() {
		for (int i = 0; i < engine.getnPlayers(); i++) {
			for (int j = 0; j < engine.getnAuks(); j++) {

			}
		}
	}

	public void drawScore() {
		for (int i = 0; i < engine.getnPlayers(); i++) {

		}
	}
}
