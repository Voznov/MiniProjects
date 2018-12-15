#include <iostream>
#include <vector>

using namespace std;

class Med
{
public:
	vector<Med*> sons;
	int deep, range;
	
	Med(int range, int deep)
	{
		if(deep != 0)
		{
			for(int i = 0; i < range; ++i)
			{
				sons.push_back(new Med(range, deep - 1));
			}
		}
		this->deep = deep;
		this->range = range;
	};

	int check(long long num, int pos, int range_check)
	{
		if(deep != 0)
		{
			int check_result = 0;
			long long shift_pos = 1;
			
			for(int i = 0; i < deep - 1; ++i)
			{
				shift_pos *= range;
				shift_pos %= range_check;
			}
			
			for(int i = 0; i < range; ++i)
			{
				check_result += sons[i]->check(num, (pos + i * shift_pos) % range_check, range_check);
			}
			return (2 * check_result) > range;
		}
		else
		{
			return (num >> pos) % 2;
		}
	}
};

int main()
{
	int range_big, range, deep, errors_count = 0, output_type;
	cout << "range_big: "; cin >> range_big;
	cout << "range: "; cin >> range;
	cout << "deep: "; cin >> deep;
	cout << "output_type: "; cin >> output_type;

	cout << "----------" << endl;

	if(output_type == 1)
	{
		for(int i = 0; i < range_big; ++i)
			cout << ((i >= 9) ? char('A' + i - 9) : char('1' + i));
		cout << " R" << endl;
	}
	
	Med med(range, deep);
	
	int now_percent = 0;
	if(output_type == 0)
		cout << "0%" << endl;
	for(long long i = 0; i < (1ll << range_big); ++i)
	{
		int check_result = 0;
		for(int j = 0; j < range_big; ++j)
		{
			int bool_number = (i >> j) % 2;
			check_result += bool_number;
			if(output_type == 1)
				cout << bool_number;
		}
		
		int med_check = med.check(i, 0, range_big);
		
		bool not_error = (2 * check_result > range_big) == med_check;
		errors_count += !not_error;
		if(output_type == 1)
			cout << " " << med_check << (not_error ? "+" : "-") << endl;
		else
			if(now_percent < (100 * (i + 1) / (1ll << range_big)))
			{
				cout << (++now_percent) << "%" << endl;
			}
	}
	cout << "----------" << endl;
	cout << "Errors: " << errors_count << "/" << (1 << range_big) << endl;
	
	return 0;
}
