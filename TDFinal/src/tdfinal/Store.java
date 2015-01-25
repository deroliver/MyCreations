/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdfinal;

import java.awt.*;

import javax.swing.ImageIcon;
/**
 *
 * @author Derik
 */
public class Store {
    public static int shopWidth = 8;
    public static int buttonSize = 31;
    public static int Space = 5;
    public static int icon = 45;
    public static int heldID = 0;
    
    public static int[] buttonID = {Values.redLaserTower, Values.blueLaserTower, Values.goldLaserTower, 7, 7, 7, 7, Values.trash};
    public static int[] buttonPrice = {100, 200, 300, 0, 0, 0, 0, 0};
    
    public Rectangle[] button = new Rectangle[shopWidth];
    public Rectangle buttonHealth;
    public Rectangle money; 
    
    public Image map;
    
    public boolean holdItem = false;
    
    
    public Store() {
        define();
    }
 
    
    public void Click(int mButton) {
    	if(mButton == 1){ // Right click mouse button
    		for(int i = 0; i < button.length; i++){
    			if(button[i].contains(Screen.ms)) { // The mouse is inside the button
    				if(buttonID[i] == Values.trash) { // Deletes the item currently holding
    					holdItem = false;
    				}
    				else {
    					if(holdItem != true) { // Can only trash the item in the trash
	    					heldID = buttonID[i];
	    					holdItem = true;
    					}
    				}
    			}
    		}
    		
    		if(holdItem){ // Placing the towers - Changes the fieldID of the block, which will draw the correct tower
    			if(Screen.money >= buttonPrice[heldID]){ // If you have more money than the item you are holding
    				for(int i = 0; i < Screen.manager.world.length; i++){
    					for(int j = 0; j < Screen.manager.world[0].length; j++){
    						if(Screen.manager.world[i][j].contains(Screen.ms)){
    							if(Screen.manager.world[i][j].trackID != Values.groundTrack && Screen.manager.world[i][j].fieldID == Values.groundField){
    								Screen.manager.world[i][j].fieldID = heldID;
    								Screen.money -= buttonPrice[heldID];
    						}
    							
    					}    					
    					}
    				}
    			}
    		}
    	} 		   			
    }
    
    
  
    
    public void define() {
        map = new ImageIcon("res/shop.png").getImage(); // Load the image for the shop background
        for(int i = 0; i < button.length; i++) { // Create the buttons
            button[i] = new Rectangle(31 + ((31 + Space) * i), (Screen.manager.world[Screen.manager.worldHeight - 1][0].y - 93), buttonSize, buttonSize);
        }
        
        buttonHealth = new Rectangle(50, 707, icon, icon); // Create the rectangle for the health indicator
        money = new Rectangle(200, 707, icon, icon); // Create the rectangle for the money indicator
    }
    
    public void draw(Graphics g) { // Draw function
        g.drawImage(map, 28, 699, null); // Draw the shop backgrond
        g.setFont(new Font("Helvetica", Font.CENTER_BASELINE, 12)); // Font setter
        g.setColor(Color.black);
        for(int i = 0; i < button.length; i++) {
            if(button[i].contains(Screen.ms)) { // Lights up the button if the mouse scrolls over
                g.setColor(new Color(0, 0, 0, 255));
                g.fillRect(button[i].x, button[i].y, button[i].width, button[i].height);
            }
            
            g.drawImage(Screen.tileset_indicators[0], button[i].x, button[i].y, button[i].width, button[i].height, null); // Draws the button background
            g.drawImage(Screen.tileset_buttons[buttonID[i]], button[i].x + 4, button[i].y + 4, button[i].width - 8, button[i].height - 8, null); // Draws the button image
           
            if(buttonPrice[i] > 0){
            	  g.drawString("" + buttonPrice[i], button[i].x + 4, button[i].y - 4); // Draws the prices
            }          
        }
        
        g.drawString("Killed: " + Screen.killed,140 ,713);
        g.drawImage(Screen.tileset_indicators[2], buttonHealth.x, buttonHealth.y, buttonHealth.width, buttonHealth.height, null); // Draws the hear
        g.drawImage(Screen.tileset_indicators[1], money.x, money.y, money.width, money.height, null); // Draws the coin
        g.setFont(new Font("Helvetica", Font.BOLD, 18)); // Font setter
        g.setColor(Color.black);
        g.drawString("" + Screen.health, buttonHealth.x + buttonHealth.width + 10, buttonHealth.y + 28); //Draws health
        g.drawString("" + Screen.money, money.x + money.width + 10, money.y + 28); // Draws current money
        
        if(holdItem) { // If you are holding an item, draws the corresponding image at the mouse location
        	g.drawImage(Screen.tileset_buttons[heldID], Screen.ms.x - ((button[heldID].width - 8)/2), Screen.ms.y - ((button[heldID].width - 8)/2), button[heldID].width - 8, button[heldID].height - 8, null);
        }
        
    }
}
