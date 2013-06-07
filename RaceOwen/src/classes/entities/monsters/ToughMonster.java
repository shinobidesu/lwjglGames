package classes.entities.monsters;


public class ToughMonster extends Monster{
	
	public ToughMonster(int level){
		super(level, 33, 4, 49);
		dropRate = 1f;
	}
}