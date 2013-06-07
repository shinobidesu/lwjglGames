package me.slamakans;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Block {
	Position pos;
	private static final int blockSize = 8;
	
	Block(float x, float y){
		pos = new Position().setX(x).setY(y);
	}
	
	public static int getBlockSize(){
		return blockSize;
	}
	
	public void render(Graphics g){
		g.setColor(Color.green);
		g.fillRect(pos.getX(), pos.getY(), blockSize, blockSize);
	}
}
