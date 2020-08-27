/*
File : isCycle.cpp
Author : abhijeet baranwal
Source Problem URL:
https://practice.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1
Ideon Link:
Github Link:
https://github.com/abhijeet-anshu/gfg-practice/isCyclic_for_Undirected.cpp
To run the file, run below command:
g++ isCyclic_for_Undirected.cpp && ./a.out < isCyclic_for_Undirected.txt
*/


#include<bits/stdc++.h>
using namespace std;
bool isCyclic(vector<int> g[], int V);
int main()
{
    int T;
    cin>>T;
    while(T--)
    {
        vector<int> g[101];
        int V, E;
        cin>>V>>E;
        for(int i=0;i<E;i++)
        {
            int u,v;
            cin>>u>>v;
            g[u].push_back(v);
            g[v].push_back(u);
        }
        cout << isCyclic(g, V);
        cout << endl;
    }
}


/*Please note that it's Function problem i.e.
you need to write your solution in the form of Function(s) only.
Driver Code to call/invoke your function is mentioned above.*/

// The array of vectors g[] stores adjacency list 
// representation of graph. Here g[u] is a vector
// containing all adjacent of vertex u.
/*You are required to complete this method*/

bool isVisited[101];
bool inStack[101];

bool dfs(vector<int> g[], int st, int par) {
    isVisited[st] = true;
    inStack[st] = true;
    //cout<<"DFS on " << st << endl;
    bool visitedPar = 0;
    for(int i=0;i<g[st].size();i++) {
        int nbr = g[st][i];
        if(nbr==par && !visitedPar) {
         visitedPar = 1;
         continue;   
        }
        if(isVisited[nbr] || inStack[nbr]) {
            //cout<<"Visited and/or instack "<<nbr << endl;
            if(inStack[nbr]) 
             return 0;
             //cout<<"Already visited "<<nbr << endl;
            continue;
        }
        bool dfsResult = dfs(g, nbr, st);
        if(!dfsResult) return 0;
    }
    inStack[st] = false;
    return 1;
}

bool isCyclic(vector<int> g[], int V) {
    for(int i=0;i<V;i++) inStack[i] = isVisited[i] = 0;
    bool flag;
    for(int i=0;i<V;i++) {
        if(isVisited[i]) continue;
        //not covered node. Run DFS on it
        //cout<<"Un visited "<<i << endl;
        flag = dfs(g, i, -1);
        if(!flag) return 1;
    }
    return 0;
}
