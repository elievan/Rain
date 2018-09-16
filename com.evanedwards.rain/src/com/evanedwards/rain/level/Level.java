package com.evanedwards.rain.level;

import java.awt.List;
import java.util.ArrayList;

import com.evanedwards.rain.entity.Entity;
import com.evanedwards.rain.graphics.Screen;
import com.evanedwards.rain.level.tile.Tile;

public class Level {

	protected int width, height; // size of the level
	protected int[] tilesInt; // which tile goes where
	protected int[] tiles;
	public static Level spawn = new Level("/levels/spawnlevel.png");
	
	private ArrayList<Entity> entitiesList = new ArrayList<Entity>();

	public Level(int width, int height) { // constructor that generates random level
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
	}

	protected Level(String path) {
		loadLevel(path);
	}

	protected void generateLevel() {
	}

	protected void loadLevel(String path) {
	}

	public void update() {
		for(int i = 0; i < entitiesList.size(); i++) {
			entitiesList.get(i).update();
		}
	}

	private void time() {
	}
	
	public boolean tileCollision(double x, double y, double xa, double ya, int entitySize) { //xy are entity. xa ya direction its heading
		for(int corner = 0; corner < 4; corner++){
			double xt = ((x + xa) + corner % 2 * entitySize / 2 - 5) / 16;
			double yt = ((y + ya) + corner / 2 * entitySize /2 + 5) / 16;

			if(getTile((int) xt, (int) yt).solid()) return true;

		}
		return false;
	}

	public void render(int xScroll, int yScroll, Screen screen) { // defines the render region of our screen. Level class handles what is rendered
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4; // cornerpins. x0 is the left side of the screen whatever x that is on the map. Dividing by 16 to get to tile precision
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
		for(int i = 0; i < entitiesList.size(); i++) {
			entitiesList.get(i).render(screen);
		}
	}

	public ArrayList<Entity> getEntities(){
		return entitiesList;
	}
	
	public void add(Entity e) {
		e.init(this);
		entitiesList.add(e);
	}
	
	public void removeEntity(int i) {
		entitiesList.remove(i);
	}
	
	//grass = 0x00FF00, water = 0x0000ff, rock = ffaa00
	public Tile getTile(int x, int y) { // return type is a Tile
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		if (tiles[x + y * width] == Tile.spawnGrassColor) return Tile.grass;
		if (tiles[x + y * width] == Tile.spawnWaterColor) return Tile.water;
		if (tiles[x + y * width] == Tile.spawnWallColor) return Tile.wall;
		return Tile.voidTile;
	}

}
