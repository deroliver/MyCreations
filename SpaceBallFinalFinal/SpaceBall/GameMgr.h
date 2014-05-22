#include "stdafx.h"
#include <CommDlg.h>





void DrawBlocks(HWND hwnd, HDC hdc);
void DrawEnclosure(HWND hwnd, HDC hdc);
void GameOver(HWND hwnd);
void DrawBlocks(HWND hwnd, HDC hdc);
void drawStars(HWND hwnd, HDC hdc); 
void fillArray();
void init();
void DrawTheBlock(HWND hwnd, HDC hdc);
void drawUser(HWND hwnd, HDC hdc);
void moveLeft();
void moveRight();
void gameOver();
void drawTitle(HWND hwnd, HDC hdc);
bool supergameover();
void messageBox(HWND hwnd, HDC hdc);

const int t = 35;
const int b = 60;


extern int top;
extern int bottom;
extern int fall;
extern bool gameover;


class Block{
public:
	Block(int l, int t, int r, int b)
	{
		left = l; top = t; right = r; bottom = b;
	}
	void fall(int f){
		bottom += f;
		top += f;
	}
	void draw(HWND hwnd, HDC hdc){
		Rectangle(hdc, left, top, right, bottom);
		}
private:
	int left, top, right, bottom;
};
