package classes.holders;

import java.util.Random;

import classes.entities.items.Item;
import classes.entities.items.armor.TestBoots;
import classes.entities.items.armor.TestChest;
import classes.entities.items.armor.TestHelmet;
import classes.entities.items.consumables.HealthPotion;
import classes.entities.items.weapons.AwesomeSword;
import classes.entities.items.weapons.RandomSword;
import classes.entities.items.weapons.TestSword;

public class ItemHolder {
	public enum ItemSort{
		AWESOME_SWORD(4, .1f) {
			@Override
			public Item create() {
				return new AwesomeSword();
			}
		}, RANDOM_SWORD(1, .1f) {
			@Override
			public Item create() {
				return new RandomSword();
			}
		}, TEST_SWORD(8, .1f) {
			@Override
			public Item create() {
				return new TestSword();
			}
		}, TEST_HELMET(1, .95f){

			@Override
			public Item create() {
				return new TestHelmet();
			}
			
		}, TEST_CHEST(2, .99f) {
			@Override
			public Item create() {
				return new TestChest();
			}
		}, TEST_BOOTS(0, -1) {
			@Override
			public Item create() {
				return new TestBoots();
			}
		}, LESSER_HEALTH_POTION(1, 1f) {
			@Override
			public Item create() {
				return new HealthPotion(15);
			}
		}, SMALL_HEALTH_POTION(7, .7f) {
			@Override
			public Item create() {
				return new HealthPotion(50);
			}
		};
		
		private static Random rnd = new Random();
		private int levelReq = -1;
		private float chance = 1;
		
		public abstract Item create();
		
		/**
		 * Returns a random item within the selected level span (inclusive)
		 * @param minlevel
		 * @param maxlevel
		 * @return item
		 */
		public static Item randomItem(int minlevel, int maxlevel){
			ItemSort[] arr = values();
			int size = arr.length;
			ItemSort is = null;
			while(is == null || maxlevel < is.levelReq || minlevel > is.levelReq || Math.random() > is.chance){
				is = arr[rnd.nextInt(size)];
			}
			
			return is.create();
		}
		
		ItemSort(int levelReq){
			this.levelReq = levelReq;
		}
		
		ItemSort(int levelReq, float chance){
			this.levelReq = levelReq;
			this.chance = chance;
		}
	}
}