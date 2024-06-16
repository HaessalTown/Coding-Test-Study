#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <vector>

using namespace std;

// 동 남 서 북 대각선
int dirx[8] = { 1, 0, -1, 0, -1, -1, 1, 1 };
int diry[8] = { 0, 1, 0, -1, -1, 1, -1, 1 };

struct Rectangle
{
    int y;
    int x;
};

int n;
vector<Rectangle> arr;
int DP[1000][2];



int main()
{
    ios::sync_with_stdio(false);
    cin.tie(0);

    // case는 두가지 ?
    // -ㅣ-ㅣ-ㅣ
    // ㅣ-ㅣ-ㅣ-


    cin >> n;

    for(int i=0;i<n;i++)
    {
        int y, x;

        cin >> y >> x;

        // 긴거를 x로 넣음
        arr.push_back({ y, x });
    }

    // 세로           가로
    DP[0][0] = arr[0].y;
    DP[0][1] = arr[0].x;


    for(int i=1;i<arr.size();i++)
    {
		// 0 은 세로 기준
        DP[i][0] = max(DP[i - 1][1] + abs(arr[i - 1].y - arr[i].x) + arr[i].y, DP[i - 1][0] + abs(arr[i - 1].x - arr[i].x) + arr[i].y);

        // 1 은 가로 기준
        DP[i][1] = max(DP[i - 1][1] + abs(arr[i - 1].y - arr[i].y) + arr[i].x, DP[i - 1][0] + abs(arr[i - 1].x - arr[i].y) + arr[i].x);
    }

    printf("%d", max(DP[arr.size() - 1][0], DP[arr.size() - 1][1]));

    return 0;
}