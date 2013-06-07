package desu;

import org.newdawn.slick.Graphics;

public class EmptyEntity extends WorldEntity {
	@Override
	public void render(Graphics g) {
		
	}

	@Override
	public WorldEntityType getWorldEntityType() {
		return null;
	}

	@Override
	public WorldEntity create() {
		return new EmptyEntity();
	}
}
