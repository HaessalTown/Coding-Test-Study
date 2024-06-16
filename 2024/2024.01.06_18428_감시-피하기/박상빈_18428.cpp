#include <iostream>

using namespace std;

int n;
char maps[7][7];
bool success = false;

int dirx[4] = {0, 0, -1, 1};
int diry[4] = {-1, 1, 0, 0};

bool teacherdetect(int x, int y)
{
    for(int k=0;k<4;k++)
    {
        int i = x;
        int j = y;
        while(1)
        {
            i += dirx[k];
            j += diry[k];
            if (i<0 || i>=n || j<0 || j >= n) break;
            
            if (maps[i][j] == 'O') break;
            else if (maps[i][j] == 'S') return true;
        }
    }
    return false;
}

void dfs(int cnt)
{
    if (cnt == 3) 
    {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if (maps[i][j] == 'T')
                {
                    if (teacherdetect(i, j)) return;
                }
            }
        }
        success = true;
        return;
    }
    
    for(int i=0;i<n;i++)
    {
        for(int j=0;j<n;j++)
        {
            if (maps[i][j] == 'X') 
            {
                maps[i][j] = 'O';
                dfs(cnt + 1);
                maps[i][j] = 'X';
            }
        }
    }
    
}

void solution()
{
    // 완탐 장애물을 세개를 설치하고 선생님의 위치에서 학생들이 보이는지 체크    
    dfs(0);
    
    if (success) cout << "YES" << endl;
    else cout << "NO" << endl;
}

int main()
{
    cin >> n;    
    
    for(int i=0;i<n;i++)
    {
        for(int j=0;j<n;j++)
        {
            char input;
            cin >> input;        
            maps[i][j] = input;
        }
    }
    
    solution();
    
    return 0;
}