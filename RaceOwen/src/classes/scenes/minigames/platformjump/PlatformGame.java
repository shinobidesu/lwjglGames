package classes.scenes.minigames.platformjump;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import classes.Game;
import classes.Inventory;
import classes.entities.items.Item;
import classes.holders.ItemHolder.ItemSort;
import classes.scenes.minigames.Minigame;

public class PlatformGame extends Minigame{
	
	public static Platform firstPlatform;
	
	public PlatformGame(int id) {
		initialize();
	}
	
	private void initialize(){
		PlatformObject.reset();
		Platform.resetPlatforms();
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		if(isAcceptingInput()){
			PlatformObject.render(g);
			JumpPlayer.render(g);
			g.setColor(Color.white);
			g.drawString(String.format("Score: %.0f", JumpPlayer.score), 30, 30);
			g.drawString("Lives: "+JumpPlayer.lives, 30, 45);
		}
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		super.update(gc, sbg, delta);
		if(isAcceptingInput()){
			JumpPlayer.update(input);
			PlatformObject.update();
			
			if(JumpPlayer.isDead()){
				reward();
				Game.setAndEnterSceneWithTransition(Game.curScene, new FadeOutTransition(Color.black, 500), new FadeInTransition(Color.black, 500));
				reset();
				initialize();
			}
		}
	}
	
	@Override
	public int getID() {
		return 101;
	}
	
	private void reward(){
		float score = JumpPlayer.score;
		
		/*
		 * !!MUST!! go from HIGHEST -> LOWEST
		 */
		if(score >= 20000){
			// TODO Inventory.addItem(ItemSort.SOME_AWESOME_ITEM.create());
		}else if(score >= 1000){
			Inventory.addItem(ItemSort.SMALL_HEALTH_POTION.create());
		}else if(score >= 500){
			Inventory.addItem(ItemSort.LESSER_HEALTH_POTION.create());
		}else if(score >= 200){
			Inventory.addItem(ItemSort.TEST_BOOTS.create());
		}
	}
	
	private static void reset(){
		PlatformObject.reset();
		JumpPlayer.reset();
		addItemEffects();
	}
	
	private static void addItemEffects(){
		for(Item i : Inventory.items){
			i.minigameEffects();
		}
	}
}