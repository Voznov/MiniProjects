#include <iostream>
#include <vector>
#include <stack>
#include <algorithm>

using namespace std;


int main()
{
	int n, k;
	cin >> n >> k;
	int _k = k;

	int m = 1;
	while (_k)
	{
		m = 3 * m + 1;
		--_k;
		if (m > n)
		{
			cout << "No";
			return 0;
		}
	}

	vector<int> v[n];
	bool was[n];
	for (int i = 0; i < n; ++i)
		was[i] = false;

	int u, w;
	for (int i = 0; i < n - 1; ++i)
	{
		cin >> u >> w;
		v[u - 1].push_back(w - 1);
		v[w - 1].push_back(u - 1);
	}

	stack<pair<int, int> > q;
	for (int i = 0; i < n; ++i)
		q.push({i, -1});

	while(q.size() > 1)
	{
		pair<int, int> y = q.top();
		int x = y.first;
		if (was[x])
			continue;
		int c = 0;
		for (int i = 0; i < v[x].size(); ++i)
			if (was[v[x][i]])
				++c;
		if (c == v[x].size() - 1)
		{
			q.pop();
			was[x] = true;
			continue;
		}
		cout << "D: size=" << q.size() << ", v=" << x << ", " << c << "/" << v[x].size() << endl;
		for (int i = 0; i < v[x].size(); ++i)
			if (!was[v[x][i]] && v[x][i] != y.second)
				q.push({v[x][i], x});
		if (x == q.top().first)
		{
			while (q.size())
				if (!was[q.top().first])
				{
					cout << "No";
					return 0;
				}
			q.pop();
		}
	}
	cout << "Yes";

	cout << "Yes";
	return 0;
}
