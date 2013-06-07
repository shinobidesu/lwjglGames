package classes.entities.monsters;

import java.util.Random;

import classes.Inventory;
import classes.Player;
import classes.entities.items.Item;
import classes.holders.ItemHolder.ItemSort;

public abstract class Monster /*extends Entity*/{
	public static final int monsters = 1;
	static {
		//type = EntityType.MONSTER;
	}
	
	protected int maxhealth;
	private int health;
	private int damage;
	protected int maxdamage;
	protected float maxX = 2f, maxY = 10f; /* Movement */
	protected float gravity = 0.18f, acceleration = 1f, friction = 0.3f; /* Physics */
	
	
	
	
	
	protected int level;
	protected int expReward;
	protected float dropRate = .65f;
	
	private static final Random rnd = new Random();
	
	public Monster(int level, int basehealth, int basedamage, int basereward){
		this.level = level;
		this.maxhealth = basehealth+(int)(level*7)+rnd.nextInt(level*3);
		this.health = this.maxhealth;
		this.damage = basedamage+(int)(level*2)+rnd.nextInt(level);
		this.maxdamage = damage+(rnd.nextInt(basedamage)+level);
		this.expReward = basereward+(level*6)+rnd.nextInt(level*3);
		
		System.out.println(damage);
		System.out.println(maxdamage);
	}
	
	public static void drop(Item i){
		if(!Inventory.isFull()){
			Inventory.addItem(i);
		}
	}
	
	protected void damage(float d){
		health -= d;
		if(health <= 0){
			die();
		}
	}
	
	public void die(){
		Player.grantExp(expReward);
		drop(ItemSort.randomItem(level-5, level+3));
	}

	public int getHealth() {
		return health;
	}
	
	public int getMaxhealth() {
		return maxhealth;
	}

	public int getDamage() {
		return damage;
	}
	
	public int getMaxdamage() {
		return maxdamage;
	}

	public float getMaxX() {
		return maxX;
	}

	public float getMaxY() {
		return maxY;
	}

	public float getGravity() {
		return gravity;
	}

	public float getAcceleration() {
		return acceleration;
	}

	public float getFriction() {
		return friction;
	}
}
