package classes.entities.items;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import classes.Player;

public abstract class ItemEquippable extends Item{
	public boolean equipped;
	
	public int levelReq = 1;
	
	private Color transRed = new Color(255, 0, 0, 100);
	
	public boolean isEquipped(){
		return equipped;
	}
	
	public abstract void equip();
	public abstract void unequip();
	
	public boolean isEquippable(){
		return Player.level >= levelReq;
	}
	
	public void render(Graphics g, int x, int y){
		super.render(g, x, y);
		if(!isEquippable()){
			g.setColor(transRed);
			g.fillRect(x, y, 32, 32);
		}
		if(isEquipped()){
			g.setColor(Color.red);
			g.drawRect(x, y, 32, 32);
		}else{
			g.setColor(getQuality().getColor());
			g.drawRect(x, y, 32, 32);
		}
	}
}