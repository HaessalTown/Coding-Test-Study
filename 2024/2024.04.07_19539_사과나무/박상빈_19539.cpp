#include <iostream>
#include <algorithm>

using namespace std;

int n, sum = 0, cnt = 0;
int arr[100001];


int main() {

	cin >> n;

	for(int i=0;i<n;i++)
	{
		cin >> arr[i];

		sum += arr[i];
		cnt += (arr[i] / 2);
	}

	if (sum % 3 == 0)
	{
		if (cnt >= (sum / 3)) cout << "YES\n";
		else cout << "NO\n";
	}
	else
	{
		cout << "NO\n";
	}
}