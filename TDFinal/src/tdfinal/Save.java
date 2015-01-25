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
    
    public void loadSave(File loadpath) {
        try {
            Scanner loadScanner = new Scanner(loadpath);
         // Reads in 1s and 0s from the text file into a 2D array
            Screen.killsToWin = loadScanner.nextInt();
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
}
