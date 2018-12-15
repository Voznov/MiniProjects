#include <iostream>
#include <cmath>
#include <iomanip>

using namespace std;

int main()
{
	double result = 1;

	cout << setprecision(15);
	for (int i = 1; i < 100; ++i)
	{
		result *= (1 + pow(2, -i));
		cout << result << endl;
		if ((1 + pow(2, -i)) == 1) 
		{
			cout << "kek" << endl;
			return 0;
		}

	}

	return 0;
}
