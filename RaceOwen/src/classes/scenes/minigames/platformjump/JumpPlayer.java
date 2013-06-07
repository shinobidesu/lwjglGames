package classes.scenes.minigames.platformjump;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

import classes.Game;

public class JumpPlayer {
	
	private static float x = Game.WIDTH/2;
	private static float y = Game.HEIGHT/2;
	
	private static float dx = 0;
	private static float dy = 0;
	
	private static int maxX = 5;
	private static int maxY = 8;
	
	public static float score = 0;
	
	private static float gravity = 0.1f;
	private static float acceleration = 0.2f;
	private static float friction = 0.03f;
	private static float jumpPower = 6f;
	
	static int lives = 3;
	
	private static Rectangle bounds = new Rectangle(x, y*3, 32, 32);
	private static Rectangle feet = new Rectangle(x, y+32, 32, 4);
	private static Rectangle head = new Rectangle(x, y, 32, 4);
	
	private static Platform beneath;
	
	/**
	 * Is calculated: <code>acceleration += change;</code>
	 * Default: 0.1f
	 * @param change Amount to change acceleration.
	 */
	public static void setSpeed(float change){
		acceleration += change;
	}
	
	/**
	 * Is calculated: <code>jumpPower += change;</code>
	 * Default: 6f
	 * @param change Amount to change jump power.
	 */
	public static void setJump(float change){
		jumpPower += change;
	}
	
	/**
	 * Is calculated: <code>gravity += change;</code>
	 * Default: 0.1f
	 * @param change Amount to change gravity.
	 */
	public static void setGravity(float change){
		gravity += change;
	}
	
	public static Platform getBeneath(){
		return beneath;
	}
	
	public static void setBeneath(Platform p){
		beneath = p;
	}
	
	public static Rectangle getBounds(){
		return bounds;
	}
	
	public static Rectangle getFeet(){
		return feet;
	}
	
	public static Rectangle getHead(){
		return head;
	}
	
	public static boolean isBelow(){
		return y > Game.HEIGHT;
	}
	
	public static boolean isDead(){
		return lives <= 0;
	}
	
	public static boolean isAirborne(){
		return !(PlatformObject.getIntersecting(feet) instanceof Platform) || dy < 0;
	}
	
	private static void limitSpeed(){
		if(dx > maxX){
			dx = maxX;
		}else if(dx < -maxX){
			dx = -maxX;
		}
		
		if(dy > maxY){
			dy = maxY;
		}else if(dy < -maxY){
			dy = -maxY;
		}
	}
	
	private static void applyFriction(){
		if(dx > friction){
			dx -= friction;
		}else if(dx < -friction){
			dx += friction;
		}else{
			dx = 0;
		}
	}
	
	private static void applyGravity(){
		if(isAirborne()){
			dy += gravity;
		}else if(jumped == false){
			dy = 0;
			if(PlatformObject.getIntersecting(feet) != null)
				JumpPlayer.y = PlatformObject.getIntersecting(feet).y-32;
		}
	}
	
	public static void jump(){
		if(!isAirborne()){
			dy = -jumpPower;
			jumped = true;
		}
	}
	
	private static boolean jumped = false;
	
	public static void update(Input input){
		if(input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A)){
			dx -= acceleration;
		}else if(input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D)){
			dx += acceleration;
		}
		
		if(input.isKeyPressed(Input.KEY_UP) || input.isKeyPressed(Input.KEY_SPACE)){
			jump();
		}
		
		applyFriction();
		applyGravity();
		limitSpeed();
		
		x += dx;
		y += dy;
		if(y < 240){
			float dif = y - 240;
			y = 240;
			PlatformObject.applyY(-dif);
		}
		
		if(x < -32){
			x += Game.WIDTH;
		}else if(x > Game.WIDTH){
			x -= Game.WIDTH;
		}
		
		feet.setLocation(x, y+32);
		bounds.setLocation(x, y);
		
		PlatformObject po = Platform.getIntersecting(feet);
		Platform p = null;
		if(po instanceof Platform){
			p = (Platform) po;
		}
		
		if(p != beneath){
			if(p != null){
				p.step();
			}else{
				beneath = null;
			}
		}else if(p != null){
			if(p.getContinous()){
				p.step();
			}
		}
		
		jumped = false;
		
		if(isBelow()){
			lives--;
			x = 464;
			y = 224;
			dx = 0;
			dy = 0;
			Platform.resetPlatforms();
		}
	}

	public static void render(Graphics g){
		g.setColor(Color.orange);
		g.fill(bounds);
	}

	public static void reset() {
		score = 0;
		lives = 3;
		x = 464;
		y = 224;
		dx = 0;
		dy = 0;
	}
}