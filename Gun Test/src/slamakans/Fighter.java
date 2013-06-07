package slamakans;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

public abstract class Fighter {
	protected float x, y, damage, maxdamage, health, maxhealth;
	protected float dx = 0, dy = 0, maxX = 6f, maxY = 10f;
	protected float gravity = 0.1f, acceleration = 0.5f, friction = 0.2f;
	
	protected boolean jumped = false;
	
	protected Rectangle bounds;
	protected Rectangle feet;
	
	//Setters
	public abstract void damage(float d);
	public abstract void attack(float d);
	public void updateBounds(){
		bounds.setLocation(x, y);
		feet.setLocation(x, y-62);
	}
	
	
	//Getters
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
	
	
	//General
	public void render(Graphics g){
		g.fillRect(x, y, 32, 64);
	}
	
	public void applyGravity(){
		if(isAirborne()){
			dy += gravity;
		}else if(jumped == false){
			dy = 0;
			if(BattlePlatform.getIntersecting(feet) != null)
				y = BattlePlatform.getIntersecting(feet).getY()-32;
		}
	}
	
	public abstract void update(Input input);
}