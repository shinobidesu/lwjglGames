package desu;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame{
	public static final String GAME_NAME = "Level Editor v0.1";
	
	public static final int SPAWN = 0;
	
	public static int WIDTH = 640;
	public static int HEIGHT = 480;
	public static boolean FULLSCREEN = false;
	
	public static AppGameContainer app;
	
	public static void main(String[] args){
		try{
			app = new AppGameContainer(new Game(GAME_NAME));
			app.setShowFPS(false);
			app.setDisplayMode(WIDTH, HEIGHT, FULLSCREEN);
			app.setTargetFrameRate(30);
			app.start();
		}catch(SlickException e){
			e.printStackTrace();
		}
	}
	
	public Game(String name) {
		super(name);
		this.addState(new Spawn(SPAWN));
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		ImageHolder.initialize();
		Mapper.TileType.initialize();
		this.getState(SPAWN).init(gc,  this);
		this.enterState(SPAWN);
	}
}