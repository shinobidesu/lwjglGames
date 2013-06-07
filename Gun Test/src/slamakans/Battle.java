package slamakans;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Battle extends Scene {
	
	private static Enemy enemy;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	@Override
	public int getID() {
		return 0;
	}

	public static Enemy getEnemy() {
		return enemy;
	}

	@Override
	public void draw(GameContainer gc, StateBasedGame sbg, Graphics g, Input input) throws SlickException {
		BattlePlatform.render(g);
		Main.battlePlayer.render(g);
		enemy.render(g);
		//Inventory.render(g);
	}

	@Override
	public void update2(GameContainer gc, StateBasedGame sbg, int delta, Input input) throws SlickException {
		enemy.update(input);
		Main.battlePlayer.update(input);
	}

	
	public static void lose(){
		
	}
	
	public static void win(){
		
	}
	
	@Override
	public void leave(GameContainer gc, StateBasedGame sbg){
		BattlePlatform.clear();
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg){
		BattlePlatform.generate();
		//setEnemy(Game.curScene.randomMonster());
	}
}
