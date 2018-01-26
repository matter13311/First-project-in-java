package dev.matt.game.tile;



import dev.matt.game.gfx.Assets;

public class StoneTile extends Tile{

	public StoneTile(int id) {
		super(Assets.stone, id);
	}

	@Override
	public boolean isSolid() {
		return true;
	}
}
