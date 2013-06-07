package classes.scenes;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import classes.Button;
import classes.Game;
import classes.Scrollbar;
import classes.entities.monsters.Monster;
import classes.holders.AudioHolder;

public class EscMenu extends Scene{
	
	public EscMenu(int state){
		
	}
	
	private static Button acceptButton;
	private static Scrollbar volumeScrollbar;
	
	public static final Timer timer = new Timer(200, null);
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		acceptButton = new Button("Accept", 200, 300);
		volumeScrollbar = new Scrollbar("Volume: ", (Game.WIDTH/2)-128, Game.HEIGHT/2);
		timer.setRepeats(false);
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		Input input = gc.getInput();
		draw(gc, sbg, g, input);
	}
	
	public void draw(GameContainer gc, StateBasedGame sbg, Graphics g, Input input) throws SlickException{
		acceptButton.render(g);
		volumeScrollbar.render(g);
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		Input input = gc.getInput();
		update2(gc, sbg, delta, input);
		if(input.isKeyDown(Input.KEY_ESCAPE) && !timer.isRunning()){
			Game.setAndEnterScene(Game.curScene);
		}
	}
	
	@Override
	public void update2(GameContainer gc, StateBasedGame sbg, int delta, Input input) throws SlickException {
		acceptButton.update(input);
		volumeScrollbar.update(input);
		
		if(acceptButton.clicked()){
			if(AudioHolder.setVolume(volumeScrollbar.getValue()) == false){
				JOptionPane.showMessageDialog(null, "Invalid number for volume!\nSome shit fucked up, contact Blackchild", "Input Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException{
		timer.start();
	}
	
	@Override
	public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException{
		timer.start();
	}

	@Override
	public int getID() {
		return 4;
	}

	@Override
	public Monster randomMonster() {
		return null;
	}
}