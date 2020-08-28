/*


File : queueUsing2Stacks.cpp
Author : abhijeet baranwal

Source Problem URL:
#Add URL here:


To run the file, run below command:
g++ queueUsing2Stacks.cpp && ./a.out <../TestInput/queueUsing2Stacks.txt


*/


#include<bits/stdc++.h>
using namespace std;


class StackQueue{
private:
    stack<int> s1;
    stack<int> s2;
public:
	StackQueue() {
		s1 = stack<int>();
		s2 = stack<int>();
	}
    void push(int B);
    int pop();

};
int main()
{
    int T;
    cin>>T;
    while(T--)
    {
        StackQueue *sq = new StackQueue();
        int Q;
        cin>>Q;
        while(Q--){
        int QueryType=0;
        cin>>QueryType;
        if(QueryType==1)
        {
            int a;
            cin>>a;
            sq->push(a);
        }else if(QueryType==2){
            cout<<sq->pop()<<" ";
        }
        }
        cout<<endl;
    }
}


/*Please note that it's Function problem i.e.
you need to write your solution in the form of Function(s) only.
Driver Code to call/invoke your function is mentioned above.*/

/* The structure of the class is
class StackQueue{
private:   
    // These are STL stacks ( http://goo.gl/LxlRZQ )
    stack<int> s1;
    stack<int> s2;
public:
    void push(int);
    int pop();
}; */
/* The method push to push element into the queue */
void StackQueue :: push(int x)
 {
	//printf("\nS2 current top::%d", s2.top());
	s2.push(x);
 }
/*The method pop which return the element poped out of the queue*/
int StackQueue :: pop()
{

    if(!s1.empty()) {
    	int element = s1.top();
    	s1.pop();
    	return element;
    }

    //pour all elements from s2 to s1
    while(!s2.empty()) {
    	s1.push(s2.top());
    	s2.pop();
    }

    if(s1.empty()) return -1;
    int element = s1.top();
    s1.pop();
    return element;
}
