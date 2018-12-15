#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int main()
{
    string s;
    cin >> s;

    int n;
    cin >> n;
    if(n % 2)
    {
        cout << "No solution.";
        return 0;
    }

    int a[n][n];
    for(int i = 0; i < n; ++i)
        for(int j = 0; j < n; ++j)
        {
            char tmp;
            cin >> tmp;
            a[i][j] = (tmp == '+' ? 1 : 0);
        }
    bool answer = true;
    for(int i = 0; i < n / 2; ++i)
        for(int j = 0; j < n / 2; ++j)
            if((a[i][j] + a[j][n - i - 1] + a[n - j - 1][i] + a[n - i - 1][n - j - 1]) != 1)
                answer = false;

    if(!answer)
    {
        cout << "No solution.";
        return 0;
    }

    char b[n][n];
    for(int i = 0; i < n; ++i)
        for(int j = 0; j < n; ++j)
            if(i * n + j >= s.length())
                b[i][j] = '*';
            else
                b[i][j] = s[i * n + j];

    bool mask[n][n];
    string result = "";

    for(int i = 0; i < n; ++i)
        for(int j = 0; j < n; ++j)
            mask[i][j] = a[i][j];

    //

    for(int i = 0; i < n; ++i)
        for(int j = 0; j < n; ++j)
            if(mask[i][j])
                result += b[i][j];

    for(int i = 0; i < n; ++i)
        for(int j = 0; j < n; ++j)
            if(mask[n - j - 1][i])
                result += b[i][j];

    for(int i = 0; i < n; ++i)
        for(int j = 0; j < n; ++j)
            if(mask[n - i - 1][n - j - 1])
                result += b[i][j];

    for(int i = 0; i < n; ++i)
        for(int j = 0; j < n; ++j)
            if(mask[j][n - i - 1])
                result += b[i][j];

    cout << result;

    return 0;
}
