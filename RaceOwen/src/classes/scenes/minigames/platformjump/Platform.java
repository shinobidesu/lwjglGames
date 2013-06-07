package classes.scenes.minigames.platformjump;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import classes.Game;

public abstract class Platform extends PlatformObject{
	protected Color color;
	
	int width = 128;
	int height = 16;
	
	protected boolean tick = false;
	
	static Random rnd2 = new Random();
	
	public Platform(int x, int y, int width, int height){
		bounds = new Rectangle(x, y, width, height);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public Platform(int x, int y){
		bounds = new Rectangle(x, y, this.width, this.height);
		this.x = x;
		this.y = y;
	}
	
	public void step(){
		JumpPlayer.setBeneath(this);
		onStep();
	}
	
	public boolean getContinous(){
		return tick;
	}
	
	public abstract void onStep();
	
	@Override
	protected void onUpdate(){
		bounds.setLocation(x, y);
		outOfBounds();
		
		for(PlatformObject p : PlatformObject.platformObjects){
			if(p instanceof Platform){
				if(p.y < Platform.top.y){
					Platform.top = (Platform) p;
				}
			}
		}
		
		if(Platform.top.y > 0){
			Platform.generatePlatform();
		}
	}
	
	@Override
	protected void onRender(Graphics g){
		g.setColor(color);
		g.fill(bounds);
	}
	
	public void outOfBounds(){
		if(y > Game.HEIGHT){
			queueRemove();
		}
		
		if(x < -width){
			x += Game.WIDTH;
		}else if(x > Game.WIDTH){
			x -= Game.WIDTH;
		}
	}

	public void setX(float x) {
		this.x = x;
		bounds.setX(x);
	}
	
	public void setY(float y){
		this.y = y;
		bounds.setY(y);
	}
	
	public void setLocation(float x, float y){
		this.x = x;
		this.y = y;
		bounds.setLocation(x, y);
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	private static Platform top;
	
	public static void generatePlatform(){
		int x = 480+rnd.nextInt(352)-rnd2.nextInt(480);
		int y = (int) top.y-80+(rnd.nextInt(20)-rnd2.nextInt(20));
		
		top = new NormalPlatform(x, y);
	}
	
	public static void resetPlatforms(){
		PlatformObject.reset();
		top = new NormalPlatform(416, 400);
		
		while(top.y > 0){
			generatePlatform();
		}
	}
}