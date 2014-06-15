#ifndef HOUSE_H_
#define HOUSE_H_

#include "stdafx.h"
#include <CommDlg.h>
#include "Zombies.h"
#include <vector>
using namespace std;


class House{
private:
	int height;
	int width;
	vector<Zombie> zombies;
	int window1b;
	int window2b;
	int window3b;
	int window4b;
public:
	House();
	void drawHouse(HWND hwnd, HDC hdc) const;
	void drawHouseBoards(HWND hwnd, HDC hdc) const;
	
	

};

void drawWindow1Boards3(HWND hwnd, HDC hdc);

#endif