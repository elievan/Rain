package com.evanedwards.rain.entity.mob;

import com.evanedwards.rain.Game;
import com.evanedwards.rain.entity.Entity;
import com.evanedwards.rain.entity.projectile.Projectile;
import com.evanedwards.rain.entity.projectile.WizardProjectile;
import com.evanedwards.rain.graphics.Screen;
import com.evanedwards.rain.graphics.Sprite;
import com.evanedwards.rain.input.Keyboard;
import com.evanedwards.rain.input.Mouse;

public class Player extends Mob {

	private Keyboard input;
	private int animate = 0;
	private boolean walking = false;
	private int fireRate = 0;

	public Player(Keyboard input) {
		this.input = input;
		sprite = Sprite.player_up;
	}

	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.player_up;
		fireRate = WizardProjectile.FIRE_RATE;
	}

	public void update() {
		if(fireRate > 0) fireRate--;
		int xa = 0, ya = 0; // note down the direction of the play movement
		if(animate < 7500) animate++; else animate = 0;
		if(input.up) ya--;
		if(input.down) ya++;
		if(input.right) xa++;
		if(input.left) xa--;

		
		if(xa != 0 || ya != 0) { 
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
		clearProjectiles();
		updateShooting();
	}

	private void updateShooting() {

		if(Mouse.getButton() == 1 && fireRate <= 0) {
			double dx = Mouse.getX() - (Game.getWindowWidth() / 2);
			double dy = Mouse.getY() - (Game.getWindowHeight() / 2);
			
			double dir = Math.atan2(dy, dx);

			shoot(x, y, dir);
			
			fireRate = WizardProjectile.FIRE_RATE;
		}
	}
	
	private void clearProjectiles() {

		for(int i = 0; i < projectilesList.size(); i++) {
			Projectile p = projectilesList.get(i);
			if (p.isRemoved()) {
				projectilesList.remove(i);
			}
		}
		
		for(int i = 0; i < level.getEntities().size(); i++) {
			Entity p = level.getEntities().get(i);
			if (p.isRemoved()) {
				level.removeEntity(i);
			}
		}
	}

	public void render(Screen screen) {
		if(dir == 0) {
			sprite = Sprite.player_up;
			if (walking) {
				if(animate % 40 > 10 && animate % 40 <= 20) {
					sprite = Sprite.player_up_1;
				}
				if(animate % 40 > 30 && animate % 40 <= 40) {
					sprite = Sprite.player_up_2;
				}
			}
		}
		if(dir == 1) {
			sprite = Sprite.player_right;
			if (walking) {
				if(animate % 40 > 10 && animate % 40 <= 20) {
					sprite = Sprite.player_right_1;
				}
				if(animate % 40 > 30 && animate % 40 <= 40) {
					sprite = Sprite.player_right_2;
				}
			}
		}
		if(dir == 2) {
			sprite = Sprite.player_down;
			if (walking) {
				if(animate % 40 > 10 && animate % 40 <= 20) {
					sprite = Sprite.player_down_1;
				}
				if(animate % 40 > 30 && animate % 40 <= 40) {
					sprite = Sprite.player_down_2;
				}
			}
		}
		if(dir == 3) {
			sprite = Sprite.player_left;
			if (walking) {
				if(animate % 40 > 10 && animate % 40 <= 20) {
					sprite = Sprite.player_left_1;
				}
				if(animate % 40 > 30 && animate % 40 <= 40) {
					sprite = Sprite.player_left_2;
				}
			}
		}
		
		screen.renderPlayer(x-16, y-16, sprite);
	}

}