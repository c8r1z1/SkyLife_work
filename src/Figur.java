import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public abstract class Figur implements Serializable {
	
	public enum FORM { Kreis, Rechteck };
	public String name;
	public int x = 0, y = 0, speed = 0,
	height = 0, width = 0;
	FORM f;
	
	//variables for movement
	SkyLife app;
	double mrx = Math.random();
	int counterX = 0;
	double mrx2 = Math.random();
	int counterX2 = 0;
	double mry = Math.random();
	int counterY = 0;
	double mry2 = Math.random();
	int counterY2 = 0;
	
	//constructor
	public Figur(String name, int x, int y, int speed, int height, int width, String a, SkyLife app){
		this.app = app;
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
	
	//toString
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
	
	public void CorrectXMovement(Figur f){

		if(f.x < 0){
			f.x = 0;
			changeDirectionX();
		}
		if((f.x + f.width) > app.PanelWidth){
			f.x = (app.PanelWidth - f.width);
			changeDirectionX();
		}

	}

	public void CorrectYMovement(Figur f){

		if(f.y < 0){
			f.y = 0;
			changeDirectionY();
		}
		if((f.y + f.height) > app.PanelHeight){
			f.y = (app.PanelHeight - f.height);
			changeDirectionY();
		}
	}
	
	//Führt zur Neuausrichtung der Bewegung bei Kontakt mit dem Panelrand
	public void changeDirectionY() {
		counterY = 200;
		counterY2 = 200;
	}
	public void changeDirectionX() {
		counterX = 200;
		counterX2 = 200;
	}
	
	//Calculation for movement
	
	public void MovementX(){
		x = (int) (x + speed * (MathRandomX() - MathRandomX2()) * 1 + 1);
	}
	
	public void MovementY(){
		y = (int) (y + speed * (MathRandomX() - MathRandomX2()) * 1 + 1);
	}
	
	public double MathRandomX(){
		if(counterX >= 150){
			mrx = Math.random();
			counterX = 0;
		}
		counterX++;
		return mrx;
	}

	public double MathRandomX2(){
		if(counterX2 >= 150){
			mrx2 = Math.random();
			counterX2 = 0;
		}
		counterX2++;
		return mrx2;
	}

	public double MathRandomY(){
		if(counterY >= 150){
			mry = Math.random();
			counterY = 0;
		}
		counterY++;
		return mry;
	}

	public double MathRandomY2(){
		if(counterY2 >= 150){
			mry2 = Math.random();
			counterY2 = 0;
		}
		counterY2++;
		return mry2;
	}
}
