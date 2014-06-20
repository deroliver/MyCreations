#ifndef HOUSE_H_
#define HOUSE_H_

#include "stdafx.h"
#include <CommDlg.h>
#include "Zombies.h"
#include "Board.h"
#include <vector>
using namespace std;


class House{
private:
	vector<Zombie> zombies;
	vector<Board> window1;
	vector<Board> window2;
	vector<Board> window3;
	vector<Board> window4;
	int left, right, secFloor, firFloor;
public:
	House();
	void drawHouse(HWND hwnd, HDC hdc) const;
	void drawHouseBoards(HWND hwnd, HDC hdc) const;
};


#endif