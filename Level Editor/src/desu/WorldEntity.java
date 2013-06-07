package desu;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public abstract class WorldEntity extends Entity{
	Image image;
	
	int x, y;
	int c, r;
	
	public WorldEntity(){
		x = -32;
		y = -32;
	}
	
	/**
	 * @param c column 0-19 are valid
	 * @param r row 0-14 are valid
	 */
	public WorldEntity(int c, int r){
		x = c*32;
		y = r*32;
	}
	
	public Image getImage(){
		return image;
	}
	
	public abstract void render(Graphics g);
	
	public enum WorldEntityType{
		EMPTY('-'), TREE('t'), TRADER('n'), GAME_INITIATOR('g');
		
		private char id;
		
		public char getId(){
			return id;
		}
		
		WorldEntityType(char id){
			this.id = id;
		}
	}
	
	
	public void setLocation(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public abstract WorldEntity create();

	public abstract WorldEntityType getWorldEntityType();
}
