package dev.matt.game.tile.worlds;

import java.awt.Graphics;

import dev.matt.game.Handler;
import dev.matt.game.entities.EntityManager;
import dev.matt.game.entities.creatures.Player;
import dev.matt.game.entities.statics.Rock;
import dev.matt.game.entities.statics.Tree;
import dev.matt.game.items.ItemManager;
import dev.matt.game.tile.Tile;
import dev.matt.game.util.Utils;

public class World {
	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	//Entities
	private EntityManager entityManager;
	//Item
	private ItemManager itemManager;
	
	public World(Handler handler, String path) {
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler,100, 100));
		itemManager = new ItemManager(handler);
		entityManager.addEntity(new Tree(handler, 250, 250));
		entityManager.addEntity(new Tree(handler, 600, 450));
		entityManager.addEntity(new Tree(handler, 800, 650));
		entityManager.addEntity(new Tree(handler, 250, 700));
		entityManager.addEntity(new Rock(handler, 700, 100));
		
		entityManager.addEntity(new Tree(handler, 841, 920));
		entityManager.addEntity(new Tree(handler, 621, 328));
		entityManager.addEntity(new Tree(handler, 569, 265));
		entityManager.addEntity(new Tree(handler, 178, 562));
		entityManager.addEntity(new Rock(handler, 655, 912));
		entityManager.addEntity(new Rock(handler, 120, 659));
		entityManager.addEntity(new Rock(handler, 132, 265));
		entityManager.addEntity(new Rock(handler, 265, 974));
		entityManager.addEntity(new Rock(handler, 113, 426));
		loadWorld(path);
		
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setX(spawnY);
	}
	
	
	public EntityManager getEntityManager() {
		return entityManager;
	}


	public void tick() {
		itemManager.tick();
		entityManager.tick();
		
		
	}
	public void render(Graphics g) {
		int xStart =(int) Math.max(0,  handler.getGameCamera().getxOffset() / Tile.TILEWIDTH );
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset()+ handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset()/Tile.TILEHEIGHT );
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset()+handler.getHeight())/Tile.TILEHEIGHT + 1);
		
		
		
		for(int y = yStart; y < yEnd; y++)
		{
			for(int x = xStart; x < xEnd; x++) 
			{
				getTile(x,y).render(g,(int)( x* Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),(int)( y* Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		//Items
		itemManager.render(g);
		//Entities
		entityManager.render(g);
	}
	
	public Handler getHandler() {
		return handler;
	}


	public void setHandler(Handler handler) {
		this.handler = handler;
	}


	public ItemManager getItemManager() {
		return itemManager;
	}


	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}


	public Tile getTile(int x, int y) {
		
		if( x < 0 || y < 0 || x >= width || y >= height)
			return Tile.woodTile;
		
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null)
			return Tile.woodTile;
		return t;

	}
	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		for(int y = 0; y < height;y++) {
			for(int x = 0; x< width; x++) {
				try {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width)+ 4]);
				}catch(Exception ex) {}
			}
		}
		
	}
	
	public int getWidth() {
		return width;
		
	}
	
	public int getHeight() {
		return height;
	}
	
}
