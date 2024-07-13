#include <iostream>
#include <queue>

using namespace std;

struct Info
{
	int num; // 이모티콘의 수
	int cnt; // 횟수
	int clip; // 복사된 이모티콘의 수
};

int s;
//		num   clip
int visited[1001][1001];

int bfs()
{
	queue<Info> que;
	que.push({ 1, 0, 0 });

	while (!que.empty())
	{
		Info now = que.front();
		que.pop();

		if (now.num == s) return now.cnt;

		for (int i = 0; i < 3; i++)
		{
			// 3가지 상태
			int nnum = now.num;
			int ncnt = now.cnt + 1;
			int nclip = now.clip;

			// 0 화면에 있는 이모티콘을 모두 복사해서 클립보드에 저장
			// 1 클립보드에 있는 모든 이모티콘을 화면에 붙여넣기
			// 2 화면에 있는 이모티콘 중 하나를 삭제
			if (i == 0) nclip = nnum;
			else if (i == 1 && nclip != 0) nnum += nclip;
			else if (i == 2) nnum--;

			if (nnum < 0 || nnum > 1000) continue;
			if (visited[nnum][nclip] < ncnt) continue;

			visited[nnum][nclip] = ncnt;
			que.push({ nnum, ncnt, nclip });
		}
	}
}


int main()
{
	cin >> s;

	for (int i = 0; i < 1001; i++)
	{
		for(int j=0;j<1001;j++) visited[i][j] = 21e8;
	}

	cout << bfs();

	return 0;
}