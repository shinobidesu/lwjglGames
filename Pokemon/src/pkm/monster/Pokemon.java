package pkm.monster;

import pkm.Game;
import pkm.Player;
import pkm.move.Move;

public abstract class Pokemon {
	int HP;
	String name = "";
	Move[] move = new Move[4];
	public Player owner;
	
	
	public Pokemon(Player owner, int HP){
		Game.activePkm.add(this);
		this.setStats(HP, name, owner);
		this.setMoves();
	}
	
	abstract void setStats(int HP, String name, Player owner);
	abstract void setMoves();
	
	public void damage(int damage){
		this.HP -= damage;
		this.onDamage();
	}
	
	public void doDamage(int damage){
		this.owner.enemy.activePkm.damage(damage);
	}
	
	public void onDamage(){
		if(this.HP <= 0){
			Game.activePkm.remove(this);
		}
	}
}