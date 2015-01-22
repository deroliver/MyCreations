/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Simple_Java_Game;

import javax.swing.JFrame;

/**
 *
 * @author doliver17
 */
public class Java_2DShooter extends JFrame {

    public Java_2DShooter() {
        
        add(new GameMap());
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setTitle("Practice");
        setResizable(false);
        setVisible(true);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Java_2DShooter();
    }
    
}
