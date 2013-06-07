package classes;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

import classes.holders.ImageHolder;

public class Button {
	private Rectangle rect;
	private Color c;
	private String label;
	
	private int x, y;
	
	private boolean clicked = false;
	
	private Image image = ImageHolder.PASSIVE_BUTTON;
	
	public Button(String label, int x, int y){
		this.label = label;
		this.c = Color.blue;
		this.x = x-Game.rectButtonSize.width/2;
		this.y = y-Game.rectButtonSize.height/2;
		this.rect = new Rectangle(this.x, this.y, Game.rectButtonSize.width, Game.rectButtonSize.height);
	}
	
	public Button(String label, Color c, int x, int y){
		this.label = label;
		this.c = c;
		this.x = x-Game.rectButtonSize.width/2;
		this.y = y-Game.rectButtonSize.height/2;
		this.rect = new Rectangle(this.x, this.y, Game.rectButtonSize.width, Game.rectButtonSize.height);
	}
	
	public void update(Input input){
		if(rect.contains(input.getMouseX(), input.getMouseY())){
			if(input.isMouseButtonDown(0)){
				image = ImageHolder.PRESSED_BUTTON;
				if(input.isMousePressed(0)){
					clicked = true;
				}else{
					clicked = false;
				}
			}else{
				image = ImageHolder.HOVER_BUTTON;
				clicked = false;
			}
		}else{
			image = ImageHolder.PASSIVE_BUTTON;
			clicked = false;
		}
	}
	
	public boolean clicked(){
		return clicked;
	}
	
	public void render(Graphics g){
		if(image != null){
			image.draw(x, y, rect.getWidth(), rect.getHeight());
		}
		g.setColor(c);
		g.drawString(label, rect.getCenterX()-(g.getFont().getWidth(label)/2), rect.getCenterY()-(g.getFont().getLineHeight()/2));
	}
}