#include <bits/stdc++.h>
using namespace std;

int main() {
	srand(time(NULL));
	
	cout << "INSERT INTO LAST_LOGINS VALUES" << endl;
	
	for (int i = 0; i < 500; i++) {
		int z = rand() % 10 + 1;
		for (int j = 0; j < z; j++) {
			int rr = rand() % 3 + 2014;
			int mm = rand() % 12 + 1;
			int dd = rand() % 28 + 1;
			int h = rand() % 24 + 1;
			int m = rand() % 60;
			int s = rand() % 60;
			cout << '(' << i << ", " << rr << '-';
			if (mm < 10)
				cout << 0;
			cout << mm << '-';
			if (dd < 10)
				cout << 0;
			cout << dd << ' ';
			if (h < 10)
				cout << 0;
			cout << h << ':';
			if (m < 10)
				cout << 0; 
			cout << m << ':';
			if (s < 10)
				cout << 0;
			cout << s << ")," << endl;
		}
		
	}
	
}
