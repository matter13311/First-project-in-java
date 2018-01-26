package dev.matt.game.entities.statics;


import java.awt.Graphics;

import dev.matt.game.Handler;
import dev.matt.game.gfx.Assets;
import dev.matt.game.items.Item;
import dev.matt.game.tile.Tile;

public class Rock extends StaticEntity {

	public Rock(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT * 2);
		
		bounds.x = 10;
		bounds.y = 160;
		bounds.width = width - 20;
		bounds.height = 70;

	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void die() {
		handler.getWorld().getItemManager().addItem(Item.rockItem.createNew((int)(x + 50),(int) (y + 200)));
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.rock,(int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		
		//g.setColor(Color.red);
		//g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),(int)(y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
	}

}
