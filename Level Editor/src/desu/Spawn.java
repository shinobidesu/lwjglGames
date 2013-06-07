package desu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import desu.Mapper.TileType;

public class Spawn extends BasicGameState{
	
	public static TileType[][] map = new TileType[15][20];
	public static WorldEntity[][] we = new WorldEntity[15][20];
	
	public Spawn(int state) {
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[i].length; j++){
				map[i][j] = TileType.GRASS;
			}
		}
		for(int i = 0; i < we.length; i++){
			for(int j = 0; j < we[i].length; j++){
				we[i][j] = null;
			}
		}
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		Input input = gc.getInput();
		Player.renderNupdate(input, g);
		drawMap();
		drawWorldEntities(g);
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		
	}
	
	public void drawMap(){
		if(map != null){
			for(int row = 0; row < map.length; row++){
				for(int col = 0; col < map[row].length; col++){
					if(map[row][col] != null){
						map[row][col].getImage().draw(col*32, row*32);
					}
				}
			}
		}
	}
	
	public void drawWorldEntities(Graphics g){
		if(we != null){
			for(int i = 0; i < we.length; i++){
				for(int j = 0; j < we[i].length; j++){
					if(we[i][j] != null){
						we[i][j].render(g);
					}
				}
			}
		}
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		
	}

	@Override
	public int getID() {
		return 0;
	}
}