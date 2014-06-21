#ifndef HOUSE_H_
#define HOUSE_H_

#include "stdafx.h"
#include <CommDlg.h>
#include "Zombie.h"
#include <vector>
using namespace std;


class House{
public:
	vector<Zombie*> zombies;
	House();
	void drawZombiesInHouse(HWND hwnd, HDC hdc);
	void addZombieToHouse();
	void drawZombiesInWindow(HWND hwnd, HDC hdc);
	bool operator+(Zombie*&);
};


#endif