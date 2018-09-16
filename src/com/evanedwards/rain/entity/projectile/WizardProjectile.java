package com.evanedwards.rain.entity.projectile;

import com.evanedwards.rain.entity.particle.Particle;
import com.evanedwards.rain.graphics.Screen;
import com.evanedwards.rain.graphics.Sprite;

public class WizardProjectile extends Projectile {

	public static final int FIRE_RATE = 10;
	
	public WizardProjectile(int x, int y, double dir) {
		super(x, y, dir);
		speed = 3;
		range = 100;
		// damage = 20;
		
		sprite = Sprite.wizardProjectile;
		
		vectorX = Math.cos(angle) * speed;
		vectorY = Math.sin(angle) * speed;
	}

	public void update() {
		move();
		if(level.tileCollision(x, y, vectorX, vectorY, 4)) {
			Particle p = new Particle((int)x, (int)y, 50, 500); //show projectiles
			level.add(p); //show projectiles
			remove(this);
		}
		if (projectileDistance() > range) remove(this);
	}

	protected void move() {
		x += vectorX;
		y += vectorY;
	}
	
	private double projectileDistance() {
		double dist = 0;
		dist = Math.sqrt(Math.abs((xOrigin - x)*(xOrigin - x) + (yOrigin - y)*(yOrigin - y)));
		return dist;
	}

	public void render(Screen screen){
		screen.renderProjectile((int) x - 12, (int) y - 2, this);
	}

}
