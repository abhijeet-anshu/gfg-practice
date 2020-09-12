/*
https://practice.geeksforgeeks.org/problems/minimum-cost-path/0/
 */

package javaCom;

import java.util.Scanner;

public class minCostPath
{

	static int[][] cost = new int [105][105];
	static int[][] currentDistance = new int [105][105];

	static class node
	{
		int x, y;
		node(int X, int Y)
		{
			update(X, Y);
		}
		void update(int X, int Y)
		{
			this.x = X;
			this.y = Y;
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
		currentDistance[0][0] = cost[0][0];

		while(currentDistance[N-1][N-1]!=-1)
		{
			node u = getMinNode(N);
			if(u.x==N-1 && u.y==N-1)
				return u.getValue(currentDistance);
			for(int index=0; index<4; index++)
			{
				node v = u.getNbr(index);
				if(!v.isValidNbr(N)) continue;
				if(v.getValue(currentDistance)==-1) continue;
				//update the distance
				int _Duv = u.getValue(currentDistance) + v.getValue(cost);
				if(v.getValue(currentDistance)>_Duv)
					v.setValue(currentDistance, _Duv);
			}
			//all neighbors updated
			u.setValue(currentDistance, -1);
		}

		return 0;
	}
	static node getMinNode(int N)
	{
		int minDist = Integer.MAX_VALUE;
		node _MinNode = new node(-1, -1);
		for(int i=0; i<N; i++)
		{
			for(int j=0; j<N; j++)
			{
				int _Sij = currentDistance[i][j];
				if(_Sij==-1) //finalized node
					continue;
				if(_Sij<minDist)
				{
					minDist = _Sij;
					_MinNode.update(i, j);
				}
			}
		}
		return _MinNode;
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
