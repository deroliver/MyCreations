#ifndef USER_H_
#define USER_H_

#include "stdafx.h"
#include "Figure.h"
#include <commdlg.h>

class User: public Figure {
public:
	User();
	static RECT Bullet;
	static bool shoot_Gun;
	void shootGun(HWND hwnd, HDC hdc);
	void shoot_GunBool();

};

#endif