package classes.scenes;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import classes.Game;
import classes.Mapper;
import classes.Mapper.TileType;
import classes.Player;
import classes.entities.monsters.Monster;
import classes.entities.worldentities.WorldItem;
import classes.holders.ItemHolder.ItemSort;


public class Spawn extends Scene{
	public Spawn(int state){
		worldItems = new WorldItem[1];
		worldItems[0] = new WorldItem(10, 7, ItemSort.AWESOME_SWORD.create());
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		map = Mapper.tile("maps/map0_tile.txt");
		worldEntities = Mapper.entity("maps/map0_entity.txt");
	}
	
	public void draw(GameContainer gc, StateBasedGame sbg, Graphics g, Input input) throws SlickException{
		Player.render(g);
	}

	@Override
	public int getID() {
		return 1;
	}

	@Override
	public void update2(GameContainer gc, StateBasedGame sbg, int delta, Input input) throws SlickException {
		Player.move(input);
		
		if(getTileType(Player.getRow(), Player.getCol()) == TileType.DOWN_ENTRANCE && !Player.moving()){
			Game.setAndEnterScene(Game.map1);
			Player.setCol(Player.getCol()-1);
			Player.setRow(Player.getRow()-3);
			Player.dy = 32;
		}
	}

	@Override
	public Monster randomMonster() {
		return null;
	}
}
