package com.evanedwards.rain.graphics;

public class Sprite { //pick sprite and put in pixels from spritesheet

	public final int SIZE;
	private int x, y;
	private int width, height;
	public int[] pixels;
	private SpriteSheet sheet;
	
	//Level sprites:
	public static Sprite voidSprite = new Sprite(16, 0x43e0b4);
	public static Sprite rock = new Sprite(16, 3, 11, SpriteSheet.tiles);
	public static Sprite water = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite grass = new Sprite(16, 8, 6, SpriteSheet.tiles);
	public static Sprite wall = new Sprite(16, 9, 6, SpriteSheet.tiles);
	public static Sprite woodfloor = new Sprite(16, 11, 6, SpriteSheet.tiles);
	
	//Projectile sprites:
	public static Sprite wizardProjectile = new Sprite(16, 0,0, SpriteSheet.projectiles);
	
	//Particles sprites
	public static Sprite particle_normal = new Sprite(3, 0xAAAAAA);
	

	//Player sprites:
	public static Sprite player_down = new Sprite(32, 4, 0, SpriteSheet.tiles);
	public static Sprite player_up = new Sprite(32, 5, 0, SpriteSheet.tiles);
	public static Sprite player_left = new Sprite(32, 6, 0, SpriteSheet.tiles);
	public static Sprite player_right = new Sprite(32, 7, 0, SpriteSheet.tiles);
	public static Sprite player_down_1 = new Sprite(32, 4, 1, SpriteSheet.tiles);
	public static Sprite player_down_2 = new Sprite(32, 4, 2, SpriteSheet.tiles);
	public static Sprite player_up_1 = new Sprite(32, 5, 1, SpriteSheet.tiles);
	public static Sprite player_up_2 = new Sprite(32, 5, 2, SpriteSheet.tiles);
	public static Sprite player_left_1 = new Sprite(32, 6, 1, SpriteSheet.tiles);
	public static Sprite player_left_2 = new Sprite(32, 6, 2, SpriteSheet.tiles);
	public static Sprite player_right_1 = new Sprite(32, 7, 1, SpriteSheet.tiles);
	public static Sprite player_right_2 = new Sprite(32, 7, 2, SpriteSheet.tiles);

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}
	
	public Sprite(int width, int height, int colour) {
		SIZE = -1;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		setColour(colour);
	}

	public Sprite(int size, int colour) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE*SIZE];
		setColour(colour);
	}
	
	private void setColour(int colour) {
		for (int i = 0; i < width*height; i++) {
			pixels[i] = colour;
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {

				pixels[x + y * SIZE] = sheet.pixels[(this.x + x) + (this.y + y) * sheet.SIZE];

			}
		}
	}
	
	

}
