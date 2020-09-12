package javaCom;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MinPathKExists
{

	static class Edge
	{
		public int next;
		public int weight;
		Edge(int n, int w)
		{
			next = n;
			weight = w;
		}
	}

	static class Graph
	{
		List<Edge>[] adjList;
		int N;
		int K;
		boolean[] _Visited;
		public Graph(int n, int k)
		{
			N = n;
			this.K = k;
			adjList = new ArrayList[n];
			_Visited = new boolean[N];
			for(int i=0; i<n; i++)
			{
				adjList[i] = new ArrayList<Edge>();
			}
		}
		void add_edge(int u, int v, int w)
		{
			adjList[u].add(new Edge(v, w));
			adjList[v].add(new Edge(u, w));
		}

		boolean isMinWeightDist(int startNode, int minWeight)
		{
			if(startNode==0)
				return minWeight<=0;
			List<Edge> _edgeList = adjList[startNode];
			for(Edge e: _edgeList)
			{
				int nbr = e.next;
				if(_Visited[nbr])
					continue;
				_Visited[nbr] = true;
				boolean checkFeasible  = isMinWeightDist(nbr, minWeight-e.weight);
				_Visited[nbr] = false;
				if(checkFeasible)
						return true;
			}
			return false;
		}

		public boolean _MinKPathExists()
		{
			for(int i=1; i<N; i++)
			{
				_Visited[i] = true;
				if(isMinWeightDist(i, this.K))
					return true;
				_Visited[i] = false;
			}

			return false;
		}

	}

	static Graph _ReadCreateGraph(Scanner s)
	{
		int n = s.nextInt();
		int edgeCount = s.nextInt();
		int K = s.nextInt();
		Graph g = new Graph(n, K);
		while (edgeCount-->0)
		{
			int u, v, w;
			u = s.nextInt();
			v = s.nextInt();
			w = s.nextInt();
			g.add_edge(u, v, w);
		}
		return g;
	}


	public static void main(String[] args)
	{
		int t;
		Scanner sc = new Scanner(System.in);
		t = sc.nextInt();
		while(t-->0)
		{
			Graph g = _ReadCreateGraph(sc);
			System.out.println(g._MinKPathExists()?1:0);
		}
	}
}
