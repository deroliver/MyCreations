package tdfinal;

import java.awt.*;

public class Tower extends Rectangle {
	public int xCoord, yCoord;
	public Image image;
	
	public Tower(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		xCoord = x/31;
		yCoord = y/31;
		image = Screen.tileset_buttons[4];
	}
	
	public void draw(Graphics g) {
		g.drawImage(image, x, y, width, height, null);	
	}
}
