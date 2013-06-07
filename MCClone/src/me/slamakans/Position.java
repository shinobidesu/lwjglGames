package me.slamakans;

public class Position {
	float x;
	float y;
	
	public float getX(){
		return this.x;
	}
	public Position setX(float x){
		this.x = x;
		return this;
	}
	
	public float getY(){
		return this.y;
	}
	public Position setY(float y){
		this.y = y;
		return this;
	}
}