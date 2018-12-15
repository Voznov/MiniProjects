#include <iostream>
#include <string>
#include <vector>

using namespace std;

int main()
{
    int m, n;
    cin >> m;

    vector<int> a[m];

    for(int i = 0; i < m; ++i)
    {
        string s;
        cin >> s;

        for(int j = 0; j < s.length(); ++j)
            a[i].push_back(s[j] - 48);
    }

    n = a[0].size();

    int r[n];
    bool is_alive[n];
    for(int i = 0; i < n; ++i)
        is_alive[i] = true;

    while(a[0].size())
    {
        for(int i = 0; i < n; ++i)
            r[i] = 0;

        for(int i = 0; i < m; ++i)
            ++r[a[i][0]];

        int max_r = r[0];
        for(int i = 1; i < n; ++i)
            max_r = max(max_r, r[i]);

        if(2 * max_r > m)
        {
            for(int i = 0; i < n; ++i)
                if(r[i] == max_r)
                {
                    cout << i;
                    return 0;
                }
        }

        int min_r = -1;
        for(int i = 0; i < n; ++i)
            min_r = ((min_r > r[i] || min_r == -1) && is_alive[i] ? r[i] : min_r);

        for(int i = 0; i < n; ++i)
            if(r[i] == min_r && is_alive[i])
            {
                is_alive[i] = false;
                for(int j = 0; j < m; ++j)
                    for(int k = 0; k < a[j].size(); ++k)
                        if(a[j][k] == i)
                        {
                            a[j].erase(a[j].begin() + k);
                            break;
                        }
            }
    }

    cout << -1;
    return 0;
}
