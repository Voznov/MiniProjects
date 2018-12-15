#include <iostream>
#include <string>

using namespace std;

int main()
{
    int n;
    cin >> n;

    int answer = 0;
    for(int i = 0; i < n; ++i)
    {
        string s;
        cin >> s;
        size_t point = s.find(".");

        if(point == string::npos)
            continue;

        string filename  = s.substr(0, point);
        string extension = s.substr(point + 1);

        if(extension.find(".") != string::npos)
            continue;

        if(filename.length() <= 8 && extension.length() <= 3 && filename.length() > 0 && extension.length() > 0)
            ++answer;
    }

    cout << answer;

    return 0;
}
