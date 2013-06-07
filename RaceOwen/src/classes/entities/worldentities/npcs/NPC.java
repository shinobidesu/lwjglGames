package classes.entities.worldentities.npcs;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import classes.entities.worldentities.WorldEntity;
import classes.holders.ImageHolder;

public abstract class NPC extends WorldEntity{
	String name = "";
	
	public NPC(int c, int r) {
		super(c, r);
		if(image == null){
			image = ImageHolder.NPC_TEMPLATE;
		}
	}

	static {
		type = EntityType.NPC;
	}
	
	public void render(Graphics g){
		image.draw(x, y);
		g.setColor(Color.white);
		if(isInteracting()){
			g.drawString(name, 650, 5);
		}
	}
}