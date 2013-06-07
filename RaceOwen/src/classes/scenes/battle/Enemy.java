package classes.scenes.battle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

import javax.swing.Timer;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

import classes.Game;
import classes.entities.monsters.Monster;

public class Enemy extends Fighter implements ActionListener{
	
	private Monster monster;
	private int range = 32;
	
	private Rectangle jumpRect = new Rectangle(0, 0, 16, 85);
	
	private Timer actionTimer = new Timer(800, this);
	private Timer attackMoveTimer = new Timer(100, this);
	private Timer moveTimer = new Timer(200, this);
	private Timer updownTimer = new Timer(200, this);
	
	private String state = "travelling";
	private String moveState = "";
	private String updownState = "";
	
	public Enemy(Monster m){
		super();
		health = m.getHealth();
		maxhealth = m.getMaxhealth();
		damage = m.getDamage();
		maxdamage = m.getMaxdamage();
		acceleration = m.getAcceleration();
		friction = m.getFriction();
		gravity = m.getGravity();
		maxX = m.getMaxX();
		maxY = m.getMaxY();
		
		x = 200;
		
		monster = m;
		
		actionTimer.setInitialDelay(0);
		actionTimer.start();
		
		moveTimer.setInitialDelay(1100);
		moveTimer.start();
		
		attackMoveTimer.start();
		
		updownTimer.start();
		
		attackTimer.setDelay(800);
	}
	
	public Monster getMonster(){
		return monster;
	}
	
	@Override
	public void damage(float d) {
		health -= d;
	}

	@Override
	public void attack() {
		Game.battlePlayer.damage(damage + rnd.nextInt((int) (maxdamage - damage)));
	}
	
	@Override
	public void updateBounds(){
		super.updateBounds();
		jumpRect.setLocation(x, y-jumpRect.getHeight());
	}
	
	@Override
	public void update2(Input input) {
		switch(state){
		case "attacking":
			approachPlayer();
			break;
		case "travelling":
			travelAbout();
			break;
		case "retreating":
			retreat();
			break;
		}
		
		
		if(moveState.equals("left")){
			goLeft();
		}else if(moveState.equals("right")){
			goRight();
		}
		
		if(getDistance() < range + (rnd.nextInt(16)-rnd.nextInt(16))){
			if(!facingPlayer()){
				reverseRotation();
			}
			swing();
			if(Math.random() > .7f){
				state = "retreating";
			}
		}
	}
	
	@Override
	public void jump(){
		if(updownState.equals("up")){
			super.jump();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == actionTimer){
			if(getDistance() < 100){
				state = "attacking";
			}else if(getDistance() > 100){
				state = "travelling";
			}else{
				state = "";
			}
			
			actionTimer.setDelay(800+rnd.nextInt(400));
		}
		
		if(ae.getSource() == attackMoveTimer && state.equals("attacking")){
			movements();
			attackMoveTimer.setDelay(100+rnd.nextInt(200));
		}
		
		if(ae.getSource() == moveTimer && state.equals("travelling")){
			movements();
			moveTimer.setDelay(200+rnd.nextInt(350));
		}
		
		if(ae.getSource() == updownTimer){
			if(Game.battlePlayer.y < y){
					updownState = "up";
			}else{
				if(Math.random() < 0.99995f){
					updownState = "down";
				}else{
					updownState = "up";
				}
			}
			
			updownTimer.setDelay(200 + rnd.nextInt(100));
		}
	}
	
	
	private double getDistance(){
		return Point2D.distance(x, y, Game.battlePlayer.getX(), Game.battlePlayer.getY());
	}
	
	private boolean platformInRange(){
		return BattlePlatform.getIntersecting(jumpRect) != null;
	}
	
	private boolean facingPlayer(){
		if(Game.battlePlayer.getX() < x && rotation == 0x0){
			return false;
		}else if(Game.battlePlayer.getX() > x && rotation == 0x1){
			return false;
		}
		
		return true;
	}
	
	private void reverseRotation(){
		if(rotation == 0x0){
			moveState = "left";
		}else{
			moveState = "right";
		}
	}
	
	private void approachPlayer(){
		if(Game.battlePlayer.getY() < y){
			if(platformInRange()){
				jump();
			}
		}
	}
	
	@Override
	public void render(Graphics g){
		super.render(g);
		g.setColor(Color.magenta);
		g.draw(jumpRect);
	}
	
	private void travelAbout(){
		if(platformInRange()){
			jump();
		}
	}
	
	private boolean left = false;
	private boolean right = false;
	
	private void movements(){
		if(state.equals("attacking")){
			if(Game.battlePlayer.getX() < x){
				moveState = "left";
			}else if(Game.battlePlayer.getX() > x){
				moveState = "right";
			}
		}
		
		if(state.equals("travelling")){
			if(x == 0 || x == Game.WIDTH-width){
				jump();
				
				if(x == 0){
					moveState = "left";
				}else{
					moveState = "right";
				}
			}
			
			if(Game.battlePlayer.getX() < x){
				if(Math.random() > .005f){
					moveState = "left";
				}else{
					moveState = "right";
				}
			}else if(Game.battlePlayer.getX() > x){
				if(Math.random() > .005f){
					moveState = "right";
				}else{
					moveState = "left";
				}
			}
		}
		
		if(updownState.equals("down")){
			if(BattlePlatform.getIntersecting(feet) != null){
				if(x > Game.WIDTH-width-rnd.nextInt(50)){
					moveState = "left";
					right = false;
					left = true;
				}else if(x < rnd.nextInt(50)){
					moveState = "right";
					left = false;
					right = true;
				}
				
				if(Math.random() > 0.5f){
					if(!right){
						moveState = "left";
						left = true;
					}
				}
				
				if(!left){
					moveState = "right";
					right = true;
				}
			}else{
				right = false;
				left = false;
			}
		}else{
			right = false;
			left = false;
		}
	}
	
	private void retreat(){
		if(Game.battlePlayer.getX() < x){
			moveState = "right";
		}else{
			moveState = "left";
		}
	}
	
	public void destroy(){
		actionTimer.stop();
		attackMoveTimer.stop();
		moveTimer.stop();
		attackTimer.stop();
		
		actionTimer = null;
		attackMoveTimer = null;
		moveTimer = null;
		attackTimer = null;
	}
}