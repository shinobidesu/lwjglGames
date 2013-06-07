package desu;

import org.newdawn.slick.Graphics;

public class TreeEntity extends WorldEntity{
	
	public TreeEntity(){
		super();
		image = ImageHolder.TREE;
	}
	
	public TreeEntity(int c, int r) {
		super(c, r);
		image = ImageHolder.TREE;
	}

	@Override
	public WorldEntityType getWorldEntityType(){
		return WorldEntityType.TREE;
	}

	@Override
	public void render(Graphics g) {
		image.draw(x, y);
	}

	@Override
	public WorldEntity create() {
		return new TreeEntity();
	}
}