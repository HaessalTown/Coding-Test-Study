#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <queue>

using namespace std;

int n, k;
string names[300001];
long long cnt[21];
long long now, ans = 0;

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);

	cin >> n;
	cin >> k;

	for (int i = 0; i < n; i++) cin >> names[i];

	for (int i = 1; i <= k; i++) cnt[names[i].length()]++;

	for (int i = 1; i < n; i++)
	{
		ans += cnt[names[i - 1].length()];

		cnt[names[i].length()]--;
		if (i + k < n) cnt[names[i + k].length()]++;
	}

	cout << ans;


	return 0;
}
