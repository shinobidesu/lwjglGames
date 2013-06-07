package classes.entities.worldentities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import classes.Button;
import classes.Game;
import classes.Inventory;
import classes.MessageBox;
import classes.entities.items.Item;

public class WorldItem extends WorldEntity{
	
	private Item item;
	private static WorldItem interactingItem;
	
	private static final Button yes = new Button("Yes", Color.black, 720, 200);
	private static final Button no = new Button("No", Color.black, 880, 200);
	
	public WorldItem(int c, int r, Item i) {
		super(c, r);
		item = i;
		image = item.getSprite();
	}

	@Override
	public void onInteract() {
		interactingItem = this;
		MessageBox.display("Do you wish to pick up "+item.getName()+"?", true);
	}
	
	@Override
	public void onClose() {
		MessageBox.clear(true);
		interactingItem = null;
	}

	@Override
	public void onUpdate(Input input, StateBasedGame sbg) {
		if(interactingItem != null){
			yes.update(input);
			no.update(input);
		}
		
		if(yes.clicked()){
			if(Inventory.isFull()){
				MessageBox.display("Inventory is full", true);
			}else{
				Inventory.addItem(item);
				onClose();
				destroy();
			}
		}else if(no.clicked()){
			onClose();
		}
	}
	
	private void destroy(){
		Game.curScene.removeWorldItem(this);
	}
	
	@Override
	public void render(Graphics g){
		onRender(g);
	}
	
	@Override
	public void onRender(Graphics g) {
		if(image != null){
			image.draw(x+4, y+4, 24, 24);
		}else{
			g.setColor(item.getQuality().getColor());
			g.fillOval(x, y, 32, 32);
		}
		
		if(interactingItem != null){
			yes.render(g);
			no.render(g);
		}
	}
}
