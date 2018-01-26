package dev.matt.game.tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	//Static variables
	public static Tile[] tiles = new Tile[256];
	public static Tile dirtTile = new DirtTile(0);
	public static Tile stoneTile = new StoneTile(1);
	public static Tile woodTile = new WoodTile(2);	
	
	public static final int TILEWIDTH = 120, TILEHEIGHT = 120;
	
	protected BufferedImage texture;
	protected final int id;
	
	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public int getId() {
		return id;
	}
	
}
