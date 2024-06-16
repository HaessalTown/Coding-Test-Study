#include <iostream>
#include <vector>

using namespace std;

int checksum[1001][1001] = {0,};

int solution(vector<vector<int>> board, vector<vector<int>> skill) {
    int answer = 0;
    
    for(int idx=0;idx<skill.size();idx++)
    {
        int number = 1;
        
        // type, r1, c1, r2, c2, degree        
        if (skill[idx][0] == 1) number *= -1;
        
        int r1, c1, r2, c2, degree;
        
        r1 = skill[idx][1];
        r2 = skill[idx][3] + 1;
        c1 = skill[idx][2];
        c2 = skill[idx][4] + 1;
        degree = skill[idx][5];
        
        // left to right
        checksum[r1][c1] += (number * degree);
        checksum[r1][c2] += (number * -1 * degree);
        // top to bottom
        checksum[r2][c1] += (number * -1 * degree);
        checksum[r2][c2] += (number * degree);
    }
    
    int x, y;
    x = board.size();
    y = board[0].size();
    
    for(int i=0;i<x;i++)
    {
        for(int j=1;j<y;j++)
        {
            checksum[i][j] += checksum[i][j-1];
        }
    }
    
    for(int i=1;i<x;i++)
    {
        for(int j=0;j<y;j++)
        {
            checksum[i][j] += checksum[i-1][j];
        }
    }
    
    for(int i=0;i<x;i++)
    {
        for(int j=0;j<y;j++)
        {
            board[i][j] += checksum[i][j];
            if(board[i][j] > 0) answer++;
        }
    }
    
    return answer;
}