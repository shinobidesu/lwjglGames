package classes;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

import classes.holders.ImageHolder;

public class SkillButton {
	private Rectangle rect;
	private int x, y;
	
	private boolean clicked = false;
	
	private Image image = ImageHolder.DISABLED_SKILL_BUTTON;
	
	public SkillButton(int x, int y){
		this.x = x;
		this.y = y;
		rect = new Rectangle(this.x, this.y, 16, 16);
	}
	
	public void update(Input input){
		if(Player.attributePoints == 0){
			image = ImageHolder.DISABLED_SKILL_BUTTON;
			return;
		}
		if(rect.contains(input.getMouseX(), input.getMouseY())){
			if(input.isMouseButtonDown(0)){
				image = ImageHolder.ENABLED_SKILL_BUTTON;
				if(input.isMousePressed(0)){
					clicked = true;
				}else{
					clicked = false;
				}
			}else{
				image = ImageHolder.HOVER_SKILL_BUTTON;
				clicked = false;
			}
		}else{
			image = ImageHolder.ENABLED_SKILL_BUTTON;
			clicked = false;
		}
	}
	
	public boolean clicked(){
		return clicked;
	}
	
	public void render(Graphics g){
		if(image != null)
			image.draw(x, y, rect.getWidth(), rect.getHeight());
	}
}