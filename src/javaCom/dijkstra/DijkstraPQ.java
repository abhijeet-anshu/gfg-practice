/* https://www.spoj.com/problems/EZDIJKST/ */

package javaCom.dijkstra;

import java.util.*;
import java.util.stream.IntStream;

class Graph
{
	class Edge
	{
		int targetVertex;
		int weight;
		Edge(int t, int w)
		{
			targetVertex = t;
			weight = w;
		}
	}
	List<Edge>[] adjList;
	int size;
	Graph(int n)
	{
		adjList = new ArrayList[n];
		size = n;
		for(int i=0; i<n; i++)
			adjList[i] = new ArrayList<Edge>();
	}
	void addEdge(int u, int v, int w,  boolean isDirectedEdge)
	{
		addEdge(u, v, w);
		if(!isDirectedEdge)
			addEdge(v, u, w);
	}
	void addEdge(int u, int v, int w)
	{
		adjList[u-1].add(new Edge(v-1, w));
	}

	class myNode implements Comparable
	{
		int vertex;
		int distance;
		myNode(int v, int d){
			vertex = v;
			distance = d;
		}

		@Override
		public int compareTo(Object o)
		{
			return Integer.compare(this.distance, ((myNode)o).distance);
		}
	}

	/* custom method to implement dijkstra on this */
	int dijkstra(int  source, int target)
	{
		source--;
		target--;

		int[] distance = IntStream.generate( () -> (Integer.MAX_VALUE) ).limit(this.size).toArray();

		PriorityQueue<myNode> pq = new PriorityQueue<myNode>(this.size);
		distance[source] = 0;
		pq.add(new myNode(source, 0));

		while(!pq.isEmpty())
		{
			myNode u = pq.remove();

			if(u.vertex==target)
				return distance[target];

			if(u.distance>distance[u.vertex]) //vertex already processed
				continue;

			int myDist = distance[u.vertex];

			//iterate over all edges, and update each target
			for (Edge _E: adjList[u.vertex])
			{
				if(myDist+_E.weight<distance[_E.targetVertex])
				{
					distance[_E.targetVertex] = myDist + _E.weight;
					pq.add(new myNode(_E.targetVertex, distance[_E.targetVertex]));
				}
			}
		}
		return distance[target];

	}

}

public class DijkstraPQ
{
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		int t = s.nextInt();
		while(t-->0)
		{
			int N, K;
			N = s.nextInt();
			K = s.nextInt();
			//init graph
			Graph G = new Graph(N);
			//read graph
			while(K-->0)
			{
				G.addEdge(read(s), read(s), read(s), true);
			}
			//run dijkstra that accepts source and target node and ends at finalising target
			int distance = G.dijkstra(read(s), read(s));
			if(distance==Integer.MAX_VALUE)
				System.out.println("NO");
			else
				System.out.println(distance);
		}
	}

	static int read(Scanner s)
	{
		return s.nextInt();
	}

}
