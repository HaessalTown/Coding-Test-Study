#include <iostream>
#include <stack>

using namespace std;

int main()
{
    int n;
    stack<int> buildings;
    
    scanf("%d", &n);
    
    long long sum = 0;
    
    for(int i=0;i<n;i++)
    {
        int input;
        scanf("%d", &input);
        
        while(!buildings.empty())
        {
            if (buildings.top() > input)
            {
                sum += buildings.size();
                break;
            }
            buildings.pop();
        }
        buildings.push(input);
    }
    
    printf("%lld", sum);
    
    
    return 0;
}