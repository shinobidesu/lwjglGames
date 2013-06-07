package slamakans;

import org.newdawn.slick.Input;

public class BattlePlayer extends Fighter{

	@Override
	public void damage(float d) {
		health -= d;
	}

	@Override
	public void attack(float d) {
		Battle.getEnemy().damage(d);
	}
	
	@Override
	public void update(Input input) {
		
		
		updateBounds();
		bounds.setLocation(x, y);
	}
}