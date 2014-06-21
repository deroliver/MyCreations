#ifndef FIGURE_H_
#define FIGURE_H_

class Figure{
public:
	Figure();
	virtual void drawFigure(HWND hwnd, HDC hdc);
	void moveFigureLeft();
	void moveFigureRight();
	static RECT BodyU;
	static bool faceLeft;
	int movespeed;
};
#endif