package classes.entities.items;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import classes.MessageBox;
import classes.entities.Entity;
import classes.entities.items.armor.ItemArmor;
import classes.entities.items.weapons.ItemWeapon;

public abstract class Item extends Entity{
	protected Image sprite;
	
	protected Quality quality = Quality.SCRAP;
	protected String name;
	protected String description;
	
	static{
		type = EntityType.ITEM;
	}
	
	public boolean drawDesc;
	
	public Quality getQuality() {
		return quality;
	}
	
	public void render(Graphics g, int x, int y){
		if(getSprite() != null){
			getSprite().draw(x, y);
		}else{
			g.setColor(Color.pink);
			g.fillOval(x, y, 32, 32);
		}
		if(drawDesc){
			if(MessageBox.display(description, Color.white, false)){
				g.setColor(getQuality().getColor());
				g.drawString(getName(), 650, 25);
				
				if(this instanceof ItemEquippable){
					ItemEquippable ie = (ItemEquippable) this;
					if(ie.isEquippable()){
						g.setColor(Color.white);
						g.drawString("\nRequires Level "+ie.levelReq, 650, 135);
					}else{
						g.setColor(Color.red);
						g.drawString("\nRequires Level "+ie.levelReq, 650, 135);
					}
				}
				if(this instanceof ItemWeapon){
					ItemWeapon iw = (ItemWeapon) this;
					g.setColor(Color.white);
					g.drawString("Damage: "+iw.mindamage+" - "+iw.maxdamage, 650, 135);
				}else if(this instanceof ItemArmor){
					ItemArmor ia = (ItemArmor) this;
					g.setColor(Color.white);
					g.drawString("Armor: "+ia.armor, 650, 135);
				}
			}
		}
	}
	
	public Image getSprite(){
		return sprite;
	}
	
	public String getName() {
		return name;
	}

	public enum Quality{
		SCRAP(Color.darkGray), COMMON(Color.white), UNCOMMON(Color.green), EPIC(new Color(160, 32, 240));
		
		public Color getColor(){
			return color;
		}
		
		private Color color;
		
		Quality(Color color){
			this.color = color;
		}
	}
	
	public void minigameEffects(){
		
	}
}