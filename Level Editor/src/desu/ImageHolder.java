package desu;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class ImageHolder {
	/*
	 * Tiles
	 */
	public static Image GRASSY_ROCK;
	public static Image PURPLE_GRASSY_ROCK;
	public static Image GRASS;
	
	public static Image WOOD_FLOOR;
	
	public static Image TOP_LEFT_CORNER_HOUSE, TOP_RIGHT_CORNER_HOUSE, BOT_LEFT_CORNER_HOUSE, BOT_RIGHT_CORNER_HOUSE;
	public static Image TOP_BORDER_HOUSE, LEFT_BORDER_HOUSE, RIGHT_BORDER_HOUSE, BOT_BORDER_HOUSE;
	public static Image FILLING_HOUSE;
	
	public static Image LAVA;
	
	public static Image DOWN_ENTRANCE, UP_ENTRANCE, LEFT_ENTRANCE, RIGHT_ENTRANCE;
	public static Image EMPTY;
	
	/*
	 * World entities
	 */
	public static Image TREE;
	
	/*
	 * NPCs
	 */
	public static Image NPC_TEMPLATE;
	
	/**
	 * Grass types = row 0;
	 * Wood types = row 1;
	 * House parts = row 2; Corner col 0, border col 1, filling col 2
	 * Special tiles = row 3; Lava etc.
	 * Miscellaneous = row 14;
	 * 
	 * x = col, y = row;
	 */
	
	public static void initialize(){
		try{
			SpriteSheet ss = new SpriteSheet(new Image("res/tileset.png"), 32, 32, 0);
			GRASSY_ROCK = ss.getSprite(0, 0);
			PURPLE_GRASSY_ROCK = ss.getSprite(1, 0);
			GRASS = ss.getSprite(2, 0);
			
			WOOD_FLOOR = ss.getSprite(0, 1);
			
			DOWN_ENTRANCE = ss.getSprite(0, 14);
			{
				UP_ENTRANCE = DOWN_ENTRANCE.copy();
				LEFT_ENTRANCE = DOWN_ENTRANCE.copy();
				RIGHT_ENTRANCE = DOWN_ENTRANCE.copy();
				
				UP_ENTRANCE.rotate(180);
				LEFT_ENTRANCE.rotate(90);
				RIGHT_ENTRANCE.rotate(270);
			}
			
			TOP_LEFT_CORNER_HOUSE = ss.getSprite(0, 2);
			{
				TOP_RIGHT_CORNER_HOUSE = TOP_LEFT_CORNER_HOUSE.copy();
				BOT_LEFT_CORNER_HOUSE = TOP_LEFT_CORNER_HOUSE.copy();
				BOT_RIGHT_CORNER_HOUSE = TOP_LEFT_CORNER_HOUSE.copy();
				
				TOP_RIGHT_CORNER_HOUSE.rotate(90);
				BOT_LEFT_CORNER_HOUSE.rotate(270);
				BOT_RIGHT_CORNER_HOUSE.rotate(180);
			}
			
			TOP_BORDER_HOUSE = ss.getSprite(1, 2);
			{
				LEFT_BORDER_HOUSE = TOP_BORDER_HOUSE.copy();
				RIGHT_BORDER_HOUSE = TOP_BORDER_HOUSE.copy();
				BOT_BORDER_HOUSE = TOP_BORDER_HOUSE.copy();
				
				LEFT_BORDER_HOUSE.rotate(270);
				RIGHT_BORDER_HOUSE.rotate(90);
				BOT_BORDER_HOUSE.rotate(180);
			}
			
			FILLING_HOUSE = ss.getSprite(2, 2);
			
			LAVA = ss.getSprite(0, 3);
			
			EMPTY = ss.getSprite(19, 14);
			
			TREE = new Image("res/tree.png");
			
			/*
			 * NPC sprites
			 */
			NPC_TEMPLATE = new Image("res/npc_template.png");
			
		}catch(SlickException e){
			e.printStackTrace();
		}
	}
}