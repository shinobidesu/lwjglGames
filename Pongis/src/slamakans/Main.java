package slamakans;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Main extends BasicGame{
	public final static int WIDTH = 600;
	public final static int HEIGHT = 600;
	
	private int padWidth = 20;
	private int padHeight = 70;
	
	static Image padSprite;
	static Image padSpriteRotated;
	
	public Main() {
		super("Monopoly - Version 0.1");
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		Ball.render(g);
		Pad.render(g);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		padSprite = new Image("data/images/bat.png");
		padSpriteRotated = new Image("data/images/bat.png");
		padSpriteRotated.setRotation(90);
		new Pad(15, HEIGHT/2, padWidth, padHeight, "left", padSprite); // Left pad.
		new Pad(WIDTH-15, HEIGHT/2, padWidth, padHeight, "right", padSprite); // Right pad.
		new Pad(WIDTH/2, 15, padHeight, padWidth, "top", padSpriteRotated); // Top pad. // The width and height vars are switched here.
		new Pad(WIDTH/2, HEIGHT-15, padHeight, padWidth, "bottom", padSpriteRotated); // Bottom pad. // Here too.
		new Ball();
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		
		if(Keyboard.isKeyDown(16)){
			Pad.moveLogic("leftup");
		}
		if(Keyboard.isKeyDown(30)){
			Pad.moveLogic("leftdown");
		}
		if(!Keyboard.isKeyDown(16) && !Keyboard.isKeyDown(30))
			Pad.moveLogic("left");
		
		
		if(Keyboard.isKeyDown(18)){
			Pad.moveLogic("rightup");
		}
		if(Keyboard.isKeyDown(32)){
			Pad.moveLogic("rightdown");
		}
		if(!Keyboard.isKeyDown(18) && !Keyboard.isKeyDown(32)){
			Pad.moveLogic("right");
		}

		
		if(Keyboard.isKeyDown(34)){
			Pad.moveLogic("topleft");
		}
		if(Keyboard.isKeyDown(35)){
			Pad.moveLogic("topright");
		}
		if(!Keyboard.isKeyDown(34) && !Keyboard.isKeyDown(35)){
			Pad.moveLogic("top");
		}

		
		if(Keyboard.isKeyDown(37)){
			Pad.moveLogic("bottomleft");
		}
		if(Keyboard.isKeyDown(38)){
			Pad.moveLogic("bottomright");
		}
		if(!Keyboard.isKeyDown(37) && !Keyboard.isKeyDown(38)){
			Pad.moveLogic("bottom");
		}
		
		if(Keyboard.isKeyDown(57)){
			Ball.shouldMove = !Ball.shouldMove;
		}
		
		
		if(Keyboard.isKeyDown(19)){
			for(Ball b : Ball.balls){
				b.x = WIDTH/2;
				b.y = HEIGHT/2;
			}
		}
		
		
		Ball.update();
		Pad.update();
	}
	
	public static void main(String args[]){
		try {
			AppGameContainer app = new AppGameContainer(new Main());
			app.setDisplayMode(WIDTH, HEIGHT, false);
			app.setTargetFrameRate(240);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}