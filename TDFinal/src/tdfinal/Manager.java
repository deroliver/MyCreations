/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdfinal;

import java.awt.*;


/**
 *
 * @author Derik
 */
public class Manager {
    public int worldWidth = 39;
    public int worldHeight = 25;
    public int blockSize = 31;
    
    public Block[][] world;
    
    public Manager() {
        define();
    }
    
    public void define() {
        world = new Block[worldHeight][worldWidth];
        for(int i = 0; i < world.length; i++) {
            for(int j = 0; j < world[0].length; j++) {
                world[i][j] = new Block(j * blockSize, i * blockSize, blockSize, blockSize, Values.trackID, Values.airID);
            }
        }
    }
    
    public void physics() {
        
    }
    
    public void draw(Graphics g) {
        g.drawImage(Screen.map, Screen.WIDTH - 3, Screen.HEIGHT - 5, null);
        for(int i = 0; i < world.length; i++) {
            for(int j = 0; j < world[0].length; j++) {
                world[i][j].draw(g);
            }
        }
    }
}
