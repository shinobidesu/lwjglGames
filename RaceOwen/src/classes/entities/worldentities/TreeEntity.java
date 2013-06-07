package classes.entities.worldentities;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import classes.holders.ImageHolder;

public class TreeEntity extends WorldEntity{

	public TreeEntity(int c, int r) {
		super(c, r);
		image = ImageHolder.TREE;
	}

	@Override
	public void onInteract() {
		
	}

	@Override
	public void onClose() {
		
	}

	@Override
	public void onUpdate(Input input, StateBasedGame sbg) {
		
	}

	@Override
	public void onRender(Graphics g) {
		
	}

}
