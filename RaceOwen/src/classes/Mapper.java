package classes;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.newdawn.slick.Image;

import classes.entities.worldentities.TreeEntity;
import classes.entities.worldentities.WorldEntity;
import classes.entities.worldentities.npcs.GameInitiator;
import classes.entities.worldentities.npcs.Trader;
import classes.holders.ImageHolder;

public class Mapper {
	
	public static TileType[][] tile(String path){
		StringBuilder sb = readFile(path);
		String content = sb.toString();
		String[] row = content.split(System.getProperty("line.separator"));
		
		TileType[][] tiles = new TileType[15][20]; // 15 = rows, 20 = columns
		for(int j = 0; j < row.length; j++){
			String s = row[j];
			for(int i = 0; i < s.length(); i++){
				switch(s.charAt(i)){
				case 'r':
					tiles[j][i] = TileType.GRASSY_ROCK;
					break;
				case 'p':
					tiles[j][i] = TileType.PURPLE_GRASSY_ROCK;
					break;
				case 'g':
					tiles[j][i] = TileType.GRASS;
					break;
				case 'w':
					tiles[j][i] = TileType.WOOD_FLOOR;
					break;
				case 'l':
					tiles[j][i] = TileType.LAVA;
					break;
				case '-':
					tiles[j][i] = TileType.EMPTY;
					break;
				case 'v':
					tiles[j][i] = TileType.DOWN_ENTRANCE;
					break;
				case '^':
					tiles[j][i] = TileType.UP_ENTRANCE;
					break;
				case '<':
					tiles[j][i] = TileType.LEFT_ENTRANCE;
					break;
				case '>':
					tiles[j][i] = TileType.RIGHT_ENTRANCE;
					break;
				case '1':
					tiles[j][i] = TileType.TOP_LEFT_CORNER_HOUSE;
					break;
				case '2':
					tiles[j][i] = TileType.TOP_BORDER_HOUSE;
					break;
				case '3':
					tiles[j][i] = TileType.TOP_RIGHT_CORNER_HOUSE;
					break;
				case '4':
					tiles[j][i] = TileType.LEFT_BORDER_HOUSE;
					break;
				case '5':
					tiles[j][i] = TileType.FILLING_HOUSE;
					break;
				case '6':
					tiles[j][i] = TileType.RIGHT_BORDER_HOUSE;
					break;
				case '7':
					tiles[j][i] = TileType.BOT_LEFT_CORNER_HOUSE;
					break;
				case '8':
					tiles[j][i] = TileType.BOT_BORDER_HOUSE;
					break;
				case '9':
					tiles[j][i] = TileType.BOT_RIGHT_CORNER_HOUSE;
					break;
				}
			}
		}
		
		return tiles;
	}
	
	public static WorldEntity[][] entity(String path){
		StringBuilder sb = readFile(path);
		String content = sb.toString();
		String[] row = content.split(System.getProperty("line.separator"));
		
		WorldEntity[][] we = new WorldEntity[15][20]; // 15 = rows, 20 = columns
		for(int j = 0; j < row.length; j++){
			String s = row[j];
			for(int i = 0; i < s.length(); i++){
				switch(s.charAt(i)){
				case 't':
					we[j][i] = new TreeEntity(i, j);
					break;
				case 'n':
					we[j][i] = new Trader(i, j);
					break;
				case 'g':
					we[j][i] = new GameInitiator(i, j);
					break;
				case '-':
					we[j][i] = null;
					break;
				}
			}
		}
		
		return we;
	}
	
	public static StringBuilder readFile(String path){
		File file = new File(path);
		try {
			System.out.println(file.getCanonicalPath());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        StringBuilder contents = new StringBuilder();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            // repeat until all lines is read
            while ((text = reader.readLine()) != null) {
                contents.append(text)
                        .append(System.getProperty(
                                "line.separator"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        return contents;
	}
	
	public enum TileType{
		EMPTY(true), GRASSY_ROCK, PURPLE_GRASSY_ROCK(0.15f, 2f), GRASS(0.15f), WOOD_FLOOR, DOWN_ENTRANCE, UP_ENTRANCE, LEFT_ENTRANCE, RIGHT_ENTRANCE, TOP_LEFT_CORNER_HOUSE(true),
		TOP_BORDER_HOUSE(true), TOP_RIGHT_CORNER_HOUSE(true), LEFT_BORDER_HOUSE(true), FILLING_HOUSE(true), RIGHT_BORDER_HOUSE(true), BOT_LEFT_CORNER_HOUSE(true), BOT_BORDER_HOUSE(true),
		BOT_RIGHT_CORNER_HOUSE(true), LAVA(0f, 1.4f);
		
		
		boolean collides = false;
		Image image;
		private float encounterRate = 0;
		public float speedMultiplier = 1;
		
		public float getEncounterRate(){
			return encounterRate;
		}
		
		public boolean collides(){
			return collides;
		}
		
		public Image getImage(){
			return image;
		}
		
		public void setImage(Image image){
			this.image = image;
		}
		
		TileType(){}
		
		TileType(boolean collides){
			this.collides = collides;
		}
		
		TileType(float er){
			this.encounterRate = er;
		}
		
		TileType(float er, float sp){
			this.encounterRate = er;
			this.speedMultiplier = sp;
		}
		
		public static void initialize(){
			GRASSY_ROCK.setImage(ImageHolder.GRASSY_ROCK);
			PURPLE_GRASSY_ROCK.setImage(ImageHolder.PURPLE_GRASSY_ROCK);
			GRASS.setImage(ImageHolder.GRASS);
			
			WOOD_FLOOR.setImage(ImageHolder.WOOD_FLOOR);
			
			DOWN_ENTRANCE.setImage(ImageHolder.DOWN_ENTRANCE);
			UP_ENTRANCE.setImage(ImageHolder.UP_ENTRANCE);
			LEFT_ENTRANCE.setImage(ImageHolder.LEFT_ENTRANCE);
			RIGHT_ENTRANCE.setImage(ImageHolder.RIGHT_ENTRANCE);
			
			TOP_LEFT_CORNER_HOUSE.setImage(ImageHolder.TOP_LEFT_CORNER_HOUSE);
			TOP_BORDER_HOUSE.setImage(ImageHolder.TOP_BORDER_HOUSE);
			TOP_RIGHT_CORNER_HOUSE.setImage(ImageHolder.TOP_RIGHT_CORNER_HOUSE);
			LEFT_BORDER_HOUSE.setImage(ImageHolder.LEFT_BORDER_HOUSE);
			FILLING_HOUSE.setImage(ImageHolder.FILLING_HOUSE);
			RIGHT_BORDER_HOUSE.setImage(ImageHolder.RIGHT_BORDER_HOUSE);
			BOT_LEFT_CORNER_HOUSE.setImage(ImageHolder.BOT_LEFT_CORNER_HOUSE);
			BOT_BORDER_HOUSE.setImage(ImageHolder.BOT_BORDER_HOUSE);
			BOT_RIGHT_CORNER_HOUSE.setImage(ImageHolder.BOT_RIGHT_CORNER_HOUSE);
			
			LAVA.setImage(ImageHolder.LAVA);
			
			EMPTY.setImage(ImageHolder.EMPTY);
		}
	}
}