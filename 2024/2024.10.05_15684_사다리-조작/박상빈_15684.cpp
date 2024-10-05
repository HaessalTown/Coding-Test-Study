#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int answer = 21e8;
int n, m, h;
int Map[31][21];

int diry[3] = { 0, 0, 1 };
int dirx[3] = { -1, 1, 0 };

void print()
{
    for (int i = 0; i < h; i++)
    {
        for (int j = 0; j < n; j++)
        {
            cout << Map[i][j] << " ";
        }
        cout << endl;
    }
}

bool test()
{
    // 모든 i 번째에 대해서 i 에 도달해야한다
    int idx;
    for (int j = 0; j < n; j += 2)
    {
        // 위에서 출발 아래로
        int y = 0;
        int x = j;
        while (true)
        {
            for (int i = 0; i < 2; i++)
            {
                int ny = y + diry[i];
                int nx = x + dirx[i];

                if (ny < 0 || nx < 0 || ny >= h || nx >= n) continue;
                // 가로선이 존재하면 해당방향으로 한칸 더 가
                // 그래야 세로선을 다시 만날테니까
                // 한 세로선을 기준으로 가로선은 하나만 존재할 수 있으니 바로 종료
                if (Map[ny][nx] == 1)
                {
                    ny += diry[i];
                    nx += dirx[i];

                    y = ny;
                    x = nx;
                    break;
                }
            }

            // 내려가기
            y++;

            // 범위 벗어나면 종료
            if (y >= h) break;
        }
        // 끝에 도착했을때
        // i 에서 i 로 간거면 ? OK
        if (x != j) return false;
    }

    return true;
}

void dfs(int depth, int y, int x)
{
    if (depth <= 3)
    {
        bool res = test();
        if (res) answer = min(answer, depth);
        // 최대 3개까지만 허용
        if (depth == 3) return;
    }

    // 중복 제거
    for (int j = x; j < n; j++)
    {
        if (Map[y][j] == 0)
        {
            Map[y][j] = 1;
            dfs(depth + 1, y, j + 1);
            Map[y][j] = 0;
        }
    }

    for (int i = y + 1; i < h; i++)
    {
        for (int j = 0; j < n; j++)
        {
            if (Map[i][j] == 0)
            {
                Map[i][j] = 1;
                dfs(depth + 1, i, j + 1);
                Map[i][j] = 0;
            }
        }
    }

}

int main() {

    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> n >> m >> h;

    n = (n * 2) - 1;

    for (int i = 0; i < h; i++)
    {
        for (int j = 0; j < n; j+=2)
        {
            Map[i][j] = 1;
        }
    }

    for (int i = 0; i < m; i++)
    {
        int a, b;
        cin >> a >> b;

        Map[a - 1][(b * 2) - 1] = 1;
    }

    dfs(0, 0, 0);

    if (answer == 21e8) answer = -1;
    cout << answer;

    return 0;
}