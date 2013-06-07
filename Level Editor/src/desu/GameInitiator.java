package desu;

import org.newdawn.slick.Graphics;

public class GameInitiator extends WorldEntity{
	
	public GameInitiator(){
		super();
		image = ImageHolder.NPC_TEMPLATE;
	}
	
	@Override
	public void render(Graphics g) {
		image.draw(x, y);
	}
	
	@Override
	public WorldEntityType getWorldEntityType() {
		return WorldEntityType.GAME_INITIATOR;
	}
	
	@Override
	public WorldEntity create() {
		return new GameInitiator();
	}
}