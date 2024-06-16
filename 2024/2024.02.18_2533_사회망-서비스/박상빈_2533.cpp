#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n;
vector<int> graph[1000001];
bool visited[1000001] = { false, };
int cnt = 0;

// 0
// 1

int DFS(int idx)
{
    visited[idx] = true;

    // 리프노드인지 확인하고 리프노드라면 리턴값을 줘서 해당 부모를 얼리로 선택
    // 자식으로 타고 들어가는데 하나라도 리턴값이 0인경우 내가 얼리가 되어야한다
    bool flag = false;
    for (int i = 0; i < graph[idx].size(); i++)
    {
        if (!visited[graph[idx][i]])
        {
            if (DFS(graph[idx][i]) == 0)
            {
                flag = true;
            }
        }
    }

    if (flag)
    {
        cnt++;
        return 1;
    }

    return 0;

}

int main()
{
    scanf("%d", &n);

    for (int i = 0; i < n - 1; i++)
    {
        int u, v;

        scanf("%d %d", &u, &v);

        graph[u].push_back(v);
        graph[v].push_back(u);
    }

    DFS(1);
    printf("%d\n", cnt);


    return 0;
}