/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdfinal;

import java.awt.*;
/**
 * Block objects make up the layout of the screen
 * @author Derik
 */
public class Block extends Rectangle {
	public Rectangle towerRange;
	
	public int towerRangeSize = 190;// The range of the towers
    public int trackID; // The ID for the track
    public int fieldID; // The ID for the field
    public int shotEnemy = -1; // The ID of the enemy the tower is shooting at
    
    public int healthFrame = 0; // Keeps track of negating health from the enemy
    public static int healthTime = 230;
    
    public boolean isShooting = false; // Is the tower shooting?
    
    /**
     * Constructor for the Block object
     * @param x The x position
     * @param y The y position
     * @param width The width
     * @param height The height
     * @param trackID The ID for the track
     * @param fieldID The ID for the field
     */
    public Block(int x, int y, int width, int height, int trackID, int fieldID) {
        setBounds(x, y, width, height);
        towerRange = new Rectangle(this.x - (towerRangeSize/2), this.y - (towerRangeSize/2), width + (towerRangeSize), height + (towerRangeSize));
        this.trackID = trackID;
        this.fieldID = fieldID;
    }
    
    /**
     * The drawing function for the Block object
     * @param g The Graphics object to draw to
     */
    public void draw(Graphics g) {
        if(trackID == Values.groundTrack) // If the trackID is equal to the values for a track piece
    		g.drawImage(Screen.track, x, y, width, height, null);
        //g.drawRect(x, y, width, height);
        g.drawImage(Screen.tileset_towers[fieldID], x, y, width, height, null);                         
    }
    
    /**
     * The function which calculates whether the tower is shooting, and which enemy it is shootng at
     */
    public void physic(){
    	if(shotEnemy != -1 && towerRange.intersects(Screen.enemies[shotEnemy])){
			isShooting = true;
		}
    	else
    		isShooting = false;
    	
    	if(!isShooting){
	    	if(fieldID == Values.redLaserTower || fieldID == Values.blueLaserTower || fieldID == Values.goldLaserTower) {
		    	for(int i = 0; i < Screen.enemies.length; i++) {
		    		if(Screen.enemies[i].isAlive){
		    			if(towerRange.intersects(Screen.enemies[i])){
		    				isShooting = true;
		    				shotEnemy = i;
		    			}
		    		}
		    	}
	    	}
    	}
    	if(isShooting){
    		if(healthFrame >= healthTime){
    			if(fieldID == Values.redLaserTower)
    				Screen.enemies[shotEnemy].loseHealth(1);
    			else if(fieldID == Values.blueLaserTower)
    				Screen.enemies[shotEnemy].loseHealth(2);
    			else if(fieldID == Values.goldLaserTower)
    				Screen.enemies[shotEnemy].loseHealth(2);
    			healthFrame = 0;
    		}
    		else {
    			healthFrame += 1;
    		}
    		
    		
    		if(Screen.enemies[shotEnemy].isDead()){
    			getMoney(Screen.enemies[shotEnemy].enemyID);
    			isShooting = false;
    			shotEnemy = -1;
    		}
    	}
    }
    
    /**
     * Adds money to the total money
     * @param enemyID The type of enemy - Could add different types of enemies which reward more money
     */
    public void getMoney(int enemyID){
    	Screen.money += Values.deathReward[enemyID];	
    }
    
    /**
     * The function to draw the line from the tower to the enemy
     * @param g The Graphics object to draw to
     */
    public void Attack(Graphics g){
    	Graphics2D g2D = (Graphics2D)g;
	    if(isShooting){
	    	g2D.setStroke(new BasicStroke(3));
	    	if(fieldID == Values.redLaserTower)
	   	    	g2D.setColor(Color.red);
	    	else if(fieldID == Values.blueLaserTower)
	    		g2D.setColor(Color.blue);
	    	else if(fieldID == Values.goldLaserTower)
	    		g2D.setColor(Color.yellow);
	    	g2D.drawLine(x + (width/2), y + (height/2), Screen.enemies[shotEnemy].x + ((Screen.enemies[shotEnemy].width)/2) , Screen.enemies[shotEnemy].y + ((Screen.enemies[shotEnemy].height/2)));
	    }
    }    	
}


