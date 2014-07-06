
public class Greifvogel extends Flugobjekt{
	
	static int x = 0, y = 0, height = 20, width = 50, speed = 2;
	static String a = "Rechteck";

	public Greifvogel(String name, SkyLife app) {
		super(name, x, y, speed, height, width, a, app);

	}
	
	@Override
	public String toString(){
		return "Greifvogel " + name;
		
	}

}
