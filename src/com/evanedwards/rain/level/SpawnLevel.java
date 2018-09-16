package com.evanedwards.rain.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.evanedwards.rain.level.tile.Tile;

public class SpawnLevel extends Level {

	public SpawnLevel(String path) {
		super(path);
	}

	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			tiles = new int[width * height];
			image.getRGB(0, 0, width, height, tiles, 0, width);

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception! Could not load spawn level file ");
		}
	}

	protected void generateLevel() { // convert pixels into tiles
	}

}
