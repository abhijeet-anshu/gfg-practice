/*


File : stackUsing2Queues.cpp
Author : abhijeet

Source Problem URL:
https://practice.geeksforgeeks.org/problems/stack-using-two-queues/1/?ref=self

Description:
Optimized for a TOP operation to run in O(1) time.


To run the file, run below command:
g++ stackUsing2Queues.cpp && ./a.out <stackUsing2Queues.txt


*/

#include <bits/stdc++.h>
using namespace std;

class QueueStack{
private:
    queue<int> q1;
    queue<int> q2;
public:
    void push(int);
    int pop();
};
int main()
{
     int T;
    cin>>T;
    while(T--)
    {
        QueueStack *qs = new QueueStack();
        int Q;
        cin>>Q;
        while(Q--){
        int QueryType=0;
        cin>>QueryType;
        if(QueryType==1)
        {
            int a;
            cin>>a;
            qs->push(a);
        }else if(QueryType==2){
            cout<<qs->pop()<<" ";
        }
        }
        cout<<endl;
    }
}

/*Please note that it's Function problem i.e.
you need to write your solution in the form of Function(s) only.
Driver Code to call/invoke your function is mentioned above.*/

/* The structure of the class is
class QueueStack{
private:
    queue<int> q1;
    queue<int> q2;
public:
    void push(int);
    int pop();
};
 */

enum queue_en_use {
	QUEUE_1, QUEUE_2
} active_queue_indicator(QUEUE_1);


/* The method push to push element into the stack */
void QueueStack :: push(int x)
{
	//printf("\noperation push<---%d", x);
	if(!q1.size() && !q2.size())  {
		//stack is null. Start by pushing to q1
		//printf("\nStack empty");
		active_queue_indicator = QUEUE_1;
		assert(q1.size()==0);
		assert(q2.size()==0);
	}

	switch(active_queue_indicator) {
		case QUEUE_1:
			q1.push(x);
			if(q2.size()) q2.pop();
			q2.push(x); // replicate the front for O(1) operation
			//printf("\nPushed to 1");
			assert(q2.size()==1);
			break;
		case QUEUE_2:
			q2.push(x);
			if(q1.size()) q1.pop();
			q1.push(x); // replicate the front for O(1) operation
			//printf("\nPushed to 2");
			assert(q1.size()==1);
			break;
	}
}

/*The method pop which return the element poped out of the stack*/
int QueueStack :: pop()
{
 //printf("\nPop operation");
 //handle scenario when stack is null
 if(!q1.size() && !q2.size())
 	return -1;
 
 
 //handle scenario when stack has only one element
 if(q1.size()==1 && q2.size()==1) {

 	//the inactive queue and active queue will 
 	//contain the top element on their fronts
 	assert(q1.front()==q2.front());
 	
 	int stack_top_element = q1.front();
 	q1.pop();
 	q2.pop();
 	return stack_top_element;
 }

 //if control moves here implies stack has atleast 2 elements

 //identify active and non active queue
 queue<int> *queue_active;
 queue<int> *queue_inactive;

 
 if(active_queue_indicator==QUEUE_1) {
 	queue_active   = &q1;
 	queue_inactive = &q2;
 	//reset indicator
 	active_queue_indicator = QUEUE_2;
 	//printf("\nq1 is active\n");
 }
 else {
 	queue_active   = &q2;
 	queue_inactive = &q1;
 	//reset indicator
 	active_queue_indicator = QUEUE_1;
 	//printf("\nq2 is active\n");
 }

 //expected from queues at any non empty state of the stack
 assert(queue_inactive->size()==1 && queue_active->size());


 /*
 to pop, first pour all elements but last from active queue to inactive queue
 */

 //keep front element handy to return
 int stack_top_element = queue_inactive->front();

 //empty the inactive queue
 queue_inactive->pop();

 // track the new last element to be maintained in new inactive queue
 int q_last_element;

 //the below pours from active queue to inactive queue
 while(queue_active->size()!=1) {
 	q_last_element = queue_active->front();
 	queue_inactive->push(q_last_element);
 	queue_active->pop();
 }

 //the last element from active should be the last element of active queue
 assert(queue_active->front()==stack_top_element);

 //empty the new inactive queue now.
 queue_active->pop();
 
 //active queue should have the new front of stack post pop
 queue_active->push(q_last_element);

 return stack_top_element;

}
