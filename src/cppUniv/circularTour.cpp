/*


File : circularTour.cpp
Author : abhijeet baranwal

Source Problem URL:
https://practice.geeksforgeeks.org/problems/circular-tour/1


To run the file, run below command:
g++ circularTour.cpp && ./a.out < ../TestInput/circularTour.txt


*/

#include <bits/stdc++.h>
using namespace std;

struct petrolPump
{
    int petrol;
    int distance;
};
int tour(petrolPump [],int );
int main()
{
    int t;
    cin>>t;
    while(t--)
    {
        int n;
        cin>>n;
        petrolPump p[n];
        for(int i=0;i<n;i++)
            cin>>p[i].petrol>>p[i].distance;
        cout<<tour(p,n)<<endl;
    }
}


/*Please note that it's Function problem i.e.
you need to write your solution in the form of Function(s) only.
Driver Code to call/invoke your function is mentioned above.*/

/*
The structure of petrolPump is 
struct petrolPump
{
    int petrol;
    int distance;
};*/
/*You are required to complete this method*/
int tour(petrolPump p[],int n)
{
    int last = 1, start = 0;
    int curr_sum = p[start].petrol - p[start].distance;
    while(start!=last || curr_sum<0) {
        while(curr_sum<0 && start!=last) {
        	curr_sum -= p[start].petrol - p[start].distance;
        	start = (1+start)%n;
        	if(start==0)
        		return -1; //never the journey will finish
        }
        curr_sum += p[last].petrol - p[last].distance;
        last = (last+1)%n;
    }
    return start;
   
}
