package classes.entities.worldentities;

import java.awt.geom.Point2D;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import classes.MessageBox;
import classes.Player;
import classes.entities.Entity;

public abstract class WorldEntity extends Entity{
	static{
		type = EntityType.OBJECT;
	}
	
	protected Image image;
	
	protected Rectangle bounds;
	
	protected int x;

	protected int y;
	int c, r;
	
	protected int state = 0;
	
	/**
	 * @param c column 0-19 are valid
	 * @param r row 0-14 are valid
	 */
	public WorldEntity(int c, int r){
		x = c*32;
		y = r*32;
		bounds = new Rectangle(x, y, 32, 32);
	}
	
	public Image getImage(){
		return image;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getCol(){
		return c;
	}
	
	public int getRow(){
		return r;
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
	
	protected boolean interacting = false;
	
	public void interact(){
		if(!isInteracting()){
			interacting = true;
			onInteract();
		}
	}
	
	public void update(Input input, StateBasedGame sbg){
		if(Point2D.distance(x, y, Player.getX(), Player.getY()) > 32 && isInteracting()){
			close();
		}
		
		onUpdate(input, sbg);
	}
	
	public void close(){
		interacting = false;
		MessageBox.clear(true);
		state = 0;
		onClose();
	}
	
	public boolean isInteracting(){
		return interacting;
	}
	
	public void render(Graphics g){
		if(image != null)
			image.draw(x, y);
		onRender(g);
	}
	
	public abstract void onInteract();
	public abstract void onClose();
	public abstract void onUpdate(Input input, StateBasedGame sbg);
	public abstract void onRender(Graphics g);
}
