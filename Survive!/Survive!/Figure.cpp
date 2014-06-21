#include "stdafx.h"
#include <typeinfo>
#include "Figure.h"


RECT Figure::BodyU = { 400, 480, 402, 500 };
bool Figure::faceLeft = true;


Figure::Figure(){
	
	//	if (typeid(Figure) == typeid(User))
		movespeed = 25;
//	else if (typeid(Figure) == typeid(Zombie))
	//	movespeed = 10;
}


void Figure::drawFigure(HWND hwnd, HDC hdc){
	
	HBRUSH brush = CreateSolidBrush(RGB(255, 0, 0));
	HPEN pen = CreatePen(PS_SOLID, 2, RGB(0, 0, 0));
	HGDIOBJ penOld, brushOld;

	penOld = SelectObject(hdc, pen);
	brushOld = SelectObject(hdc, brush);

	Rectangle(hdc, BodyU.left, BodyU.top, BodyU.right, BodyU.bottom);
	Ellipse(hdc, BodyU.left - 10, BodyU.top - 20, BodyU.right + 10, BodyU.top);

	if (faceLeft){
		MoveToEx(hdc, BodyU.right, BodyU.bottom, NULL);
		LineTo(hdc, BodyU.right + 2, BodyU.bottom + 14);

		MoveToEx(hdc, BodyU.left, BodyU.bottom, NULL);
		LineTo(hdc, BodyU.left - 5, BodyU.bottom + 14);

		MoveToEx(hdc, BodyU.left, BodyU.top + 8, NULL);
		LineTo(hdc, BodyU.left - 20, BodyU.top + 8);

		Rectangle(hdc, BodyU.left - 65, BodyU.top + 10, BodyU.left - 35, BodyU.top + 15);
		Rectangle(hdc, BodyU.left - 40, BodyU.top + 15, BodyU.left - 35, BodyU.top + 30);

	}

	else if (!faceLeft){
		MoveToEx(hdc, BodyU.left, BodyU.bottom, NULL);
		LineTo(hdc, BodyU.left - 2, BodyU.bottom + 14);

		MoveToEx(hdc, BodyU.right, BodyU.bottom, NULL);
		LineTo(hdc, BodyU.right + 5, BodyU.bottom + 14);

		MoveToEx(hdc, BodyU.right, BodyU.top + 8, NULL);
		LineTo(hdc, BodyU.right + 20, BodyU.top + 8);

		Rectangle(hdc, BodyU.right + 40, BodyU.top + 5, BodyU.right + 15, BodyU.top + 5);
		Rectangle(hdc, BodyU.right + 40, BodyU.top + 15, BodyU.right + 35, BodyU.top + 30);
	}

	SelectObject(hdc, brushOld);
	SelectObject(hdc, penOld);

	DeleteObject(brush);
	DeleteObject(pen);
}

void Figure::moveFigureLeft(){
	
	BodyU.left -= movespeed;
	BodyU.right -= movespeed;
	faceLeft = true;
}

void Figure::moveFigureRight(){
	BodyU.left += movespeed;
	BodyU.right += movespeed;
	faceLeft = false;
}
