#include <bits/stdc++.h>
using namespace std;

int main() {
	srand(time(NULL));
	for(int i = 0; i < 500; i++) {
		
		string A;
		cin >> A;
		getline(cin, A);
		cout << '(' << i << " " << A;
		
		int z = rand() % 10 + 1;
		for (int j = 0; j < z; j++) {
			int yy = rand() % 3 + 2015;
			int mm = rand() % 12 + 1;
			int dd = rand() % 28 + 1;
			int hh = rand() % 24;
			int mi = rand() % 60;
			int ss = rand() % 60;
			cout << yy << '-';
			if (mm < 10)
				cout << 0;
			cout << mm << '-';
			if (dd < 10)
				cout << 0;
			cout << dd << ' ';
			if (hh < 10)
				cout << 0;
			cout << hh << ':';
			if (mi < 10)
				cout << 0;
			cout << mi << ':';
			if (ss < 10)
				cout << 0;
			cout << ss << ',';
		}
		cout << endl;
	}
	return 0;
}
