/*


File : farthestBiggest.cpp
Author : luke

Source Problem URL:
https://practice.geeksforgeeks.org/problems/maximum-index/0/


To run the file, run below command:
g++ farthestBiggest.cpp && ./a.out <farthestBiggest.txt


*/

#include <bits/stdc++.h>
#define DEBUG 0
using namespace std;
typedef long long unsigned int uli;
const int LIMIT = 1000001;
uli myArr[LIMIT];
int ArraySize;

uli lMin[LIMIT];
uli rMax[LIMIT];


void printDebug() 
{
	if(!DEBUG) 
	{
		return;
	}
	cout<<"Input Array\n";
	for(int i=0;i<ArraySize;i++) 
	{
		cout<<myArr[i]<<' ';
	}
	cout<<"\nLMin Index\n";
	for(int i=0;i<ArraySize;i++) 
	{
		cout<<lMin[i]<<' ';
	}
	cout<<"\nRMax Index\n";
	for(int i=0;i<ArraySize;i++) 
	{
		cout<<rMax[i]<<' ';
	}
	cout<<"\n\n";

}

void readArray() {
	int _n;
	scanf("%d", &_n);
	assert(_n<LIMIT);
	for(int i=0;i<_n;i++) {
		scanf("%llu", myArr+i);
	}
	ArraySize = _n;
}

void computeLeftMin() 
{
	// compute lMin
	int _leftIndex = 0;
	lMin[_leftIndex] = myArr[_leftIndex];
	
	while(++_leftIndex<ArraySize)
		lMin[_leftIndex] = std::min(lMin[_leftIndex-1], myArr[_leftIndex]);
}

void computeRightMax()
{
	int _rightIndex =  ArraySize - 1;
	// compute rMax
	rMax[_rightIndex] = myArr[_rightIndex];
	while(_rightIndex-->0)
		rMax[_rightIndex] = std::max(myArr[_rightIndex], rMax[1+_rightIndex]);
}

int computeFarthestBiggest() 
{
	
	readArray();

	computeRightMax();

	computeLeftMin();

	printDebug();

	int _lMinIndex;
	int _rMaxIndex;

	_lMinIndex = _rMaxIndex = 0;

	int maxDistance = 0;

	while(_lMinIndex<ArraySize && _rMaxIndex<ArraySize) 
	{
		if( lMin[_lMinIndex] <=  rMax[_rMaxIndex] )
		{
			maxDistance = std::max(_rMaxIndex - _lMinIndex, maxDistance);
			_rMaxIndex++;
		}
		else 
		{
			_lMinIndex++;
		}
	}

	return maxDistance;//==0?-1:maxDistance;
}

int main(void) {
	int t, a, b;
	scanf("%d", &t);
	while(t--) {
		printf("%d\n", computeFarthestBiggest());
		// readArray();
		// printf("%d\n", maxIndexDiff(myArr, ArraySize));

	}
	return 0;
}