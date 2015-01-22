/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdfinal;

import java.io.*;
import java.util.*;
/**
 *
 * @author Derik
 */
public class Save {
    
    public void loadSave(File loadpath) {
        try {
            Scanner loadScanner = new Scanner(loadpath);
            
            while(loadScanner.hasNext()) {
                for(int i = 0; i < Screen.manager.world.length; i++) {
                    for(int j = 0; j < Screen.manager.world[0].length; j++) {
                        Screen.manager.world[i][j].trackID = loadScanner.nextInt();
                    }
                }
                
                for(int i = 0; i < Screen.manager.world.length; i++) {
                    for(int j = 0; j < Screen.manager.world[0].length; j++) {
                        Screen.manager.world[i][j].airID = loadScanner.nextInt();
                    }
                }
            }
            loadScanner.close();
        }
        catch(Exception e) {
                    
                    }
    }
}
