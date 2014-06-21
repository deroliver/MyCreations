#ifndef ZOMBIE_H_
#define ZOMBIE_H_
#include "stdafx.h"
#include "Figure.h"
#include <commdlg.h>




class Zombie: public Figure{
public:
	int zombiePos;
	void runWhenAddingZombie();
	static bool faceLeft;
	int movespeed;
	Zombie();
	void drawZombieInWindow(HWND hwnd, HDC hdc);
	void drawFigure(HWND hwnd, HDC hdc);
};


const int l1 = 140;
const int r1 = 142;
const int l2 = 600;
const int r2 = 602;
extern int move;

#endif