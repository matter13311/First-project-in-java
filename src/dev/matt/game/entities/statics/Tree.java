package dev.matt.game.entities.statics;


import java.awt.Graphics;
import java.util.Random;

import dev.matt.game.Handler;
import dev.matt.game.gfx.Assets;
import dev.matt.game.items.Item;
import dev.matt.game.tile.Tile;

public class Tree extends StaticEntity {

	Random rand = new Random();
	
	public Tree(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT * 2);
		
		bounds.x = 43;
		bounds.y = (int) (height / 1.5f);
		bounds.width = width - 87;
		bounds.height = (int) (height - height / 1.5f);

	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void die() {
		int n = rand.nextInt(100) +1;
		if(n <100 && n>80) {
		handler.getWorld().getItemManager().addItem(Item.logItem.createNew((int)(x + 50),(int)(y + 200)));
		}else if(n <80 && n>60)
		{
		handler.getWorld().getItemManager().addItem(Item.logItem.createNew((int)(x + 50),(int)(y + 100)));
		}else if(n < 60 && n > 40)
		{
			handler.getWorld().getItemManager().addItem(Item.appleItem.createNew((int)(x + 50),(int)(y + 130)));
		}else if(n < 40 && n>20) {
			handler.getWorld().getItemManager().addItem(Item.appleItem.createNew((int)(x + 50),(int)(y + 130)));
			handler.getWorld().getItemManager().addItem(Item.logItem.createNew((int)(x + 30),(int)(y + 200)));
		}else {
			handler.getWorld().getItemManager().addItem(Item.appleItem.createNew((int)(x + 70),(int)(y + 210)));
			handler.getWorld().getItemManager().addItem(Item.logItem.createNew((int)(x + 20),(int)(y + 260)));
		}
		System.out.println(n);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.tree,(int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		
		//g.setColor(Color.red);
		//g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),(int)(y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
	}

}
