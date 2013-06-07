package classes.scenes;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import classes.Game;
import classes.Inventory;
import classes.Mapper.TileType;
import classes.MessageBox;
import classes.Player;
import classes.SkillButton;
import classes.Stats;
import classes.entities.monsters.Monster;
import classes.entities.worldentities.WorldEntity;
import classes.entities.worldentities.WorldItem;
import classes.holders.AudioHolder;
import classes.scenes.battle.Battle;

@SuppressWarnings("deprecation")
public abstract class Scene extends BasicGameState{
	
	TileType[][] map;
	WorldEntity[][] worldEntities;
	WorldItem[] worldItems;
	
	public static final SkillButton strSkill = new SkillButton(770, 160);
	public static final SkillButton dexSkill = new SkillButton(770, 175);
	
	protected Color shade = null;
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		Input input = gc.getInput();
		if(this instanceof Battle == false){
			drawMap();
			Stats.render(g);
			renderWorldEntities(g);
			renderWorldItems(g);
			draw(gc, sbg, g, input);
			if(shade != null){
				g.setColor(shade);
				g.fillRect(0, 0, 640, 480);
			}
		}else{
			draw(gc, sbg, g, input);
		}
		MessageBox.render(g);
		Inventory.render(g);
		
		if(!AudioHolder.isMelodyPlaying() && !AudioHolder.isBattlePlaying()){
			AudioHolder.resume();
		}
		
		
		if(Player.gameOverTimer.isRunning()){
			String msg = "You have died";
			int gameOverWidth = Game.gameOverFont.getWidth(msg);
			int gameOverHeight = Game.gameOverFont.getLineHeight();
			Font f = g.getFont();
			g.setFont(Game.gameOverFont);
			g.setColor(Color.white);
			g.drawString(msg, (Game.WIDTH-gameOverWidth)/2, (Game.HEIGHT-gameOverHeight)/2);
			g.setFont(f);
		}
		
		if(Game.DEBUG_MODE){
			g.setColor(Color.white);
			g.drawString("Mouse x: "+input.getMouseX()+"  y: "+input.getMouseY(), 50, 50);
			int[] rowCol = Player.getRowAndCol();
			g.drawString("Player position  row: "+rowCol[0]+"  col: "+rowCol[1], 50, 65);
		}
		
		if(Game.VERBOSE_DEBUG_MODE){
			g.setColor(Color.white);
			g.drawString("Player.dx: "+Player.dx, 50, 185);
			g.drawString("Player.dy: "+Player.dy, 50, 200);
			g.drawString("Player.x: "+Player.getX(), 50, 215);
			g.drawString("Player.y: "+Player.getY(), 50, 230);
			int mRow = 0;
			int mCol = 0;
			int mx = input.getMouseX();
			int my = input.getMouseY();
			
			mCol = (mx - mx%32)/32;
			mRow = (my - my%32)/32;
			
			g.drawString("Mouse position  row: "+mRow+"  col: "+mCol, 50, 245);
		}
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		Input input = gc.getInput();
		updateWorldEntities(input, sbg);
		updateWorldItems(input, sbg);
		update2(gc, sbg, delta, input);
		strSkill.update(input);
		dexSkill.update(input);
		
		if(Player.attributePoints > 0){
			if(strSkill.clicked()){
				Player.skillStrength();
			}else if(dexSkill.clicked()){
				Player.skillDexterity();
			}
		}
		
		if(input.isKeyPressed(Input.KEY_Z)){
			Player.skillDexterity();
		}
		
		
		if(Player.dx == 0){
			if(Player.getCol() > 19){
				onLeaveRight();
			}else if(Player.getCol() < 0){
				onLeaveLeft();
			}
		}
		if(Player.dy == 0){
			if(Player.getRow() < 0){
				onLeaveUp();
			}else if(Player.getRow() > 14){
				onLeaveDown();
			}
		}
		
		if(input.isKeyPressed(Input.KEY_I)){
			if(Inventory.isOpen())
				Inventory.close();
			else
				Inventory.open();
		}else if(input.isKeyPressed(Input.KEY_ESCAPE) && !EscMenu.timer.isRunning() && this instanceof Battle == false){
			Game.game.enterState(Game.escMenu.getID());
		}
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException{
		super.enter(gc, sbg);
		if(AudioHolder.isMelodyPlaying() == false){
			AudioHolder.resume();
		}
	}
	
	protected Scene right;
	protected Scene left;
	protected Scene up;
	protected Scene down;
	
	protected void onLeaveRight(){
		Player.dx = 32;
		Player.setCol(-1);
		if(right != null){
			Game.setAndEnterScene(right);
		}
	}
	
	protected void onLeaveLeft(){
		Player.dx = -32;
		Player.setCol(20);
		if(left != null){
			Game.setAndEnterScene(left);
		}
	}
	
	protected void onLeaveUp(){
		Player.dy = -32;
		Player.setRow(15);
		if(up != null){
			Game.setAndEnterScene(up);
		}
	}
	
	protected void onLeaveDown(){
		Player.dy = 32;
		Player.setRow(-1);
		if(down != null){
			Game.setAndEnterScene(down);
		}
	}
	
	public abstract void draw(GameContainer gc, StateBasedGame sbg, Graphics g, Input input) throws SlickException;
	public abstract void update2(GameContainer gc, StateBasedGame sbg, int delta, Input input) throws SlickException;
	
	public boolean isWalkable(int r, int c){
		if(c >= 0 && c <= 19 && r >= 0 && r <= 14){
			if(map[r][c].collides()){
				return false;
			}
		}
		
		return true;
	}
	
	public WorldEntity getEntity(int r, int c){
		if(worldEntities != null){
			for(WorldEntity[] we : worldEntities){
				if(we != null){
					for(WorldEntity w : we){
						if(w == null)
							continue;
						if(w.getBounds().contains((c*32)+16, (r*32)+16)){
							return w;
						}
					}
				}
			}
		}
		
		if(worldItems != null){
			for(WorldItem i : worldItems){
				if(i != null){
					if(i.getBounds().contains((c*32)+16, (r*32)+16)){
						return i;
					}
				}
			}
		}
		
		return null;
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
	
	public void renderWorldEntities(Graphics g){
		if(worldEntities == null)
			return;
		for(WorldEntity[] we : worldEntities){
			if(we != null){
				for(WorldEntity w : we){
					if(w == null)
						continue;
					w.render(g);
				}
			}
		}
	}
	
	public void updateWorldEntities(Input input, StateBasedGame sbg){
		if(worldEntities == null)
			return;
		for(WorldEntity[] we : worldEntities){
			if(we != null){
				for(WorldEntity w : we){
					if(w == null)
						continue;
					w.update(input, sbg);
				}
			}
		}
	}
	
	static Random rnd = new Random();
	public abstract Monster randomMonster();
	
	public void renderWorldItems(Graphics g){
		if(worldItems == null)
			return;
		for(WorldItem i : worldItems){
			if(i != null){
				i.render(g);
			}
		}
	}
	
	public void updateWorldItems(Input input, StateBasedGame sbg){
		if(worldItems == null)
			return;
		for(WorldItem i : worldItems){
			if(i != null){
				i.update(input, sbg);
			}
		}
	}
	
	public void removeWorldItem(WorldItem wi){
		for(int i = 0; i < worldItems.length; i++){
			if(worldItems[i] == wi){
				worldItems[i] = null;
			}
		}
	}
	
	public TileType getTileType(int r, int c){
		if(map != null){
			if((c >= 0 && c < 20) && (r >= 0 && r < 15)){
				return map[r][c];
			}
		}
		
		return null;
	}
}