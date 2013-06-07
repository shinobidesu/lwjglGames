package classes;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import classes.scenes.Scene;

public class Stats {
	static int baseY = 85;
	static int a = 15;
	
	private static final Color pointsColor = new Color(0, 206, 209);
	
	public static void render(Graphics g){
		if(MessageBox.s == null){
			g.setColor(Color.green);
			g.drawString("Level: "+Player.level, 645, baseY-a*2);
			g.drawString("Health: "+Player.health+"/"+Player.getMaxhealth(), 645, baseY);
			g.drawString("Experience: "+Player.exp+"/"+Player.reqExp, 645, baseY+a);
			g.drawString("Armor: "+Player.getArmor(), 645, baseY+a*2);
			
			g.setColor(pointsColor);
			g.drawString("Points: "+Player.attributePoints, 645, baseY+a*4);
			
			g.setColor(Color.orange.darker(.17f));
			g.drawString("Strength: "+Player.getStrength(), 645, baseY+a*5);
			g.drawString("Dexterity: "+Player.getDexterity(), 645, baseY+a*6);
			Scene.strSkill.render(g);
			Scene.dexSkill.render(g);
			
			g.setColor(Color.white);
			g.drawString("Damage: "+Player.getDamage()+" - "+Player.getMaxdamage(), 645, baseY+a*7);
			g.drawString("Crit: "+Player.getCritChance()*100+"%", 645, baseY+a*8);
		}
	}
}