package com.evanedwards.rain.entity;

import java.util.Random;

import com.evanedwards.rain.graphics.Screen;
import com.evanedwards.rain.graphics.Sprite;
import com.evanedwards.rain.level.Level;

public abstract class Entity {

	public int x, y; //redundant if entity isn't going to have a sprite
	private boolean removed = false; //has it been removed from the level
	protected Sprite sprite;
	protected Level level;
	protected final Random random = new Random();
	
	public void update() {
	}
	
	public void render(Screen screen) {
	}
	
	public void remove(Entity e){
		removed = true;
	}
	
	public boolean isRemoved(){
		return removed;
	}
	
	public void init(Level level) {
		this.level = level;
	}
	
}
