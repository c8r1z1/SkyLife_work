
public class MovementStepbyStepThread extends Thread{

	boolean running = true;

	SkyLife app;

	public MovementStepbyStepThread(SkyLife app){

		this.app = app;

	}

	public void StepbyStep(){
		synchronized (app.ObjectList){
			for (Figur f : app.ObjectList){

				System.out.println("StepbyStep-Modus gestartet");

				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if(f instanceof Flugobjekt){

					System.out.println(f.toString() + " vorher ");
					f.x = (int) (f.x + f.speed * Math.random() * 10 - Math.random() * f.speed * 10 );
					f.y = (int) (f.y + f.speed * Math.random() * 10 - Math.random() * f.speed * 10 );
					System.out.println(f.toString() + " nachher ");
					app.panel.repaint();

				}
			}
		}
	}

	public void run(){

		while(running){
			
			StepbyStep();

		}
	}

}
