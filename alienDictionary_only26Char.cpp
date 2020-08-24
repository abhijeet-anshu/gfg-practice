/*


File : alienDictionalry.cpp
Author : luke

Source Problem URL:
https://practice.geeksforgeeks.org/problems/alien-dictionary/1


To run the file, run below command:
g++ alienDictionary_only26Char.cpp && ./a.out <alienDictionary.txt


*/

#include <bits/stdc++.h>

using namespace std;

const int LIMIT = 301;

int isNode[26];
unordered_set<int> Graph[26];

void reset()
{
	for(int i=0; i<26; i++)
	{
		Graph[i].clear();
		isNode[i] = 0;
	}
		
}

int setInt(char c)
{
	int val = c - 'a';
	if(isNode[val]) 
		return val;
	
	isNode[val] = 1;
	return val;
}


void add_edge(char pred, char succ)
{
	int firstNode  = pred-'a';
	int secondNode = succ-'a';

	//add edge from first to second
	Graph[firstNode].insert(secondNode);
}

int visited[26];

char myFinalList[27];
int finalListIndex;

void dfsVisit(int node)
{
	if(visited[node])
		return;
	visited[node] = 1;// temporary
	for(auto nbr : Graph[node])
	{
		if(visited[nbr]) continue;
		dfsVisit(nbr);
	}
	visited[node] = 2;// permanent
	myFinalList[finalListIndex--] = node + 'a';
}

string topoligical_sort(int nodeSize)
{
	
	//mark all nodes as unvisited
	
	for(int i=0; i<26; i++)
	{
		visited[i] = 0;
	}
	finalListIndex = nodeSize-1;
	//for each node if not visited, visit
	for(int i=0; i<26; i++)
	{
		if(!(isNode[i])) continue;
		if(visited[i])  continue;

		dfsVisit(i);
	}
	myFinalList[nodeSize] = 0;
	
	return myFinalList;
}

string findOrder(string dict[], int n, int k)
{
	reset();
	

	//read all chars and create a map
	for(int i=0; i<n; i++) 
	{
		for(auto c : dict[i])
			setInt(c);
	}
		
	//read all relations

	for(int i=0; i<n-1; i++)
	{
		string word1 = dict[i], word2 = dict[i+1];
		//find the first unequal character
		

		for(int j=0; j<std::min(word1.length(), word2.length()); j++)
		{
			if(word1[j]==word2[j])
			{
				continue;
			}
			add_edge(word1[j], word2[j]);
			break;
		}
	}
	//graph created, sort it now. using below algo for topo sorting

	return topoligical_sort(k) ;

}



string words[LIMIT];

int main(void)
{
	int t, n, k;
	cin >> t;
	while(t--)
	{
		cin >> n >> k;
		for(int i=0; i<n; i++)
			cin>>words[i];
		assert(n!=0);
		cout<<findOrder(words, n, k)<<endl;
	}
}

