#include <iostream>
#include <fstream>
#include <windows.h>

using namespace std;

int main()
{
    ofstream fout("test", ios_base::out);
    float c[10];
    for (int i = 0; i < 10; ++i)
        c[i] = i / 10.0f + 'A';

    for (int i = 0; i < 10; ++i)
        cout << c[i] << ' ';
    cout << endl << "-----" << endl;

    for (int i = 0; i < 10; ++i)
        fout << c[i] << ' ';

    fout.close();

    ifstream fin("test", ios_base::in);
    for (int i = 0; i < 10; ++i)
        fin >> c[i];

    fin.close();

    for (int i = 0; i < 10; ++i)
        cout << c[i] << ' ';
    cout << endl << "-----" << endl;


    return 0;
}
