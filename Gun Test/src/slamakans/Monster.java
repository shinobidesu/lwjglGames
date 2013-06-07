package slamakans;

import java.util.Random;

import org.newdawn.slick.Image;

/*import classes.Healthbar;
import classes.Inventory;
import classes.Player;
import classes.entities.Entity;
import classes.entities.items.Item;
import classes.holders.ItemHolder.ItemSort;*/

public abstract class Monster /*extends Entity*/{
	public static final int monsters = 1;
	static {
		//type = EntityType.MONSTER;
	}
	
	protected int maxhealth, health, damage, maxdamage, level, expReward;
	protected Image img;
	protected float dropRate = .65f;
	
	private static final Random rnd = new Random();
	
	public Monster(int level, int basehealth, int basedamage, int basereward){
		this.level = level;
		this.maxhealth = basehealth+(int)(level*7)+rnd.nextInt(level*3);
		this.health = this.maxhealth;
		this.damage = basedamage+(int)(level*2)+rnd.nextInt(level);
		this.maxdamage = this.damage+(rnd.nextInt(basedamage)+level);
		this.expReward = basereward+(level*2)+rnd.nextInt(level*4);
	}
	
	public static void drop(/*Item i*/){
		//if(!Inventory.isFull()){
		//	Inventory.addItem(i);
		//}
	}
	
	protected void die(){
		//Player.grantExp(expReward);
		//dropItems();
	}
	
	public abstract int onAttacked(int dmg);
}
