#include <iostream>

using namespace std;

int n, sum = 0, ans = 21e8;
int arr[101];
int dp[100001];

int main() {
    
    cin >> n;

    for(int i=0;i<n;i++) 
    {
        cin >> arr[i];
        sum += arr[i];
    }

    for(int i=0;i<100001;i++) dp[i] = -1;
    dp[0] = 0;

    for(int j=0;j<n;j++)
    {
        for(int i=100000;i>=0;i--)
        {
            if (i >= arr[j])
            {
                if (dp[i - arr[j]] == -1) continue;

                dp[i] = 1;
            }
        }
    }

    
    for(int i=1;i<100000;i++) 
    {
        if (dp[i] == 1) 
        {
            int b = sum - i;

            ans = min(ans, abs(b - i));
        }
    }

    cout << ans;

    return 0;
}