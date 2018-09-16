package com.evanedwards.rain.entity.particle;

import com.evanedwards.rain.entity.Entity;
import com.evanedwards.rain.graphics.Screen;
import com.evanedwards.rain.graphics.Sprite;

public class Particle extends Entity {

	private int life;
	
	protected double xa, ya; // number of pixels it moves in x and y axis
	protected double xx, yy; //actuals
	
	public Particle(int x, int y, int life) {
		this.x = x;
		this.y = y;
		this.xx = x; //x is now a double
		this.yy = y;
		this.life = life;
		sprite = Sprite.particle_normal;
		
		this.xa = random.nextGaussian(); //normally distributed number between -1 and 1. Mostly around 0
		this.ya = random.nextGaussian();
	}

	public void update() {
		this.xx += xa;
		this.yy += ya;
	}
	
	public void render(Screen screen) {
		screen.renderSprite((int)xx, (int)yy, sprite, true); //only change to int when rendering to get max precision
	}
	
}
