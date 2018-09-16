package com.evanedwards.rain.entity.particle;

import java.util.ArrayList;
import java.util.List;

import com.evanedwards.rain.entity.Entity;
import com.evanedwards.rain.graphics.Screen;
import com.evanedwards.rain.graphics.Sprite;

public class Particle extends Entity {

	private List<Particle> particlesList = new ArrayList<Particle>();
	
	private int life;
	
	public Particle(int x, int y, int life) {
		this.x = x;
		this.y = y;
		this.life = life;
		sprite = Sprite.particle_normal;
		particlesList.add(this);
	}
	
	public Particle(int x, int y, int life, int number) {
		this(x, y, life); //runs the previous constructor. not another instance of the particle class. just calling the other constructor like it was any other method.
		for(int i = 0; i < number - 1; i++) {
			particlesList.add(new Particle(x, y, life));
		}
	}
	
	public void update() {
	}
	
	public void render(Screen screen) {
	}
	
}
