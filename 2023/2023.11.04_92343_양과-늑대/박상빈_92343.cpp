#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> informs;
vector<vector<int>> edges;
bool visited[18] = {false,};
vector<int> answer;


void dfs(int sheeps,int wolfs)
{
    // 양의 수보다 늑대수가 같거나 많아지면 종료
    if (sheeps > wolfs) answer.push_back(sheeps);
    else return;
    
    // 아래 노드로 방문하지 않았으면 방문 , 백트래킹
    for(int i=0;i<edges.size();i++)
    {
        if (visited[edges[i][0]] && !visited[edges[i][1]])
        {
            visited[edges[i][1]] = true;
            if (informs[edges[i][1]] == 0) dfs(sheeps + 1, wolfs);
            else if (informs[edges[i][1]] == 1) dfs(sheeps, wolfs + 1);
            visited[edges[i][1]] = false;
        }
    }
}

int solution(vector<int> info, vector<vector<int>> edge) {
    
    informs = info;
    edges = edge;    
    
    visited[0] = true;
    dfs(1, 0);

    
    return *max_element(answer.begin(), answer.end());
}