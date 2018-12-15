#include <iostream>

using namespace std;

int main()
{
	unsigned long long x, n, m , k, l;
	cin >> n >> m >> k >> l;

	if (k + l > n)
	{
		cout << -1;
		return 0;
	}

	x = (k + l) / m + ((k + l) % m ? 1 : 0);

	if (m * x > n)
	{
		cout << -1;
		return 0;
	}

	cout << x;
	return 0;
}
