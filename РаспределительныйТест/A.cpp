#include <iostream>

using namespace std;

int main()
{
    int a, b;
    cin >> a >> b;

    cout << ((a % 10000) / 10) + ((b % 10000) / 10);

    return 0;
}
