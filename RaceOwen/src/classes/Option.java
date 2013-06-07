package classes;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Option {
	private static String s;
	
	public static String getOptionString(){
		return s;
	}
	
	public static void clear(){
		s = null;
	}
	
	public static void display(String msg){
		s = msg;
	}
	
	public static void render(Graphics g){
		if(s != null){
			g.setColor(Color.white);
			g.drawString(s, 650, 200);
		}
	}
}