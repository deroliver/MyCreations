#include "stdafx.h"
#include "User.h"


RECT User::Bullet = { 0, 0, 0, 0 };

bool User::shoot_Gun = false;


User::User() : Figure(){}


void User::shootGun(HWND hwnd, HDC hdc){
	if (shoot_Gun){
		if (faceLeft){
			int l = BodyU.right + 68;
			int t = BodyU.top + 12;
			int r = BodyU.right + 67;
			int b = BodyU.top + 13;
			do{
				Ellipse(hdc, l, t, r, b);
				l -= 40;
				r -= 40;
			} while (l > 10);

		}

		else if (!faceLeft){
			int l = BodyU.left - 69;
			int t = BodyU.top + 12;
			int r = BodyU.right + 67;
			int b = BodyU.top + 13;
			do{
				Ellipse(hdc, l, t, r, b);
				l += 40;
				r += 40;
			} while (l < 1000);
		}
		shoot_Gun = false;
	}
}

void User::shoot_GunBool(){
	shoot_Gun = true;
}



