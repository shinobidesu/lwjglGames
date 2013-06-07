package classes.entities.items.armor;

import classes.Player;
import classes.entities.items.ItemEquippable;

public class ItemArmor extends ItemEquippable{
	public int armor;
	
	protected ItemArmor(int armor, String name, String description){
		this.armor = armor;
		this.name = name;
		this.description = description;
	}
	
	public void equip(){
		if(isEquippable()){
			if(isEquipped()){
				this.unequip();
				return;
			}
			if(this instanceof ItemHelmet){
				if(Player.equippedHelmet != null){
					Player.equippedHelmet.unequip();
				}
				Player.equippedHelmet = (ItemHelmet) this;
			}else if(this instanceof ItemChest){
				if(Player.equippedChest != null){
					Player.equippedChest.unequip();
				}
				Player.equippedChest = (ItemChest) this;
			}else if(this instanceof ItemBoots){
				if(Player.equippedBoots != null){
					Player.equippedBoots.unequip();
				}
				Player.equippedBoots = (ItemBoots) this;
			}
			
			Player.armor += armor;
			equipped = true;
		}
	}
	
	public void unequip(){
		Player.armor -= armor;
		
		if(this instanceof ItemHelmet){
			Player.equippedHelmet = null;
		}else if(this instanceof ItemChest){
			Player.equippedChest = null;
		}else if(this instanceof ItemBoots){
			Player.equippedBoots = null;
		}
		
		equipped = false;
	}
}