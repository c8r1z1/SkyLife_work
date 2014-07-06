
public class Meteorit extends Figur {
	
	static String name = "Destroyer", a = "Rechteck";
	static int x = 0, y = 0, speed = 3, height = 285, width = 99;

	public Meteorit(String name, int PanelWidth, SkyLife app) {
		super(name, (PanelWidth / 2), y, speed, height, width, a, app);
	}
	
	

}
