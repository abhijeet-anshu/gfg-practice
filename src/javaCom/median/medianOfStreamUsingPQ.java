/*
https://practice.geeksforgeeks.org/problems/find-median-in-a-stream/0
 */

package javaCom.median;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class medianOfStreamUsingPQ
{
	PriorityQueue<Integer> minHeap;
	PriorityQueue<Integer> maxHeap;
	public medianOfStreamUsingPQ()
	{
		this.minHeap = new PriorityQueue<>(LIMIT_N);
		this.maxHeap = new PriorityQueue<>(LIMIT_N,
											Comparator.reverseOrder());
	}

	int _Compare()
	{
		int count = maxHeap.size() - minHeap.size();
		if(count>1 || count< -1)
			throw new RuntimeException("Invalid computation");
		return count;
	}

	public int getMedianOnline(int element)
	{
		switch (_Compare())
		{
			case 1:
				//max heap is bigger (lheap)
				int lTop = maxHeap.peek();
				if(element<lTop)
				{
					minHeap.add(lTop);
					maxHeap.remove();
					maxHeap.add(element);
				}
				else
				{
					minHeap.add(element);
				}
				return (minHeap.peek()+maxHeap.peek())/2;
			case 0:

				PriorityQueue<Integer> _TempHeap = maxHeap;
				if(maxHeap.size()>0)
					_TempHeap = (element> maxHeap.peek())?minHeap:maxHeap;
				_TempHeap.add(element);
				return _TempHeap.peek();

			case -1:

				int rTop = minHeap.peek();
				if(element>rTop)
				{
					maxHeap.add(rTop);
					minHeap.remove();
					minHeap.add(element);
				}
				else
				{
					maxHeap.add(element);
				}
				return (minHeap.peek()+maxHeap.peek())/2;
			default:
				throw new RuntimeException("in getMedian, invalid state");
		}
	}

	static final int LIMIT_N = 1000000;
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);

		int t = s.nextInt();
		medianOfStreamUsingPQ m = new medianOfStreamUsingPQ();
		while (t-->0)
		{
			int elem =  s.nextInt();
			System.out.println(m.getMedianOnline(elem));
		}
		return;
	}

}
