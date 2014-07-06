
public class Wolkenkratzer extends Figur{
	
	static int x = 0, y = 0, height = 325, width = 70, speed = 0;
	static String a = "Rechteck";
		
	public Wolkenkratzer(String name){
		super(name, x, y, speed, height, width, a);
		//form f = form.Rechteck;
	}

	@Override
	public String toString(){
		return "Wolkenkratzer " + name;
		
	}

}
