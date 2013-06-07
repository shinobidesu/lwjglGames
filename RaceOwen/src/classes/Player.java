package classes;

import java.util.Random;

import javax.swing.Timer;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import classes.Mapper.TileType;
import classes.entities.items.armor.ItemBoots;
import classes.entities.items.armor.ItemChest;
import classes.entities.items.armor.ItemHelmet;
import classes.entities.items.weapons.ItemWeapon;
import classes.entities.monsters.Monster;
import classes.entities.worldentities.WorldEntity;

public class Player {
	private static float x = 192;
	private static float y = 128;
	
	public static float dx = 0;
	public static float dy = 0;
	
	public static final int WIDTH = 32;
	public static final int HEIGHT = 32;
	
	static Circle bounds = new Circle(x, y, WIDTH/2);
	
	private static final Healthbar healthbar = new Healthbar();
	
	public static boolean inBattle = false;
	public static Image battleImage;
	
	public static boolean trading = false;
	
	public static String name = "Neko Fetish Guy";
	
	public static void render(Graphics g){
		Input i = Game.game.getContainer().getInput();
		if(dy == 0 && dx == 0 && !(i.isKeyDown(Input.KEY_W) || i.isKeyDown(Input.KEY_A) || i.isKeyDown(Input.KEY_S) || i.isKeyDown(Input.KEY_D) ||
			i.isKeyDown(Input.KEY_UP) || i.isKeyDown(Input.KEY_LEFT) || i.isKeyDown(Input.KEY_DOWN) || i.isKeyDown(Input.KEY_RIGHT))){
			anim.setCurrentFrame(1);
			anim.stop();
		}else{
			anim.start();
			g.drawAnimation(anim, -300, -300);
		}
		Image c = anim.getCurrentFrame().copy();
		c.rotate(angle);
		c.draw(x, y);
		if(Game.game.isAcceptingInput()){
			healthbar.render(g);
		}
	}
	
	private static int angle = 180;
	
	public static float getX(){
		return x;
	}
	
	public static float getY(){
		return y;
	}
	
	public static void setX(float xx){
		x = xx;
	}
	
	public static void setY(float yy){
		y = yy;
	}
	
	public static Circle getBounds(){
		return bounds;
	}
	
	public static int[] getRowAndCol(){
		int[] rowNcol = {-1, -1};
		
		if(getX()%32 == 0 && getY()%32 == 0){
			rowNcol[0] = (int) getY()/32;
			rowNcol[1] = (int) getX()/32;
		}
		
		return rowNcol;
	}
	
	/**
	 * Sets the vertical tile for the player.
	 * @param row 0-14 are valid.
	 */
	public static void setRow(int row){
		setY(row*32);
	}
	
	/**
	 * Sets the horizontal tile for the player.
	 * @param col 0-19 are valid.
	 */
	public static void setCol(int col){
		setX(col*32);
	}
	
	public static int getRow(){
		float yy = y;
		if(yy%32 >= 16){
			yy += 32 - (yy%32);
		}else if(yy%32 < 16){
			yy -= yy%32;
		}
		
		return (int)(yy/32);
	}
	
	public static int getCol(){
		float xx = x;
		
		if(xx%32 >= 16){
			xx += 32 - (xx%32);
		}else if(xx%32 < 16){
			xx -= xx%32;
		}
		
		return (int)(xx/32);
	}
	
	public static void move(Input input){
		if(Game.curScene != Game.mainMenu){
			if(!moving() && getX() != -1 && getY() != -1){
				if(input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A)){
					WorldEntity e = Game.curScene.getEntity(getRow(), getCol()-1);
					if(e != null){
						e.interact();
					}else if(Game.curScene.isWalkable(getRow(), getCol()-1)){
						dx = -32;
					}
				}else if(input.isKeyDown(Input.KEY_RIGHT)  || input.isKeyDown(Input.KEY_D)){
					WorldEntity e = Game.curScene.getEntity(getRow(), getCol()+1);
					if(e != null){
						e.interact();
					}else if(Game.curScene.isWalkable(getRow(), getCol()+1)){
						dx = 32;
					}
				}else if(input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_W)){
					WorldEntity e = Game.curScene.getEntity(getRow()-1, getCol());
					if(e != null){
						e.interact();
					}else if(Game.curScene.isWalkable(getRow()-1, getCol())){
						dy = -32;
					}
				}else if(input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_S)){
					WorldEntity e = Game.curScene.getEntity(getRow()+1, getCol());
					if(e != null){
						e.interact();
					}else if(Game.curScene.isWalkable(getRow()+1, getCol())){
						dy = 32;
					}
				}
			}
			
			if(input.isKeyDown(Input.KEY_SPACE) && Game.DEBUG_MODE){
				health++;
				if(health > maxhealth)
					maxhealth = health;
			}
			
			if(input.isKeyPressed(Input.KEY_R) && input.isKeyPressed(Input.KEY_LCONTROL)){
				dy = 0;
				dx = 0;
				setRow(getRow());
				setCol(getCol());
			}
			
			update();
		}
	}
	
	public static boolean isDead(){
		return health <= 0;
	}
	
	public static boolean moving(){
		return dx != 0 || dy != 0;
	}
	
	private static float dd;
	private static float speedMultiplier = 1;
	
	public static Animation anim;
	
	public static void update(){
		/*
		 * Makes it so Player moves at a constant speed regardless of the computer's speed.
		 */
		if(Game.getFPS() != 0){
			dd = (80f/Game.getFPS())*speedMultiplier;
		}else{
			dd = 1;
		}
		
		if(dx > 0){
			dx -= dd;
			x += dd;
			angle = 0;
		}else if(dx < 0){
			dx += dd;
			x -= dd;
			angle = 180;
		}else if(dy > 0){
			dy -= dd;
			y += dd;
			angle = 90;
		}else if(dy < 0){
			dy += dd;
			y -= dd;
			angle = 270;
		}
		
		if(dx > -0.3*speedMultiplier && dx < 0.3*speedMultiplier && dx != 0){
			dx = 0;
		}
		
		if(dy > -0.3*speedMultiplier && dy < 0.3*speedMultiplier && dy != 0){
			dy = 0;
		}
		
		if(x%32 > 16 && x%32 != 0 && dx == 0){
			x += 32 - (x%32);
			if(x%32 == 0)
				onTileMove();
		}else if(x%32 < 16 && x%32 != 0 && dx == 0){
			x -= x%32;
			if(x%32 == 0)
				onTileMove();
		}
		
		if(y%32 > 16 && y%32 != 0 && dy == 0){
			onTileMove();
			y += 32 - (y%32);
		}else if(y%32 < 16 && y%32 != 0 && dy == 0){
			y -= y%32;
			onTileMove();
		}
		
		bounds.setX(x);
		bounds.setY(y);
		
		healthbar.update();
	}
	
	private static void onTileMove(){
		TileType tt = Game.curScene.getTileType(Player.getRow(), Player.getCol());
		
		if(tt == null)
			return;
		
		speedMultiplier = tt.speedMultiplier;
		
		if(tt == TileType.LAVA){
			health -= 1;
			if(Player.isDead()){
				Player.die();
			}
		}
		
		if(Math.random() < tt.getEncounterRate()){
			Monster m = Game.curScene.randomMonster();
			if(m != null)
				Game.enterBattle(m);
		}
	}
	
	public static final Timer gameOverTimer = new Timer(2000, null);
	
	public static void damage(float d){
		health -= d;
	}
	
	public static void die() {
		setX(192);
		setY(128);
		health = maxhealth;
		Game.setAndEnterSceneWithTransition(Game.spawn, new FadeOutTransition(Color.black, 2500), new FadeInTransition(Color.black, 2500));
		gameOverTimer.start();
	}

	static Random rnd = new Random();
	
	public static int maxhealth = 50;
	public static int health = maxhealth;
	
	public static int strength = 5;
	public static int dexterity = 5;
	public static int armor = 0;
	
	public static int damage = (int) (strength*1.618);
	public static int maxdamage = 3+(int) (strength*1.718);
	
	public static float critChance = 0.05f+(((dexterity/2)/100f));
	
	public static void onWeaponChange(){
		damage = (int) (strength*1.618);
		maxdamage = 3+(int) (strength*1.718);
		if(equippedWeapon != null){
			damage += equippedWeapon.mindamage;
			maxdamage += equippedWeapon.maxdamage;
		}
	}
	
	public static ItemWeapon equippedWeapon;
	public static ItemHelmet equippedHelmet;
	public static ItemChest equippedChest;
	public static ItemBoots equippedBoots;
	//public static ItemLegs equippedLegs;
	
	public static int level = 1;
	
	public static void heal(int heal){
		health += heal;
		if(health > maxhealth){
			health = maxhealth;
		}
	}
	
	public static float getPercentageHealth(){
		return health / (float) maxhealth;
	}
	
	public static int reqExp = 100;
	public static int exp = 0;
	public static int attributePoints = 0;
	
	public static void grantExp(int expReward) {
		exp += expReward;
		if(exp >= reqExp){
			exp -= reqExp;
			level++;
			maxhealth += 2;
			heal(10);
			attributePoints++;
			reqExp *= Math.sqrt(1.618);
		}
	}
	
	public static void skillHealth(){
		attributePoints--;
		health += 3;
		maxhealth += 3;
	}
	
	
	public static void skillStrength(){
		attributePoints--;
		strength += 2;

		onWeaponChange();
	}

	public static void skillDexterity() {
		attributePoints--;
		dexterity += 2;
		
		critChance = 0.05f+(((dexterity/2)/100f));
	}
	

	// Selectors

	public static Image getBattleImage() {
		return battleImage;
	}
	
	public static float getCritChance() {
		return critChance;
	}

	public static int getMaxhealth() {
		return maxhealth;
	}

	public static int getHealth() {
		return health;
	}

	public static int getStrength() {
		return strength;
	}

	public static int getDexterity() {
		return dexterity;
	}

	public static int getArmor() {
		return armor;
	}

	public static int getDamage() {
		return damage;
	}

	public static int getMaxdamage() {
		return maxdamage;
	}
}