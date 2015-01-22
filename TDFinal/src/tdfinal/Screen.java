/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdfinal;

import javax.swing.*;
import java.awt.*;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;
/**
 *
 * @author Derik
 */
public class Screen extends JPanel implements Runnable {
    public Thread gameLoop = new Thread(this);
    
    public static Image[] tileset_track = new Image[100];
    public static Image[] tileset_buttons = new Image[8];
    
    public static Image map;
    
    public static boolean isFirst = true;
    
    public static Point ms = new Point(0, 0);
    
    public static int myWidth, myHeight;
    
    public static Manager manager;
    public static Save save;
    public static Store store;
    
    public Screen(Frame frame) {        
        frame.addMouseListener(new KeyHandler());
        frame.addMouseMotionListener(new KeyHandler());
        gameLoop.start();
    }
    
    public void Define() {
        manager = new Manager();
        save = new Save();
        store = new Store();
        
        map = new ImageIcon("res/map.png").getImage();
        for(int i= 0; i < tileset_track.length; i++) {
            tileset_track[i] = new ImageIcon("res/TrackCorner.png").getImage();
            tileset_track[i] = createImage(new FilteredImageSource(tileset_track[i].getSource(), new CropImageFilter(0, 31*i, 31, 31)));
        } 
        
        tileset_buttons[0] = new ImageIcon("res/button.png").getImage();
        tileset_buttons[1] = new ImageIcon("res/money.png").getImage();
        tileset_buttons[2] = new ImageIcon("res/heart.png").getImage();
        
        save.loadSave(new File("save/mission.txt"));
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        if(isFirst) {
            myWidth = getWidth();
            myHeight = getHeight();
            Define();
            isFirst = false;            
        }
        
        g.clearRect(0, 0, getWidth(), getHeight());
        manager.draw(g); // Draws the map
        store.draw(g); // Draws the store
    }

    public void run() {
        while(true) {
            if(!isFirst) {
            
            }
           repaint();
                        
           try {
                Thread.sleep(1);
           } 
           catch (Exception ex) {
               System.out.println(ex.getMessage());
           }
        }
    }
}
