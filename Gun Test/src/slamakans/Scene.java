package slamakans;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Scene extends BasicGameState{
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		Input input = gc.getInput();
		draw(gc, sbg, g, input);
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		Input input = gc.getInput();
		update2(gc, sbg, delta, input);
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException{
		
	}
	
	public abstract void draw(GameContainer gc, StateBasedGame sbg, Graphics g, Input input) throws SlickException;
	public abstract void update2(GameContainer gc, StateBasedGame sbg, int delta, Input input) throws SlickException;
}