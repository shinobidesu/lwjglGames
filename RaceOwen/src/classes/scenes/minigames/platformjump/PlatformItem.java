package classes.scenes.minigames.platformjump;

import java.util.Vector;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public abstract class PlatformItem extends PlatformObject{
	private Color color = Color.yellow;
	
	private int width = 16;
	private int height = 16;
	
	private Platform parent;
	
	private static Vector<PlatformItem> platformItems = new Vector<PlatformItem>(2, 2);
	
	PlatformItem(){
		super();
		bounds = new Rectangle(0, 0, width, height);
		
		platformItems.add(this);
	}
	
	PlatformItem(Platform parent){
		bounds = new Rectangle(0, 0, width, height);
		this.parent = parent;
		platformItems.add(this);
	}
	
	@Override
	public void onUpdate(){
		if(parent != null){
			x = parent.x + parent.width/2;
			y = parent.y - parent.height/2;
			bounds.setLocation(x, y);
		}
		interactsWithPlayer();
	}
	
	@Override
	public void onRender(Graphics g){
		g.setColor(color);
		g.fill(bounds);
	}
	
	private void interactsWithPlayer(){
		if(bounds.intersects(JumpPlayer.getBounds())){
			interact();
		}
	}
	
	public void interact(){
		destroy();
		onInteract();
	}
	
	protected abstract void onInteract();
}