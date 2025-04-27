#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <algorithm>
#include <vector>
#include <unordered_map>
#include <queue>

using namespace std;

struct Node {
    int f;
    int t;
    int v;
    bool operator<(const Node& right) const {
        return v > right.v;
    }
};

int n, ans = 0;
int unf[51];
char arr[51][51];
unordered_map<char, int> mapped;
priority_queue<Node> pq;

void init()
{
    for (int i = 0; i < 51; i++) unf[i] = i;
}

int find(int tar)
{
    if (tar == unf[tar]) return tar;
    return unf[tar] = find(unf[tar]);
}

bool Union(int a, int b, int v)
{
    a = find(a);
    b = find(b);

    if (a == b) return false;

    if (a < b) unf[b] = a;
    else unf[a] = b;
    ans += v;

    return true;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    init();

    int total = 0;
    int cnt = 1;
    for (char ch = 'a'; ch <= 'z'; ch++) mapped[ch] = cnt++;
    for (char ch = 'A'; ch <= 'Z'; ch++) mapped[ch] = cnt++;


    cin >> n;

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            cin >> arr[i][j];
            if (arr[i][j] != '0' && mapped.count(arr[i][j]) != 0)
            {
                pq.push({ i, j, mapped[arr[i][j]] });
                total += mapped[arr[i][j]];
            }
        }
    }

    int count = n - 1;
    while (count && !pq.empty())
    {
        Node now = pq.top();
        pq.pop();

        bool res = Union(now.f, now.t, now.v);

        if (res) count--;
    }

    if (count != 0) cout << -1;
    else cout << total - ans;

    return 0;
}