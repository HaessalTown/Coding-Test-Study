#include <iostream>
#include <map>
#include <iomanip>
#include <queue>
#include <algorithm>

using namespace std;

int base;
int n, m, k;
long long tree[4000004];

void print()
{
    for (int i = 1; i < 16; i++) cout << tree[i] << " ";
}

void update(int idx, long long val)
{
    int pos = base + idx;
    long long diff = val - tree[pos];
    while(pos > 0)
    {
        tree[pos] += diff;
        pos /= 2;
    }
}

long long sum(int idx, int s, int e, int ts, int te)
{
    if (e < ts || te < s) return 0;
    if (ts <= s && e <= te) return tree[idx];

    int mid = (s + e) / 2;
    return sum(idx * 2, s, mid, ts, te) + sum(idx * 2 + 1, mid + 1, e, ts, te);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> n >> m >> k;

    base = 1;
    while (base < n) base *= 2;

    for(int i=0;i<n;i++) cin >> tree[base + i];

    for (int i = base - 1; i > 0; i--) tree[i] = tree[i * 2] + tree[i * 2 + 1];

    for(int i=0;i<m+k;i++)
    {
        long long a, b, c;
        cin >> a >> b >> c;

        if (a == 1) update(b - 1, c);
        else if (a == 2) cout << sum(1, 0, base-1, b - 1, c - 1) << "\n";
    }

    return 0;
}