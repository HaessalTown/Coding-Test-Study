#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <algorithm>
#include <cmath>
#include <unordered_set>
#include <stack>

using namespace std;

long long n;

int func()
{
    stack<pair<long long, long long>> st;
    st.push({ n, 0 });

    while (!st.empty())
    {
        pair<long long, long long> now = st.top();
        st.pop();

        if (now.first == 2) return now.second;

        if (sqrt(now.first) != (long long)sqrt(now.first))
        {
            long long tmp = ((long long)sqrt(now.first) + 1) * ((long long)sqrt(now.first) + 1);
            st.push({ tmp, now.second + tmp - now.first });
        }
        else st.push({ (long long)sqrt(now.first), now.second + 1 });
    }
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    int tc;
    cin >> tc;

    for (int i = 0; i < tc; i++)
    {
        cin >> n;

        cout << "#" << i + 1 << " " << func() << endl;
    }

    return 0;
}