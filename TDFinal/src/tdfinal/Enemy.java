package tdfinal;

import java.awt.*;

/**
 * The class that defines the enemies
 * @author Derik
 *
 */
public class Enemy extends Rectangle {
	public int xCoord, yCoord;
	public int eWalk;
	public int up = 0, down = 1, right = 2, left = 3;
	public int dir = down;
	public int Size = 28;
	public int health;
	public int healthCushion = 2, healthHeight = 5;
	public int enemyID;
			
	
	public boolean isAlive = false;
	public boolean hasUp = false;
	public boolean hasDown = false;
	public boolean hasRight = false;
	public boolean hasLeft = false;
	public boolean one = true;
	
	public Image image;
	
	/**
	 * Creates an enemy at the position of the first block with a TrackID corresponding to the ID of a track piece
	 * @param ID
	 */
	public void Create(int ID) {
		// Spawns the enemy at the location of the first piece of track across the top of the map layout
		for(int i = 0; i < Screen.manager.world.length; i++) {
			if(Screen.manager.world[0][i].trackID == Values.groundTrack) {
				setBounds(Screen.manager.world[0][i].x, Screen.manager.world[0][i].y, Size, Size);
			xCoord = i; // Initializes the c and y coordinates relative to the map's layout
			yCoord = 0;
			}
			enemyID = ID;
		}

		this.health = Size;
		isAlive = true;
		image = Screen.tileset_soldier[0];
	}
	
	/**
	 * Animates the enemy walking
	 */
    public void Animate() {
    	if(dir == down)	{
    		if(one) {
				image = Screen.tileset_soldier[1];
				one = false;
			}
			else {
				image = Screen.tileset_soldier[0];
				one = true;
			}
			
		}
		else if(dir == right) {
			if(one) {
				image = Screen.tileset_soldier[3];
				one = false;
			}
			else {
				image = Screen.tileset_soldier[2];
				one = true;
			}
		}
		else if(dir == up) {
			if(one) {
				image = Screen.tileset_soldier[7];
				one = false;
			}
			else {
				image = Screen.tileset_soldier[6];
				one = true;
			}
		}
		else if(dir == left) {
			if(one) {
				image = Screen.tileset_soldier[5];
				one = false;
			}
			else {
				image = Screen.tileset_soldier[4];
				one = true;
			}
		}
    }
	
	/**
	 * Removes the ennym from the screen
	 */
	public void deleteEnemy() {
		dir = down;
		isAlive = false;
		eWalk = 0;
		xCoord = 0;
		yCoord= 0;

	}
	

	// Variable to kee track of the animation speed as well as the move speed
	public int animateFrame = 0, animationSpeed = 300;
	public int moveFrame = 0;
	public static int moveSpeed = 18;
	
	/**
	 * The function that moves the enemy 
	 */
	public void move() {		
		if(animateFrame >= animationSpeed) {
			Animate();
			animateFrame = 0;
		}
		else
			animateFrame += 1;
		
		// Position relative to screen - Moves the image		
		if(moveFrame >= moveSpeed) { 
			if(dir == down)	{
				y += 1;
				hasDown = true;
			}
			else if(dir == right) {
				x += 1;
				hasRight = true;
			}
			else if(dir == up) {
				y -= 1;
				hasUp = true;
			}
			else if(dir == left) {
				x -= 1;
				hasLeft = true;
			}
			
			eWalk += 1;
			
			// Keeps track of coordinates relative to the map's layout
			if(eWalk == Screen.manager.blockSize) { // If the enemy has gone the distance of a block 
				if(dir == down)	 // Increment the coordinate values 
					yCoord += 1;
				else if(dir == right)
					xCoord += 1;
				else if(dir == up)
					yCoord -= 1;
				else if(dir == left)
					xCoord -= 1;
				
			// Changing Direction - Follows the path which is determined by the trackID	
			if(!hasRight){ // If the enemy is not already moving right
					// Checks if there is a track piece to the right
				if(Screen.manager.world[yCoord][xCoord - 1].trackID == Values.groundTrack){
					dir = left;
						image = Screen.tileset_soldier[4];
				}
			}				
					
			if(!hasDown){  // If the enemy is not already moving down					
				// Checks if there is a track piece downwards
				if(Screen.manager.world[yCoord - 1][xCoord].trackID == Values.groundTrack){
					dir = up;
					image = Screen.tileset_soldier[6];
				}
			}
					
			if(!hasUp){	  // If the enemy is not already moving up				
				// Checks if there is a track piece upwards
				if(Screen.manager.world[yCoord + 1][xCoord].trackID == Values.groundTrack){
					dir = down;
					image = Screen.tileset_soldier[0];
				}
			}
					
			if(!hasLeft){ // If the enemy is not already moving left				
				// Checks if there is a track piece to the left
				if(Screen.manager.world[yCoord][xCoord + 1].trackID == Values.groundTrack){					
					dir = right;
					image = Screen.tileset_soldier[2];
				}
						
			// The enemy reaches the SpaceShip
			if(Screen.manager.world[yCoord][xCoord].trackID == Values.Ship) {
				Screen.health -= 1;
				deleteEnemy();
			}
			}
					
				// Reset all direction flags as well as the integer used to track the enemies coordinates
				hasUp = false;
				hasDown = false;
				hasRight = false;
				hasLeft = false;
				eWalk = 0;
			}				
			moveFrame = 0;
		}				
		else
			moveFrame += 1;						
	}
	
	/**
	 * When the enemy loses health
	 * @param rate The rate the health should be subtracted
	 */
	public void loseHealth(int rate){
		health -= rate;
			checkDeath();		
	}
	
	/**
	 * Checks if the enemy is dead
	 */
	public void checkDeath(){
		if(health < 1) {
			deleteEnemy();
			Screen.killed += 1;
			Screen.killCount += 1;
		}
	}
	
	/**
	 * Returns if the enemy is dead or not
	 * @return If the enemy is dead or not
	 */
	public boolean isDead(){
		if(isAlive){
			return false;
		}
		else {
			return true;
		}
	}
	
	/**
	 * Function to draw the enemy to the screen
	 * @param g The Graphics to draw to
	 */
	public void draw(Graphics g) {
		
		if(isAlive) {
			Graphics2D g2D = (Graphics2D)g;
			g2D.drawImage(image, x, y, width, height, null);
			
			g2D.setColor(Color.white);
			g2D.fillRect(x, y - (healthCushion + healthHeight), width, healthHeight);
			
			g2D.setColor(Color.GREEN);
			g2D.fillRect(x, y - (healthCushion + healthHeight), health, healthHeight);
			
			g2D.setStroke(new BasicStroke(2));
			g2D.setColor(Color.black);
			g2D.drawRect(x, y - (healthCushion + healthHeight), health - 1, healthHeight - 1);
		}
		
	}
}
