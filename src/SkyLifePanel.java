import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SkyLifePanel extends JPanel {

	SkyLife app;

	Image taubeImg = null;
	Image gvImg = null;
	Image fzImg = null;
	Image wkImg = null;
	Image mImg = null;

	public SkyLifePanel(SkyLife app) {
		this.app = app;
		try {
			taubeImg = ImageIO.read(new File("img/taube.png"));
			gvImg = ImageIO.read(new File("img/greifvogel.png"));
			fzImg = ImageIO.read(new File("img/flugzeug.png"));
			wkImg = ImageIO.read(new File("img/wolkenkratzer.png"));
			mImg = ImageIO.read(new File("img/meteorit.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void Draw(Graphics g){
		synchronized (app.ObjectList){
			for (Figur f : app.ObjectList) {
				if (f instanceof Taube) {
					g.setColor(Color.red);
					g.drawOval(f.x + 5, f.y, 30, 30);
					g.drawImage(taubeImg, f.x, f.y, null);	
				}
				if(f instanceof Greifvogel) {
					g.drawImage(gvImg, f.x, f.y, null);	
				}
				if(f instanceof Flugzeug) {
					g.drawImage(fzImg, f.x, f.y, null);	
				}
				if(f instanceof Wolkenkratzer) {
					g.drawImage(wkImg, f.x, f.y, null);	
				}
				if (f instanceof Meteorit){
					g.drawImage(mImg, f.x, (f.y - 44), null);
				}
			}	
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);


		Image img;

		if(app.comboBoxTyp.getSelectedItem().toString() == "Taube"){
			img = taubeImg;			
		}
		else if (app.comboBoxTyp.getSelectedItem().toString() == "Greifvogel"){
			img = gvImg;
		}
		else if (app.comboBoxTyp.getSelectedItem().toString() == "Flugzeug"){
			img = fzImg;
		}
		else if (app.comboBoxTyp.getSelectedItem().toString() == "Wolkenkratzer") {
			img = wkImg;
		}
		else{
			img = mImg;
		}

		Draw(g);
	}

}
