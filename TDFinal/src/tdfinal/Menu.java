package tdfinal;

import java.awt.*;

public class Menu {
	
	public Rectangle playButton;

	public void draw(Graphics g){
		g.setFont(new Font("Helvetica", Font.BOLD, 70));
		g.setColor(Color.green);
		g.drawString("Alien Defense", 450, 100);
		
	}
}
