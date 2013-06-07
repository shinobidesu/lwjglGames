package classes.scenes.battle;

import java.util.Random;
import java.util.Vector;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import classes.holders.ImageHolder;

public class BattlePlatform {
	private static Image image = ImageHolder.BATTLE_PLATFORM;
	private static Vector<BattlePlatform> platforms = new Vector<BattlePlatform>(5, 1);
	
	private float x, y, width = 256, height = 16;
	private Rectangle bounds;
	
	private BattlePlatform(){
		platforms.add(this);
	}
	
	public BattlePlatform(float x, float y){
		this();
		this.x = x;
		this.y = y;
		bounds = new Rectangle(x, y, width, height);
	}
	
	public BattlePlatform(float x, float y, float w, float h){
		this();
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		bounds = new Rectangle(x, y, width, height);
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
	
	public static void render(Graphics g){
		if(platforms != null){
			for(BattlePlatform bp : platforms){
				image.draw(bp.x, bp.getY(), bp.width, bp.height);
			}
		}
	}
	
	

	private static Random rnd = new Random();
	private static Random rnd2 = new Random();
	private static BattlePlatform top;
	
	
	private static void generatePlatform(){
		int x = 480+rnd.nextInt(400)-rnd2.nextInt(400);
		int y = (int) top.getY()-75+(rnd.nextInt(40)-rnd2.nextInt(40));
		
		if(top.getY() != 462)
			while((x > top.x+300 && x < top.x-400) /* Not too close */ && (x < 0 || x > 704) /* Not OOB */){
				x = 480+rnd.nextInt(400)-rnd2.nextInt(400);
			}
		else
			while(x < 0 || x > 704 /* Not OOB */){
				x = 480+rnd.nextInt(400)-rnd2.nextInt(400);
			}
		int xx = -1;
		
		while(xx == -1 || x+xx < 0 || x+xx > 704 || Math.abs(xx) < 256){
			xx = rnd.nextInt(768)-rnd2.nextInt(768);
		}
		
		top = new BattlePlatform(x+xx, y);
		top = new BattlePlatform(x, y);
	}
	
	public static void generate(){
		if(platforms != null)
			clear();
		platforms = new Vector<BattlePlatform>(5, 1);
		
		new BattlePlatform(0, 462);
		new BattlePlatform(256, 462);
		new BattlePlatform(512, 462);
		top = new BattlePlatform(768, 462);
		
		while(top.getY() > 100){
			generatePlatform();
		}
	}
	
	public static void clear(){
		platforms = null;
	}
	
	public static BattlePlatform getIntersecting(Shape sh){
		for(BattlePlatform bp : platforms){
			if(bp != null){
				if(bp.bounds.intersects(sh)){
					return bp;
				}
			}
		}
		
		return null;
	}

	public float getY() {
		return y;
	}
}