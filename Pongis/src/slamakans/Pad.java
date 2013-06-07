package slamakans;

import java.util.Vector;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class Pad {
	float x;
	float y;
	float width;
	float height;
	float speed;
	float acceleration;
	float friction;
	
	private int rMin;
	private int rMax;
	int rMid;
	
	Rectangle bounds;
	
	String id;
	
	boolean down, up, right, left = false;
	Image sprite;
	
	static Vector<Pad> pads = new Vector<Pad>(1, 1);
	
	Pad(float x, float y, float width, float height, String id, Image sprite){
		this();
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.bounds = new Rectangle(this.x-this.width/2, this.y-this.height/2, this.width, this.height);
		this.acceleration = 0.01F;
		this.friction = 0.02F;
		this.speed = 0;
		this.id = id;
		this.sprite = sprite;
		
		switch(this.id){
		case "left":
			rMin = 0;
			rMax = rMin+180;
			rMid = rMax-90;
			break;
		case "top":
			rMin = 90;
			rMax = rMin+180;
			rMid = rMax-90;
			break;
		case "right":
			rMin = 180;
			rMax = 0;
			rMid = 270;
			break;
		case "bottom":
			rMin = 270;
			rMax = 90;
			rMid = rMax-90;
			break;
		}
	}
	
	Pad(){
		pads.add(this);
		this.speed = 0;
	}
	
	
	
	
	static void render(Graphics g){
		for(Pad p : pads){
			p.sprite.drawCentered(p.x, p.y);
		}
	}
	
	static void update(){
		for(Pad p : pads){
			if(p.down || p.right){
				p.speed += p.acceleration;
				if(p.speed > 1.5F){
					p.speed = 1.5F;
				}
			}else if(p.up || p.left){
				p.speed -= p.acceleration;
				if(p.speed < -1.5F){
					p.speed = -1.5F;
				}
			}
			
			if(p.speed > 0F){
				p.speed -= 0.001F;
				if(p.speed < 0.002F){
					p.speed = 0F;
				}
			}else if(p.speed < 0F){
				p.speed += 0.001F;
				if(p.speed > -0.002F){
					p.speed = 0F;
				}
			}
			
			if((p.down || p.speed > 0 && (p.id == "right" || p.id == "left")) && p.y+p.speed < Main.HEIGHT-p.height/2){
				if(p.speed > 0){
					p.y += p.speed;
				}
			}
			if((p.up || p.speed < 0 && (p.id == "right" || p.id == "left")) && p.y+p.speed > p.height/2){
				if(p.speed < 0){
					p.y += p.speed;
				}
			}
			if((p.right || p.speed > 0 && (p.id == "top" || p.id == "bottom")) && p.x+p.speed < Main.WIDTH-p.width/2){
				if(p.speed > 0){
					p.x += p.speed;
				}
			}
			if((p.left || p.speed < 0 && (p.id == "top" || p.id == "bottom")) && p.x+p.speed > p.width/2){
				if(p.speed < 0){
					p.x += p.speed;
				}
			}
			
			if(p.y+p.speed >= Main.HEIGHT-p.height/2){
				p.speed = 0F;
			}else if(p.y+p.speed <= p.height/2){
				p.speed = 0F;
			}else if(p.x+p.speed > Main.WIDTH-p.width/2){
				p.speed = 0F;
			}else if(p.x+p.speed < p.width/2){
				p.speed = 0F;
			}
			
			p.bounds.setLocation(p.x-p.width/2, p.y-p.height/2);
		}
	}
	
	static void moveLogic(String s){
		for(Pad p : pads){
			switch(s){
			case "leftdown":
				if(p.id == "left"){
					p.down = true;
					p.up = false;
				}
				break;
			case "leftup":
				if(p.id == "left"){
					p.up = true;
					p.down = false;
				}
				break;
			case "rightdown":
				if(p.id == "right"){
					p.down = true;
					p.up = false;
				}
				break;
			case "rightup":
				if(p.id == "right"){
					p.up = true;
					p.down = false;
				}
				break;
			case "topleft":
				if(p.id == "top"){
					p.left = true;
					p.right = false;
				}
				break;
			case "topright":
				if(p.id == "top"){
					p.right = true;
					p.left = false;
				}
				break;
			case "bottomleft":
				if(p.id == "bottom"){
					p.left = true;
					p.right = false;
				}
				break;
			case "bottomright":
				if(p.id == "bottom"){
					p.right = true;
					p.left = false;
				}
				break;
			default:
				if(p.id == s){
					p.down = false;
					p.up = false;
					p.left = false;
					p.right = false;
				}
			}
		}
	}
}
