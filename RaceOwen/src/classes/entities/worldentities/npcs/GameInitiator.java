package classes.entities.worldentities.npcs;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import classes.Game;
import classes.MessageBox;
import classes.Option;

public class GameInitiator extends NPC{
	
	public GameInitiator(int c, int r) {
		super(c, r);
		name = "Goranel Oxcatcher";
	}
	
	//TODO Just saving this here: MessageBox.display("Welcome, "+Player.name+", to the Virgin Kingdom.");
	
	@Override
	public void onInteract() {
		MessageBox.display("In this world there are many minigames that you can play if battling monsters day in and day out becomes too much for you." +
				" If you get a really high score in a minigame you might get some great rewards!", true);
		Option.display("Click to continue...");
	}

	@Override
	public void onClose() {
		
	}
	
	@Override
	public void onUpdate(Input input, StateBasedGame sbg) {
		if(isInteracting()){
			switch(state){
			case 0:
				if(input.isMousePressed(0)){
					MessageBox.display("There are also items that can give you significant boosts within the minigames." +
							" I am the one responsible for the Jump Minigame. Would you like to play?", true);
					Option.display("1. Yes\n2. No");
					state = 1;
				}
				break;
			case 1:
				if(input.isKeyDown(Input.KEY_1)){
					Game.enterMinigame(Game.platform);
					close();
				}else if(input.isKeyDown(Input.KEY_2)){
					close();
				}
				break;
			}
		}
	}
	
	@Override
	public void onRender(Graphics g) {
		
	}
}