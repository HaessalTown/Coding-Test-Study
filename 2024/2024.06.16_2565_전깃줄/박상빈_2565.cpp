#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

bool compare (const pair<int, int> &a, const pair<int, int> &b)
{
	return a.first < b.first;
}

int n, maxi = -21e8;
vector<pair<int, int>> arr;
int dp[501];

int main() {

	cin >> n;

	for(int i=0;i<n;i++)
	{
		int a, b;
		cin >> a >> b;

		arr.push_back({ a, b });
	}

	sort(arr.begin(), arr.end(), compare);

	dp[0] = 1;

	for(int i=1;i<arr.size();i++)
	{
		for(int j=0;j<i;j++)
		{
			if (arr[j].second < arr[i].second)
			{
				dp[i] = max(dp[i], dp[j] + 1);
				maxi = max(dp[i], maxi);
			}

			if (dp[i] == 0) dp[i] = 1;
		}
	}

	cout << n - maxi;


    return 0;
}