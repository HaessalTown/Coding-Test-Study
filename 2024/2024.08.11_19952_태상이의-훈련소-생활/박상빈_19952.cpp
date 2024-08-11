#include <iostream>

using namespace std;

int n, m;
int arr[100001];
int sum[100001];

int main() {

    cin >> n >> m;

    for (int i = 0; i < n; i++) cin >> arr[i];

    for(int i=0;i<m;i++)
    {
        int s, e, h;
        cin >> s >> e >> h;

        sum[s - 1] += h;
        sum[e] -= h;
    }

    arr[0] += sum[0];
    cout << arr[0] << " ";

    for(int i=1;i<n;i++)
    {
        sum[i] += sum[i - 1];
        arr[i] += sum[i];
        cout << arr[i] << " ";
    }

    return 0;
}