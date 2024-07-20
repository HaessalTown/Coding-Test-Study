#include <iostream>
#include <unordered_map>

using namespace std;

long long ans = 0;
int n;
unordered_map<int, int> AB;
unordered_map<int, int> CD;
int A[5001];
int B[5001];
int C[5001];
int D[5001];

int main() {

    cin >> n;

    // 5000을 4중포문 돌리면 ??
    // 5000 * 5000 * 5000 * 5000 터진다
    // 5000 * 5000 + 5000 * 5000 은 안터진다 나눠서 보자

    // 5000
    for(int i=0;i<n;i++) cin >> A[i];
    for(int i=0;i<n;i++) cin >> B[i];
    for(int i=0;i<n;i++) cin >> C[i];
    for(int i=0;i<n;i++) cin >> D[i];

    // 25000000
    // 5000
    for(int i=0;i<n;i++)
    {
        // 5000
        for(int j=0;j<n;j++)
        {
            AB[A[i] + B[j]]++;
        }
    }

    for(int i=0;i<n;i++)
    {
        for(int j=0;j<n;j++)
        {
            CD[C[i] + D[j]]++;
        }
    }


    for(auto first : AB)
    {
        int tmp = first.first;
        if (CD.count(-tmp) != 0)
        {
            ans += (first.second * CD[-tmp]);
        }
    }

    cout << ans;
    

    return 0;
}