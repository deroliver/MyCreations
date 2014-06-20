#include "stdafx.h"
#include "House.h"
using namespace std;


House::House(){
	left = 100;
	right = 1075;
	firFloor = 685;
	secFloor = 485;

}

void House::drawHouse(HWND hwnd, HDC hdc) const{
	GdiplusStartupInput gdiplusStartupInput;//GDIPlus starup code
	ULONG_PTR gdiplusToken;
	GdiplusStartup(&gdiplusToken, &gdiplusStartupInput, NULL);
	
	Pen hPen(Color(232, 70, 20, 1), 5);
	SolidBrush hBrush(Color(232, 70, 20, 1));
	SolidBrush inWBrush(Color(255, 150, 85, 45));
	
	Graphics House(hdc);

	//House.DrawRectangle(&hPen, 100, 175, 975, 510);//Draw the inner Wall
	//House.DrawRectangle(&hPen, 85, 150, 1005, 550);//Draw the Outer Wall

	MoveToEx(hdc, 100, 175, NULL);
	LineTo(hdc, 1075, 175);
	
	House.FillRectangle(&inWBrush, 88, 153, 1000, 20);//Fills the space between the inner and outer wall with color
	House.FillRectangle(&inWBrush, 88, 173, 10, 525);//Fills the space between the inner and outer wall with color
	House.FillRectangle(&inWBrush, 98, 688, 990, 10);//Fills the space between the inner and outer wall with color
	House.FillRectangle(&inWBrush, 1078, 173, 10, 515);//Fills the space between the inner and outer wall with color

	House.FillRectangle(&inWBrush, 103, 178, 970, 136);

	House.FillRectangle(&hBrush, 102, 485, 438, 10);//Draws the second floor
	House.FillRectangle(&hBrush, 638, 485, 438, 10);//Draws the second floor
	
//	MoveToEx(hdc, 85, 147, NULL);//Draws the top of the house
	//LineTo(hdc, 588, 25);

	//MoveToEx(hdc, 1090, 147, NULL);//Draws the top of the house
	LineTo(hdc, 588, 25);

	House.DrawRectangle(&hPen, 150, 315, 100, 115);//Draws the Windows
	House.DrawRectangle(&hPen, 150, 525, 100, 115);//Draws the Windows
	House.DrawRectangle(&hPen, 925, 315, 100, 115);//Draws the Windows
	House.DrawRectangle(&hPen, 925, 525, 100, 115);//Draws the Windows
}

void House::drawHouseBoards(HWND hwnd, HDC hdc) const{
	
	
}
	 


//}


