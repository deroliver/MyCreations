#include "stdafx.h"
#



class Block{
public:
	Block(int l, int t, int r, int b)
	{
		left = l; top = t; right = r; bottom = b;
	}
	void fall(int f){
		bottom += f;
		top += f;
	}
	void draw(HWND hwnd, HDC hdc){
		Rectangle(hdc, left, top, right, bottom);
	}
private:
	int left, top, right, bottom;
};
