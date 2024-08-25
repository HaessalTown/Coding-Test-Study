#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <queue>

using namespace std;

int n, mid;
priority_queue<int, vector<int>, greater<int>> rightheap;
priority_queue<int> leftheap;

int main()
{
	scanf("%d", &n);

	for (int i = 0; i < n; i++)
	{
		int num;
		scanf("%d", &num);

		if (i == 0)
		{
			mid = num;
		}
		else
		{
			if (mid <= num) rightheap.push(num);
			else leftheap.push(num);

			if (leftheap.size() >= rightheap.size() + 2)
			{
				rightheap.push(mid);
				mid = leftheap.top();
				leftheap.pop();
			}
			else if (rightheap.size() > leftheap.size())
			{
				leftheap.push(mid);
				mid = rightheap.top();
				rightheap.pop();
			}
		}

		if (i != 0 && i % 2 == 1 && !leftheap.empty()) printf("%d\n", leftheap.top());
		else printf("%d\n", mid);
	}



	return 0;
}