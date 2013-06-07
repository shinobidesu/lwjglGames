package classes.entities.items.armor;

public class TestChest extends ItemChest{
	public TestChest(){
		super(4, "Chest of Testing", "A chest that protects you from harm");
		quality = Quality.COMMON;
		levelReq = 2;
	}
}