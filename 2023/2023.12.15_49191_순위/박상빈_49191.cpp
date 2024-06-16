#include <iostream>
#include <vector>

using namespace std;

int ranked[101][101] = {0,};

int solution(int n, vector<vector<int>> results) {
    int answer = 0;
    
    for(int i=0;i<results.size();i++)
    {
        ranked[results[i][0]][results[i][1]] = 1;
        ranked[results[i][1]][results[i][0]] = -1;
    }
    
    for(int k=1;k<=n;k++)
    {
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                // i > k > j // i < k < j
                if (ranked[i][k] == 1 && ranked[k][j] == 1) ranked[i][j] = 1;
                else if (ranked[i][k] == -1 && ranked[k][j] == -1) ranked[i][j] = -1;
            }
        }
    }
    
    for(int i=0;i<=n;i++)
    {
        int cnt = 0;
        for(int j=0;j<=n;j++)
        {
            if (ranked[i][j] != 0) cnt++;
        }
        if (cnt == n - 1) answer++;
    }
    
    return answer;
}