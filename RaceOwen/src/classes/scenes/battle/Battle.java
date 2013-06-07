package classes.scenes.battle;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import classes.Game;
import classes.Inventory;
import classes.Player;
import classes.entities.monsters.Monster;
import classes.holders.AudioHolder;
import classes.scenes.Scene;

public class Battle extends Scene {
	
	private static Enemy enemy;
	
	public Battle(int i) {
		
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}
	
	public static void setEnemy(Enemy e){
		enemy = e;
	}
	
	public static Enemy getEnemy() {
		return enemy;
	}

	@Override
	public void draw(GameContainer gc, StateBasedGame sbg, Graphics g, Input input) throws SlickException {
		BattlePlatform.render(g);
		Game.battlePlayer.render(g);
		if(enemy != null){
			enemy.render(g);
		}
	}

	@Override
	public void update2(GameContainer gc, StateBasedGame sbg, int delta, Input input) throws SlickException {
		Game.battlePlayer.update(input);
		if(enemy != null){
			enemy.update(input);
		}
		
		if(enemy.health <= 0){
			win();
		}else if(Player.isDead()){
			lose();
		}
	}

	
	public static void lose(){
		enemy.destroy();
		Player.die();
	}
	
	public static void win(){
		enemy.getMonster().die();
		enemy.destroy();
		enemy = null;
		Game.setAndEnterSceneWithTransition(Game.lastScene, new FadeOutTransition(Color.white, 300), new FadeInTransition(Color.white, 300));
	}
	
	@Override
	public void leave(GameContainer gc, StateBasedGame sbg){
		Player.inBattle = false;
		BattlePlatform.clear();
		AudioHolder.BATTLE_MUSIC.stop();
		if(prevInvState == true && !Inventory.isOpen()){
			Inventory.open();
		}
		enemy = null;
	}
	
	private boolean prevInvState;
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg){
		Player.inBattle = true;
		prevInvState = Inventory.isOpen();
		Inventory.close();
		BattlePlatform.generate();
		AudioHolder.playBattle();
	}

	@Override
	public Monster randomMonster() {
		return null;
	}

	@Override
	public int getID() {
		return 3;
	}
}
