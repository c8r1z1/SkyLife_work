
public class Taube extends Flugobjekt{
	
	static int x = 0, y = 0, height = 30, width = 45, speed = 1;
	static String a = "Rechteck";

	public Taube(String name, SkyLife app) {
		super(name, x, y, speed, height, width, a, app);
	}
	
	@Override
	public String toString(){
		return "Taube " + name;
		
	}
}
