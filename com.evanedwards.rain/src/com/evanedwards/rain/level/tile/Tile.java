package com.evanedwards.rain.level.tile;

import com.evanedwards.rain.graphics.Screen;
import com.evanedwards.rain.graphics.Sprite;

public class Tile {

	public int x, y;
	public Sprite sprite;
	
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	public static Tile rock = new RockTile(Sprite.rock); //set tile object to grasstile object because extends. Can change properties of grass.
	public static Tile water = new WaterTile(Sprite.water);
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile wall = new WallTile(Sprite.wall);
	public static Tile woodfloor = new WoodfloorTile(Sprite.woodfloor);
	
	public static final int spawnGrassColor = 0xff00ff00;
	public static final int spawnWaterColor = 0xff0000ff;
	public static final int spawnWallColor = 0xffff0000;
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen) {		
	}
	
	public boolean solid() {
		return false;
	}
	
}
