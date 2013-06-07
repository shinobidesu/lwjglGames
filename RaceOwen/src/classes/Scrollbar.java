package classes;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import classes.holders.ImageHolder;

public class Scrollbar {
	private int x, y;
	private int xx, yy; // LAST 231
	private String label = null;
	
	public Scrollbar(int x, int y){
		this.x = x;
		this.xx = 118;
		
		this.y = y;
		this.yy = y+6;
	}
	
	public Scrollbar(String label, int x, int y){
		this.label = label;
		
		this.x = x;
		this.xx = 231;
		
		this.y = y;
		this.yy = y+6;
	}
	
	public void update(Input input){
		if(input.isMouseButtonDown(0)){
			int var1 = input.getMouseY();
			if(var1 > y && var1 < y+32){
				int var2 = input.getMouseX();
				if(var2 > x && var2 < x+256){
					xx = var2-x-10;
					if(xx < 5){
						xx = 5;
					}else if(xx > 231){
						xx = 231;
					}
				}
			}
		}
	}
	
	public float getValue(){
		return 1.0f-(231f-xx)/226f;
	}
	
	public void render(Graphics g){
		if(label != null){
			g.setColor(Color.white);
			g.drawString(label, x-(g.getFont().getWidth(label)+5), y+(g.getFont().getLineHeight()/4));
		}
		
		g.drawImage(ImageHolder.SCROLLBAR, x, y);
		g.drawImage(ImageHolder.PEG, x+xx, yy);
	}
}