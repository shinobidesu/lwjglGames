package desu;

import javax.swing.JOptionPane;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import desu.Mapper.TileType;

public class Player {
	public static int[] getRowAndCol(int x, int y){
		int[] rowNcol = new int[2];
		rowNcol[0] = getRow(y);
		rowNcol[1] = getCol(x);
		
		return rowNcol;
	}
	
	public static int getRow(int y){
		float yy = y;
		if(yy%32 != 0){
			yy -= yy%32;
		}
		
		return (int)(yy/32);
	}
	
	public static int getCol(int x){
		float xx = x;
		
		if(xx%32 != 0){
			xx -= xx%32;
		}
		
		return (int)(xx/32);
	}
	
	public static void renderNupdate(Input input, Graphics g){ // r, p, g, w, l, -(space), v, ^, <, >, 1, 2, 3, 4, 5, 6, 7, 8, 9
		int x = getCol(input.getMouseX())*32;
		int y = getRow(input.getMouseY())*32;
		
		/*
		 * Change TileType
		 */
		if(input.isKeyDown(Input.KEY_R)){
			s = TileType.GRASSY_ROCK;
		}else if(input.isKeyDown(Input.KEY_P)){
			s = TileType.PURPLE_GRASSY_ROCK;
		}else if(input.isKeyDown(Input.KEY_G)){
			s = TileType.GRASS;
		}else if(input.isKeyDown(Input.KEY_W)){
			s = TileType.WOOD_FLOOR;
		}else if(input.isKeyDown(Input.KEY_L)){
			s = TileType.LAVA;
		}else if(input.isKeyDown(Input.KEY_SPACE)){
			s = TileType.EMPTY;
		}else if(input.isKeyDown(Input.KEY_DOWN)){
			s = TileType.DOWN_ENTRANCE;
		}else if(input.isKeyDown(Input.KEY_UP)){
			s = TileType.UP_ENTRANCE;
		}else if(input.isKeyDown(Input.KEY_LEFT)){
			s = TileType.LEFT_ENTRANCE;
		}else if(input.isKeyDown(Input.KEY_RIGHT)){
			s = TileType.RIGHT_ENTRANCE;
		}else if(input.isKeyDown(Input.KEY_1)){
			s = TileType.TOP_LEFT_CORNER_HOUSE;
		}else if(input.isKeyDown(Input.KEY_2)){
			s = TileType.TOP_BORDER_HOUSE;
		}else if(input.isKeyDown(Input.KEY_3)){
			s = TileType.TOP_RIGHT_CORNER_HOUSE;
		}else if(input.isKeyDown(Input.KEY_4)){
			s = TileType.LEFT_BORDER_HOUSE;
		}else if(input.isKeyDown(Input.KEY_5)){
			s = TileType.FILLING_HOUSE;
		}else if(input.isKeyDown(Input.KEY_6)){
			s = TileType.RIGHT_BORDER_HOUSE;
		}else if(input.isKeyDown(Input.KEY_7)){
			s = TileType.BOT_LEFT_CORNER_HOUSE;
		}else if(input.isKeyDown(Input.KEY_8)){
			s = TileType.BOT_BORDER_HOUSE;
		}else if(input.isKeyDown(Input.KEY_9)){
			s = TileType.BOT_RIGHT_CORNER_HOUSE;
		}else if(input.isKeyDown(Input.KEY_LSHIFT) && input.isKeyDown(Input.KEY_LCONTROL) && input.isKeyDown(Input.KEY_S)){
			String name = JOptionPane.showInputDialog("What do you want it to be called?");
			Mapper.saveFile(Mapper.readMap(), name);
			Mapper.saveEntities(Mapper.readWorldEntities(), name);
			System.exit(0);
		}else if(input.isKeyDown(Input.KEY_LSHIFT) && input.isKeyDown(Input.KEY_S)){
			Mapper.saveFile(Mapper.readMap());
			Mapper.saveEntities(Mapper.readWorldEntities());
			System.exit(0);
		}
		
		/*
		 * Change WorldEntity
		 */
		if(input.isKeyDown(Input.KEY_SPACE)){
			d = new EmptyEntity();
		}else if(input.isKeyDown(Input.KEY_N)){
			d = new Trader();
		}else if(input.isKeyDown(Input.KEY_G)){
			d = new GameInitiator();
		}else if(input.isKeyDown(Input.KEY_T)){
			d = new TreeEntity();
		}
		
		g.setColor(new Color(255, 0, 0, 150));
		g.fillRect(x, y, 32, 32);
		if(input.isMouseButtonDown(0)){
			if(y/32 > 14)
				return;
			if(x/32 > 19)
				return;
			if(input.isKeyDown(Input.KEY_LCONTROL)){
				Spawn.map[y/32][x/32] = TileType.EMPTY;
			}else{
				Spawn.map[y/32][x/32] = s;
			}
		}else if(input.isMouseButtonDown(1)){
			if(y/32 > 14)
				return;
			if(x/32 > 19)
				return;
			if(input.isKeyDown(Input.KEY_LCONTROL)){
				Spawn.we[y/32][x/32] = null;
			}else{
				if(d != null){
					if(d.x == -32){
						d.setLocation(x, y);
						Spawn.we[y/32][x/32] = d;
					}else{
						WorldEntity dd = d.create();
						dd.setLocation(x, y);
						Spawn.we[y/32][x/32] = dd;
					}
				}
			}
		}
	}
	
	private static TileType s = TileType.GRASS;
	private static WorldEntity d = null;
}