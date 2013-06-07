package pkm;

import java.util.Vector;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import pkm.gui.Button;
import pkm.gui.Layout;
import pkm.monster.Pokemon;

public class Game extends BasicGame{
	public final static int WIDTH = 1280;
	public final static int HEIGHT = 720;
	
	public static Vector<Pokemon> activePkm = new Vector<Pokemon>(2, 1);
	public static Vector<Button> buttons = new Vector<Button>(1, 1);
	
	public Game() {
		super("PKM - V 0.0.1");
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		new Layout();
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		for(Button b : buttons){
			b.render(g);
		}
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		
	}
	
	
	public static void draw3DText(float x, float y, float offset, String text, Color main, Color sub, Graphics g){
		Graphics g2 = g;
		// Draw Shadow
		g2.setColor(sub);
		g2.drawString(text, x+offset, y+offset);
		// Draw Text
		g2.setColor(main);
		g2.drawString(text, x, y);
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