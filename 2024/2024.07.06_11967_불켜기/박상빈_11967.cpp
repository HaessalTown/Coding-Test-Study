#include <iostream>
#include <vector>
#include <unordered_map>
#include <queue>

using namespace std;

struct Position
{
	int y;
	int x;
};

int diry[4] = { 0, 0, 1, -1 };
int dirx[4] = { 1, -1, 0, 0 };

int n, m, ans = 1;
int arr[101][101];
// 방문 좌표
bool visited[101][101];
// 불은 켠 방의 좌표
bool light[101][101];
// 좌표 압축
// (y * n) + x
unordered_map<int, vector<Position>> um;

void bfs(int y, int x)
{
	visited[y][x] = true;
	queue<Position> que;
	que.push({ y, x });

	while (!que.empty())
	{
		Position now = que.front();
		que.pop();

		int pos = (now.y * n) + now.x;

		// 현재 방에서 켤 수 있는 방의 모든 불을 켜
		for (int i = 0; i < um[pos].size(); ++i)
		{
			int y = um[pos][i].y;
			int x = um[pos][i].x;

			// 이미 켠 방이면 아래 4방향 방문 때문에 계속 진행됨 주의
			if (light[y][x]) continue;

			light[y][x] = true;
			ans++;

			// 불이 켜진 방 기준으로 방문했던 방이 있다면 계속 진행 가능
			for (int j = 0; j < 4; j++)
			{
				int ny = y + diry[j];
				int nx = x + dirx[j];

				if (ny < 1 || ny > n || nx < 1 || nx > n) continue;

				if (visited[ny][nx])
				{
					visited[y][x] = true;
					que.push({ y, x });
				}
			}
		}


		// 4방향 탐색 진행
		for (int i = 0; i < 4; ++i)
		{
			int ny = now.y + diry[i];
			int nx = now.x + dirx[i];

			if (ny < 1 || ny > n || nx < 1 || nx > n) continue;
			if (!light[ny][nx]) continue;
			if (!visited[ny][nx])
			{
				visited[ny][nx] = true;
				que.push({ ny, nx });
			}
		}
	}
}


int main()
{
	ios::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);

	cin >> n >> m;

	for(int i=1;i<=n;++i)
	{
		for(int j=1;j<=n;++j)
		{
			arr[i][j] = 0;
		}
	}


	for(int i=0;i<m;++i)
	{
		int x, y;
		int a, b;
		cin >> x >> y >> a >> b;

		um[(y * n) + x].push_back({ b, a });
	}

	light[1][1] = true;
	bfs(1, 1);

	cout << ans;


	return 0;
}