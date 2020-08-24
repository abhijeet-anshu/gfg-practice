/*


File : alienDictionalry.cpp
Author : luke

Source Problem URL:
https://practice.geeksforgeeks.org/problems/alien-dictionary/1


To run the file, run below command:
g++ alienDictionary.cpp && ./a.out <alienDictionary.txt


*/

#include <bits/stdc++.h>

using namespace std;

const int LIMIT = 301;
const int DEBUG = 0;
int lastIndex;
char intChar[26];
unordered_map<char, int > charInt;
unordered_set<int> Graph[26];

void reset()
{
	charInt.clear();
	lastIndex = -1;
	for(int i=0; i<26; i++)
		Graph[i].clear();
}

int getInt(char c)
{
	if(charInt.find(c)!=charInt.end())
	{
		return charInt[c];
	}
	charInt.insert({c, ++lastIndex});
	intChar[lastIndex] = c;
	return lastIndex;
}


void add_edge(char pred, char succ)
{
	if(DEBUG) { cout<<"\tEdge: "<<pred << "_____" << succ<<endl;}
	int firstNode = getInt(pred);
	int secondNode = getInt(succ);

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
	myFinalList[finalListIndex--] = intChar[node];
	if(DEBUG) { cout<<"finalised : " << intChar[node] << endl; }
}

string topoligical_sort(int nodeSize)
{
	assert(nodeSize==1+lastIndex);
	//mark all nodes as unvisited
	
	for(int i=0; i<nodeSize; i++)
	{
		visited[i] = 0;
	}
	finalListIndex = nodeSize-1;
	//for each node if not visited, visit
	for(int i=0; i<nodeSize; i++)
	{
		if(visited[i])  continue;

		dfsVisit(i);
	}
	myFinalList[nodeSize] = 0;
	
	if(DEBUG) {cout<<"\nAnswer:\t";}
	
	return myFinalList;
}
void printWords(string[], int);

string findOrder(string dict[], int n, int k)
{
	reset();
	printWords(dict, n); //debug

	//read all chars and create a map
	for(int i=0; i<n; i++) 
	{
		for(auto c : dict[i])
			getInt(c);
	}
		
	//read all relations

	for(int i=0; i<n-1; i++)
	{
		string word1 = dict[i], word2 = dict[i+1];
		//find the first unequal character
		
		if(DEBUG) {cout<<"\n\nComparing words : "<< word1 + " with "  + word2<<endl;}

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

	/*
	-------TOPOLOGICAL SORTING usign DFS
	L ← Empty list that will contain the sorted nodes
	while exists nodes without a permanent mark do
		select an unmarked node n
		visit(n)

	function visit(node n)
		if n has a permanent mark then
			return
		if n has a temporary mark then
			stop	 (not a DAG)

		mark n with a temporary mark

		for each node m with an edge from n to m do
			visit(m)

		remove temporary mark from n
		mark n with a permanent mark
		add n to head of L

	*/
	
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
		if(DEBUG) {cout<<"$$$$$$$$$$$$$$$$$\n\n\n\n\n";}
	}
}

void printWords(string words[], int n)
{
	if(!DEBUG) return;
	cout<<" "<<n<<endl;
	for(int i=0; i<n; i++)
		cout<<words[i]<<' ';
	cout<<'\n';
}