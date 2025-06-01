#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <algorithm>
#include <vector>
#include <unordered_map>
#include <queue>

using namespace std;

int n, m;
vector<int> arr;

bool ps(int mid)
{
    priority_queue<int, vector<int>, greater<>> pq;

    for (int i = 0; i < m; i++) pq.push(0);

    for (int i = 0; i < n; i++)
    {
        int now = pq.top();
        pq.pop();

        if (now + arr[i] > mid) return false;

        pq.push(now + arr[i]);
    }

    return true;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> n >> m;
    int left = 0, right = 0;

    for (int i = 0; i < n; i++)
    {
        int num;
        cin >> num;
        arr.push_back(num);
        right += num;
    }

    sort(arr.rbegin(), arr.rend());

    int ans = right;
    while (left <= right)
    {
        int mid = (left + right) / 2;

        if (ps(mid))
        {
            ans = mid;
            right = mid - 1;
        }
        else left = mid + 1;
    }

    cout << ans;

    return 0;
}