package slamakans;

import org.newdawn.slick.Input;

public class Enemy extends Fighter{
	
	Enemy(Monster m){
		health = m.health;
		damage = m.damage;
	}
	
	@Override
	public void damage(float d) {
		health -= d;
	}

	@Override
	public void attack(float d) {
		
	}

	@Override
	public void update(Input input) {
		
	}
}