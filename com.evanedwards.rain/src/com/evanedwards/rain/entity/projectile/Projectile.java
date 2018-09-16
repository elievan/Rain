package com.evanedwards.rain.entity.projectile;

import com.evanedwards.rain.entity.Entity;
import com.evanedwards.rain.graphics.Screen;
import com.evanedwards.rain.graphics.Sprite;
import com.evanedwards.rain.level.Level;

public class Projectile extends Entity {

	protected final int xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double x, y; //overwrites entities int based x and y
	protected double distanceTravelled;
	protected double vectorX, vectorY;
	protected double speed, range, damage; 
	
	public Projectile(int x, int y, double dir) { // can add modifiers here. damage, speed etc.
		xOrigin = x;
		yOrigin = y;
		angle = dir;
		this.x = x; //coords of the entity at all times
		this.y = y;
		
	}
	
	protected void move(){
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public int getSpriteSize() {
		return sprite.SIZE;
	}

	
}
