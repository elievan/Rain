package com.evanedwards.rain.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet { //manages any spritesheet and caches them

	private String path;
	public final int SIZE;
	public int[] pixels;
	
	public static SpriteSheet tiles = new SpriteSheet("/textures/tileset3.png", 300);
	public static SpriteSheet projectiles = new SpriteSheet("/textures/projectiles.png", 64);
	
	public SpriteSheet(String path, int size) {
		this.path = path;
		this.SIZE = size;
		pixels = new int[SIZE*SIZE];
		load();
	}
	
	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w); //code that translates image into pixels
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
