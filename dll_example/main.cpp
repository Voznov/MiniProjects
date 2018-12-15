#include <iostream>
#include "main.h"

using namespace std;

class Kek
{
	int lol;
	Kek* child;

public:
	Kek()
	{
		lol = 24;
	}

	~Kek()
	{

	}

	int heh(int a)
	{
		if (a > 0)
		{
			child = new Kek();
			return child->heh(a - 1) + 1;
		}
		else
			return lol;
	}
};

Kek kek;

int DLL_EXPORT ReturnKek(int a)
{
	//cout << "kek " << a << endl;
	return kek.heh(a);
	//return a;
}
