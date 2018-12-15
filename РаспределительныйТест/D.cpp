#include <iostream>
#include <string>
#include <vector>
#include <sstream>
#include <iomanip>

using namespace std;

int main()
{
    string monthes[12];
    monthes[0] = "January";
    monthes[1] = "February";
    monthes[2] = "March";
    monthes[3] = "April";
    monthes[4] = "May";
    monthes[5] = "June";
    monthes[6] = "July";
    monthes[7] = "August";
    monthes[8] = "September";
    monthes[9] = "October";
    monthes[10] = "November";
    monthes[11] = "December";

    int days[12];
    days[0] = 31;
    days[1] = 28;
    days[2] = 31;
    days[3] = 30;
    days[4] = 31;
    days[5] = 30;
    days[6] = 31;
    days[7] = 31;
    days[8] = 30;
    days[9] = 31;
    days[10] = 30;
    days[11] = 31;

    string date, temp;
    cin >> date;

    int month;

    for(int i = 0; i < 12; ++i)
        if(date.find(monthes[i]) != string::npos)
        {
            month = i;
            break;
        }

    int day, year, h, m;
    char temp_char;
    cin >> day >> temp >> year >> h >> temp_char >> m;

    if(!(year % 400) || (!(year % 4) && year % 100))
        days[1] = 29;

    int day_now = day - 1;
    for(int i = 0; i < month; ++i)
        day_now += days[i];

    int day_all = 0;
    for(int i = 0; i < 12; ++i)
        day_all += days[i];

    int time_now = day_now * 24 * 60 + h * 60 + m;
    int time_all = day_all * 24 * 60;

    cout << setprecision(15) << (double(100) * time_now / time_all);

    return 0;
}
