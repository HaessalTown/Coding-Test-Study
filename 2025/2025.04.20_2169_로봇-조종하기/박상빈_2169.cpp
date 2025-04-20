#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

int diry[3] = { 0, 0, -1 };
int dirx[3] = { 1, -1, 0 };

int n, m;
int arr[1001][1001];
int dp[1001][1001];

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> n >> m;

    for (int i = 0;i < n;i++)
    {
        for (int j = 0;j < m;j++)
        {
            cin >> arr[i][j];
        }
    }

    dp[0][0] = arr[0][0];
    for (int j = 1;j < m;j++) dp[0][j] = dp[0][j - 1] + arr[0][j];

    for (int i = 1;i < n;i++)
    {
        vector<int> left(m), right(m);

        left[0] = dp[i - 1][0] + arr[i][0];
        for (int j = 1;j < m;j++) left[j] = max(left[j - 1], dp[i - 1][j]) + arr[i][j];

        right[m - 1] = dp[i - 1][m - 1] + arr[i][m - 1];
        for (int j = m - 2;j >= 0;j--) right[j] = max(right[j + 1], dp[i - 1][j]) + arr[i][j];

        for (int j = 0;j < m;j++) dp[i][j] = max(left[j], right[j]);
    }

    cout << dp[n - 1][m - 1];

    return 0;
}