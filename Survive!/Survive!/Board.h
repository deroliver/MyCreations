#ifndef BOARD_H_
#define BOARD_H_

#include "stdafx.h"
#include <CommDlg.h>

class Board{
	int x, y, width, height;
	void drawBoard(HWND hwnd, HDC hdc);
	Board(int, int, int, int);
};
#endif