package classes;

import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.state.transition.Transition;

import classes.entities.monsters.Monster;
import classes.holders.*;
import classes.holders.ItemHolder.ItemSort;
import classes.scenes.battle.*;
import classes.scenes.*;
import classes.scenes.minigames.Minigame;
import classes.scenes.minigames.platformjump.PlatformGame;

@SuppressWarnings("deprecation")
public class Game extends StateBasedGame{
	public static final String VERSION = "v0.5.3";
	public static final String GAME_NAME = "Epicest Adventure";
	
	public static Scene mainMenu;
	public static Scene spawn;
	public static Scene map1;
	public static Scene battle;
	public static Scene escMenu;
	public static Scene map2;
	
	public static Minigame platform;
	
	public static Game game;
	
	public static int WIDTH = 960;
	public static int HEIGHT = 480;
	public static int CENTER_X = WIDTH/2;
	public static int CENTER_Y = HEIGHT/2;
	
	public static boolean DEBUG_MODE = false;
	public static boolean LIMIT_FRAMERATE = true;
	
	public static boolean VERBOSE_DEBUG_MODE = false;
	
	public static final boolean READ_VARIABLES_FROM_FILE = true;
	
	public static final Dimension rectButtonSize = new Dimension(100, 50);
	
	public static AppGameContainer app;
	
	public static TrueTypeFont gameOverFont;
	
	public static final BattlePlayer battlePlayer = new BattlePlayer();
	
	public static void main(String[] args){
		try{
			app = new AppGameContainer(game = new Game(GAME_NAME + " " + VERSION));
			app.setShowFPS(false);
			
			if(READ_VARIABLES_FROM_FILE){
				Properties props = new Properties();
				FileInputStream file = null;
				String path = "settings.properties";
				
				if(new File(path).exists())
					file = new FileInputStream(path);
				if(file != null) props.load(file);
				
				if(props.isEmpty()){
					props.setProperty("debug-mode", "false");
					props.setProperty("limit-framerate", "true");
					FileOutputStream out = new FileOutputStream(path);
					props.store(out, "'Greetings' - Blackchild, 2012+1");
				}
				if(file != null) file.close();
				
				if(props.getProperty("debug-mode").equalsIgnoreCase("true")){
					DEBUG_MODE = true;
					if(props.getProperty("limit-framerate").equalsIgnoreCase("true")){
						LIMIT_FRAMERATE = true;
					}
				}
			}
			
			app.setDisplayMode(WIDTH, HEIGHT, false);
			if(DEBUG_MODE){
				app.setShowFPS(true);
				if(LIMIT_FRAMERATE){
					app.setTargetFrameRate(120);
				}
			}else{
				app.setTargetFrameRate(120);
			}
			app.start();
		}catch(SlickException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Game(String name) {
		super(name);
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		ImageHolder.initialize();
		Mapper.TileType.initialize();
		AudioHolder.initialize();
		gameOverFont = new TrueTypeFont(new Font("TimesRoman", Font.PLAIN, 48), false);
		Player.gameOverTimer.setRepeats(false);
		Player.gameOverTimer.setDelay(2000);
		
		/*
		 * Add scenes, 100 and below
		 */
		this.addState(mainMenu = new MainMenu(0));
		this.addState(spawn = new Spawn(1));
		this.addState(map1 = new Map1(2));
		this.addState(battle = new Battle(3));
		this.addState(escMenu = new EscMenu(4));
		this.addState(map2 = new Map2(5));
		
		/*
		 * Add minigames, 101 and above
		 */
		this.addState(platform = new PlatformGame(101));
		
		
		//enterBattle(new CubeMonster(1));
		setAndEnterScene(mainMenu);
		addStarterItems();
	}
	
	private static void addStarterItems(){
		Inventory.addItem(ItemSort.RANDOM_SWORD.create());
		Inventory.addItem(ItemSort.LESSER_HEALTH_POTION.create());
		Inventory.addItem(ItemSort.LESSER_HEALTH_POTION.create());
		Inventory.addItem(ItemSort.LESSER_HEALTH_POTION.create());
	}
	
	public static Scene curScene;
	public static Scene lastScene;
	
	public static void setAndEnterScene(Scene s){
		game.enterState(s.getID());
		setScene(s);
	}
	
	public static void setAndEnterSceneWithTransition(Scene s, Transition t1, Transition t2){
		game.enterState(s.getID(), t1, t2);
		setScene(s);
	}
	
	public static void setScene(Scene s){
		lastScene = curScene;
		curScene = s;
	}
	
	public static void enterBattle(Monster m){
		AudioHolder.stop();
		AudioHolder.playSoundEffect(AudioHolder.ENTER_BATTLE);
		Battle.setEnemy(new Enemy(m));
		setAndEnterSceneWithTransition(Game.battle, new FadeOutTransition(Color.white, 400), new FadeInTransition(Color.white, 400));
		
	}
	
	public static void enterMinigame(Minigame m){
		game.enterState(m.getID(), new FadeOutTransition(Color.white, 400), new FadeInTransition(Color.white, 400));
	}
	
	public static int getFPS(){
		return app.getFPS();
	}
}