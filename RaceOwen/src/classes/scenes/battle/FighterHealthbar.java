package classes.scenes.battle;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;


public class FighterHealthbar {
	Color red = Color.red;
	Color green = Color.green;
	
	float x;
	float y;
	
	int height = 8;
	int width = 32;
	
	Fighter f;
	
	public FighterHealthbar(Fighter e){
		this.f = e;
		this.x = e.getX();
		this.y = e.getY();
	}
	
	public void render(Graphics g){
		g.setColor(red);
		g.fillRect(x, y, width, height);
		g.setColor(green);
		if(width*f.getPercentageHealth() > 0){
			g.fillRect(x, y, width*f.getPercentageHealth(), height);
		}
	}
	
	public void update(){
		x = f.getX()-width/4;
		y = f.getY()-16;
	}
}