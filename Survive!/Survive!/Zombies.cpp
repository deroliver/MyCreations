#include "stdafx.h"
#include "Zombie.h"


int window = 0;
int m = 2;
int left1 = l1, right1 = r1;
int left2 = l2, right2 = r2;
RECT Body = { -1, -1, -1, -1};
bool Zombie::faceLeft = true;

void Zombie::runWhenAddingZombie(){
	zombiePos = 2;
}



Zombie::Zombie() : Figure(){
	if (zombiePos == 1)
		window = 1;
	if (zombiePos == 2)
		window = 2;
}

void Zombie::drawZombieInWindow(HWND hwnd, HDC hdc){
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



void Zombie::drawFigure(HWND hwnd, HDC hdc){
	switch (window){
	case 1:
		if (Body.left == -1){
			Body.left = left1;
			Body.top = 265;
			Body.right = right1;
			Body.bottom = 285;
			faceLeft = false;
		}
		break;
	case 2:
		if (Body.left == -1){
			Body.left = left2;
			Body.top = 265;
			Body.right = right2;
			Body.bottom = 285;
			faceLeft = false;
		}
		HBRUSH brush = CreateSolidBrush(RGB(255, 0, 0));
		HPEN pen = CreatePen(PS_SOLID, 2, RGB(0, 0, 0));
		HGDIOBJ penOld, brushOld;

		penOld = SelectObject(hdc, pen);
		brushOld = SelectObject(hdc, brush);

		Rectangle(hdc, Body.left, Body.top, Body.right, Body.bottom);
		Ellipse(hdc, Body.left - 10, Body.top - 20, Body.right + 10, Body.top);
		if (faceLeft){
			MoveToEx(hdc, Body.right, Body.bottom, NULL);
			LineTo(hdc, Body.right + 2, Body.bottom + 14);

			MoveToEx(hdc, Body.left, Body.bottom, NULL);
			LineTo(hdc, Body.left - 5, Body.bottom + 14);

			MoveToEx(hdc, Body.left, Body.top + 8, NULL);
			LineTo(hdc, Body.left - 20, Body.top + 8);

			Body.left -= m;
			Body.right -= m;
			if (Body.right < 120)
				faceLeft = false;

		}
		else if (!faceLeft){

			MoveToEx(hdc, Body.left, Body.bottom, NULL);
			LineTo(hdc, Body.left - 2, Body.bottom + 14);

			MoveToEx(hdc, Body.right, Body.bottom, NULL);
			LineTo(hdc, Body.right + 5, Body.bottom + 14);

			MoveToEx(hdc, Body.right, Body.top + 8, NULL);
			LineTo(hdc, Body.right + 20, Body.top + 8);

			Body.left += m;
			Body.right += m;
			if (Body.right > 700)
				faceLeft = true;
		}
		SelectObject(hdc, brushOld);
		SelectObject(hdc, penOld);

		DeleteObject(brush);
		DeleteObject(pen);
	}


}