package classes.entities.worldentities.npcs;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import classes.MessageBox;
import classes.Player;

public class Trader extends NPC{

	public Trader(int c, int r) {
		super(c, r);
		name = "Trader";
	}

	@Override
	public void onInteract() {
		MessageBox.display("Click on one of your items to trade it for a Small Health Potion", true);
		Player.trading = true;
	}

	@Override
	public void onClose() {
		MessageBox.clear(true);
		Player.trading = false;
	}

	@Override
	public void onUpdate(Input input, StateBasedGame sbg) {
		
	}

	@Override
	public void onRender(Graphics g) {
		
	}
}