package classes.scenes.minigames.platformjump;

import org.newdawn.slick.Color;

public class NormalPlatform extends Platform{
	public NormalPlatform(int x, int y) {
		super(x, y);
		color = Color.red;
		tick = true;
	}

	public NormalPlatform(int x, int y, int width, int height) {
		super(x, y, width, height);
		color = Color.red;
		tick = true;
	}

	@Override
	public void onStep() {
		JumpPlayer.jump();
	}
}