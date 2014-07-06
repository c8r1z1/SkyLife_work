
public class CollisionThread extends Thread {

	boolean running = true;

	SkyLife app;
	boolean collision = false;
	int deletedObjects = 0;
	int deletedObjectscleanList = 0;

	public CollisionThread(SkyLife app){

		this.app = app;

	}

	public void CorrectXCollision(Figur f){
		if(f.x < 0){
			f.x = 0;
		}
		if((f.x + f.width) > app.PanelWidth){
			f.x = (app.PanelWidth - f.width);
		}

	}

	public void CorrectYCollision(Figur f){

		if(f.y < 0){
			f.y = 0;
		}
		if((f.y + f.height) > app.PanelHeight){
			f.y = (app.PanelHeight - f.height);
		}
	}

	public boolean isAnimal(int i){

		if(app.ObjectList.get(i) instanceof Taube || app.ObjectList.get(i) instanceof Greifvogel){
			return true;
		}
		return false;
	}

	public void Collision(){

		synchronized (app.ObjectList){

			if(app.ObjectList.size() > 1){
				//Zur������cksetzen f������r neuen Schleifendurchlauf
				deletedObjects = 0;

				for (int i = 0; i < (app.ObjectList.size() - 1 - this.deletedObjects); i++){

					for(int j = 1; j < (app.ObjectList.size() - this.deletedObjects); j++){

						collision = app.collision(app.ObjectList.get(i), app.ObjectList.get(j));
						if(app.ObjectList.get(i).equals(app.ObjectList.get(j))){
							collision = false;
						}

						if(collision){

							if(app.ObjectList.get(i).getClass().equals(app.ObjectList.get(j).getClass()) == false){
								//Ausgabe Message: Objekt a kollidiert mit Objekt b
								app.lblMessageTxt.setText(app.ObjectList.get(i).toString() + " " + " kollidiert mit " + app.ObjectList.get(j).toString());
							}
							if(app.ObjectList.get(i) instanceof Wolkenkratzer && app.ObjectList.get(j) instanceof Flugzeug){
								//Game Over da Kollateralschaden entstanden
								GameOver("Flugzeug in Wolkenkratzer gecrasht");
							}
							if (app.ObjectList.get(i) instanceof Wolkenkratzer && isAnimal(j) == true){
								DeleteObjectCollision(j, i);
							}
							if(app.ObjectList.get(i) instanceof Flugzeug && app.ObjectList.get(j) instanceof Wolkenkratzer){
								//Game Over da Kollateralschaden entstanden
								GameOver("Flugzeug in Wolkenkratzer gecrasht");
							}
							if(app.ObjectList.get(i) instanceof Flugzeug && app.ObjectList.get(j) instanceof Flugzeug){
								//Game Over da Kollateralschaden entstanden
								GameOver("Flugzeug in anderes Flugzeug gecrasht");
							}
							if (app.ObjectList.get(i) instanceof Flugzeug && isAnimal(j) == true){
								if(Math.random() * 10 > 9.9){
									GameOver("Flugzeugabsturz durch Vogel in der Turbine");
								}
								else{
									DeleteObjectCollision(j, i);
								}
							}
							if (app.ObjectList.get(j) instanceof Flugzeug && isAnimal(i) == true){
								if(Math.random() * 10 > 9.9){
									GameOver("Flugzeugabsturz durch Vogel in der Turbine");
								}
								else{
									DeleteObjectCollision(i, j);
								}
							}
							if(app.ObjectList.get(i) instanceof Greifvogel && app.ObjectList.get(j) instanceof Wolkenkratzer){
								DeleteObjectCollision(i, j);
							}
							if(app.ObjectList.get(i) instanceof Greifvogel && app.ObjectList.get(j) instanceof Taube){
								DeleteObjectCollision(j, i);
							}
							if(app.ObjectList.get(i) instanceof Taube && (app.ObjectList.get(j) instanceof Greifvogel ||app.ObjectList.get(j) instanceof Wolkenkratzer)){
								DeleteObjectCollision(i, j);
							}
							//Ausweichman������ver bei 2 Objekten gleichen Typs
							if(app.ObjectList.get(i) instanceof Greifvogel && app.ObjectList.get(j) instanceof Greifvogel || app.ObjectList.get(i) instanceof Taube && app.ObjectList.get(j) instanceof Taube){
								dodge(i, j);
							}
							cleanList();
						}
					}

				}
				//testweise Verschiebung in if Abfragen
				//cleanList();
				app.updateInfo();
			}
		}
	}
	//Aufr������umen der Liste, da "null" Objekte angelegt werden
	public void cleanList(){
		deletedObjectscleanList = 0;
		for(int i = 0; i < app.ObjectList.size() - deletedObjectscleanList; i++){
			if(app.ObjectList.get(i) == null){
				app.ObjectList.remove(i);
				deletedObjectscleanList++;
			}
		}
	}

	//Ausweichman������ver + ������berpr������fung auf Verlassen des Panels
	public void dodge(int i, int j){
		app.lblMessageTxt.setText("Ausweichman������ver: " + app.ObjectList.get(i).toString() + " kollidiert mit " + app.ObjectList.get(j).toString());
		//Seitverschiebung
		if(app.ObjectList.get(i).y <= (app.ObjectList.get(j).y + app.ObjectList.get(j).height) || app.ObjectList.get(j).y <= (app.ObjectList.get(i).y + app.ObjectList.get(i).height)){
			if(app.ObjectList.get(j).x < ((app.ObjectList.get(i).x + app.ObjectList.get(i).width) / 2)){
				app.ObjectList.get(i).x += (app.ObjectList.get(j).width / 2);
				app.ObjectList.get(j).x -= (app.ObjectList.get(j).width / 2);
			}
			else if(app.ObjectList.get(j).x > ((app.ObjectList.get(i).x + app.ObjectList.get(i).width) / 2)){
				app.ObjectList.get(i).x -= (app.ObjectList.get(j).width / 2);
				app.ObjectList.get(j).x += (app.ObjectList.get(j).width / 2);
			}
			CorrectXCollision(app.ObjectList.get(i));
			CorrectXCollision(app.ObjectList.get(j));

		}
		//Vertikalverschiebung
		else if(app.ObjectList.get(j).x <= (app.ObjectList.get(i).x + app.ObjectList.get(i).width) || app.ObjectList.get(i).x <= (app.ObjectList.get(j).x + app.ObjectList.get(j).width)){
			if(app.ObjectList.get(j).y < ((app.ObjectList.get(i).y + app.ObjectList.get(i).height) / 2)){
				app.ObjectList.get(i).y += (app.ObjectList.get(j).height / 2);
				app.ObjectList.get(j).y -= (app.ObjectList.get(j).height / 2);
			}
			else if(app.ObjectList.get(j).y > ((app.ObjectList.get(i).y + app.ObjectList.get(i).height) / 2)){
				app.ObjectList.get(i).y -= (app.ObjectList.get(j).height / 2);
				app.ObjectList.get(j).y += (app.ObjectList.get(j).height / 2);
			}
			CorrectYCollision(app.ObjectList.get(i));
			CorrectYCollision(app.ObjectList.get(j));

		}
	}

	//L������schung eines Objektes nach Kollision
	public void DeleteObjectCollision(int i, int j){
		if(isAnimal(i) && !isAnimal(j)){
			app.killedAnimals++;
			app.updateKilledAnimals();
			meteoritProof();
		}
		app.deleteObjectList(app.ObjectList.get(i).name);
		app.ObjectList.set(i, null);
		deletedObjects++;
	}

	//Bei 10 oder mehr get������teten V������geln, r������cht sich die Natur
	public void meteoritProof() {
		if(app.killedAnimals >= 1){
			Meteorit meteor = new Meteorit("Destroyer", app.PanelWidth);
			app.ObjectList.add(meteor);
			//Zerst������rung aller Objekte auf dem Weg
			//Einschlag: GameOver
		}

	}

	//L������schung verbelibende Objekte bei Game Over
	public void DeleteObjectCollisionAll(){
		app.ObjectList.clear();
		app.comboBoxNameEnt.removeAllItems();
		app.panel.repaint();
	}

	//Game Over da Kollateralschaden entstanden
	public void GameOver(String a){		
		app.lblMessageTxt.setText("Game Over: " + a);
		//L������schen aller verbleibenden Objekte in der Liste
		DeleteObjectCollisionAll();
		//Aktivierung des Buttons zum Setzen der Panelgr������������e, wenn keine Objekte im Panel
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

			//Aufruf Collision Methode
			Collision();

			try {
				sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
