#include <iostream>
#include <vector>
#include <map>

using namespace std;

int N;
vector<int> XI;
map<int, int> XIm;

void solution()
{
    // iter를 통해 맵을 순회하면서 해당 번호의 값을 cnt로 바꾼다
    int cnt = 0;
    for(auto iter = XIm.begin(); iter != XIm.end(); iter++)
    {
        iter->second = cnt;
        cnt++;
    }
    
    for(int i=0;i<N;i++)
    {
        printf("%d ", XIm[XI[i]]);
    }
    
    printf("\n");

}

int main()
{
    // map에 넣어볼까 ?
    scanf("%d", &N);
    
    for(int i=0;i<N;i++)
    {
        int input;
        scanf("%d", &input);
        
        XI.push_back(input);
        XIm[input] = 0;
    }
    
    solution();

    return 0;
}