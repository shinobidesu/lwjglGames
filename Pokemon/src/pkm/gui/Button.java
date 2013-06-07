package pkm.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import pkm.Game;

public class Button {
	int width;
	int height;
	int x;
	int y;
	String text;
	
	public Button(String text, int width, int height, int x, int y){
		Game.buttons.add(this);
		this.text = text;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}
	
	public void render(Graphics g){
		Graphics g2 = g;
		g2.setColor(Color.red);
		g2.fillRect(this.x-this.width/2, this.y-this.height/2, width, height);
		Game.draw3DText(this.x-(this.text.length()*6), this.y-6, 2, this.text, Color.black, Color.gray, g2);
	}
}