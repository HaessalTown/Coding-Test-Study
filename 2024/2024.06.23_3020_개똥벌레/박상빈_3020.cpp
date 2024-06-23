#include <iostream>

using namespace std;

int n, h, mini, ans;
int ob[500005];

int main() {

    cin >> n >> h;

    for(int i=0;i<n;i++)
    {
        int num;
        cin >> num;

        // 누적 합
        // 석순
        if (i % 2 == 0)
        {
            ob[0]++;
            ob[num + 1]--;
        }
        // 종유석
        else
        {
            ob[h + 1]--;
            ob[abs(h - num) + 1]++;
        }
    }

    for(int i=1;i<=h;i++)
    {
        ob[i] += ob[i - 1];
    }

    mini = 21e8;
    ans = 0;

    for(int i=1;i<=h;i++)
    {
        if (mini > ob[i])
        {
            mini = ob[i];
            ans = 1;
        }
        else if (mini == ob[i]) ans++;
    }

    cout << mini << " " << ans;


    return 0;
}