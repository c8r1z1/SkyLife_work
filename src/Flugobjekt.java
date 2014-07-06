
public class Flugobjekt extends Figur{
	
	public Flugobjekt(String name, int x, int y, int speed, int height, int width, String a, SkyLife app){
		super(name, x, y, speed, height, width, a, app);
	}

	
	public boolean collidesWith(Flugzeug flugzeug) {
		// TODO Auto-generated method stub
		return false;
	}

}
