#define _CRT_SECURE_NO_WARNINGS
#include <iostream>

using namespace std;

int k;
int magnet[4][8];
int firstidx[4];

bool isrotate[4];
int direction[4];

// 1 시계방향 회전 idx - 1
// -1 반시계방향 회전 idx + 1

void rotate()
{
	for(int i=0;i<4;i++)
	{
		if (isrotate[i])
		{
			if (direction[i] == 1) firstidx[i] = (firstidx[i] + 7) % 8;
			else firstidx[i] = (firstidx[i] + 1) % 8;
		}
	}
}

void judge(int idx, int dir)
{
	isrotate[idx] = true;
	direction[idx] = dir;

	int tmp = idx;
	// 오른쪽
	while(++tmp <= 3)
	{
		if (isrotate[tmp - 1] && magnet[tmp - 1][(firstidx[tmp - 1] + 2) % 8] != magnet[tmp][(firstidx[tmp] + 6) % 8])
		{
			isrotate[tmp] = isrotate[tmp - 1];
			direction[tmp] = -direction[tmp - 1];
		}
	}

	tmp = idx;
	// 왼쪽
	while(--tmp >= 0)
	{
		if (isrotate[tmp + 1] && magnet[tmp + 1][(firstidx[tmp + 1] + 6) % 8] != magnet[tmp][(firstidx[tmp] + 2) % 8])
		{
			isrotate[tmp] = isrotate[tmp + 1];
			direction[tmp] = -direction[tmp + 1];
		}
	}

	rotate();
}

int main()
{
	ios::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);

	for(int i=0;i<4;i++)
	{
		for(int j=0;j<8;j++)
		{
			char input;
			cin >> input;

			magnet[i][j] = (int)(input - '0');
		}
		firstidx[i] = 0;
	}

	cin >> k;

	for(int i=0;i<k;i++)
	{
		int num, dir;
		cin >> num >> dir;

		for(int j=0;j<4;j++)
		{
			isrotate[j] = false;
			direction[j] = 0;
		}

		judge(num - 1, dir);
	}

	int answer = 0;
	int num = 1;
	for(int i=0;i<4;i++)
	{
		if (magnet[i][firstidx[i]] == 1)
		{
			answer += num;
		}
		num *= 2;
	}

	cout << answer;

	return 0;
}