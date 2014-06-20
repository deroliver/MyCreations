#include "stdafx.h"
#include "Board.h"

Board::Board(int x, int y, int width, int height){
	this->x = x;
	this->y = y;
	this->width = width;
	this->height = height;
}

void Board::drawBoard(HWND hwnd, HDC hdc){
	Pen pen(Color(255, 0, 0, 5));
	SolidBrush brush(Color(255, 150, 85, 45));
	Graphics R1(hdc);
	
	R1.FillRectangle(&brush, x, y, width, height);


	

}