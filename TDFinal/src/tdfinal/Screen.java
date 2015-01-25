/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdfinal;

import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;
/**
 * JPanel that does all of the painting, repainting, and moving using multi threading
 * @author Derik
 */
public class Screen extends JPanel implements Runnable { // All drawing is done here 
    public Thread gameLoop = new Thread(this); // Creates this Class into a thread
    
    public static Image track;// Holds image for the track
    public static Image[] tileset_indicators = new Image[8]; // Holds images for indicators
    public static Image[] tileset_buttons = new Image[8]; // Holds images for buttons
    public static Image[] tileset_soldier = new Image[8]; // Holds images for the soldier enemy
    public static Image[] tileset_towers = new Image[8];
    
    public static Image map; // The image for the background map
    
    public static boolean isFirst = true; // Flag for first time running
    public static boolean isDebug = true;
    
    public static Point ms = new Point(0, 0); // Point to keep track of mouse position
    
    public static int myWidth, myHeight; // Width and Height of the JPanel
    public static int money = 200; // Starting money	
    public static int health = 20; // Starting Health
    public static int killed = 0, killsToWin = 0, level = 1;
    
    public static Manager manager;
    public static Save save;
    public static Store store;
    public static Menu menu;
    
    private enum STATE {
    	MENU, GAME
    };
    
    private STATE state = STATE.MENU;
    
    public static Enemy[] enemies = new Enemy[100]; // Holds all the enemies
    
    public Screen(Frame frame) {        
        frame.addMouseListener(new KeyHandler()); // Adds a mouse listener to the JFrame
        frame.addMouseMotionListener(new KeyHandler()); // Adds a mouse motion listener to the JFrame
        gameLoop.start(); // Starts the thread
    }
    
    public void Define() {
        manager = new Manager(); // Initialize other classes
        save = new Save();
        store = new Store();
        menu = new Menu();

        map = new ImageIcon("res/map.png").getImage(); // Load background image
        	
        
        
        track = new ImageIcon("res/TrackCorner.png").getImage(); // Initialize the track image file
        track = createImage(new FilteredImageSource(track.getSource(), new CropImageFilter(0, 31*0, 31, 31)));
        
        tileset_towers[0] = new ImageIcon("res/redlasertower.png").getImage();
        tileset_towers[1] = new ImageIcon("res/blueLaserTower.png").getImage();
        tileset_towers[2] = new ImageIcon("res/goldLaserTower.png").getImage();
        
        // Load images for the indicators
        tileset_indicators[0] = new ImageIcon("res/button.png").getImage();
        tileset_indicators[1] = new ImageIcon("res/money.png").getImage();
        tileset_indicators[2] = new ImageIcon("res/heart.png").getImage();
        
        // Load images for the buttons
        tileset_buttons[0] = new ImageIcon("res/redLaserTower.png").getImage();
        tileset_buttons[1] = new ImageIcon("res/blueLaserTower.png").getImage();
        tileset_buttons[2] = new ImageIcon("res/goldLaserTower.png").getImage();
        tileset_buttons[3] = new ImageIcon("res/trash.png").getImage();
        
        // Load images for the solider
        tileset_soldier[0] = new ImageIcon("res/enemyD1.png").getImage();
        tileset_soldier[1] = new ImageIcon("res/enemyD2.png").getImage();
        
        tileset_soldier[2] = new ImageIcon("res/enemyR1.png").getImage();
        tileset_soldier[3] = new ImageIcon("res/enemyR2.png").getImage();
        
        tileset_soldier[4] = new ImageIcon("res/enemyL1.png").getImage();
        tileset_soldier[5] = new ImageIcon("res/enemyL2.png").getImage();
        
        tileset_soldier[6] = new ImageIcon("res/enemyU1.png").getImage();
        tileset_soldier[7] = new ImageIcon("res/enemyU2.png").getImage();
        
        // Save the configuration of the track
        save.loadSave(new File("save/mission.txt"));
        
        // Initialize enemy objects
        for(int i = 0; i < enemies.length; i++) {
        	enemies[i] = new Enemy();
        }
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        if(isFirst) { // If its the first time painting
            myWidth = getWidth(); // Set width
            myHeight = getHeight(); // Set height
            Define(); 
            isFirst = false;            
        }
        
        
	    if(state == STATE.GAME){    
	    	g.clearRect(0, 0, getWidth(), getHeight());
	    	manager.draw(g); // Draws the map
		        for(int i = 0; i < enemies.length; i++) {
		        	if(enemies[i].isAlive) {
		        		enemies[i].draw(g); // Draws the enemies if they are alive
		        	}
		        }	    
		     store.draw(g); // Draws the store
	    }
	    else if(state == STATE.MENU){
	    	menu.draw(g);
	    }
        
        if(health < 1) { // Displays "Game Over" if health < 1
        	g.setColor(Color.red);
        	g.setFont(new Font("Helvetica", Font.BOLD, 40));
        	g.drawString("Game Over", myWidth/2, myHeight/2);
        }
    }
    
    public int createTime = 2800, createFrame = 0; // The rate to create enemies
    public static int killCount = 0;
    
    public void enemyCreator() { // Creates enemies
    	if(createFrame >= createTime) {
    		for(int i = 0; i < enemies.length; i++) {
    			if(!enemies[i].isAlive) { // If the enemy is no longer alive
    				enemies[i].Create(0); // Create a new one
    				break;
    			}
    				
    		}
    		createFrame = 0;
    	}
    	else
    		createFrame += 1;
    	
    	if(killCount == 10){
    		killCount = 0;
    		if(createTime > 800)
    			createTime -= 250;
    		
    		if(Enemy.moveSpeed > 10)	
    				Enemy.moveSpeed -= 2;

        	Block.healthTime += 15;
    	}
    }
    
    
    // The moving of the enemies along with the repainting is done within the thread's run function
    public void run() { 
        while(true) {
            if(!isFirst && health > 0 && state == STATE.GAME) { // If its not the first time running and the user has health
            	manager.physic();
            	enemyCreator(); // Creates enemies
            	for(int i= 0; i < enemies.length; i++) {
            		if(enemies[i].isAlive) {
            			enemies[i].move(); // Moves the enemies
            		}
            	}
            }
            else if(health < 1) {
            	for(int i = 0; i < enemies.length; i++)
            		enemies[i].deleteEnemy(); // Deletes all enemies on the screen
            	
            }
            
           repaint();// Repaint the screen
                        
           try {
                Thread.sleep(1);// Causes the thread to pause for 1 millisecond in order to slow down movement
           } 
           catch (Exception ex) {
               System.out.println(ex.getMessage());
           }
        }
    }
}
