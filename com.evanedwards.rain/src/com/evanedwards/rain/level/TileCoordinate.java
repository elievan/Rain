package com.evanedwards.rain.level;

import com.evanedwards.rain.graphics.Sprite;

public class TileCoordinate {

	private static final int spriteSize = 16;
	
	public TileCoordinate(){

	}
	
	public static int x(int x) {
		int r = x * spriteSize;
		return r;
	}
	
	public static int y(int y) {
		int r = y * spriteSize;
		return r;
	}
	
	public static int[] xy(int x, int y){
		int[] r = new int[2];
		r[0] = x * spriteSize;
		r[1] = y * spriteSize;
		return r;
	}
	
}
