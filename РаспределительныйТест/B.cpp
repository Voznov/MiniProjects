#include <iostream>

using namespace std;

int main()
{
    int n, a[100][100];

    cin >> n;

    for (int i = 0; i < n; ++i)
        for (int j = 0; j < n; ++j)
            cin >> a[i][j];

    bool answer = true;

    for (int i = 0; i < n; ++i)
        for (int j = i; j < n; ++j)
            if(a[i][j] != a[j][i])
                answer = false;

    cout << (answer ? "YES" : "NO");

    return 0;
}
