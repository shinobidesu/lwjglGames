package me.slamakans;

import java.awt.Font;
import java.util.Vector;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

@SuppressWarnings("deprecation")
public class Game extends BasicGame{
	public final static int WIDTH = 1280;
	public final static int HEIGHT = 720;
	
	Chunk c;
	static Vector<Chunk> chunks = new Vector<Chunk>(1, 1);
	
	public Game() {
		super("Clone v0.01");
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		createChunks();
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		for(Chunk c : chunks){
			c.render(g);
		}
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static Chunk[][] createChunks(){
		Chunk[][] c = new Chunk[8][8];
		
		for(int xx = 0; xx < 8; xx++){
			for(int yy = 0; yy < 8; yy++){
				c[xx][yy] = createChunk(xx*Chunk.chunkSize, yy*Chunk.chunkSize);
			}
		}
		
		return c;
	}
	
	
	public static Chunk createChunk(float x, float y){
		Chunk c = new Chunk(x, y);
		chunks.add(c);
		return c;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static boolean isDown(int keyValue){
		return Keyboard.isKeyDown(keyValue);
	}
	
	public static void draw3DText(float x, float y, float offset, String text, Color main, Color sub, Graphics g, boolean antialias){
		Graphics g2 = g;
		TrueTypeFont font = new TrueTypeFont(new Font("Consolas", Font.BOLD, 16), antialias);
		
		float var1 = text.length()*font.getWidth("A")/2;
		int var3 = font.getHeight("A")/2;
		
		g2.setFont(font);
		
		// Draw Shadow first so the main text can overlap it.
		g2.setColor(sub);
		g2.drawString(text, x+offset-var1, y+offset-var3);
		// Draw Text
		g2.setColor(main);
		g2.drawString(text, x-var1, y-var3);
	}
	
	public static void main(String args[]){
		try {
			AppGameContainer app = new AppGameContainer(new Game());
			app.setDisplayMode(WIDTH, HEIGHT, false);
			app.setTargetFrameRate(240);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}