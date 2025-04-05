#include <iostream>
#include <string>
#include <vector>
#include <unordered_set>

using namespace std;

int solution(vector<vector<string>> relation) {
    int row = relation.size();
    int col = relation[0].size();
    vector<int> candidate_keys;

    for (int i = 1; i < (1 << col); i++) 
    {
        unordered_set<string> s;

        for (int r = 0; r < row; r++) 
        {
            string temp = "";
            for (int c = 0; c < col; c++) if (i & (1 << c)) temp += relation[r][c] + "|";
            s.insert(temp);
        }

        if (s.size() == row) 
        {
            bool isMinimal = true;
            for (int key : candidate_keys) 
            {
                if ((key & i) == key) 
                {
                    isMinimal = false;
                    break;
                }
            }
            if (isMinimal) candidate_keys.push_back(i);
        }
    }

    return candidate_keys.size();
}