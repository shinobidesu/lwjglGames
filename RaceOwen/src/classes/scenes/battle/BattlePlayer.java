package classes.scenes.battle;

import org.newdawn.slick.Input;

import classes.Player;

public class BattlePlayer extends Fighter{
	
	public BattlePlayer(){
		super();
		this.x = 50;
		this.y = 200;
	}
	
	@Override
	public void attack() {
		Battle.getEnemy().damage(damage+rnd.nextInt((int) (maxdamage-damage)));
	}
	
	@Override
	public void damage(float d){
		float mod = (100f-Player.armor)/100f;
		if(mod < .3f){
			mod = .3f;
		}
		d = (int) (d * mod);
		
		Player.damage(d);
	}
	
	@Override
	protected void update2(Input input) {
		in = input;
		if(keyPress(Input.KEY_UP) || keyPress(Input.KEY_W)){
			jump();
		}
		
		if(keyDown(Input.KEY_SPACE)){
			swing();
		}
		
		if(keyDown(Input.KEY_LEFT) || keyDown(Input.KEY_A)){
			goLeft();
		}else if(keyDown(Input.KEY_RIGHT) || keyDown(Input.KEY_D)){
			goRight();
		}
		
		updateStats();
	}
	
	private void updateStats(){
		this.maxdamage = Player.getMaxdamage();
		this.damage = Player.getDamage();
		this.maxhealth = Player.getMaxhealth();
		this.health = Player.getHealth();
		this.armor = Player.getArmor();
	}
	
	private Input in;
	
	private boolean keyDown(int i){
		return in.isKeyDown(i);
	}
	
	private boolean keyPress(int i){
		return in.isKeyPressed(i);
	}
}