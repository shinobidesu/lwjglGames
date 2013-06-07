package classes.scenes;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import classes.Game;
import classes.Mapper;
import classes.Player;
import classes.Mapper.TileType;
import classes.entities.monsters.CubeMonster;
import classes.entities.monsters.Monster;
import classes.entities.monsters.ToughMonster;

public class Map1 extends Scene{
	public Map1(int state) {
		shade = new Color(0f, 0f, 0f, .4f);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		map = Mapper.tile("maps/map1_tile.txt");
		worldEntities = Mapper.entity("maps/map1_entity.txt");
		right = Game.map2;
	}
	
	public void draw(GameContainer gc, StateBasedGame sbg, Graphics g, Input input){
		Player.render(g);
	}

	@Override
	public int getID() {
		return 2;
	}

	@Override
	public void update2(GameContainer gc, StateBasedGame sbg, int delta, Input input) throws SlickException {
		Player.move(input);
		
		if(getTileType(Player.getRow(), Player.getCol()) == TileType.UP_ENTRANCE && !Player.moving()){
			Game.setAndEnterScene(Game.spawn);
			Player.setCol(Player.getCol()+1);
			Player.setRow(Player.getRow()+3);
			Player.dy = -32;
		}
	}
	
	@Override
	public Monster randomMonster() {
		switch(rnd.nextInt(4)){
		case 0:
			return new CubeMonster(rnd.nextInt(3)+1);
		case 1:
			return new ToughMonster(rnd.nextInt(3)+1);
		default:
			return null;
		}
	}
	
}