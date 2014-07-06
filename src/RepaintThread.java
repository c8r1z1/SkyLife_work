
public class RepaintThread extends Thread{

	boolean running = true;

	SkyLife app;

	public RepaintThread(SkyLife app){
		this.app = app;
	}

	public void run(){
			while(running){
				app.panel.repaint();

				try {
					sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
}
