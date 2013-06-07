package pkm.move;

import pkm.monster.Pokemon;

public abstract class Move {
	int PP;
	int power;
	String type;
	int acc;
	Pokemon user;
	
	public Move(Pokemon user, int PP, int power, String type, int acc){
		this.PP = PP;
		this.power = power;
		this.type = type;
		this.acc = acc;
		this.user = user;
	}
	
	public abstract void onUse();
}