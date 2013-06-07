package classes.entities.items.consumables;

import classes.Inventory;
import classes.Player;
import classes.holders.ImageHolder;

public class HealthPotion extends ItemConsumable{
	
	private int amount;
	
	{
		sprite = ImageHolder.HEALTH_POTION;
	}
	
	public HealthPotion(int healAmount){
		super("Heals you for "+healAmount+ " health points");
		amount = healAmount;
		
		if(healAmount == 15){
			name = "Lesser Health Potion";
			quality = Quality.COMMON;
		}else if(healAmount == 50){
			name = "Small Health Potion";
			quality = Quality.COMMON;
		}
	}
	
	@Override
	public void consume() {
		Player.heal(amount);
		Inventory.removeItem(this);
	}
}
