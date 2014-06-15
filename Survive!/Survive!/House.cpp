#include "stdafx.h"
#include "House.h"
using namespace std;


House::House(){
	window1b = 3;
	window2b = 3;
	window3b = 3;
	window4b = 3;
}

void House::drawHouse(HWND hwnd, HDC hdc) const{
	GdiplusStartupInput gdiplusStartupInput;
	ULONG_PTR gdiplusToken;
	GdiplusStartup(&gdiplusToken, &gdiplusStartupInput, NULL);
	
	RoundRect(hdc, 85, 225, 1090, 840, 3, 3);
	Rectangle(hdc, 100, 250, 1075, 830);
	
	MoveToEx(hdc, 85, 225, NULL);
	LineTo(hdc, 588, 50);

	MoveToEx(hdc, 1090, 225, NULL);
	LineTo(hdc, 588, 50);

	MoveToEx(hdc, 100, 565, NULL);
	LineTo(hdc, 538, 565);

	MoveToEx(hdc, 638, 565, NULL);
	LineTo(hdc, 1075, 565);

	RoundRect(hdc, 150, 360, 250, 475, 2, 2);
	RoundRect(hdc, 150, 650, 250, 765, 2, 2);
	RoundRect(hdc, 925, 650, 1025, 765, 2, 2);
	RoundRect(hdc, 925, 360, 1025, 475, 2, 2);
}

void House::drawHouseBoards(HWND hwnd, HDC hdc) const{
	switch (this->window1b){
	case 3:
		drawWindow1Boards3(hwnd, hdc);
	}
	 


}


void drawWindow1Boards3(HWND hwnd, HDC hdc){

	Graphics R1(hdc);
	SolidBrush pen(Color(77, 53, 12, 2));
	R1.FillRectangle(&pen, 165, 350, 20, 135);
	
	
	//RoundRect(hdc, 165, 350, 185, 485, 2, 2);
	RoundRect(hdc, 135, 440, 265, 460, 2, 2);
}