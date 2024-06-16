#include <iostream>
#include <queue>

using namespace std;

int R, C;
bool fvisited[1001][1001] = {false, };
bool jvisited[1001][1001] = {false, };
char arr[1001][1001];
int visited[1001][1001] = {0,};

int dirx[4] = {0, 0, 1, -1};
int diry[4] = {1, -1, 0, 0};

int MAX = 1000000;

queue<pair<pair<int, int>, int>> que;

int positionx, positiony;

void solution()
{
    
    //fire bfs
    while(!que.empty())
    {
        pair<pair<int, int>, int> tmp = que.front();
        que.pop();
        
        for(int i=0;i<4;i++)
        {
            int nx = tmp.first.first + dirx[i];
            int ny = tmp.first.second + diry[i];
            
            if (nx >= 0 && ny >= 0 && nx < R && ny < C && !fvisited[nx][ny])
            {
                if (arr[nx][ny] == '#') continue;
                fvisited[nx][ny] = true;
                visited[nx][ny] = tmp.second;
                que.push({{nx, ny}, tmp.second + 1});
            }
        }
    }
    
    que.push({{positionx, positiony}, 1});
    
    // bfs
    while(!que.empty())
    {
        pair<pair<int, int>, int> tmp = que.front();
        que.pop();
        
        for(int i=0;i<4;i++)
        {
            int nx = tmp.first.first + dirx[i];
            int ny = tmp.first.second + diry[i];
            
            if (nx >= 0 && ny >= 0 && nx < R && ny < C)
            {
                if (arr[nx][ny] == '#') continue;
                if (visited[nx][ny] > tmp.second && arr[nx][ny] == '.' && !jvisited[nx][ny])
                {
                    jvisited[nx][ny] = true;
                    que.push({{nx, ny}, tmp.second + 1});

                }
            }
            if (nx < 0 || ny < 0 || nx >= R || ny >= C)
            {
                cout << tmp.second << endl;
                return;
            }
        }
    }
    
    printf("IMPOSSIBLE\n");
}

int main()
{
    
    cin >> R >> C;
    
    for(int i=0;i<R;i++)
    {
        for(int j=0;j<C;j++)
        {
            visited[i][j] = MAX;
            char input;
            cin >> input;
            if (input == '#') visited[i][j] = -1;
            if (input == 'J'){
                positionx = i;
                positiony = j;
            }
            if (input == 'F') {
                visited[i][j] = 0;
                que.push({{i, j}, 1});
                fvisited[i][j] = true;
            }
            arr[i][j] = input;
        }
    }
    
    solution();


    return 0;
}