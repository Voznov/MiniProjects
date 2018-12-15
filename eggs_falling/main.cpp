#include <iostream>
#include <cmath>
#include <cstdlib>

using namespace std;

int main()
{
	const int height = 100;
	
	int current_level = 0;
	
	double _level_count = (-1 + sqrt(1 + 8 * height)) / 2;
	int level_count = _level_count;
	level_count += (level_count < _level_count ? 1 : 0);
	
	int* levels = (int*) malloc(level_count * sizeof(int));

	for(int i = 0; i < level_count; ++i)
	{
		levels[level_count - 1 - i] = height - i * (i + 1) / 2;
	}
	
	bool is_broken = false;
	int now_level = 0;
	
	while(!is_broken)
	{
		cout << "Is egg broken from " << levels[now_level] << " floor? ";
		cin >> is_broken;
		++now_level;
	}

	is_broken = false;
	int now_floor = (now_level == 1 ? 1 : levels[now_level - 2] + 1);
	while(!is_broken)
	{
		cout << "Is egg broken from " << now_floor << " floor? ";
		cin >> is_broken;
		++now_floor;
	}

	cout << "Egg's strengh is " << now_floor - 1 << " floor";
	
	return 0;
}
