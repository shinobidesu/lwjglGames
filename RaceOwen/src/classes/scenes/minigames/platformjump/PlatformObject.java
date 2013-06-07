package classes.scenes.minigames.platformjump;

import java.util.Random;
import java.util.Vector;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public abstract class PlatformObject {
	public float x = 0, y = 0;
	protected Rectangle bounds;
	
	protected static Vector<PlatformObject> platformObjects = new Vector<PlatformObject>(5, 5);
	protected static Vector<PlatformObject> removeQueue = new Vector<PlatformObject>(5, 5);
	protected static Vector<PlatformObject> addQueue = new Vector<PlatformObject>(5, 5);
	
	protected static Random rnd = new Random();
	
	PlatformObject(){
		queueAdd();
	}
	
	public static void applyY(float dy){
		JumpPlayer.score += dy/10;
		
		for(PlatformObject po : platformObjects){
			if(po != null){
				po.y += dy;
			}
		}
	}
	
	public static boolean intersectsWith(Shape sh){
		for(PlatformObject po : platformObjects){
			if(po != null){
				if(po.bounds.intersects(sh)){
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static PlatformObject getIntersecting(Shape sh){
		for(PlatformObject po : platformObjects){
			if(po != null){
				if(po.bounds.intersects(sh)){
					return po;
				}
			}
		}
		
		return null;
	}
	
	public static void render(Graphics g){
		for(PlatformObject po : platformObjects){
			if(po != null){
				po.onRender(g);
			}
		}
	}
	
	public static void update(){
		if(addQueue != null){
			for(PlatformObject p : addQueue){
				add(p);
			}
			addQueue = null;
		}
		if(removeQueue != null){
			for(PlatformObject p : removeQueue){
				remove(p);
			}
			removeQueue = null;
		}
		for(PlatformObject po : platformObjects){
			if(po != null){
				po.onUpdate();
			}
		}
	}
	
	public static void remove(PlatformObject po){
		platformObjects.remove(po);
	}
	
	public void queueRemove(){
		if(PlatformObject.removeQueue == null)
			removeQueue = new Vector<PlatformObject>(5, 5);
		PlatformObject.removeQueue.add(this);
	}
	
	public static void add(PlatformObject po){
		platformObjects.add(po);
	}
	
	public void queueAdd(){
		if(PlatformObject.addQueue == null)
			addQueue = new Vector<PlatformObject>(5, 5);
		PlatformObject.addQueue.add(this);
	}
	
	public void destroy(){
		PlatformObject.remove(this);
	}
	
	public static void reset(){
		platformObjects = null;
		platformObjects = new Vector<PlatformObject>(5, 5);
	}
	
	protected abstract void onRender(Graphics g);
	protected abstract void onUpdate();
}