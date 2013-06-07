package classes;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;


public class Healthbar {
	Color red = Color.red;
	Color green = Color.green;
	
	float x = 0;
	float y = 0;
	
	int height = 8;
	int width = 32;
	
	public Healthbar(int x, int y, int width, int height, int distance){
		this.x = x;
		this.y = y-distance;
		this.width = width;
		this.height = height;
	}
	
	public Healthbar(){
		x = (Player.getCol()*32);
		y = (Player.getRow()*32)-12;
	}
	
	public void render(Graphics g){
		g.setColor(red);
		g.fillRect(x, y, width, height);
		g.setColor(green);
		g.fillRect(x, y, width*Player.getPercentageHealth(), height);
	}
	
	public void update(){
		x = Player.getX();
		y = Player.getY()-12;
	}
}