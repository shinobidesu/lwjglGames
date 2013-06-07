package pkm.move;

import pkm.monster.Pokemon;

public class MoveTackle extends Move{
	public MoveTackle(Pokemon user){
		super(user, 10, 100, "faggot", 100);
	}
	
	public void onUse(){
		user.doDamage(power);
	}
}