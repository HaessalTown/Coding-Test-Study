#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <queue>

using namespace std;

int n, ans = 21e8;
int dp[61][61][61];
int hp[3];
int dmg[6][3] = {
	{1, 3, 9},
	{1, 9, 3},
	{3, 1, 9},
	{3, 9, 1},
	{9, 1, 3},
	{9, 3, 1}
};

int dfs(int h1, int h2, int h3)
{
	if (h1 < 0) h1 = 0;
	if (h2 < 0) h2 = 0;
	if (h3 < 0) h3 = 0;

	if (h1 == 0 && h2 == 0 && h3 == 0) return 0;

	if (dp[h1][h2][h3] != -1) return dp[h1][h2][h3];

	int res = 21e8;

	for(int i=0;i<6;i++) res = min(res, dfs(h1 - dmg[i][0], h2 - dmg[i][1], h3 - dmg[i][2]) + 1);

	return dp[h1][h2][h3] = res;
}

int main()
{
	scanf("%d", &n);

	for (int i = 0; i < n; i++) scanf("%d", &hp[i]);

	for(int i=0;i<61;i++)
	{
		for(int j=0;j<61;j++)
		{
			for(int k=0;k<61;k++)
			{
				dp[i][j][k] = -1;
			}
		}
	}

	printf("%d", dfs(hp[0], hp[1], hp[2]));

	return 0;
}
