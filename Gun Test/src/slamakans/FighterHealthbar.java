package slamakans;

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
		this.width = 32;
		this.height = 16;
	}
	
	public void render(Graphics g){
		g.setColor(red);
		g.fillRect(x, y, width, height);
		g.setColor(green);
		g.fillRect(x, y, width*f.getPercentageHealth(), height);
	}
	
	public void update(){
		x = f.getX();
		y = f.getY()-12;
	}
}