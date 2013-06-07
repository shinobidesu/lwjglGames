package us.blackowen.chess;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ImageHandler{
	
	public static Image	WHITE_ROOK, WHITE_KNIGHT, WHITE_BISHOP, WHITE_QUEEN, WHITE_KING, WHITE_PAWN;
	
	public static Image	BLACK_ROOK, BLACK_KNIGHT, BLACK_BISHOP, BLACK_QUEEN, BLACK_KING, BLACK_PAWN;
	
	public static void initialize(){
		try{
			ImageHandler.WHITE_ROOK = new Image("res/white_rook.png");
			ImageHandler.WHITE_KNIGHT = new Image("res/white_knight.png");
			ImageHandler.WHITE_BISHOP = new Image("res/white_bishop.png");
			ImageHandler.WHITE_QUEEN = new Image("res/white_queen.png");
			ImageHandler.WHITE_KING = new Image("res/white_king.png");
			ImageHandler.WHITE_PAWN = new Image("res/white_pawn.png");
			
			ImageHandler.BLACK_ROOK = new Image("res/black_rook.png");
			ImageHandler.BLACK_KNIGHT = new Image("res/black_knight.png");
			ImageHandler.BLACK_BISHOP = new Image("res/black_bishop.png");
			ImageHandler.BLACK_QUEEN = new Image("res/black_queen.png");
			ImageHandler.BLACK_KING = new Image("res/black_king.png");
			ImageHandler.BLACK_PAWN = new Image("res/black_pawn.png");
		}catch(final SlickException e){
			e.printStackTrace();
		}
	}
	
}
