#include <iostream>
#include <vector>

using namespace std;

int fares[201][201] = { 0, };

int solution(int n, int s, int a, int b, vector<vector<int>> fare) {
    int answer = 100000*n;

    
    // 플로이드 워샬
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            if (i != j) fares[i][j] = 100000*n;
        }
    }
    
    // fare -> (start, end, fare)
    for (int i = 0; i < fare.size(); i++)
    {
        fares[fare[i][0] - 1][fare[i][1] - 1] = fare[i][2];
        fares[fare[i][1] - 1][fare[i][0] - 1] = fare[i][2];
    }

    // k 거쳐가는 지점
    for (int k = 0; k < n; k++)
    {
        // i 출발 지점
        for (int i = 0; i < n; i++)
        {
            // j 도착 지점
            for (int j = 0; j < n; j++)
            {
                fares[i][j] = min(fares[i][j], fares[i][k] + fares[k][j]);
            }
        }
    }
    
    for(int i=0;i<n;i++)
    {
        for(int j=0;j<n;j++) cout << fares[i][j] << " ";
        cout << endl;
    }
    
    // i 거쳐가는 지점
    for(int i = 0;i<n;i++)
    {
        answer = min(fares[s-1][i] + fares[i][a-1] + fares[i][b-1], answer);
    }
    

    return answer;
}