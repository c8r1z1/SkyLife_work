
public class Flugobjekt extends Figur{
	
	public Flugobjekt(String name, int x, int y, int speed, int height, int width, String a){
		super(name, x, y, speed, height, width, a);
	}

	
	public boolean collidesWith(Flugzeug flugzeug) {
		// TODO Auto-generated method stub
		return false;
	}

}
