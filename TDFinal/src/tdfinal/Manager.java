/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdfinal;

import java.awt.*;


/**
 * Class that fills the 2D array with Block objects and paints the track as well as the background on the screen
 * @author Derik
 */
public class Manager {
    public int worldWidth = 43; // How many blocks for the world's width
    public int worldHeight = 29; // How many blocks for the world's height
    public int blockSize = 31; // The size of each block
    
    public Block[][] world; // The 2D array of block objects
    
    /**
     * Simple Constructor calls the define function
     */
    public Manager() { 
        define();
    }
    
    /**
     * The define function that fills the 2D array with Block objects that span the screen
     */    
    public void define() {
        world = new Block[worldHeight][worldWidth];
        for(int i = 0; i < world.length; i++) {// Fills the 2D Array with Block objects
            for(int j = 0; j < world[0].length; j++) {
                world[i][j] = new Block(j * blockSize, i * blockSize, blockSize, blockSize, Values.trackID, Values.groundField);
            }
        }
    }
    
    /**
     * Calls the physic function for each block
     */
    public void physic() {
        for(int i = 0; i < world.length; i++) {
        	for(int j = 0; j < world[0].length; j++){
        		world[i][j].physic();
        	}
        }
    }
    
    /**
     * Draws all the Blocks in the array on the screen 
     * @param g The Graphics object to draw to
     */
    public void draw(Graphics g) {
        g.drawImage(Screen.map, Screen.WIDTH - 3, Screen.HEIGHT - 5, null); // Draw the background image
        for(int i = 0; i < world.length; i++) { // Draws the 2D Array 
            for(int j = 0; j < world[0].length; j++) {
                world[i][j].draw(g);
            }
        }
     
	    for(int i = 0; i < world.length; i++) { // Draws the 2D Array 
	        for(int j = 0; j < world[0].length; j++) {
	            world[i][j].Attack(g);
	        }
	    }
    }
}
