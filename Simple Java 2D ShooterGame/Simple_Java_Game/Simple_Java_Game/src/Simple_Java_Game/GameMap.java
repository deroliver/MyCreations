/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Simple_Java_Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.ArrayList;


/**
 *
 * @author doliver17
 */
public class GameMap extends JPanel implements ActionListener { 
    private Timer timer;
    private Enemy enemy;
    private ArrayList<Alien> aliens;
    private boolean inGame;
    private int B_WIDTH;
    private int B_HEIGHT;
    
    private int[][] pos = { 
        {2380, 29}, {2500, 59}, {1380, 89},
        {780, 109}, {580, 139}, {680, 239}, 
        {790, 259}, {760, 50}, {790, 150},
        {980, 209}, {560, 45}, {510, 70},
        {930, 159}, {590, 80}, {530, 60},
        {940, 59}, {990, 30}, {920, 200},
        {900, 259}, {660, 50}, {540, 90},
        {810, 220}, {860, 20}, {740, 180},
        {820, 128}, {490, 170}, {700, 30}
     };

    
    public GameMap() {
        
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        inGame = true;
        setSize(400, 300);
        
        enemy = new Enemy();
        
        initAliens();
        
        timer = new Timer(5, this);
        timer.start();
    }
    
    public void addNotify() {
        super.addNotify();
        B_WIDTH = getWidth();
        B_HEIGHT = getHeight();
    }
    
    public void initAliens() {
        aliens = new ArrayList<Alien>();
        
        for(int i = 0; i < pos.length; i++) {
            aliens.add(new Alien(pos[i][0], pos[i][1]));
        }
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        
        if(inGame) {
            Graphics2D g2D = (Graphics2D)g;
            if(enemy.isVisible())
                g2D.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), this);
        
            ArrayList ms = enemy.getMissiles();
        
            Missile M;
            for(int i = 0; i < ms.size(); i++) {
                M = (Missile)ms.get(i);
                g2D.drawImage(M.getImage(), M.getX(), M.getY(), this);
            }
            Alien A;
            for(int i = 0; i < aliens.size(); i++) {
                A = (Alien)aliens.get(i);
                if(A.isVisible())
                    g2D.drawImage(A.getImage(), A.getX(), A.getY(), this);
            }
            
            g2D.setColor(Color.WHITE);
            g2D.drawString("Aliens left: " + aliens.size(), 5, 15);
       
        }
        else{
            String msg = "Game Over";
            Font small = new Font("Helvetica", Font.BOLD, 14);
            FontMetrics  metr = this.getFontMetrics(small);
            
            g.setColor(Color.white);
            g.setFont(small);
            g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
            
        }
        
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    
    public void actionPerformed(ActionEvent e) {
        if(aliens.size() == 0)
            inGame = false;
        
        ArrayList ms = enemy.getMissiles();
        
        Missile M;
        for(int i = 0; i < ms.size(); i++) {
            M = (Missile)ms.get(i);
            if(M.isVisible())
                M.move();
            else
                ms.remove(i);
        }
        Alien A;
        for(int i = 0; i < aliens.size(); i++) {
            A = (Alien)aliens.get(i);
            if(A.isVisible())
                A.move();
            else
                aliens.remove(i);
        }
        
        enemy.move();
        checkCollision();
        repaint();
    }
    
    public void checkCollision() {
        Rectangle r3 = enemy.getBounds();
        Alien A;
        for(int j = 0; j < aliens.size(); j++) {
            A = (Alien)aliens.get(j);
            Rectangle r2 = A.getBounds();
            
            if(r3.intersects(r2)) {
                enemy.setVisible(false);
                A.setVisible(false);
                inGame = false;
            }
        }
        
        ArrayList<Missile> ms = enemy.getMissiles();
        
        Missile M;
        for(int i = 0; i < ms.size(); i++) {
            M = (Missile)ms.get(i);
            
            Rectangle r1 = M.getBounds();
            
            for(int j = 0; j < aliens.size(); j ++) {
                A = (Alien)aliens.get(j);
                Rectangle r2 = A.getBounds();
                
                if(r1.intersects(r2)) {
                    M.setVisible(false);
                    A.setVisible(false);
                }
            }
        }
    }
    
     private class TAdapter extends KeyAdapter {
        public void keyReleased(KeyEvent e) {
            enemy.keyReleased(e);
        }
        
        public void keyPressed(KeyEvent e) {
            enemy.keyPressed(e);
        }
    }
    
 
}
