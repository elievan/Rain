package com.evanedwards.rain.level.tile;

import com.evanedwards.rain.graphics.Screen;
import com.evanedwards.rain.graphics.Sprite;

public class GrassTile extends Tile {

	public GrassTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this); //asking for tile positions in this x and y, but giving it pixel positions
	}
	
	public boolean solid() {
		return false;
	}
	
}
