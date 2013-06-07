package classes.holders;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import classes.Player;

public class AudioHolder {
	public static Sound MELODY0;
	public static Sound ENTER_BATTLE;
	public static Sound DEATH_SOUND;
	public static Sound BATTLE_MUSIC;
	
	private static Sound curMelody = null;
	
	private static float volume = 1.0f;
	
	public static void initialize(){
		try {
			
			MELODY0 = new Sound("res/melody0.ogg");
			ENTER_BATTLE = new Sound("res/enter_battle.ogg");
			DEATH_SOUND = new Sound("res/death_sound.ogg");
			BATTLE_MUSIC = new Sound("res/battle_music.ogg");
		}catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isBattlePlaying(){
		return BATTLE_MUSIC.playing();
	}
	
	public static boolean isMelodyPlaying(){
		if(curMelody == null)
			return false;
		return curMelody.playing();
	}
	
	public static void playBattle(){
		curMelody.stop();
		while(ENTER_BATTLE.playing()){}; // Wait for intro music to end
		BATTLE_MUSIC.loop(1.0f, volume);
	}
	
	public static void playMelody(int i) {
		switch(i){
		case 0:
			curMelody = MELODY0;
			break;
		case 1:
			break;
		case 2:
			break;
		}
		
		curMelody.loop(1f, volume);
	}
	
	public static void playSoundEffect(Sound sfx){
		sfx.play(1.0f, volume);
	}
	
	public static void resume() {
		if(Player.inBattle){
			BATTLE_MUSIC.loop(1.0f, volume);
			return;
		}
		if(curMelody != null){
			curMelody.loop(1.0f, volume);
		}
	}
	
	public static void stop() {
		DEATH_SOUND.stop();
		ENTER_BATTLE.stop();
		BATTLE_MUSIC.stop();
		if(curMelody != null){
			curMelody.stop();
		}
	}
	
	public static boolean setVolume(float f) {
		try{
			stop();
			if(f < 0f || f > 1f){
				return false;
			}
			volume = f;
		}finally{
			resume();
		}
		return true;
	}
}