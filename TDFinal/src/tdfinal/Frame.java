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
    public static Dimension size = new Dimension(1345, 850);
    
    public Frame() {
        setTitle(title); // Set title to title string
        setSize(size); // Set size to Dimension object created
        setResizable(false); // Unable to resize window 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        
        init();
    }
    
    public void init() {
        setLayout(new GridLayout(1, 1, 0, 0)); // Adds objects onto the whole area of the frame
        Screen screen = new Screen(this); // Create a new screen which extends JPanel
        add(screen); // Add the JPanel to the JFrame
        setVisible(true); // Set visibility True
        
    }
    
    public static void main(String[] args) {
        Frame frame = new Frame();
    }
    
   
    
    
    
}
