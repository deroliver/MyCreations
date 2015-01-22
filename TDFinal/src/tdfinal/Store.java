/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdfinal;

import java.awt.*;
import javax.swing.ImageIcon;
/**
 *
 * @author Derik
 */
public class Store {
    public static int shopWidth = 8;
    public static int buttonSize = 31;
    public static int Space = 5;
    public static int icon = 45;
    
    public Rectangle[] button = new Rectangle[shopWidth];
    public Rectangle buttonHealth;
    public Rectangle money; 
    
    public Image map;
    
    
    public Store() {
        define();
    }
    
    public void define() {
        map = new ImageIcon("res/shop.png").getImage();
        for(int i = 0; i < button.length; i++) {
            button[i] = new Rectangle(31 + ((31 + Space) * i), (Screen.manager.world[Screen.manager.worldHeight - 1][0].y), buttonSize, buttonSize);
        }
        
        buttonHealth = new Rectangle(50, 685, icon, icon);
        money = new Rectangle(200, 685, icon, icon);
    }
    
    public void draw(Graphics g) {
        g.drawImage(map, 28, 668, null);
        for(int i = 0; i < button.length; i++) {
            if(button[i].contains(Screen.ms)) {
                g.setColor(new Color(0, 0, 0, 255));
                g.fillRect(button[i].x, button[i].y, button[i].width, button[i].height);
            }
            g.drawImage(Screen.tileset_buttons[0],button[i].x, button[i].y, button[i].width, button[i].height, null);
        }
        g.setColor(new Color(0, 0, 0, 255));
        g.drawImage(Screen.tileset_buttons[2], buttonHealth.x, buttonHealth.y, buttonHealth.width, buttonHealth.height, null);
        g.drawImage(Screen.tileset_buttons[1], money.x, money.y, money.width, money.height, null);
    }
}
