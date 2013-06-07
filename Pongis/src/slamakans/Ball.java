package slamakans;

import java.util.Random;
import java.util.Vector;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

public class Ball {
	private int r = 7;
	static Vector<Ball> balls = new Vector<Ball>(1, 1);
	private static Random rand = new Random();
	
	float x;
	float y;
	private float speed;
	private float angle;
	
	private int yMod = 1;
	private int xMod = 1;
	
	private Circle bounds;
	
	private float xSpin;
	private float ySpin;
	
	
	static boolean shouldMove = false;
	
	Ball(float x, float y, int r){
		this();
		
		this.r = r;
		this.x = x;
		this.y = y;
	}
	
	Ball(){
		balls.add(this);
		
		this.x = Main.WIDTH/2;
		this.y = Main.HEIGHT/2;
		this.r = 7;
		this.speed = 0.3F;
		this.angle = rand.nextInt(359);
		this.bounds = new Circle(this.x-r, this.y-r, this.r);
	}
	
	/*void reactToCollisionsWithOtherBalls(){
		for(Ball b : balls){
			if(b != this){
				double distance = Math.sqrt(((b.x-this.x)*(b.x-this.x))+((b.y-this.y)*(b.y-this.y)));
				if(distance <= r+b.r){
					float angleOfCollision = 
				}
			}
		}
	}*/
	
	void reactToCollisions(){
		for(Pad p : Pad.pads){
			if(p.bounds.intersects(this.bounds)){
				
				this.angle += (p.rMid - this.angle)*2;
				
				if(p.down){
					this.ySpin += p.speed*5;
				}else if(p.up){
					this.ySpin -= -p.speed*5;
				}
				
				if(p.right){
					this.xSpin += p.speed*5;
				}else if(p.left){
					this.xSpin -= -p.speed*5;
				}
				
				if(this.angle > 360){
					this.angle -= 360;
				}else if(this.angle < 0){
					this.angle += 360;
				}
			}
		}
	}
	
	
	
	static void update(){
		for(Ball b : balls){
			if(shouldMove){
				if(b.angle > 360){
					b.angle -= 360;
				}else if(b.angle < 0){
					b.angle += 360;
				}
				b.reactToCollisions();
				
				if(b.xSpin != 0){
					b.angle += b.xSpin/1000;
					b.xSpin -= b.xSpin/100;
				}
				
				if(b.ySpin != 0){
					b.angle += b.ySpin/1000;
					b.ySpin -= b.ySpin/100;
				}
				
				if(b.xSpin > -0.01F && b.xSpin < 0.01F){
					b.xSpin = 0;
				}
				
				if(b.ySpin > -0.01F && b.ySpin < 0.01F){
					b.ySpin = 0;
				}
				
				System.out.println(b.angle);
				b.x += (b.speed * Math.sin(b.angle));
				b.y -= (b.speed * Math.cos(b.angle));
				
				b.bounds.setLocation(b.x-b.r, b.y-b.r);
			}
		}
	}
	
	static void render(Graphics g){
		for(Ball b : balls){
			g.fillOval(b.x-b.r, b.y-b.r, b.r*2, b.r*2);
		}
	}
}
