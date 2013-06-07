package classes.entities.items.weapons;

import classes.Player;
import classes.entities.items.ItemEquippable;
import classes.holders.ImageHolder;

public abstract class ItemWeapon extends ItemEquippable{
	public int mindamage, maxdamage;
	public int damagerange;
	
	protected ItemWeapon(int mindamage, int maxdamage, int levelReq, String name, Quality quality, String description){
		this.sprite = ImageHolder.SWORD;
		this.mindamage = mindamage;
		this.maxdamage = maxdamage;
		this.levelReq = levelReq;
		this.name = name;
		this.quality = quality;
		this.description = description;
	}
	
	static{
		type = EntityType.WEAPON;
	}
	
	public void equip(){
		if(isEquippable()){
			if(isEquipped()){
				Player.equippedWeapon.unequip();
				return;
			}
			if(Player.equippedWeapon != null){
				Player.equippedWeapon.unequip();
			}
			Player.equippedWeapon = this;
			equipped = true;
			Player.onWeaponChange();
		}
	}
	
	public void unequip(){
		Player.equippedWeapon = null;
		equipped = false;
		Player.onWeaponChange();
	}
}