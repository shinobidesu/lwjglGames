package us.blackowen.chess;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import us.blackowen.chess.pieces.Piece;

public class Tile{
	private final int			x, y;
	public static final int		WIDTH		= 64, HEIGHT = 64;
	
	private static final Color	lightColor	= new Color(255, 211, 155);
	private static final Color	darkColor	= new Color(139, 115, 85);
	
	private final boolean		light;
	private static boolean		lastLight;
	
	private Piece				piece;
	public Piece getPiece(){return piece;}
	public void setPiece(Piece p){piece = p;}
	
	public Tile(final int x, final int y){
		this.x = x;
		this.y = y;
		this.light = !Tile.lastLight;
		Tile.lastLight = this.light;
	}
	
	public Tile(final int x, final int y, final boolean light){
		this.x = x;
		this.y = y;
		this.light = light;
		Tile.lastLight = this.light;
	}
	
	public void render(final Graphics g){
		if(this.light){
			g.setColor(Tile.lightColor);
		}else{
			g.setColor(Tile.darkColor);
		}
		
		g.fillRect(this.x * Tile.WIDTH, this.y * Tile.HEIGHT, Tile.WIDTH, Tile.HEIGHT);
		if(piece != null)
			this.piece.render(this.x, this.y);
	}
}
