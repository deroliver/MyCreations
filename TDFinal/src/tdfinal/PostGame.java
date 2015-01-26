package tdfinal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tdfinal.Screen.STATE;

/**
 * The menu to display after the user loses - Displays the high score and allows the user to retry
 * @author Derik
 *
 */
public class PostGame {

	
	public static Image retryButton = new ImageIcon("res/retryButton.png").getImage(), 
			quitButton = new ImageIcon("res/exitButton.png").getImage();
	
	public static Rectangle rP = new Rectangle(590, 200, retryButton.getWidth(null), retryButton.getHeight(null));
	public static Rectangle rQ = new Rectangle(590, 400, quitButton.getWidth(null), quitButton.getHeight(null));
	
	/**
	 * Draws the post game menu to the screen
	 * @param g
	 */
	public void draw(Graphics g){
		g.drawImage(new ImageIcon("res/menuBackground.png").getImage(), 0, 0, 1345, 850, null);
		
		g.setColor(Color.green);
		g.setFont(new Font("Helvetica", Font.BOLD, 20));
		g.drawString("High Score- " + Screen.name + ": " + Screen.highScore, 550, 150);
		
		
		
		g.setColor(Color.green);
		g.setFont(new Font("Helvetica", Font.BOLD, 70));
		g.drawString("Game Over", 475, 100);
		   
		g.drawImage(retryButton, rP.x, rP.y, rP.width, rP.height, null);
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
	
	/**
	 * Handles clicks for the post game menu
	 * @param mButton
	 */
	public static void Click(int mButton){
		if(mButton == 1){
			if(rP.contains(Screen.ms)) {
				Screen.state = STATE.GAME;
				Screen.health = 20;
				Screen.killed = 0;
				Screen.killCount = 0;
				Screen.money = 200;
				Screen.save.loadSave(new File("save/mission.txt"));
			 }
			
			if(rQ.contains(Screen.ms)) {
				System.exit(1);
			}
		}		
	}
}
	

