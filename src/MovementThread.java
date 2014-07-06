
public class MovementThread extends Thread {

	boolean running = true;

	SkyLife app;
//	double mrx = Math.random();
//	int counterX = 0;
//	double mrx2 = Math.random();
//	int counterX2 = 0;
//	double mry = Math.random();
//	int counterY = 0;
//	double mry2 = Math.random();
//	int counterY2 = 0;

	public MovementThread(SkyLife app){

		this.app = app;

	}

	public void CorrectXMovement(Figur f){

		if(f.x < 0){
			f.x = 0;
			f.changeDirectionX();
		}
		if((f.x + f.width) > app.PanelWidth){
			f.x = (app.PanelWidth - f.width);
			f.changeDirectionX();
		}

	}

	public void CorrectYMovement(Figur f){

		if(f.y < 0){
			f.y = 0;
			f.changeDirectionY();
		}
		if((f.y + f.height) > app.PanelHeight){
			f.y = (app.PanelHeight - f.height);
			f.changeDirectionY();
		}
	}
//	//Führt zur Neuausrichtung der Bewegung bei Kontakt mit dem Panelrand
//	public void changeDirectionY() {
//		counterY = 200;
//		counterY2 = 200;
//	}
//	public void changeDirectionX() {
//		counterX = 200;
//		counterX2 = 200;
//	}
//
//	public double MathRandomX(){
//		if(counterX >= 150){
//			mrx = Math.random();
//			counterX = 0;
//		}
//		counterX++;
//		return mrx;
//	}
//
//	public double MathRandomX2(){
//		if(counterX2 >= 150){
//			mrx2 = Math.random();
//			counterX2 = 0;
//		}
//		counterX2++;
//		return mrx2;
//	}
//
//	public double MathRandomY(){
//		if(counterY >= 150){
//			mry = Math.random();
//			counterY = 0;
//		}
//		counterY++;
//		return mry;
//	}
//
//	public double MathRandomY2(){
//		if(counterY2 >= 150){
//			mry2 = Math.random();
//			counterY2 = 0;
//		}
//		counterY2++;
//		return mry2;
//	}

	public void Movement(){

		synchronized (app.ObjectList) {
			for (Figur f : app.ObjectList){

				if(f instanceof Flugobjekt){
					f.MovementX();
					//Korrektur x-Koordinate bei Lage außerhalb des Panels
					CorrectXMovement(f);

					f.MovementY();
					//Korrektur y-Koordinate bei Lage außerhalb des Panels
					CorrectYMovement(f);				
				}
				if(f instanceof Meteorit){
					f.y += 10;
					CorrectYMovement(f);
					if(f.y >= app.PanelHeight - f.height){
						GameOver("Meteoriteneinschlag, die Natur übt Rache für tote Vögel");
					}
				}
			}}
	}


	//Löschung verbleibender Objekte bei Game Over
	public void DeleteObjectCollisionAll(){
		app.ObjectList.clear();
		app.comboBoxNameEnt.removeAllItems();
		app.panel.repaint();
	}

	//Game Over da Kollateralschaden entstanden
	public void GameOver(String a){		
		app.lblMessageTxt.setText("Game Over: " + a);
		//Löschen aller verbleibenden Objekte in der Liste
		DeleteObjectCollisionAll();
		//Aktivierung des Buttons zum Setzen der Panelgröße, wenn keine Objekte im Panel
		app.btnSetPanelSize.setEnabled(true);
		app.stop.setEnabled(false);
		app.updateInfo();
		app.killedAnimals = 0;
		app.updateKilledAnimals();
		app.tmov.running = false;
		app.trep.running = false;
		app.tcol.running = false;
	}

	public void run(){

		while(running){

			//Aufruf Movement Methode
			Movement();

			try {
				sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
	}

}
