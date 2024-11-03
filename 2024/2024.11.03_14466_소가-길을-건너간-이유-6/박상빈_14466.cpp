#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <queue>

using namespace std;


vector<pair<int, int>> pos;
vector<pair<int, int>> paths[101][101];

int n, k, r, ans = 0;
bool visited[101][101];

int diry[4] = { 0, 0, 1, -1 };
int dirx[4] = { 1, -1, 0, 0 };

void init()
{
	for(int i=0;i<n;i++)
	{
		for(int j=0;j<n;j++)
		{
			visited[i][j] = false;
		}
	}
}

void bfs(int idx)
{
	queue<pair<int, int>> que;
	visited[pos[idx].first][pos[idx].second] = true;
	que.push({ pos[idx].first, pos[idx].second });

	while(!que.empty())
	{
		pair<int, int> now = que.front();
		que.pop();

		for(int i=0;i<4;i++)
		{
			int ny = now.first + diry[i];
			int nx = now.second + dirx[i];

			if (ny < 0 || ny >= n || nx < 0 || nx >= n) continue;

			bool pass = true;

			// 좌표에 기록된 길을 지나는 곳이면 진행 X
			for(int j=0;j<paths[now.first][now.second].size();j++)
			{
				if (paths[now.first][now.second][j].first == ny && paths[now.first][now.second][j].second == nx)
				{
					pass = false;
					break;
				}
			}

			if (pass)
			{
				if (!visited[ny][nx])
				{
					visited[ny][nx] = true;
					que.push({ ny, nx });
				}
			}
		}
	}
}


int main()
{
	// 길이 있는 곳을 제외하고 BFS
	cin >> n >> k >> r;

	int r1, r2, c1, c2;

	for(int i=0;i<r;i++)
	{
		cin >> r1 >> c1 >> r2 >> c2;

		// 양방향
		paths[r1 - 1][c1 - 1].push_back({ r2 - 1, c2 - 1 });
		paths[r2 - 1][c2 - 1].push_back({ r1 - 1, c1 - 1 });
	}

	for(int i=0;i<k;i++)
	{
		cin >> r1 >> c1;

		// 소 좌표
		pos.push_back({ r1 - 1, c1 - 1 });
	}

	for(int i=0;i<k;i++)
	{
		// idx의 소를 기준으로 BFS
		// 길이 있는 곳은 진행 X
		init();
		bfs(i);

		// 이 후 idx 뒤의 소들의 좌표가 방문된 적 없다면 길을 건너야만 만날 수 있음
		for(int j=i + 1;j<k;j++)
		{
			if (!visited[pos[j].first][pos[j].second]) ans++;
		}
	}

	cout << ans;

	return 0;
}
