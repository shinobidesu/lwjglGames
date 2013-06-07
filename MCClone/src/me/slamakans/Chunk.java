package me.slamakans;

import org.newdawn.slick.Graphics;

public class Chunk {
	private Position pos;
	public static int chunkSize = 8*Block.getBlockSize();
	Block[][] blocksInChunk;
	
	Chunk(float x, float y){
		this.pos = new Position().setX(x*chunkSize).setY(y*chunkSize);
		this.blocksInChunk = createBlocks();
	}
	
	public Position getPos(){
		return pos;
	}
	
	public Block[][] createBlocks(){
		Block[][] c = new Block[8][8];
		
		for(int xx = (int) this.pos.getX(); xx < 8; xx++){
			for(int yy = (int) this.pos.getY(); yy < 8; yy++){
				c[xx][yy] = new Block(xx*Block.getBlockSize(), yy*Block.getBlockSize());
			}
		}
		
		
		return c;
	}
	
	public void render(Graphics g){
		for(int xx = 0; xx < this.blocksInChunk.length; xx++){
			for(int yy = 0; yy < this.blocksInChunk.length; yy++){
				this.blocksInChunk[xx][yy].render(g);
			}
		}
	}
}
