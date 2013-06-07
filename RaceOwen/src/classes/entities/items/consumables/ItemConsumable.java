package classes.entities.items.consumables;

import classes.entities.items.Item;

public abstract class ItemConsumable extends Item{
	
	ItemConsumable(String description){
		this.description = description;
	}
	
	ItemConsumable(String name, String description){
		this.description = description;
		this.name = name;
	}
	
	public abstract void consume();
}