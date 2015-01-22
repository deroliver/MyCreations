/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Simple_Java_Game;

import java.awt.Image;
import java.awt.event.*;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.awt.Rectangle;

/**
 *
 * @author doliver17
 */
public class Enemy {
    private String craft = "craft.png";
    
    private int dx = 0;
    private int dy = 0;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean visible;
    private Image image;
    
    private ArrayList<Missile> missiles;
    
    private final int CRAFT_SIZE = 20;
    
    public Enemy() {
        ImageIcon i = new ImageIcon(this.getClass().getResource(craft));
        image = i.getImage();
        missiles = new ArrayList();
        visible = true;
        width = image.getWidth(null);
        height = image.getHeight(null);
        x = 40;
        y = 40;
    }
    
    public void move() {
        x += dx;
        y += dy;
        
        if(x < 1)
            x = 1;
        if(y < 1)
            y = 1;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public Image getImage() {
        return image;
    }
    
    public ArrayList getMissiles() {
        return missiles;
    }
    
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    public boolean isVisible() {
        return visible;
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_SPACE)
            fire();
        
        if(key == KeyEvent.VK_LEFT) 
            dx = -1;
        
        if(key == KeyEvent.VK_RIGHT)
            dx = 1;
        
        if(key == KeyEvent.VK_UP) 
            dy = -1;
        
        if(key == KeyEvent.VK_DOWN)
            dy = 1;        
    }
    
    public void fire() {
        if(missiles.size() < 10)
            missiles.add(new Missile(x + CRAFT_SIZE, y + CRAFT_SIZE/2));
    }
    
     public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_LEFT) 
            dx = 0;
        
        if(key == KeyEvent.VK_RIGHT) 
            dx = 0;
        
        if(key == KeyEvent.VK_UP) 
            dy = 0;
        
        if(key == KeyEvent.VK_DOWN)
            dy = 0;
        
    }
    
    
}
