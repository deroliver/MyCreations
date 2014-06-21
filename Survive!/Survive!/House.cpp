#include "stdafx.h"
#include "House.h"
#include "Zombie.h"
using namespace std;


House::House(){

}

//bool House::operator+(Zombie*& Z1){
//	zombies.push_back(Z1);
//}
void House::drawZombiesInHouse(HWND hwnd, HDC hdc){
	for (int i = 0; i < zombies.size(); i++){
		zombies[i]->drawFigure(hwnd, hdc);
	}
}

void House::addZombieToHouse(){
	Zombie* Z1 = new Zombie();
	Z1->runWhenAddingZombie();
	zombies.push_back(Z1);
}

void House::drawZombiesInWindow(HWND hwnd, HDC hdc){
	for (int i = 0; i < zombies.size(); i++){
		zombies[i]->drawZombieInWindow(hwnd, hdc);
	}
}


