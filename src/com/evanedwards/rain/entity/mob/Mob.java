package com.evanedwards.rain.entity.mob;

import java.util.ArrayList;

import com.evanedwards.rain.entity.Entity;
import com.evanedwards.rain.entity.projectile.Projectile;
import com.evanedwards.rain.entity.projectile.WizardProjectile;
import com.evanedwards.rain.graphics.Sprite;
import com.evanedwards.rain.level.Level;
import com.evanedwards.rain.level.TileCoordinate;

public abstract class Mob extends Entity {

	protected int dir = 0; //north
	protected boolean moving = false; //moving? animate
	
	protected ArrayList<Projectile> projectilesList = new ArrayList<Projectile>(); // in mob so we know who owns each projectile
	
	public void move(int xa, int ya) { //xa is mob movement on x axis
		if(xa > 0) dir = 1; //right
		if(xa < 0) dir = 3; //left
		if(ya > 0) dir = 2; //down
		if(ya < 0) dir = 0; //up
		
		if(!collision(xa, 0)) {
			x += xa;
		}
		if(!collision(0, ya)) {
			y += ya;
		}
	}
	
	public void update() {
	}
	
	protected void shoot(int x, int y, double dir) {
		//dir = dir * 180 / Math.PI;
		Projectile newProjectile = new WizardProjectile(x, y, dir);
		projectilesList.add(newProjectile);
		level.add(newProjectile);
	}

	
	private boolean collision(int xa, int ya) {
		for(int corner = 0; corner < 4; corner++){
			int xt = ((x + xa) + corner % 2 * 17 - 8) >> 4;
			int yt = ((y + ya) + corner / 2 * 17 - 2) >> 4;

			if(level.getTile(xt, yt).solid()) return true;

		}
		return false;
	}
	
	public void render() {
	}
	
}
