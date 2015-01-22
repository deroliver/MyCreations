/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Simple_Java_Game;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
/**
 *
 * @author doliver17
 */
public class Alien {
    private String craft = "alien.png";
    
    private int x, y;
    private int width, height;
    private boolean visible;
    private Image image;
    
    public Alien(int x, int y) {
        ImageIcon i = new ImageIcon(this.getClass().getResource(craft));
        image = i.getImage();
        width = image.getWidth(null);
        height = image.getHeight(null);
        visible = true;
        this.x = x;
        this.y = y;
    }
    
    public void move() {
        if(x < 0)
            x = 400;
        x -= 1;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public boolean isVisible() {
        return visible;
    }
    
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    public Image getImage() {
        return image;
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height); 
    }
}
