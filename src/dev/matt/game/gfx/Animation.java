package dev.matt.game.gfx;

import java.awt.image.BufferedImage;

public class Animation {
	
	public int speed, index;
	private long lastTime, timer;
	private BufferedImage[] frames;
	
	public Animation(int speed, BufferedImage[] frames) {
		this.speed = speed;
		this.frames = frames;
		index = 0;
		timer = 0;
		lastTime = System.currentTimeMillis();//Going to get the miliseconds that has passed since the start of the program
	}
	
	public void tick() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(timer > speed) {
			index++;
			timer = 0;
			if(index >= frames.length)
				index = 0;
		}
	}
	
	
	public BufferedImage getCurrentFrame() {
		return frames[index];
	}
	
}
