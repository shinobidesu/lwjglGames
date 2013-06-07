package slamakans;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame{
	public final static int WIDTH = 960;
	public final static int HEIGHT = 480;
	
	public static final Fighter battlePlayer = new BattlePlayer();
	
	public Main() {
		super("Street Fighteresque");
	}
	
	public static void main(String args[]){
		try {
			AppGameContainer app = new AppGameContainer(new Main());
			app.setDisplayMode(WIDTH, HEIGHT, false);
			app.setTargetFrameRate(120);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		BattlePlatform.initialize();
		
		this.addState(new Battle());
		
		this.enterState(0);
	}
}