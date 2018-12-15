#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int main()
{
    int m, n;

    cin >> m >> n;

    if(m*n == 0)
        return 0;

    char a[m][n];
    vector<string> a_s;

    for(int i = 0; i < m; ++i)
    {
        string s;
        cin >> s;
        for(int j = 0; j < n; ++j)
            a[i][j] = s[j];
    }


    for(int i = 0; i < m; ++i)
    {
        string s = "";
        for(int j = 0; j < n; ++j)
            if(a[i][j] == '.')
            {
                if(s.length() > 1)
                    a_s.push_back(s);
                s = "";
            }
            else
            {
                s += a[i][j];
            }
        if(s.length() > 1)
            a_s.push_back(s);
    }

    for(int j = 0; j < n; ++j)
    {
        string s = "";
        for(int i = 0; i < m; ++i)
            if(a[i][j] == '.')
            {
                if(s.length() > 1)
                    a_s.push_back(s);
                s = "";
            }
            else
            {
                s += a[i][j];
            }
        if(s.length() > 1)
            a_s.push_back(s);
    }

    sort(a_s.begin(), a_s.end());
    for(int i = 0; i < a_s.size(); ++i)
        cout << a_s[i] << ' ';

    return 0;
}
