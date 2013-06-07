package classes;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class MessageBox {
	public static String[] s;
	
	static Color c;
	
	private static int x = 650;
	private static int y = 40;
	
	private static boolean locked = false;
	
	public static boolean display(String text, Color color, boolean lock){
		if(!locked || lock){
			buildString(text);
			
			c = color;
			locked = lock;
			return true;
		}
		
		return false;
	}
	
	public static boolean display(String text, Color color){
		if(!locked){
			buildString(text);
			
			c = color;
			return true;
		}
		
		return false;
	}
	
	public static boolean display(String text, boolean lock){
		if(!locked || lock){
			buildString(text);
			
			c = Color.orange.darker(0.5f);
			locked = lock;
			return true;
		}
		return false;
	}
	
	public static boolean display(String text){
		if(!locked){
			buildString(text);
			
			c = Color.orange.darker(0.5f);
			return false;
		}
		
		return true;
	}
	
	public static boolean clear(boolean force){
		if(locked){
			if(force){
				s = null;
				Option.clear();
				locked = false;
				return true;
			}else{
				return false;
			}
		}else{
			s = null;
			Option.clear();
			return true;
		}
	}
	
	public static void buildString(String text){
		int segs = 1+(text.length()/32);
		String[] segments = new String[segs];
		for(int i = 0; i < segs; i++){
			if(i == segs-1){
				segments[i] = text.substring(i*32);
			}else{
				segments[i] = text.substring(i*32, 32+(i*32));
			}
			
			if(segments[i].startsWith(" ")){
				segments[i] = segments[i].replaceFirst(" ", "");
			}
		}
		
		s = segments;
	}
	
	public static void render(Graphics g){
		if(s != null){
			g.setColor(c);
			for(int i = 0; i < s.length; i++){
				g.drawString(s[i], x, y+(i*(g.getFont().getLineHeight()+2)));
			}
			
			if(Option.getOptionString() != null){
				Option.render(g);
			}
		}
	}
}