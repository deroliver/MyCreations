#include "stdafx.h"
#include <commdlg.h>

class Zombie{
private:
	int xpos;//Based on the head of the zombie
	int ypos;//Based on the head of the zombie
public:
	Zombie();
	void drawZombie();
	void moveZombie();
};