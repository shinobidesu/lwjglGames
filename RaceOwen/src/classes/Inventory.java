package classes;

import java.util.Vector;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import classes.entities.items.Item;
import classes.entities.items.ItemEquippable;
import classes.entities.items.consumables.ItemConsumable;
import classes.holders.ItemHolder.ItemSort;

public class Inventory {
	private static final int slots = 54;
	private static final int x = 640, y = 260;
	public static final int firstX = x + 4;
	public static final int firstY = y + 6;
	
	private static boolean open = true;
	
	public static Vector<Item> items = new Vector<Item>(64, 0);
	
	private static int alpha = 150;
	private static Color color = new Color(184, 134, 11, alpha);
	
	
	public static boolean isFull(){
		if(items.size() < slots){
			return false;
		}
		
		return true;
	}
	
	public static void open(){
		open = true;
	}
	
	public static void close(){
		if(hoverItem != null){
			MessageBox.clear(false);
		}
		open = false;
	}
	
	public static boolean isOpen(){
		return open;
	}
	
	public static void addItem(Item i){
		if(!isFull()){
			items.add(i);
		}
	}
	
	public static void removeItem(Item i){
		if(i instanceof ItemEquippable){
			if(((ItemEquippable) i).isEquipped()){
				((ItemEquippable) i).unequip();
			}
		}
		items.remove(i);
	}
	
	public static boolean hasItem(Item i){
		if(items.contains(i)){
			return true;
		}
		
		return false;
	}
	
	private static Item getItem(int row, int col){
		if((row*9)+col < items.size() && (row*9)+col >= 0)
			return items.get((row*9)+col);
		return null;
	}
	
	public static boolean isHovering(){
		return hoverItem != null;
	}
	
	private static Item selItem;
	private static boolean showDestroyMenu = false;

	private static final Button yes = new Button("Yes", Color.black, 720, 370);
	private static final Button no = new Button("No", Color.black, 880, 370);
	
	public static void render(Graphics g){
		if(isOpen()){
			if(showDestroyMenu){
				g.drawString("Destroy?", 800-(g.getFont().getWidth("Destroy?")/2), 300);
				selItem.render(g, 784, 420);
				hoverItem = selItem;
				
				yes.render(g);
				no.render(g);
				Input input = Game.game.getContainer().getInput();
				yes.update(input);
				no.update(input);
				
				if(yes.clicked()){
					removeItem(selItem);
					selItem = null;
					showDestroyMenu = false;
				}else if(no.clicked()){
					selItem = null;
					showDestroyMenu = false;
				}
			}else{
				update();
				g.setColor(color);
				g.fillRect(x, y, 320, 240);
				
				for(int i = 0; i < 6; i++){
					for(int j = 0; j < 9; j++){
						if((i*9)+j < items.size()){
							items.get((i*9)+j).render(g, firstX+(j*35), firstY+(i*35));
						}
					}
				}
			}
		}
		
		
	}
	
	private static int getCol(int x){
		if(x < 0)
			return -1;
		
		if(x%35 != 0){
			x -= x%35;
		}
		
		return x/35;
	}
	
	private static int getRow(int y){
		if(y < 0)
			return -1;
		
		if(y%35 != 0){
			y -= y%35;
		}
		
		return y/35;
	}
	
	private static Item hoverItem;
	
	public static void update(){
		Input input = Game.game.getContainer().getInput();
		int col = getCol(input.getMouseX()-firstX);
		int row = getRow(input.getMouseY()-firstY);
		
		if(hoverItem != getItem(row, col) && hoverItem != null){
			hoverItem.drawDesc = false;
			MessageBox.clear(false);
		}
		
		if(row != -1 && col != -1){
			hoverItem = getItem(row, col);
			if(hoverItem != null){
				hoverItem.drawDesc = true;
				if(input.isMousePressed(0)){
					if(Player.trading){
						Inventory.removeItem(hoverItem);
						Inventory.addItem(ItemSort.SMALL_HEALTH_POTION.create());
					}else{
						if(hoverItem instanceof ItemEquippable){
							((ItemEquippable) hoverItem).equip();
						}else if(hoverItem instanceof ItemConsumable){
							((ItemConsumable) hoverItem).consume();
						}
					}
				}else if(input.isMousePressed(1)){
					selItem = hoverItem;
					showDestroyMenu = true;
				}
			}
		}
	}
}