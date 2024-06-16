#include <iostream>
#include <vector>
#include <queue>

using namespace std;

struct Position {
    int y;
    int x;
    int cost;
    int dir;
    bool operator<(Position right) const {
        return cost > right.cost;
    }
};

int w, h;
// 0 빈공간 // 1 벽 // 2 목표 지점 ?
char Map[101][101];
int dist[101][101][4];
vector<pair<int, int>> goal;

int diry[4] = { 0, 0, 1, -1 };
int dirx[4] = { 1, -1, 0, 0 };

void print()
{

    for (int i = 0; i < h; i++)
    {
        for (int j = 0; j < w; j++)
        {
            int output = 21e8;
            for (int k = 0; k < 4; k++) output = min(output, dist[i][j][k]);
            if (output == 21e8) cout << -1 << " ";
            else cout << output << " ";
        }
        cout << endl;
    }
}

void dijkstra()
{
    priority_queue<Position> pq;
    for (int i = 0; i < 4; i++)
    {
        dist[goal[0].first][goal[0].second][i] = 0;
    	pq.push({ goal[0].first, goal[0].second, 0, i });
    }

    while (!pq.empty())
    {
        //cout << endl;
        //print();

        Position now = pq.top();
        pq.pop();

        for (int i = 0; i < 4; i++)
        {
            int ny = now.y + diry[i];
            int nx = now.x + dirx[i];
            int ncost = now.cost;
            if (i != now.dir) ncost++;

            if (ny < 0 || nx < 0 || ny >= h || nx >= w) continue;
            if (Map[ny][nx] == '*') continue;

            if (dist[ny][nx][i] > ncost)
            {
                dist[ny][nx][i] = ncost;
                pq.push({ ny, nx, ncost, i });
            }
        }
    }
}

int main() {

    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> w >> h;

    for (int i = 0; i < h; i++)
    {
        for (int j = 0; j < w; j++)
        {
            cin >> Map[i][j];
            for(int k=0;k<4;k++) dist[i][j][k] = 21e8;
            if (Map[i][j] == 'C') goal.push_back({ i, j });
        }
    }

    dijkstra();

    //cout << endl;
    //print();

    int answer = 21e8;

    for (int i = 0; i < 4; i++) answer = min(answer, dist[goal[1].first][goal[1].second][i]);

    cout << answer;

    return 0;
}