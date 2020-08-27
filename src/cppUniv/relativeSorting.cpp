/*


File : relativeSorting.cpp
Author : luke

Source Problem URL:
https://practice.geeksforgeeks.org/problems/relative-sorting/0/


To run the file, run below command:
g++ relativeSorting.cpp && ./a.out <relativeSorting.txt


*/

#include <bits/stdc++.h>
#define DEBUG 0
using namespace std;

const int LIMIT = 1000001;

const int LOWEST = 0;
const int HIGHEST = LIMIT-1;

int targetArr[LIMIT];
int patternArr[LIMIT];
int frequency[LIMIT];

void relativeSort(int n, int m)
{
	//set frequency to -1
	for(int i = LOWEST; i<=HIGHEST; i++) 
		frequency[i] = -1;
		
	//for each element in pattern, set the freq to 0
	for(int i = 0; i<m; i++) 
	{
		int _elem = patternArr[i];
		assert(_elem>=LOWEST && _elem<=HIGHEST);
		frequency[_elem] = 0;
	}

	//for each element in targetArr, set the right freq if initial freq is <> -1
	 //blanken the array with -1 for points with such elements
	for(int i = 0; i<n; i++) 
	{
		int _elem = targetArr[i];
		assert(_elem>=LOWEST && _elem<=HIGHEST);
		if(frequency[_elem]>-1) 
		{
			if(DEBUG) {cout<<_elem<<' ';}
			targetArr[i] = -1;
			frequency[_elem] += 1;
			if(DEBUG) {cout<<endl;}
		}
	}


	//sort the target array
	sort(targetArr, targetArr+n);

	//re iterate over patternArr, and replace the -1 in target with actual values 
	//with relative order from pattern
	int _targetStartIndex = -1;
	for(int I = 0; I<m; I++) 
	{
		int _elem = patternArr[I];
		int _freq = frequency[_elem];
		for(int J=1; J <=_freq; J++)
		{
			_targetStartIndex++;
			assert(targetArr[_targetStartIndex]==-1);
			targetArr[_targetStartIndex] = _elem;
		}
	}
	//exit

}

int main(void) 
{
	int t, n, m;
	

	cin>>t;
	while(t--) 
	{
		cin>>n>>m;
		for(int i=0;i<n;i++) 
		{
			cin>>targetArr[i];
		}
		for(int i=0;i<m;i++) 
		{
			cin>>patternArr[i];
		}
		relativeSort(n, m);
		for(int i=0;i<n;i++) 
		{
			cout<<targetArr[i]<<' ';
		}
		cout<<'\n';
	}
}