/*

https://practice.geeksforgeeks.org/problems/next-larger-element/0

To run the file, run below command.
g++ nextLargerElement.cpp && ./a.out <../TestInput/nextLargerElement.txt

*/

#include <bits/stdc++.h>
using namespace std;

typedef unsigned long long int uli;


const int MAX_SIZE = 10000001;
uli LARGEST = 1000000000000000000llu ;
uli InpArray[MAX_SIZE];

void next_larger_element() {
	stack<uli> S;
	uli INF = LARGEST+1;
	int n;
	scanf("%d", &n);

	for(int i=0;i<n;i++)
		scanf("%llu", InpArray+i);

	for(int i=n-1;i>=0;i--) {

		uli Out     = INF;
		uli Element = InpArray[i];

		while(S.size() && Element>S.top())
			S.pop();

		if(S.size())
			Out = S.top();

		S.push(Element);

		InpArray[i] = Out;
	}

	for(int i=0;i<n;i++) {
		if(InpArray[i]==INF)
			printf("-1 ");
		else
			printf("%llu ", InpArray[i]);
	}
	printf("\n");
	while(S.size()) S.pop();
}



int main(void) {
	int t;
	scanf("%d", &t);
	while(t--) {
		next_larger_element();
	}

	return 0;
}