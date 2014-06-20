#include "stdafx.h"
#include <commdlg.h>

class Zombie{
private:
	int xpos;//Based on the head of the zombie
	int ypos;//Based on the head of the zombie
public:
	Zombie(int x, int y);
	void drawZombieInWindow(HWND hwnd, HDC hdc);
	void drawZombieInHouse(HWND hwnd, HDC hdc);
	void moveZombie();
};