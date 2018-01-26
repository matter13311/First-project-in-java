package dev.matt.game.entities.statics;

import dev.matt.game.Handler;
import dev.matt.game.entities.Entity;

public abstract class StaticEntity extends Entity{ //static, something that doesn't move.
	
	public StaticEntity(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
	}
	
}
