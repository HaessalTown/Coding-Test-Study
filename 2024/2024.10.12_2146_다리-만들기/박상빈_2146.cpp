#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <queue>

using namespace std;

int diry[4] = { 0, 0, 1, -1 };
int dirx[4] = { 1, -1, 0, 0 };

int n, ans = 21e8;
int arr[101][101];

// visited이자 대륙 넘버링
int visited[101][101];

void bfs(int r, int c, int num)
{
	visited[r][c] = num;
	queue<pair<int, int>> que;
	que.push({ r, c });

	while(!que.empty())
	{
		pair<int, int> now = que.front();
		que.pop();

		for(int i=0;i<4;i++)
		{
			int ny = now.first + diry[i];
			int nx = now.second + dirx[i];

			if (ny < 0 || ny >= n || nx < 0 || nx >= n) continue;
			if (arr[ny][nx] == 0) continue;

			if (visited[ny][nx] == 0)
			{
				visited[ny][nx] = num;
				que.push({ ny, nx });
			}
		}
	}
}

struct Node
{
	int y;
	int x;
	int cnt;
};

void bridge(int r, int c, int num)
{
	bool bridgevisited[101][101] = { false, };

	queue<Node> que;
	que.push({ r, c, 0 });

	while (!que.empty())
	{
		Node now = que.front();
		que.pop();

		if (ans <= now.cnt) continue;

		//cout << now.y << " " << now.x << " " << now.cnt << endl;

		for (int i = 0; i < 4; i++)
		{
			int ny = now.y + diry[i];
			int nx = now.x + dirx[i];
			int ncnt = now.cnt + 1;

			if (ny < 0 || ny >= n || nx < 0 || nx >= n) continue;
			if (visited[ny][nx] == num) continue;

			if (visited[ny][nx] != num && visited[ny][nx] != 0) ans = min(ans, now.cnt);

			if (!bridgevisited[ny][nx])
			{
				bridgevisited[ny][nx] = true;
				que.push({ ny, nx, ncnt });
			}
		}
	}
}


int main()
{
	cin >> n;

	for(int i=0;i<n;i++)
	{
		for(int j=0;j<n;j++)
		{
			cin >> arr[i][j];
		}
	}

	int num = 1;

	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n; j++)
		{
			if (visited[i][j] == 0 && arr[i][j] != 0)
			{
				bfs(i, j, num++);
			}
		}
	}



	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n; j++)
		{
			if (visited[i][j] != 0)
			{
				//cout << "다리 건설 시도 : " << i << " " << j << " " << visited[i][j] << endl;
				bridge(i, j, visited[i][j]);
				//cout << "결과 : " << ans << endl;
			}
		}
	}

	cout << ans;

	return 0;
}
