/*
File : topologicalSort_DAG.cpp
Author : abhijeet baranwal
Source Problem URL:
https://practice.geeksforgeeks.org/problems/topological-sort/1/
Ideon Link:
Github Link:
https://github.com/abhijeet-anshu/gfg-practice/topologicalSort_DAG.cpp
To run the file, run below command:
g++ topologicalSort_DAG.cpp && ./a.out < topologicalSort_DAG.tx

*/



#include<bits/stdc++.h>
using namespace std;
vector<int> graph[10001];
int * topoSort(vector<int> graph[],int N);
int main()
{
int T;
cin>>T;
while(T--)
{
    memset(graph,0,sizeof graph);
    int N,E;
    cin>>E>>N;
    for(int i=0;i<E;i++)
    {
        int u,v;
        cin>>u>>v;
        graph[u].push_back(v);
    }
    int *res = topoSort(graph,N);
    bool valid =true;
    for(int i=0;i<N;i++)
    {
        int n=graph[res[i]].size();
        for(int j=0;j<graph[res[i]].size();j++)
        {
            for(int k=i+1;k<N;k++)
            {
                if(res[k]==graph[res[i] ] [j] )
                    n--;
            }
        }
        if(n!=0)
        {
            valid =false;
            break;
        }
    }
    if(valid==false)
        cout<<0<<endl;
    else
        cout<<1<<endl;
}
}


/*Please note that it's Function problem i.e.
you need to write your solution in the form of Function(s) only.
Driver Code to call/invoke your function is mentioned above.*/

enum color {Unmarked, Temporary, Permanent};

int mark[10001];
int output[10001];
stack<int> StackList;
void doDfsVisit(vector<int> graph[], int st) {
    //cout<<"Color " <<st<<":"<<(mark[st]!=Unmarked) <<";"<<endl;
    if(mark[st]!=Unmarked) return;
    //to handle negative case of a non DAG graph, 
    //return false - when mark[st] is Temporary
    
    //mark st as temporary 
    mark[st] = Temporary;
    //cout<<"Mark as Temp " <<st <<";"<<endl;
    //for all edges from st
    for(int i=0;i<graph[st].size();i++) {
       doDfsVisit(graph, graph[st][i]) ;
    }
    mark[st] = Permanent;
    //cout<<"Mark as Perm " <<st <<";"<<endl;
    StackList.push(st);
}

/* You need to complete this function */
int * topoSort(vector<int> graph[], int N)
{
   //initialize
   //StackList.clear();
   for(int i=0;i<N;i++) {
     mark[i] = Unmarked;
   }
   
   //in a random order visit unmarked nodes of the graph
   //cout<<"N " <<N<<endl;
   //cout<<"anc"<<endl;
   for(int i=0;i<N;i++) {
       //cout<<"Color " <<i<<endl;
       //cout<<"Color " <<i<<":"<<(mark[i]) <<";"<<endl;
       if(mark[i] == Unmarked) {
          doDfsVisit(graph, i); 
       }
   }
   
   //StackList is initialised.
   //create a array and return
   //int *arr = new int[N];
   int ind = -1;
   //cout<<N<<" Disco "<<endl;
   while(!StackList.empty()) {
    output[++ind] = StackList.top();
    //cout<<output[ind]<<" ";
    StackList.pop();
   }
   //cout<<N<<" Disco D"<<endl;
   return output;
   
}
