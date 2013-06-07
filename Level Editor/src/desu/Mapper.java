package desu;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.newdawn.slick.Image;

public class Mapper {
	public enum TileType{
		EMPTY('-'), GRASSY_ROCK('r'), PURPLE_GRASSY_ROCK('p'), GRASS('g'), WOOD_FLOOR('w'), DOWN_ENTRANCE('v'), UP_ENTRANCE('^'), LEFT_ENTRANCE('<'), RIGHT_ENTRANCE('>'), TOP_LEFT_CORNER_HOUSE('1'),
		TOP_BORDER_HOUSE('2'), TOP_RIGHT_CORNER_HOUSE('3'), LEFT_BORDER_HOUSE('4'), FILLING_HOUSE('5'), RIGHT_BORDER_HOUSE('6'), BOT_LEFT_CORNER_HOUSE('7'), BOT_BORDER_HOUSE('8'),
		BOT_RIGHT_CORNER_HOUSE('9'), LAVA('l');
		
		
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
		
		public char getId(){
			return id;
		}
		
		public void setImage(Image image){
			this.image = image;
		}
		
		char id;
		
		TileType(char id){
			this.id = id;
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
	
	public static String[] readMap(){
		String[] sd = new String[15];
		
		for(int i = 0; i < Spawn.map.length; i++){
			String s = "";
			for(int j = 0; j < Spawn.map[i].length; j++){
				if(Spawn.map[i][j] != null){
					s += Spawn.map[i][j].getId();
				}else{
					s += '-';
				}
			}
			sd[i] = s;
		}
		return sd;
	}
	
	public static String[] readWorldEntities(){
		String[] sd = new String[15];
		
		for(int i = 0; i < Spawn.map.length; i++){
			String s = "";
			for(int j = 0; j < Spawn.map[i].length; j++){
				if(Spawn.we[i][j] != null && Spawn.we[i][j] instanceof EmptyEntity == false){
					s += Spawn.we[i][j].getWorldEntityType().getId();
				}else{
					s += '-';
				}
			}
			sd[i] = s;
		}
		return sd;
	}
	
	static int desu = (int) (Math.random()*100);
	
	public static void saveFile(String[] sa){
		try {
			File file = new File(desu+"_tile.txt");
			FileWriter fw = new FileWriter(file);
			BufferedWriter out = new BufferedWriter(fw);
			String sc = "";
			for(String s : sa){
				if(sa[sa.length-1].equals(s)){
					sc += s;
				}else{
					sc += (s+System.getProperty("line.separator"));
				}
			}
			
			out.write(sc);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void saveFile(String[] sa, String name){
		try {
			File file = new File(name+"_tile.txt");
			FileWriter fw = new FileWriter(file);
			BufferedWriter out = new BufferedWriter(fw);
			String sc = "";
			for(String s : sa){
				if(sa[sa.length-1].equals(s)){
					sc += s;
				}else{
					sc += (s+System.getProperty("line.separator"));
				}
			}
			
			out.write(sc);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void saveEntities(String[] sa){
		try {
			File file = new File(desu+"_entity.txt");
			FileWriter fw = new FileWriter(file);
			BufferedWriter out = new BufferedWriter(fw);
			String sc = "";
			for(String s : sa){
				if(sa[sa.length-1].equals(s)){
					sc += s;
				}else{
					sc += (s+System.getProperty("line.separator"));
				}
			}
			
			out.write(sc);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void saveEntities(String[] sa, String name){
		try {
			File file = new File(name+"_entity.txt");
			FileWriter fw = new FileWriter(file);
			BufferedWriter out = new BufferedWriter(fw);
			String sc = "";
			for(String s : sa){
				if(sa[sa.length-1].equals(s)){
					sc += s;
				}else{
					sc += (s+System.getProperty("line.separator"));
				}
			}
			
			out.write(sc);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}