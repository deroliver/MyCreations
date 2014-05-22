#include "stdafx.h"
#include "gamemgr.h"
#include <string>
#include <ctime>
using std::string;
int rColor(string, string);
int gColor(string, string);
int bColor(string, string);

int f = 2;
int top = t, bottom = b, fall = f;
const int LEVEL = 5;
const int SUBLEVEL = 4;
static int c = 0, n = 0;
int move = 25;
bool gameover = false;

RECT fallingBlocks[40] = {};

static RECT r1 = { 115, 445, 135, 470 };

static string Level[LEVEL][SUBLEVEL] = {
	{ "Level 1", "1", "2", "3" },
	{ "Level 2", "1", "2", "3" },
	{ "Level 3", "1", "2", "3" },
	{ "Level 4", "1", "2", "3" },
	{ "Level 5", "1", "2", "3" },
};


//init: Gets placed in InitInstance, fillArray is placed in here so it gets ran when the program starts.
void init(){
	fillArray();
}


//DrawEnclosure: Draws the games background, updates depending on int variable 'c', which is
// updated everytime a block hits the bottom.
void DrawEnclosure(HWND hwnd, HDC hdc) {
	int r = 255, g = 255, b = 255;
	switch (c){//Switch on 'c', determines the color of the background, sends values in array Level to rColor, gColor, and bColor.
	case 0: { r = rColor(Level[0][0], Level[0][1]), g = gColor(Level[0][0], Level[0][1]), b = bColor(Level[0][0], Level[0][1]); break; }
	case 1: { r = rColor(Level[0][0], Level[0][2]), g = gColor(Level[0][0], Level[0][2]), b = bColor(Level[0][0], Level[0][2]); break; }
	case 2: { r = rColor(Level[0][0], Level[0][3]), g = gColor(Level[0][0], Level[0][3]), b = bColor(Level[0][0], Level[0][3]); break; }
	
	case 3: { r = rColor(Level[1][0], Level[1][1]), g = gColor(Level[1][0], Level[1][1]), b = bColor(Level[1][0], Level[1][1]); break; }
	case 4: { r = rColor(Level[1][0], Level[1][2]), g = gColor(Level[1][0], Level[1][2]), b = bColor(Level[1][0], Level[1][2]); break; }
	case 5: { r = rColor(Level[1][0], Level[1][3]), g = gColor(Level[1][0], Level[1][3]), b = bColor(Level[1][0], Level[1][3]); break; }
	
	case 6: { r = rColor(Level[2][0], Level[2][1]), g = gColor(Level[2][0], Level[2][1]), b = bColor(Level[2][0], Level[2][1]); break; }
	case 7: { r = rColor(Level[2][0], Level[2][2]), g = gColor(Level[2][0], Level[2][2]), b = bColor(Level[2][0], Level[2][2]); break; }
	case 8: { r = rColor(Level[2][0], Level[2][3]), g = gColor(Level[2][0], Level[2][3]), b = bColor(Level[2][0], Level[2][3]); break; }
	
	case 9:  { r = rColor(Level[3][0], Level[3][1]), g = gColor(Level[3][0], Level[3][1]), b = bColor(Level[3][0], Level[3][1]); break; }
	case 10: { r = rColor(Level[3][0], Level[3][2]), g = gColor(Level[3][0], Level[3][2]), b = bColor(Level[3][0], Level[3][2]); break; }
	case 11: { r = rColor(Level[3][0], Level[3][3]), g = gColor(Level[3][0], Level[3][3]), b = bColor(Level[3][0], Level[3][3]); break; }
	
	case 12: { r = rColor(Level[4][0], Level[4][1]), g = gColor(Level[4][0], Level[4][1]), b = bColor(Level[4][0], Level[4][1]); break; }
	case 13: { r = rColor(Level[4][0], Level[4][2]), g = gColor(Level[4][0], Level[4][2]), b = bColor(Level[4][0], Level[3][2]); break; }
	case 14: { r = rColor(Level[4][0], Level[4][3]), g = gColor(Level[4][0], Level[4][3]), b = bColor(Level[4][0], Level[4][3]); break; }

	default: c = 0; {//resets 'c' to 0 once it gets past 14, so colors start over.
		break;
	}

}
	
	HPEN pen = CreatePen(PS_SOLID, 3, RGB(32, 24, 64));//Creats a new pen to draw the enclosure.
	HBRUSH brush = CreateSolidBrush(RGB(r, g, b));//Uses the given R G and B values from the switch statement on 'c' to determine the color.
	HGDIOBJ penOld, brushOld;

	penOld = SelectObject(hdc, pen);
	brushOld = SelectObject(hdc, brush);
	Rectangle(hdc, 10, 10, 255, 475);//Paints the rectangle enclosure.

	SelectObject(hdc, brushOld);
	SelectObject(hdc, penOld);

	DeleteObject(brush);//Deletes the brush we created.
	DeleteObject(pen);
	
	drawStars(hwnd, hdc);//Runs drawStars, which draws several small ellipses on the screen.
}


//fillArray: Is in init, gets ran when the program starts up.
void fillArray(){
	for (int i = 0; i < 40; i++){ //Fills array with -1 values initially.
		fallingBlocks[i] = { -1, -1, -1, -1};
	}
}


//gameOver: Gets ran when the Player selects NewGame in the menu.
void gameOver(){
	gameover = false; //Sets gameover back to false so DrawBlocks will run.
	for (int i = 0; i < 40; i++){//Resets all RECT in fallingBlocks.
		fallingBlocks[i] = { -1, -1, -1, -1 };
	}
	n = 0;
}


//DrawBlocks: Draws the falling blocks on the screen,
//and checks for collision of the falling and player's block.
void DrawBlocks(HWND hwnd, HDC hdc){
	if (gameover == true)//If gameover = true, don't draw anymore blocks.
		return;
	if (rand() % 10 == 0){//Times the falling of the blocks, so they don't fall in a line.
		for (int i = 0; i < 40; i++){ //Fills the RECT array with values to draw with.
			if (fallingBlocks[i].left == -1){
				fallingBlocks[i].left = (rand() % 240);//Random horizantal value
				fallingBlocks[i].top = top;
				fallingBlocks[i].right = (fallingBlocks[i].left + 20);
				fallingBlocks[i].bottom = bottom;
				break;
			}
		}
	}
	for (int j = 0; j < 40; j++){//Loops through the RECT array fallingBlocks, and paints them on the screen.
		if (fallingBlocks[j].left != -1){
			Rectangle(hdc, fallingBlocks[j].left, fallingBlocks[j].top, fallingBlocks[j].right, fallingBlocks[j].bottom);
			fallingBlocks[j].top += f;
			fallingBlocks[j].bottom += f;
			if (fallingBlocks[j].bottom >= r1.top && ((fallingBlocks[j].left >= (r1.left + 19) && fallingBlocks[j].left <= r1.right)//Check for collision with player's block.
				|| (fallingBlocks[j].right >= r1.left && fallingBlocks[j].right <= (r1.right + 20)))){
				gameover = true;//If collision then gameover = true: will stop drawing blocks
				std::string score = std::to_string(n);//converts the int 'n' which counts the blocks that fall to a string.
				MessageBoxA(NULL, score.c_str(), "Well You Lost, Score:" , MB_OK);//Displays a you lose message, as well as a score.
				for (int i = 0; i < 40; i++){//Resets all RECT in fallingBlocks to -1.
					fallingBlocks[i] = { -1, -1, -1, -1 };
				}
			}
			else if (fallingBlocks[j].bottom > 470){//If no collision, reset all values in fallingBlocks to -1 one they reach the bottom.
				fallingBlocks[j].left = fallingBlocks[j].top = fallingBlocks[j].right = fallingBlocks[j].bottom = -1;
				c++;
				n++;
			}
		}
	}
}
	


//drawTitle: Displays the level on top of the screen
//and adjusts the speed depending on the varialbe 'n'.
void drawTitle(HWND hwnd, HDC hdc){
	if (n < 10){ //If statement on variable 'n' that gets updated everytime a block hits the bottom.
		TextOut(hdc, 100, 10, _T("Level 1"), 9); //Displays the according Level, dependent on 'n'.
		f = 5; //Changes the falling speed of the blocks, depending on the variable 'n'.
	}
	else if (n >= 10 && n < 20){
		TextOut(hdc, 100, 10, _T("Level 2"), 9);
		f = 5;
	}
	else if (n >= 20 && n < 30){
		TextOut(hdc, 100, 10, _T("Level 3"), 9);
		f = 5;
	}
	else if (n >= 30 && n < 40){
		TextOut(hdc, 100, 10, _T("Level 4"), 9);
		f = 6;
	}
	else if (n >= 40 && n < 50){
		TextOut(hdc, 100, 10, _T("Level 5"), 9);
		f = 7;
	}
	else if (n >= 50 && n < 60){
		TextOut(hdc, 100, 10, _T("Level 6"), 9);
		f = 8;
	}
	else if (n >= 60 && n < 70){
		TextOut(hdc, 100, 10, _T("Level 7"), 9);
		f = 9;
	}
	else if (n >= 70 && n < 80){
		TextOut(hdc, 100, 10, _T("Level 8"), 9);
		f = 10;
	}
	else if (n >= 80 && n < 90){
		TextOut(hdc, 100, 10, _T("Level 9"), 9);
		f = 11;
	}
	else if (n >= 90 && n < 100){
		TextOut(hdc, 100, 10, _T("Level 10"), 9);
		f = 13;
	}
	else if (n >= 100 && n < 110){
		TextOut(hdc, 100, 10, _T("Level 11"), 9);
		f = 15;
	}
	else if (n >= 110 && n < 120){
		TextOut(hdc, 100, 10, _T("Level 12"), 9);
		f = 17;
	}
	else if (n >= 120 && n < 130){
		TextOut(hdc, 100, 10, _T("Level 13"), 9);
		f = 19;
	}
	else if (n >= 130 && n < 140){
		TextOut(hdc, 100, 10, _T("Level 14"), 9);
		f = 21;
	}
	else if (n >= 140 && n < 150){
		TextOut(hdc, 100, 10, _T("Level 15"), 9);
		f = 23;
	}
	else if (n >= 150 && n < 160){
		TextOut(hdc, 100, 10, _T("Level 16"), 9);
		f = 25;
	}
	else if (n >= 160 && n < 170){
		TextOut(hdc, 100, 10, _T("Level 17"), 9);
		f = 27;
	}
	else if (n >= 170 && n < 180){
		TextOut(hdc, 100, 10, _T("Level 18"), 9);
		f = 29;
	}
	else if (n >= 180 && n < 190){
		TextOut(hdc, 100, 10, _T("Level 19"), 9);
		f = 29;	
	}
	else if (n >= 190 && n < 200){
		TextOut(hdc, 100, 10, _T("Level 20"), 9);
		f = 31;
	}
	else if (n > 200){
		TextOut(hdc, 100, 10, _T("WOW"), 9);
		f = 35;
	}
}


//rColor: Takes in two string variable, and outputs the Red
//color code to paint the background of the screen.
int rColor(string x, string y){
	int R = 0;
	if (x == "Level 1" && y == "1")//Purple
		R = 72;
	else if (x == "Level 1" && y == "2")
		R = 48;
	else if (x == "Level 1" && y == "3")
		R = 72;
	
	else if (x == "Level 2" && y == "1")//Blue
		R = 96;
	else if (x == "Level 2" && y == "2")
		R = 27;
	else if (x == "Level 2" && y == "3")
		R = 120;
	
	else if (x == "Level 3" && y == "1")//Green
		R = 8;
	else if (x == "Level 3" && y == "2")
		R = 170;
	else if (x == "Level 3" && y == "3")
		R = 156;
	
	else if (x == "Level 4" && y == "1")//Yellow
		R = 71;
	else if (x == "Level 4" && y == "2")
		R = 178;
	else if (x == "Level 4" && y == "3")
		R = 181;
	
	else if (x == "Level 5" && y == "1")//Red
		R = 83;
	else if (x == "Level 5" && y == "2")
		R = 108;
	else if (x == "Level 5" && y == "3")
		R = 250;

	return R;
}


//gColor: Takes in two string variable, and outputs the Green
//color code to paint the background of the screen.
int gColor(string x, string y){
	int G = 0;
	if (x == "Level 1" && y == "1")//Purple
		G = 96;
	else if (x == "Level 1" && y == "2")
		G = 24;
	else if (x == "Level 1" && y == "3")
		G = 48;
	
	else if (x == "Level 2" && y == "1")//Blue
		G = 48;
	else if (x == "Level 2" && y == "2")
		G = 27;
	else if (x == "Level 2" && y == "3")
		G = 96;
	
	else if (x == "Level 3" && y == "1")//Green
		G = 6;
	else if (x == "Level 3" && y == "2")
		G = 134;
	else if (x == "Level 3" && y == "3")
		G = 144;
	
	else if (x == "Level 4" && y == "1")//Yellow
		G = 52;
	else if (x == "Level 4" && y == "2")
		G = 91;
	else if (x == "Level 4" && y == "3")
		G = 201;
	
	else if (x == "Level 5" && y == "1")//Red
		G = 75;
	else if (x == "Level 5" && y == "2")
		G = 82;
	else if (x == "Level 5" && y == "3")
		G = 169;

	return G;
}


//bColor: Takes in two string variable, and outputs the Blue
//color code to paint the background of the screen.
int bColor(string x, string y){
	int B = 0;
	if (x == "Level 1" && y == "1")//Purple
		B = 198;
	else if (x == "Level 1" && y == "2")
		B = 48;
	else if (x == "Level 1" && y == "3")
		B = 24;
	
	else if (x == "Level 2" && y == "1")//Blue
		B = 48;
	else if (x == "Level 2" && y == "2")
		B = 177;
	else if (x == "Level 2" && y == "3")
		B = 72;
	
	else if (x == "Level 3" && y == "1")//Green
		B = 69;
	else if (x == "Level 3" && y == "2")
		B = 154;
	else if (x == "Level 3" && y == "3")
		B = 156;
	
	else if (x == "Level 4" && y == "1")//Yellow
		B = 54;
	else if (x == "Level 4" && y == "2")
		B = 81;
	else if (x == "Level 4" && y == "3")
		B = 220;
	
	else if (x == "Level 5" && y == "1")//Red
		B = 86;
	else if (x == "Level 5" && y == "2")
		B = 81;
	else if (x == "Level 5" && y == "3")
		B = 0;

	return B;
}


//drawStars: Draws several small ellipses on the screen to emulate stars.
void drawStars(HWND hwnd, HDC hdc){
	Ellipse(hdc, 50, 100, 55, 105);
	Ellipse(hdc, 150, 250, 155, 255);
	Ellipse(hdc, 200, 125, 205, 130);
	Ellipse(hdc, 30, 400, 35, 405);
	Ellipse(hdc, 60, 200, 65, 205);
	Ellipse(hdc, 240, 300, 245, 305);
	Ellipse(hdc, 175, 420, 180, 425);
	Ellipse(hdc, 125, 375, 130, 380);
	Ellipse(hdc, 80, 350, 85, 355);
	Ellipse(hdc, 50, 300, 55, 305);
	Ellipse(hdc, 140, 40, 145, 45);
	Ellipse(hdc, 155, 105, 150, 110);
}


//drawUser: Draws the player's block on the screen.
void drawUser(HWND hwnd, HDC hdc){
	RoundRect(hdc, r1.left, r1.top, r1.right, r1.bottom, 2, 2);
}


//moveLeft: Moves the player's block left.
void moveLeft(){
	r1.left -= move;
	r1.right -= move;
	if (r1.left < 20){
	r1.left = 20;
	r1.right = 40;
	}
}


//moveRight: Moves the player's block right.
void moveRight(){
	r1.left += move;
	r1.right += move;
	if (r1.right > 245){
	r1.right = 245;
	r1.left = 225;
	}
}





