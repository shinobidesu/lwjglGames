package us.blackowen.chess.pieces;

import us.blackowen.chess.Game;
import us.blackowen.chess.ImageHandler;
import us.blackowen.chess.Player;

public class Pawn extends Piece{

	public Pawn(Player o){
		// if player is white then white sprite, else niqqa
		super(o, o == Game.WHITE_PLAYER ? ImageHandler.WHITE_PAWN : ImageHandler.BLACK_PAWN);
		
	}
}
