package us.blackowen.chess.pieces;

import org.newdawn.slick.Image;

import us.blackowen.chess.Player;
import us.blackowen.chess.Tile;

public class Piece{
	Player	owner;
	Image	sprite;
	
	protected Piece(Player o, Image s){
		this.owner = o;
		this.sprite = s;
	}
	
	public void render(final int x, final int y){
		if(sprite != null)
			this.sprite.draw(x * Tile.WIDTH, y * Tile.HEIGHT);
	}
}
