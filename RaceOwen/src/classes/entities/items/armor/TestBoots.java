package classes.entities.items.armor;

import classes.scenes.minigames.platformjump.JumpPlayer;

public class TestBoots extends ItemBoots{
	public TestBoots() {
		super(5, "Boots of Testing, High Jumpin And Shit", "It gives things and stuff for Jump Minigame");
		quality = Quality.EPIC;
	}
	
	@Override
	public void minigameEffects(){
		JumpPlayer.setJump(9f);
		JumpPlayer.setSpeed(.15f);
	}
}