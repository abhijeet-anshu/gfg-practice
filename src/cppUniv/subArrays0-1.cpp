/*


File : subArrays0-1.cpp
Author : luke

Source Problem URL:
https://practice.geeksforgeeks.org/problems/count-subarrays-with-equal-number-of-1s-and-0s-1587115620/1


To run the file, run below command:
g++ subArrays0-1.cpp && ./a.out <subArrays0-1.txt


*/

#include <bits/stdc++.h>
using namespace std;

const int LIMIT = 1000001;


long long int countSubarrWithEqualZeroAndOne(int arr[], int N)
{

	unordered_map<int, int> frequency;
	frequency.clear();
	frequency[0] = 1;

	int sum_i = 0;

	for(int i=0;i<N; i++)
	{
		sum_i += arr[i] ? 1 : -1;

		if(frequency.find(sum_i)==frequency.end()) 
		{
			frequency[sum_i] = 1;
		}
		else
		{
			frequency[sum_i] += 1;
		}
	}

	long long int _answer = 0;

	const long long int TWO = 2;
	const long long int ONE = 1;
	
	//iterate over all keys
	for(auto key_value : frequency) 
	{
    
		//int _sum  = key_value.first;
    long long int _freq = key_value.second;  
		_answer +=  (_freq * (_freq - ONE))/TWO;
	}

	return _answer;
	

}//end of method

int main(void) 
{
	int t, n;
	int arr[LIMIT];

	cin>>t;
	while(t--) 
	{
		cin>>n;
		for(int i=0;i<n;i++) 
		{
			cin>>arr[i];
		}
		cout<<countSubarrWithEqualZeroAndOne(arr, n)<<endl;
	}

}