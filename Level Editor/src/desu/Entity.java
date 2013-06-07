package desu;

public abstract class Entity {
	public enum EntityType{
		NPC, MONSTER, ITEM, OBJECT;
	}
	
	static protected EntityType type;
	
	public EntityType getType(){
		return type;
	}
}