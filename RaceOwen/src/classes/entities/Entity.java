package classes.entities;

public abstract class Entity {
	public enum EntityType{
		NPC, MONSTER, ITEM, WEAPON, OBJECT;
	}
	
	static protected EntityType type;
	
	public EntityType getType(){
		return type;
	}
}