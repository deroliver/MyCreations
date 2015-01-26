package tdfinal;

import java.awt.*;

import javax.swing.ImageIcon;

import tdfinal.Screen.STATE;


/**
 * The class to display the game menu
 * @author Derik
 *
 */
public class Menu {

	public static Image playButton = new ImageIcon("res/playButton.png").getImage(), 
			quitButton = new ImageIcon("res/exitButton.png").getImage();
	
	public static Rectangle rP = new Rectangle(590, 200, playButton.getWidth(null), playButton.getHeight(null));
	public static Rectangle rQ = new Rectangle(590, 400, quitButton.getWidth(null), quitButton.getHeight(null));
	
	/**
	 * Handles clicks for the buttons
	 * @param mButton
	 */
	public static void Click(int mButton){
		if(mButton == 1){
			if(rP.contains(Screen.ms)) { 
	             Screen.state = STATE.GAME;
			 }
			
			if(rQ.contains(Screen.ms)) {
				System.exit(1);
			}
		}		
	}
	
	/**
	 * Draws the menu to the screen
	 * @param g The Graphics object to draw to
	 */
	public void draw(Graphics g){
		g.drawImage(new ImageIcon("res/menuBackground.png").getImage(), 0, 0, 1345, 850, null);
		
		g.setColor(Color.green);
		g.setFont(new Font("Helvetica", Font.BOLD, 70));
		g.drawString("Alien Defense", 450, 100);
		   
		g.drawImage(playButton, rP.x, rP.y, rP.width, rP.height, null);
		g.drawImage(quitButton, rQ.x, rQ.y, rQ.width, rQ.height, null);
		
		
		 if(rP.contains(Screen.ms)) { // Lights up the button if the mouse scrolls over
             g.setColor(new Color(255, 255, 255, 100));
             g.fillRect(rP.x, rP.y, rP.width, rP.height);
		 }
		 
		 if(rQ.contains(Screen.ms)) { // Lights up the button if the mouse scrolls over
             g.setColor(new Color(255, 255, 255, 100));
             g.fillRect(rQ.x, rQ.y, rQ.width, rQ.height);
		 }	
	}
}


