#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <set>

using namespace std;

int diry[4] = { 0, 0, 1, -1 };
int dirx[4] = { 1, -1, 0, 0 };

int n, m;
int itemcnt = 0;

int sy, sx, ey, ex;

char house[51][51];
bool visited[51][51][35] = { false, };

int bfs() {
    queue<pair<pair<int, int>, pair<int, int>>> que;

    que.push({ {sy, sx}, {0, 0} });
    visited[sy][sx][0] = true;

    while(!que.empty())
    {
        int y = que.front().first.first;
        int x = que.front().first.second;
        int cost = que.front().second.first;
        int item = que.front().second.second;

        que.pop();

        if (house[y][x] == 'E')
        {
            if (item == itemcnt) return cost;
        }

        for (int i = 0; i < 4; i++)
        {
            int ny = y + diry[i];
            int nx = x + dirx[i];

            if (ny < 0 || ny >= m || nx < 0 || nx >= n) continue;
            if (house[ny][nx] == '#') continue;

            if (house[ny][nx] >= '0' && house[ny][nx] <= '4')
            {
                int nitemstate = item | (1 << (house[ny][nx] - '0'));

                if (visited[ny][nx][nitemstate]) continue;

                que.push({ {ny, nx}, {cost + 1, nitemstate} });
                visited[ny][nx][nitemstate] = true;
            }
            else
            {
                if (visited[ny][nx][item]) continue;

                que.push({ {ny, nx}, {cost + 1, item} });
                visited[ny][nx][item] = true;
            }
        }
      
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> n >> m;

    int cnt = 0;

    for (int i = 0; i < m; i++)
    {
        for (int j = 0; j < n; j++)
        {
            cin >> house[i][j];

            if (house[i][j] == 'S')
            {
                sy = i;
                sx = j;
                house[i][j] = '.';
            }
            else if (house[i][j] == 'X')
            {
                house[i][j] = cnt + '0';
                itemcnt = itemcnt | (1 << cnt);
                cnt++;
            }
        }
    }

    cout << bfs();

    return 0;
}