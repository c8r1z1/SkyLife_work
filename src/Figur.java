import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public abstract class Figur implements Serializable {
	
	public enum FORM { Kreis, Rechteck };
	public String name;
	public int x = 0, y = 0, speed = 0,
	height = 0, width = 0;
	FORM f;
	
	public Figur(String name, int x, int y, int speed, int height, int width, String a){
		this.name = name;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.height = height;
		this.width = width;
		if(a == "Kreis"){
			f = FORM.Kreis;
		}
		else if (a == "Rechteck"){
			f = FORM.Rechteck;
		}
		
	}
	
	public String toString(){
		return name;
	}
	
	//Bestimmung x-Koordinate Mittelpunkt
	public double middleX(){
		double X = 0.0;
		X = (this.x + this.x / 2) * 1.0;
		return X;
	}
	//Bestimmung y-Koordinate Mittelpunkt
	public double middleY(){
		double Y = 0.0;
		Y = (this.y + this.y / 2) * 1.0;
		return Y;
	}
}
