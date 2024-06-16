#include <iostream>

using namespace std;

// 0 들판
// 1 나무
// 2 돌

int n, m;
int Map[1001][1001];
int dp[1001][1001];

int main() {

	cin >> m >> n;

	for (int i = 0; i < m; i++)
	{
		for (int j = 0; j < n; j++)
		{
			cin >> Map[i][j];
		}
	}

	int ans = 0;

	for(int i=1;i<m + 1;i++)
	{
		for(int j=1;j<n + 1;j++)
		{
			if (Map[i - 1][j - 1] == 0)
			{
				dp[i][j] = min(min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
				ans = max(ans, dp[i][j]);
			}
		}
	}

	printf("%d", ans);

	return 0;
}