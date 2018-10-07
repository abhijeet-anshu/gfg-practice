/*


File : minOfStackinO1.cpp
Author : abhijeet baranwal

Source Problem URL:
https://practice.geeksforgeeks.org/problems/get-minimum-element-from-stack/1/?ref=self


To run the file, run below command:
g++ minOfStackinO1.cpp && ./a.out <minOfStackinO1.txt


*/

#include <bits/stdc++.h>
using namespace std;

#include<bits/stdc++.h>
using namespace std;
class _stack{
stack<int> s;
int minEle;
public :
    int getMin();
    int pop();
    void push(int);
};
int main()
{
   int t;
   cin>>t;
   while(t--)
   {
       int q;
       cin>>q;
        _stack *a = new _stack();
       while(q--){
       int qt;
       cin>>qt;
       if(qt==1)
       {
           //push
           int att;
           cin>>att;
           a->push(att);
       }
       else if(qt==2)
       {
           //pop
           cout<<a->pop()<<" ";
       }
       else if(qt==3)
       {
           //getMin
           cout<<a->getMin()<<" ";
       }
       }
       cout<<endl;
   }
}

/*Please note that it's Function problem i.e.
you need to write your solution in the form of Function(s) only.
Driver Code to call/invoke your function is mentioned above.*/

/*
The structure of the class is as follows
class _stack{
stack<int> s;
int minEle;
public :
    int getMin();
    int pop();
    void push(int);
};
*/
/*returns min element from stack*/
int _stack :: getMin()
{
   return s.empty()?-1:minEle;
}
/*returns poped element from stack*/
int _stack ::pop()
{
   //handle empty scenario
   if(s.empty()) return -1;

   int value = s.top();
   s.pop();

   //when s.top() > minEle, implies does not affect current min
   //no update required

   //when value < minEle, implies current position is lowest value in stack
   if(value<minEle) {
   	//derive a new minEle, post pop of the lowest
   	int minEleNew = 2*minEle - value;
   	value = minEle;

   	//update minEle
   	minEle = minEleNew;

   }

   return value;

}
/*push element x into the stack*/
void _stack::push(int x)
{
   if(s.empty()) {
   	minEle = x;
   	s.push(x);
   }
   //if below does not disturb current minimum, simply insert it
   else if(x>=minEle) {
   	s.push(x);
   }
   else {
   	//scenario when s.top()<minEle
   	int minEleOld = minEle;
   	//update new local min
   	minEle = x;
   	//by storing, 2x - minEleOld,  minEleOld is preserved
   	//since x<minEleOld
   	//	2*x - minEleOld <minEleOld
   	//  insertVal  = x -(minEleOld-x)
   	//  (minEleOld-x) < 0
   	//  insertVal  < x
   	//  insertVal  < minEle (curr)
   	int insertVal = 2 * x - minEleOld;
   	s.push(insertVal);
   	//for derivation of minEleOld later, 
   	//   2*minEle - minEleOld = insertVal
   	//	 minEleOld = 2*minEle - insertVal
   }
}
