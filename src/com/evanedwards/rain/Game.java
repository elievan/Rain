package com.evanedwards.rain;

import java.awt.Canvas; 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import javax.swing.JFrame;

import com.evanedwards.rain.entity.mob.Player;
import com.evanedwards.rain.graphics.Screen;
import com.evanedwards.rain.graphics.Sprite;
import com.evanedwards.rain.input.Keyboard;
import com.evanedwards.rain.input.Mouse;
import com.evanedwards.rain.level.Level;
import com.evanedwards.rain.level.RandomLevel;
import com.evanedwards.rain.level.SpawnLevel;
import com.evanedwards.rain.level.TileCoordinate;

public class Game extends Canvas implements Runnable { 
	private static final long serialVersionUID = 1L;

	private static int width = 300;
	private static int height = width / 16 * 9;
	private static int scale = 3;
	
	public static String title = "Rain";
	
	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	private Level level;
	private Player player;
	private Screen screen;
	
	private boolean running = false;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	public Game() {
	    Dimension size = new Dimension(width * scale, height * scale);
	    setPreferredSize(size);
	
	    screen = new Screen(width, height);
	    frame = new JFrame();
	    
	    key = new Keyboard();
	    addKeyListener(key);
	    
	    Mouse mouse = new Mouse();
	    addMouseListener(mouse); //buttons
	    addMouseMotionListener(mouse); // location
	 
	    level = new SpawnLevel("/levels/spawnlevel.png"); //random level size is 64 by 64 tiles
	    
	    int[] playerSpawn = TileCoordinate.xy(17, 42);
	    player = new Player(playerSpawn[0], playerSpawn[1], key);
	    player.init(level);
	}
	
	public synchronized void start() {
	    running = true;
	    thread = new Thread(this, "Display");
	    thread.start();
	}
	
	public synchronized void stop() {
	    running = false;
	    try {
	        thread.join();
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}
	
	public void run() {
		double ns = 1000000000.0 / 60.0;
		double delta = 0;
		
		int frames = 0;
		int updates = 0;
		
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		
		requestFocus();
		
	    while (running) {
	    	long now = System.nanoTime();
	    	
	    	delta += (now - lastTime) / ns;
	    	lastTime = now;
	    	
	    	while(delta >= 1) {
	    		update();
	    		updates++;
	    		delta--;
	    	}
	    	
	        render();
	        frames++;
	        
	        if(System.currentTimeMillis() - timer >= 1000) {
	        	timer += 1000;
	        	frame.setTitle(title + "  |  " + updates + " ups, " + frames + " fps");
	        	frames = 0;
	        	updates = 0;
	        }
	    }
	    
	    stop();
	}
	
	public void update() {
		key.update();
		player.update();
		level.update();
	}
	
	public void render() {
	    BufferStrategy bs = getBufferStrategy();
	    if (bs == null) {
	        createBufferStrategy(3);
	        return;
	    }
	    
	    screen.clear();
	    int xScroll = player.x - screen.width/2;
	    int yScroll = player.y - screen.height/2;
	    level.render(xScroll, yScroll, screen);
	    player.render(screen);

	    for(int i = 0; i < pixels.length; i++) {
	    	pixels[i] = screen.pixels[i];
	    }
	    
	    Graphics g = bs.getDrawGraphics();
	    g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	    //g.setFont(new Font("Verdana", 0, 50));
	    //g.drawString("X: " + player.x + "Y: " + player.sy, 450,400);
	    g.drawString("X: " + Mouse.getX() + " | Y: " + Mouse.getY(), Mouse.getX(), Mouse.getY());
	    g.drawString("Button: " + Mouse.getButton(), 80, 80);
	    g.dispose();
	    bs.show();
	}
	
	public static void main(String[] args) {
	    Game game = new Game();
	    game.frame.setResizable(false);
	    game.frame.setTitle(Game.title);
	    game.frame.add(game);
	    game.frame.pack();
	    game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    game.frame.setLocationRelativeTo(null);
	    game.frame.setVisible(true);
	    
	    game.start();
	}
	
	public static int getWindowWidth(){
		return width*scale;
		
	}
	
	public static int getWindowHeight(){
		return height*scale;
	}

}