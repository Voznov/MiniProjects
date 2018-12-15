#include <iostream>
#include <cmath>

using namespace std;

int main()
{
	unsigned long long x = 0, b;
	cin >> b;

	for (unsigned long long i = 1; i <= sqrt(b); ++i)
		if (b % i == 0)
			++x;

	x *= 2;
	if ((unsigned long long) sqrt(b) * (unsigned long long) sqrt(b) == b)
		--x;

	cout << x;
	return 0;
}
