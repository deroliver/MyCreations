/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdfinal;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Derik
 */
public class Frame extends JFrame {
    public static String title = "Alien Defense";
    public static Dimension size = new Dimension(1215, 810);
    
    public Frame() {
        setTitle(title);
        setSize(size);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        init();
    }
    
    public void init() {
        setLayout(new GridLayout(1, 1, 0, 0));
        Screen screen = new Screen(this);
        add(screen);
        setVisible(true);
        
    }
    
    public static void main(String[] args) {
        Frame frame = new Frame();
    }
    
   
    
    
    
}
