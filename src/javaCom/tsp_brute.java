package javaCom;

import java.util.Scanner;

public class tsp_brute
{

	static int[][] distance = new int[14][14];
	static boolean[] visited = new boolean[14];

	static int tspRecur(int stNode, int nodesToCover, int N)
	{
		if(nodesToCover==0)
			return distance[stNode][0];
		int minDist = Integer.MAX_VALUE;
		for(int i=1; i<N; i++)
		{
			if(visited[i]) continue;
			visited[i] = true;
			int _dist = distance[stNode][i] + tspRecur(i, nodesToCover-1, N);
			minDist = Integer.min(_dist, minDist);
			visited[i] = false;
		}
		return minDist;
	}

	public static void main(String[] args)
	{
		int t;
		Scanner sc = new Scanner(System.in);
		t = sc.nextInt();
		while(t-->0)
		{
			int n = sc.nextInt();
			for(int i=0; i<n; i++)
			{
				visited[i] = false;
				for(int j=0; j<n; j++)
					distance[i][j] = sc.nextInt();
			}
			visited[0] = true;
			System.out.println(tspRecur(0, n-1, n));
		}
	}
}
