package classes.scenes.battle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

import classes.Game;
import classes.holders.ImageHolder;

public abstract class Fighter {
	protected float damage, maxdamage, health, maxhealth, armor; /* Stats */
	protected float x, y, dx = 0, dy = 0, maxX = 2f, maxY = 10f, jump = 6f, maxJumps = 1, jumpsLeft = maxJumps; /* Movement */
	protected float gravity = 0.18f, acceleration = 1f, friction = 0.3f; /* Physics */
	
	protected static int width = 16, height = 32;
	
	protected boolean jumped = false;
	private boolean swinging = false;
	private boolean didDamage = false;
	
	protected Rectangle bounds;
	protected Rectangle feet;
	protected Rectangle attack;
	
	/**
	 * <code>0x0: right<br>
	 * 0x1: left</code>
	 */
	protected byte rotation = 0x0;
	
	protected Random rnd = new Random();
	
	private FighterHealthbar fighterHealthbar;
	
	private ActionListener swingListener = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent ae){
			swinging = false;
			didDamage = false;
		}
	};
	
	protected Timer attackTimer = new Timer(500, null);
	protected Timer jumpTimer = new Timer(180, null);
	protected Timer swingTimer = new Timer(180, swingListener);
	{
		attackTimer.setRepeats(false);
		jumpTimer.setRepeats(false);
		swingTimer.setRepeats(false);
	}
	
	// Constructor
	public Fighter(){
		bounds = new Rectangle(0, 0, width, height);
		feet = new Rectangle(0, 0, width, 4);
		attack = new Rectangle(0, 0, width*2, height/2);
		fighterHealthbar = new FighterHealthbar(this);
	}
	
	// Mutators
	public abstract void damage(float d);
	
	public void updateBounds(){
		bounds.setLocation(x, y);
		feet.setLocation(x, y+height);
		if(rotation == 0x0){
			attack.setLocation(x, y);
		}else{
			attack.setLocation(x-width, y);
		}
	}
	
	public void regulateDelta(){
		if(dx > maxX){
			dx = maxX;
		}else if(dx < -maxX){
			dx = -maxX;
		}
		
		if(dy > maxY){
			dy = maxY;
		}else if(dx < -maxY){
			dy = -maxY;
		}
	}
	
	public void regulateX(){
		if(outOfBounds()){
			if(x < 0){
				x = 0;
				dx = 0;
			}else if(x > Game.WIDTH-width){
				x = Game.WIDTH-width;
				dx = 0;
			}
		}
	}
	
	
	// Selectors
	public Fighter hit(){
		if(swinging){
			if(attack.intersects(Battle.getEnemy().getBounds()) && this != Battle.getEnemy()){
				return this;
			}
			if(attack.intersects(Game.battlePlayer.getBounds()) && this != Game.battlePlayer){
				return this;
			}
		}
		
		return null;
	}
	
	public float getHealth(){
		return health;
	}
	
	public float getDamage(){
		return damage;
	}
	
	public Rectangle getBounds(){
		return bounds;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
	public float getPercentageHealth(){
		return health / (float) maxhealth;
	}
	
	public boolean isAirborne(){
		return BattlePlatform.getIntersecting(feet) == null || dy < 0;
	}
	
	public boolean outOfBounds(){
		return x < 0 || x > Game.WIDTH-width;
	}
	
	
	//General
	
	public void jump(){
		if(!isAirborne() || (jumpTimer.isRunning() == false && jumpsLeft > 0)){
			if(isAirborne()){
				jumpsLeft -= 1;
			}
			dy = -jump;
			
			if(x == 0){
				dx += jump/2;
			}else if(x == Game.WIDTH-width){
				dx -= jump/2;
			}

			jumpTimer.start();
		}
	}
	
	protected void applyFriction(){
		if(isAirborne()){
			if(dx > friction/30){
				dx -= friction/30;
			}else if(dx < -friction/30){
				dx += friction/30;
			}else{
				dx = 0;
			}
		}else{
			if(dx > friction){
				dx -= friction;
			}else if(dx < -friction){
				dx += friction;
			}else{
				dx = 0;
			}
		}
	}
	
	public void applyGravity(){
		if(isAirborne()){
			dy += gravity;
		}else if(jumped == false){
			dy = 0;
			if(BattlePlatform.getIntersecting(feet) != null){
				y = BattlePlatform.getIntersecting(feet).getY()-32;
				jumpsLeft = maxJumps;
				jumpTimer.stop();
			}
		}
	}
	
	protected void swing(){
		if(attackTimer.isRunning() == false){
			attackTimer.start();
			swingTimer.start();
			swinging = true;
		}
	}
	
	private int swingAnimTimer = 0;
	
	public void render(Graphics g){
		fighterHealthbar.render(g);
		g.setColor(Color.orange);
		g.draw(bounds);
		g.setColor(Color.red);
		g.draw(feet);
		if(swinging){
			g.setColor(Color.green);
			g.draw(attack);
			swingAnimTimer++;
			if(swingAnimTimer > 180){
				getImage(3).draw(x, y, 16, 32);
			}else if(swingAnimTimer > 120){
				getImage(2).draw(x, y, 16, 32);
			}else if(swingAnimTimer > 60){
				getImage(1).draw(x, y, 16, 32);
			}
		}else{
			swingAnimTimer = 0;
			if(dx == 0){
				getImage(0).draw(x, y, 16, 32);
			}else{
				getImage(1).draw(x, y, 16, 32);
			}
		}
	}
	
	public void update(Input input){
		update2(input);
		applyFriction();
		applyGravity();
		
		regulateDelta();
		
		x += dx;
		y += dy;
		
		regulateX();
		
		updateBounds();
		if(hit() != null && didDamage == false){
			hit().attack();
			didDamage = true;
		}
		fighterHealthbar.update();
	}
	
	public void goLeft(){
		if(isAirborne()){
			dx -= acceleration/15f;
		}else{
			dx -= acceleration;
			rotation = 0x1;
		}
	}
	
	public void goRight(){
		if(isAirborne()){
			dx += acceleration/15f;
		}else{
			dx += acceleration;
			rotation = 0x0;
		}
	}
	
	public Image getImage(int i){
		Image img = ImageHolder.BATTLE_ANIM.getSprite(i, 0);
		
		if(rotation == 0x0){
			return img;
		}else{
			return img.getFlippedCopy(true, false);
		}
	}
	
	public abstract void attack();
	
	protected abstract void update2(Input input);
}