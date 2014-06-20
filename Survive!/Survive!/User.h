#ifndef USER_H_
#define USER_H_

#include "stdafx.h"
#include <commdlg.h>

class User{
public:
	static bool faceLeft;
	static bool shoot_Gun;
	int movespeed;
	static RECT Body;
	static RECT Bullet;
	User();
	void drawUser(HWND hwnd, HDC hdc);
	void moveUserLeft();
	void moveUserRight();
	void shootGun();

};

extern int bl, bt, br, bb;
#endif