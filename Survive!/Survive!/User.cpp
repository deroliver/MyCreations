#include "stdafx.h"
#include "User.h"

RECT User::Body = { 400, 480, 402, 500 };
RECT User::Bullet = { 0, 0, 0, 0 };
bool User::faceLeft = true;
bool User::shoot_Gun = false;


User::User(){
	movespeed = 25;
}


void User::drawUser(HWND hwnd, HDC hdc){
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

		Rectangle(hdc, Body.left - 65, Body.top + 10, Body.left - 35, Body.top + 15);
		Rectangle(hdc, Body.left - 40, Body.top + 15, Body.left - 35, Body.top + 30);
		
	}
	
	else if (!faceLeft){
		MoveToEx(hdc, Body.left, Body.bottom, NULL);
		LineTo(hdc, Body.left - 2, Body.bottom + 14);

		MoveToEx(hdc, Body.right, Body.bottom, NULL);
		LineTo(hdc, Body.right + 5, Body.bottom + 14);
		
		MoveToEx(hdc, Body.right, Body.top + 8, NULL);
		LineTo(hdc, Body.right + 20, Body.top + 8);

		Rectangle(hdc, Body.right + 40, Body.top + 5, Body.right + 15, Body.top + 5);
		Rectangle(hdc, Body.right + 40, Body.top + 15, Body.right + 35, Body.top + 30);
	}

	SelectObject(hdc, brushOld);
	SelectObject(hdc, penOld);

	DeleteObject(brush);
	DeleteObject(pen);


	if (shoot_Gun){
		if (faceLeft){
			int l = Body.right + 68;
			int t = Body.top + 12;
			int r = Body.right + 67;
			int b = Body.top + 13;
			do{
				Ellipse(hdc, l, t, r, b);
				l -= 40;
				r -= 40;
			} while (l > 10);

		}
	
		else if (!faceLeft){
			int l = Body.left - 69;
			int t = Body.top + 12;
			int r = Body.right + 67;
			int b = Body.top + 13;
			do{
				Ellipse(hdc, l, t, r, b);
				l += 40;
				r += 40;
			} while (l < 1000);
		}
		shoot_Gun = false;
	}

	
}

void User::moveUserLeft(){
	Body.left -= movespeed;
	Body.right -= movespeed;
	faceLeft = true;
}

void User::moveUserRight(){
	Body.left += movespeed;
	Body.right += movespeed;
	faceLeft = false;
}

void User::shootGun(){
	shoot_Gun = true;
}

