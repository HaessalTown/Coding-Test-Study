#include <iostream>
#include <queue>
#include <unordered_map>

using namespace std;

// 어떤 칸에서 시작해서 탈출한다면 해당 경로에서 시작하는 경우는 모두 탈출 가능하다고 볼 수 있다

int diry[4] = { 0, 0, 1, -1 };
int dirx[4] = { 1, -1, 0, 0 };

int n, m;
unordered_map<char, int> dir;
char Map[2001][2001];
int cost[2001][2001];
int visited[2001][2001] = { 0, };
int answer = 0;

int findmin(int y, int x)
{
	int mini = cost[y][x];
	queue<pair<int, int>> que;
	que.push({ y, x });

	while(!que.empty())
	{
		pair<int, int> now = que.front();
		que.pop();

		int i = dir[Map[now.first][now.second]];
		int ny = now.first + diry[i];
		int nx = now.second + dirx[i];

		mini = min(mini, cost[ny][nx]);
		if (ny == y && nx == x) break;
		que.push({ ny, nx });
	}

	return mini;
}

void bfs(int y, int x, int num)
{
	// y, x 에서 시작해서 벽 밖으로 나간다면 return
	// 갔던 곳을 또 가는것은 X, 벽 밖으로 못나가고 que가 끝나면
	// 해당 과정 중 바깥과 맞닿아있는 애들 중 최소값 answer에 추가
	queue<pair<int, int>> que;

	visited[y][x] = num;
	que.push({ y, x });

	while(!que.empty())
	{
		pair<int, int> now = que.front();
		que.pop();

		int i = dir[Map[now.first][now.second]];
		int ny = now.first + diry[i];
		int nx = now.second + dirx[i];

		if (ny < 0 || nx < 0 || ny >= n || nx >= m)
		{
			// 탈출 가능한 경우
			return;
		}

		if (visited[ny][nx] == 0)
		{
			visited[ny][nx] = num;
			que.push({ ny, nx });
		}
		else if (visited[ny][nx] < num) return;
		else if (visited[ny][nx] == num)
		{
			// 탈출 못한 경우
			// 맞닿은 위치부터 후보가 될 수 있음
			answer += findmin(ny, nx);
			break;
		}
	}

}


int main() {

	ios::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);

	cin >> n >> m;

	// 방향 설정
	dir['R'] = 0;
	dir['L'] = 1;
	dir['D'] = 2;
	dir['U'] = 3;

	for(int i=0;i<n;i++)
	{
		for(int j=0;j<m;j++)
		{
			cin >> Map[i][j];
		}
	}

	for(int i=0;i<n;i++)
	{
		for(int j=0;j<m;j++)
		{
			cin >> cost[i][j];
		}
	}

	int num = 1;
	for(int i=0;i<n;i++)
	{
		for(int j=0;j<m;j++)
		{
			if (visited[i][j] == 0) bfs(i, j, num++);
		}
	}

	cout << answer;


    return 0;
}