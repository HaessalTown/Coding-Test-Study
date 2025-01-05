#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

struct Num {
    int num;
    int idx;
    bool operator<(const Num& right) const {
        return num > right.num;
    }
};

int n, l;
priority_queue<Num> pq;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> n >> l;

    for (int i = 0; i < n; i++)
    {
        int num;
        cin >> num;

        pq.push({ num, i });

        while (!pq.empty())
        {
            Num now = pq.top();

            // 뽑은 수의 인덱스가 i - l + 1 ~ i 사이면 출력하고 종료
            // 그렇지 않으면 제거
            if (now.idx >= i - l + 1 && now.idx <= i)
            {
                cout << now.num << " ";
                break;
            }
            else pq.pop();
        }
    }


    return 0;
}
