package us.blackowen.chess;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import us.blackowen.chess.pieces.Pawn;
import us.blackowen.chess.pieces.Piece;

public class Board{
	private final Tile[][]	tiles	= new Tile[8][8];
	
	public void initialize(){
		boolean light = true;
		for(int x = 0; x < this.tiles.length; x++){
			for(int y = 0; y < this.tiles[x].length; y++){
				if(y == 0){
					this.tiles[x][y] = new Tile(x, y, light);
				}else{
					this.tiles[x][y] = new Tile(x, y);
				}
			}
			
			light = !light;
		}
		
		tiles[0][1].setPiece(new Pawn(Game.WHITE_PLAYER));
	}
	
	public void movePiece(Piece p, int x, int y){
		for(final Tile[] t : this.tiles){
			for(final Tile tile : t){
				if(tile.getPiece() == p){
					tile.setPiece(null);
					break;
				}
			}
		}
		
		this.tiles[x][y].setPiece(p);
	}
	
	public void update(final Input in){
		
	}
	
	public void render(final Graphics g){
		for(final Tile[] t : this.tiles){
			for(final Tile tile : t){
				tile.render(g);
			}
		}
	}
}