/*


File : longestMatchingSpan.cpp
Author : luke

Source Problem URL:
https://practice.geeksforgeeks.org/problems/longest-span-with-same-sum-in-two-binary-arrays/0/


To run the file, run below command:
g++ longestMatchingSpan.cpp && ./a.out <longestMatchingSpan.txt


*/

#include <bits/stdc++.h>
using namespace std;

const int LIMIT = 101;

int DIFF_ARRAY[LIMIT];
int FIRST[LIMIT];
int SECOND[LIMIT];

int longestMatchingSpan(int n)
{
  //int _diff = 0;

  unordered_map <int, pair<int, int>> diff_frequency;
  diff_frequency.insert({0, make_pair(-1, -1)});

  for(int i=0; i<n ;i++)
  {
    int _diff = -FIRST[i] + SECOND[i] ;
    
    if(diff_frequency.find(_diff)==diff_frequency.end())
    {
      diff_frequency.insert({_diff, make_pair(i, i)});
    }
    else
    {
      //mark the last occurrence
      diff_frequency[_diff].second = i;
    }
  }

  int _maxAnswer = -1;

  for(auto key_value : diff_frequency)
  {
    int _firstOccurrence = key_value.second.first;
    int _lastOccurrence  = key_value.second.second;
    _maxAnswer = std::max(_maxAnswer, _lastOccurrence-_firstOccurrence);
  }

      
  return _maxAnswer;
}

int main(void)
{
  int t, n;
  cin>>t;
  while(t--)
  {
    cin >> n;
    int _sum = 0, _value;
    for(int i=0; i<n ;i++)
    {
      cin >> _value;
      _sum = FIRST[i] = _sum + _value;
    }
    _sum = 0;
    for(int i=0; i<n ;i++)
    {
      cin >> _value;
      _sum = SECOND[i] = _sum + _value;
    }
      
    cout<<  longestMatchingSpan(n)<<endl;
  }
  return 0;
}
