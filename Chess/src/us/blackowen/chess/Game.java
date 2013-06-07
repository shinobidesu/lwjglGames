package us.blackowen.chess;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame{
	public static final int	WIDTH	= 512;
	public static final int	HEIGHT	= 512;
	public static  Player	WHITE_PLAYER = new Player();
	
	private Board			board;
	
	public Game(){
		super("Chess");
	}
	
	@Override
	public void render(final GameContainer gc, final Graphics g) throws SlickException{
		this.board.render(g);
	}
	
	@Override
	public void init(final GameContainer arg0) throws SlickException{
		this.board = new Board();
		this.board.initialize();
		ImageHandler.initialize();
	}
	
	@Override
	public void update(final GameContainer gc, final int delta) throws SlickException{
		
		this.board.update(gc.getInput());
	}
	
	public static void main(final String[] args){
		try{
			final AppGameContainer app = new AppGameContainer(new Game());
			app.setDisplayMode(Game.WIDTH, Game.HEIGHT, false);
			app.start();
		}catch(final SlickException e){
			e.printStackTrace();
		}
	}
	
}
