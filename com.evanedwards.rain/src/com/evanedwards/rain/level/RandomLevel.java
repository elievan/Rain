package com.evanedwards.rain.level;

import java.util.Random;

public class RandomLevel extends Level {

	private static final Random random = new Random();

	public RandomLevel(int width, int height) {
		super(width, height); // refers to the extending constructor, the superclass constructor, and runs all its code
	}

	protected void generateLevel() { //because the extending constructor in RandomLevel calls generateLevel. Protected overrides the extending class
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {

				tilesInt[x + y * width] = random.nextInt(4);

			}
		}
	}

}
