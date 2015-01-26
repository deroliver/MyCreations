/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdfinal;

import java.io.*;
import java.util.*;
/**
 * Class to save and load the map configuration from the text file
 * @author Derik
 */
public class Save {
    /**
     * Loads the map layout
     * @param loadpath The path for the map layout
     */
    public void loadSave(File loadpath) {
        try {
            Scanner loadScanner = new Scanner(loadpath);
         // Reads in 1s and 0s from the text file into a 2D array
            Screen.killsToWin = loadScanner.nextInt();
            Screen.highScore = loadScanner.nextInt();
            while(loadScanner.hasNext()) {
                for(int i = 0; i < Screen.manager.world.length; i++) { 
                    for(int j = 0; j < Screen.manager.world[0].length; j++) {
                        Screen.manager.world[i][j].trackID = loadScanner.nextInt();
                    }
                }
                
                for(int i = 0; i < Screen.manager.world.length; i++) { 
                    for(int j = 0; j < Screen.manager.world[0].length; j++) {
                        Screen.manager.world[i][j].fieldID = loadScanner.nextInt();
                    }
                }           
            }
            loadScanner.close();
        }
        catch(Exception e) {
                    
                    }
    }
    /**
     * Loads the high score from a text file
     */
    public void loadHighScore(){
    	try {
    		String name, nameSplit[];
    		Scanner highScanner = new Scanner(new File("save/highscore.txt"));
    		name = highScanner.nextLine();
    		nameSplit = name.split(":");
    		Screen.name = nameSplit[0];
    		Screen.highScore = Integer.parseInt(nameSplit[1]);
    	}
    	catch(Exception e){
    		
    	}
    }
    
    /**
     * Write the high score to a text file
     */
    public void writeHighScore(){
    	try{
    		File file = new File("save/highscore.txt");
    		BufferedWriter wtr = new BufferedWriter(new FileWriter(file));
    		wtr.write(Screen.name + ":" + Screen.highScore);
    		wtr.close();    		
    	}
    	catch(Exception e){
    		System.out.println("Error");
    	}
    }
}
