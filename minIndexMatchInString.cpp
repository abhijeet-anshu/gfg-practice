/*


File : minIndexMatchInString.cpp
Author : luke

Source Problem URL:
https://practice.geeksforgeeks.org/problems/minimum-indexed-character0221/1


To run the file, run below command:
g++ minIndexMatchInString.cpp && ./a.out <minIndexMatchInString.txt


*/

#include <bits/stdc++.h>
//#define DEBUG 0
using namespace std;



string printMinIndexChar(string str, string patt) {
	const int asciiLength = 127;
	int freq[asciiLength];
	for(int i=0;i<asciiLength; i++) {
		freq[i] = -1;
	}
	for(int i=0;i<str.length();i++) {
		int asciiValue = str[i];
		if(freq[asciiValue]==-1) {
			////if(DEBUG) {cout<<"0.frequency for "<< str[i] << freq[asciiValue]<<endl;}
			freq[asciiValue] = i;	
		}
		
		//if(DEBUG) {cout<<"frequency for "<< str[i] << freq[asciiValue]<<endl;}

	}
	int minIndex = INT_MAX;
	char matchChar = '$';

	for(int i=0;i<patt.length();i++) {
		int asciiValue = patt[i];
		if(freq[asciiValue]==-1) {
			continue;
		}
		//if(DEBUG) {cout<<"PATTERN:frequency for "<< patt[i] << freq[asciiValue]<<endl;}
		int myIndex = freq[asciiValue];
		if(minIndex>myIndex) {
			minIndex = myIndex;
			matchChar = patt[i];
		}
	}
	char resp[2] = "";
	resp[0] = matchChar;

	string myAns = resp;
	return myAns;
}

void findMinIndexUtil() {
	string mainStr, pattern;
	cin>>mainStr>>pattern;
	//
	cout<<printMinIndexChar(mainStr, pattern)<<endl;
}

int main(void) 
{
	int t;
	scanf("%d", &t);
	while(t--) {
		findMinIndexUtil();
	}
}
