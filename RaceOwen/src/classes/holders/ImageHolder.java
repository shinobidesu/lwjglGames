package classes.holders;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import classes.Player;

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
	 * Miscellaneous
	 */
	public static Image PASSIVE_BUTTON, PRESSED_BUTTON, HOVER_BUTTON;
	public static Image DISABLED_SKILL_BUTTON, ENABLED_SKILL_BUTTON, HOVER_SKILL_BUTTON;
	public static Image SCROLLBAR, PEG;
	public static Image BATTLE_PLATFORM;
	
	/*
	 * Player in battle
	 */
	public static SpriteSheet BATTLE_ANIM;
	
	/*
	 * World entities
	 */
	public static Image TREE;
	
	/*
	 * Items
	 */
	public static Image SWORD;
	public static Image HEALTH_POTION;
	
	/*
	 * NPCs
	 */
	public static Image NPC_TEMPLATE;
	
	/*
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
			/*
			 * Tile sprites
			 */
			
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
			
			/*
			 * Miscellaneous sprites
			 */
			
			PASSIVE_BUTTON = new Image("res/passive_button.png");
			PRESSED_BUTTON = new Image("res/pressed_button.png");
			HOVER_BUTTON = new Image("res/hover_button.png");
			
			DISABLED_SKILL_BUTTON = new Image("res/disabled_skill_button.png");
			ENABLED_SKILL_BUTTON = new Image("res/enabled_skill_button.png");
			HOVER_SKILL_BUTTON = new Image("res/hover_skill_button.png");
			
			SCROLLBAR = new Image("res/scrollbar.png");
			PEG = new Image("res/peg.png");
			
			BATTLE_PLATFORM = new Image("res/battle_platform.png");
			
			/*
			 * Player sprites
			 */
			SpriteSheet rs = new SpriteSheet(new Image("res/player.png"), 32, 32, 0);
			Image[] imgs = {rs.getSprite(0, 0), rs.getSprite(1, 0), rs.getSprite(2, 0), rs.getSprite(3, 0)};
			Player.anim = new Animation(imgs, 120);
			
			BATTLE_ANIM = new SpriteSheet(new Image("res/battle_sheet.png"), 22, 29, 0);
			
			/*
			 * World entity sprites
			 */
			TREE = new Image("res/tree.png");
			
			/*
			 * Item sprites
			 */
			SWORD = new Image("res/sword.png");
			HEALTH_POTION = new Image("res/health_potion.png");
			
			/*
			 * NPC sprites
			 */
			NPC_TEMPLATE = new Image("res/npc_template.png");
			
		}catch(SlickException e){
			e.printStackTrace();
		}
	}
}