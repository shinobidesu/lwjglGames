package classes.entities.items.weapons;

import java.util.Random;


public class RandomSword extends ItemWeapon{
	private static final Random rnd = new Random();
	public RandomSword(){
		super(7+rnd.nextInt(4), 11+rnd.nextInt(3), 1, ""+Math.random()*100, Quality.UNCOMMON, "'The numbers Mason... What do they mean?!' - Blackchild, 2012+1");
	}
}