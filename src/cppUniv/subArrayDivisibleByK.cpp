/*


File : subArrayDivisibleByK.cpp
Author : luke

Source Problem URL:
#Add URL here:


To run the file, run below command:
g++ subArrayDivisibleByK.cpp && ./a.out <../TestInput/subArrayDivisibleByK.txt


*/

#include <bits/stdc++.h>
#define DEBUG 0
using namespace std;

const int LIMIT = 1000001;

int arr[LIMIT];

void print5d(int num)
{
  printf("%5d ", num);
}

void debugPrint(int n)
{
  if(!DEBUG) 
    return;

  for(int i=0; i<n; i++)
    print5d(arr[i]);
  cout<<'\n';
}

int longestSubArray(int n, int k) 
{
  assert(k>0);

  unordered_map<int, pair<int, int>> remainder_occurrence;

  //for each unique remainder, find the first and last index.
      // for remainder 0, the first index is -1
  remainder_occurrence.insert({0, make_pair(-1, -1)});

  debugPrint(n);

  int _sum_i_k = 0;

  

  for(int i=0; i<n; i++)
  {
    _sum_i_k = (arr[i]%k + _sum_i_k)%k;
    _sum_i_k += _sum_i_k<0?k:0;
    if(DEBUG) {print5d(_sum_i_k);}

    if(remainder_occurrence.find(_sum_i_k)==remainder_occurrence.end())
    {
      remainder_occurrence.insert({_sum_i_k, make_pair(i, i)});
    }
    else
    {
      remainder_occurrence[_sum_i_k].second = i;
    }
  }

  if(DEBUG) {cout<<'\n';}

  if(DEBUG) 
  {
    int _actualDebugSum = 0;
    for(int i=0; i<n; i++)
      print5d((_actualDebugSum=_actualDebugSum+arr[i]));
    cout<<'\n';
    _actualDebugSum = 0;
    for(int i=0; i<n; i++)
      print5d((_actualDebugSum=_actualDebugSum+arr[i])%k);
    cout<<'\n';
  }

  int _maxLenghtSubarray = 0;

  // iterate over all remainders, ( keys of hashmap)
  // for each value, last - first is a valid sub array length, answer is the largest sub array length
  for(auto key_value : remainder_occurrence)
  {
    _maxLenghtSubarray = std::max(_maxLenghtSubarray, 
                  key_value.second.second- key_value.second.first);

    if(DEBUG) {cout<<"for remainder:"<<key_value.first<<" difference is " 
    << key_value.second.second- key_value.second.first<<'\n';}
  }

  return _maxLenghtSubarray;
}

int main(void)
{
  int t, n, k;
  cin>>t;
  while(t--)
  {
    cin >> n >> k;
    for(int i=0; i<n ;i++)
      cin >> arr[i];
    cout << longestSubArray(n, k) << endl;
  }
  return 0;
}

