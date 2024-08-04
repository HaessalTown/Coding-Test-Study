#include <iostream>
#include <set>

using namespace std;

int n, m;
set<int> nums;
set<pair<int, pair<int, int>>> lens;

int main() {

    cin >> n >> m;

    nums.insert(-1);
    nums.insert(n + 1);

    lens.insert({-(n + 1), {-1, n + 1}});

    for(int i=0;i<m;i++)
    {
        int num;
        cin >> num;

        nums.insert(num);

        auto iter = nums.find(num);
        iter++;
        int e = *iter;

        iter = nums.find(num);
        iter--;
        int s = *iter;

        lens.erase({-(e - s - 1), {s, e}});
        lens.insert({-(num - s - 1), {s, num}});
        lens.insert({-(e - num - 1), {num, e}});

        cout << -(*lens.begin()).first << endl;
    }


    return 0;
}
// #include <iostream>
// #include <set>

// using namespace std;

// int n, m, ans = -21e8;
// set<int> removed;

// int main() {

//     cin >> n >> m;

//     // arr 라는 곳에 0 ~ n 까지 있다고 가정
//     for (int i = 0; i < m; i++)
//     {
//         int num;
//         cin >> num;

//         removed.insert(num);

//         auto iter = removed.find(num);
//         int tmp = num;
//         ans = -21e8;

//         // 10만 * 10만 -> 터지네 ..
//         // left 확인
//         for (; iter != removed.begin(); iter--)
//         {
//             ans = max(ans, (tmp - *iter) - 1);
//             tmp = *iter;
//         }
//         tmp = *iter;
//         ans = max(ans, (tmp - 0));

//         iter = removed.find(num);
//         tmp = num;

//         // right 확인
//         for (; iter != removed.end(); iter++)
//         {
//             ans = max(ans, (*iter - tmp) - 1);
//             tmp = *iter;
//         }
//         ans = max(ans, n - tmp);


//         cout << ans << endl;
//     }


//     return 0;
// }