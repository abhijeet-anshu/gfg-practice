/*
https://practice.geeksforgeeks.org/problems/minimum-cost-path/0/
 */

package javaCom;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class minCostPathUsingPQ
{

	static int[][] cost = new int [105][105];
	static int[][] currentDistance = new int [105][105];

	static class node
	{
		int x, y;
		int _SDistance;
		node(int X, int Y, int dist)
		{
			update(X, Y); _SDistance = dist;
		}
		node(int X, int Y)
		{
			update(X, Y);
		}
		void update(int X, int Y)
		{
			this.x = X;
			this.y = Y;
			_SDistance = Integer.MAX_VALUE;
		}
		boolean isValidNbr(int N)
		{
			return !(x<0 || y<0 || x>=N || y>=N);
		}

		node getNbr(int index)
		{
			int X = x;
			int Y = y;
			if(index==0)
				X = x-1;
			else if(index==1)
				X = x+1;
			else if(index==2)
				Y = y-1;
			else// if(index==3)
				Y = y+1;
			return new node(X, Y);
		}

		int getValue(int[][] arr)
		{
			return arr[x][y];
		}
		void setValue(int[][] arr, int value)
		{
			arr[x][y] = value;
		}
	}






	static int dijkstraCustom(int N)
	{
		//all dist to INF
		for(int i=0; i<N; i++)
		{
			for (int j = 0; j < N; j++)
				currentDistance[i][j] = Integer.MAX_VALUE;
		}


		Comparator<node> byDistance =
						(node o1, node o2)->Integer.compare(o1.getValue(currentDistance), o2.getValue(currentDistance));
		PriorityQueue<node> pq = new PriorityQueue<>((N+1)*(N+1), byDistance);

		pq.add(new node(0, 0,  cost[0][0]));
		currentDistance[0][0] = cost[0][0];
		while(!pq.isEmpty())
		{
			node u = pq.remove();

			if(u.x==N-1 && u.y==N-1)
				return u.getValue(currentDistance);

			if(u._SDistance>u.getValue(currentDistance))
				continue;

			for(int index=0; index<4; index++)
			{
				node v = u.getNbr(index);
				if(!v.isValidNbr(N)) continue;
				//if(v.getValue(currentDistance)==-1) continue;
				//update the distance
				int _Duv = u.getValue(currentDistance) + v.getValue(cost);
				if(v.getValue(currentDistance)>_Duv)
				{
					v.setValue(currentDistance, _Duv);
					v._SDistance = _Duv;
					pq.add(v);
				}
			}
			//all neighbors updated
			//u.setValue(currentDistance, -1);
		}

		return currentDistance[N-1][N-1];
	}

	public static void main(String[] args)
	{
		int t;
		Scanner sc = new Scanner(System.in);
		t = sc.nextInt();
		while(t-->0)
		{
			int n = sc.nextInt();
			for (int i = 0; i < n; i++)
			{
				for (int j = 0; j < n; j++)
					cost[i][j] = sc.nextInt();
			}
			System.out.println(dijkstraCustom(n));
		}
	}
}
