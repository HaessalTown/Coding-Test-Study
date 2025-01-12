#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>

using namespace std;

struct Info {
	int from;
	int to;
	int boxes;
	bool operator<(const Info& right) const {
		// to가 작은 순서대로
		// 같으면 from이 작은 순서대로
		if (to == right.to) return from < right.from;
		return to < right.to;
	}
};

int n, c, m, ans = 0;
vector<Info> vec;
int weights[2001];

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);

	cin >> n >> c;
	cin >> m;

	for (int i = 0; i < m; i++)
	{
		int f, t, b;
		cin >> f >> t >> b;

		vec.push_back({ f, t, b });
	}

	sort(vec.begin(), vec.end());

	for (int i = 0; i < m; i++)
	{
		int f = vec[i].from;
		int t = vec[i].to;
		int b = vec[i].boxes;
		int maxi = 0;
		for (int j = f; j < t; j++) maxi = max(weights[j], maxi);
		int weight = min(b, c - maxi);
		for (int j = f; j < t; j++) weights[j] += weight;
		ans += weight;
	}

	cout << ans;

	return 0;
}