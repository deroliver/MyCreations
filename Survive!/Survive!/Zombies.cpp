#include "stdafx.h"
#include "Zombies.h"

Zombie::Zombie(int x, int y){
	xpos = x;
	ypos = y;

}

void Zombie::drawZombieInWindow(HWND hwnd, HDC hdc){
	if (xpos > 150 && xpos < 250 && ypos > 315 && ypos < 430){
		HBRUSH brush1 = CreateSolidBrush(RGB(0, 102, 0));
		HGDIOBJ brushOld1;

		brushOld1 = SelectObject(hdc, brush1);


		Ellipse(hdc, 180, 353, 220, 393);

		SelectObject(hdc, brushOld1);
		DeleteObject(brush1);


		HBRUSH brush = CreateSolidBrush(RGB(204, 0, 0));
		HGDIOBJ brushOld;

		brushOld = SelectObject(hdc, brush);

		Ellipse(hdc, 189, 363, 194, 368);
		Ellipse(hdc, 211, 363, 206, 368);

		SelectObject(hdc, brushOld);
		DeleteObject(brush);

		HBRUSH brush2 = CreateSolidBrush(RGB(153, 0, 153));
		HGDIOBJ brushOld2;

		brushOld2 = SelectObject(hdc, brush2);

		Ellipse(hdc, 193, 373, 207, 383);

		SelectObject(hdc, brushOld2);
		DeleteObject(brush2);

	}

	
}