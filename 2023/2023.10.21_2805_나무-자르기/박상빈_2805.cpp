#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int solution(int m, vector<int> tree) {
    
    int answer = 0;
    // 작은 순서대로 정렬
    sort(tree.begin(), tree.end());
    
    // 절단기의 높이 answer 에 따라서 가져갈 수 있는 양이 정해짐
    // 중간 값을 기준으로 잘랐다고 했을 때 걔보다 높은 애들이 전부 걔랑 같은 높이가 될것이므로
    // 전체 양 - (size - index) * answer 얘가 최소가 되는 애를 찾으면 되겠네
    int start = 0;
    int end = tree[tree.size() - 1];
    int mid;
    
    while(start<=end)
    {
        long long int sum = 0;
        mid = (start + end) / 2;
        for(auto elem : tree)
        {
            if (elem > mid) sum += elem - mid;
        }
        if (sum<m) end = mid - 1;
        else {
            answer = mid;
            start = mid + 1;
        }
    }
    
    return answer;
}

int main() {

    int n, m;
    
    cin >> n >> m;
    
    vector<int> trees;
    
    for(int i=0;i<n;i++)
    {
        int input;
        cin >> input;
        trees.push_back(input);
    }

    cout << solution(m, trees) << endl;

    return 0;
}