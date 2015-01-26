/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdfinal;

import java.awt.event.*;
import java.awt.*;

import tdfinal.Screen.STATE;
/**
 * Class used to handle mouse movement and clicks
 * @author Derik
 */
public class KeyHandler implements MouseMotionListener, MouseListener {

	
    public void mouseDragged(MouseEvent e) {     
    	Screen.ms = new Point(e.getX(), e.getY() - 25);   
    	}

    
    public void mouseMoved(MouseEvent e) {             
    	Screen.ms = new Point(e.getX(), e.getY() - 25);   
    }

    
    public void mouseClicked(MouseEvent e) {
        
    }

    
    public void mousePressed(MouseEvent e) {
    	if(Screen.state == STATE.MENU)
    		Menu.Click(e.getButton());
    	else if(Screen.state == STATE.GAME)
        	Screen.store.Click(e.getButton());
    	else if(Screen.state == STATE.POSTGAME)
    		PostGame.Click(e.getButton());    
    	}

    
    public void mouseReleased(MouseEvent e) {
        
    }

    
    public void mouseEntered(MouseEvent e) {
        
    }

    
    public void mouseExited(MouseEvent e) {
        
    }
    
}
