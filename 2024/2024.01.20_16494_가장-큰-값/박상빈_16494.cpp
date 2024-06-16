#define _CRT_SECURE_NO_WARNINGS
#include <iostream>

using namespace std;

int N, M;
int dp[21][21] = { 0, };
int MAX = -9999;

void recursion(int sum, int cnt, int idx)
{
	// 주어진 M만큼 선택하고 MAX 비교
	if (cnt == M)
	{
		if (sum > MAX) MAX = sum;

		return;
	}
	else
	{
		// idx를 기준으로 더할 부분 수열을 찾을 것
		for (int i = idx; i < N; i++)
		{
			for (int j = idx; j < N; j++)
			{
				if (j < i) continue;
				recursion(sum + dp[i][j], cnt + 1, j + 1);
			}
		}
	}

}

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(0);

	cin >> N >> M;

	for (int i = 0; i < N; i++)
	{
		cin >> dp[i][i];
	}

	// i 부터 j 까지의 합 dp로 구하고
	// dp 중 M개 선택해서 최대값 찾기

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < N; j++)
		{
			if (i >= j) continue;
			dp[i][j] = dp[i][j - 1] + dp[j][j];
		}
	}

	recursion(0, 0, 0);

	cout << MAX;


	return 0;
}