package classes.scenes;

import javax.swing.JOptionPane;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import classes.Button;
import classes.Game;
import classes.Player;
import classes.entities.monsters.Monster;
import classes.holders.AudioHolder;

public class MainMenu extends Scene{
	
	private static final Button playButton = new Button("Play!", Color.green, Game.CENTER_X, Game.CENTER_Y-45);
	private static final Button helpButton = new Button("Help", Color.blue.darker(0.1f), Game.CENTER_X, Game.CENTER_Y+45);
	
	public MainMenu(int state){
		
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		Input input = gc.getInput();
		draw(gc, sbg, g, input);
	}
	
	public void draw(GameContainer gc, StateBasedGame sbg, Graphics g, Input input) throws SlickException{
		playButton.render(g);
		helpButton.render(g);
	}

	@Override
	public int getID() {
		return 0;
	}
	
	@Override
	public void update2(GameContainer gc, StateBasedGame sbg, int delta, Input input) throws SlickException {
		playButton.update(input);
		helpButton.update(input);
		
		if(playButton.clicked()){
			Player.name = JOptionPane.showInputDialog("What is your name?\nDefault: "+Player.name);
			Game.setAndEnterSceneWithTransition(Game.spawn, new FadeOutTransition(Color.black, 2000), new FadeInTransition(Color.black, 2000));
			AudioHolder.playMelody(0);
		}
		
		if(helpButton.clicked()){
			
		}
	}

	@Override
	public Monster randomMonster() {
		return null;
	}
}