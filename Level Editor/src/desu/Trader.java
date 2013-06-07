package desu;

import org.newdawn.slick.Graphics;

public class Trader extends WorldEntity{
	
	public Trader(){
		super();
		image = ImageHolder.NPC_TEMPLATE;
	}
	
	@Override
	public void render(Graphics g) {
		image.draw(x, y);
	}
	
	@Override
	public WorldEntityType getWorldEntityType() {
		return null;
	}

	@Override
	public WorldEntity create() {
		return new Trader();
	}
}