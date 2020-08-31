package automatedTest;

import javaCom.util.Heap.*;
import org.junit.Test;
import qa.GenerateArray;

import javax.naming.LimitExceededException;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;


public class HeapTest
{
	int[] initArr(int n)
	{
		int sizeN = n==0?GenerateArray.getRandomNumber(100, 150) : n;
		int minVal = GenerateArray.getRandomNumber(-1000, 500);
		int maxVal = minVal + (GenerateArray.getRandomNumber(sizeN+100, sizeN+2000));
		boolean unique = GenerateArray.getRandomNumber(0, 2)==1;
		System.out.println("\tsize :" + sizeN + "\tmin: " + minVal
		                 + "\tmax: "+ maxVal + "\tunique:" + unique);
		return new GenerateArray(sizeN, maxVal, minVal).generateArray(unique);

	}

	int[] initArr(int n, int min, int max, boolean unique)
	{
		return new GenerateArray(n, max, min).generateArray(unique);
	}

	@Test
	public void testMinHeapOrder()
	{
		int count = 0;
		int repeatCount = 5;
		while(repeatCount-->0)
		{
			System.out.println("Iter: " + ++count);
			//int arr[] = initArr(15, 1, 20, true);
			int arr[] = initArr(0);
			int n = arr.length;
			Heap minHeap = new MinHeap(n, arr, 0, n);
			Arrays.sort(arr);
			int maxHeapOutput[] = minHeap.sortedArr();
			for(int i=0; i<n; i++)
				assertEquals("Max Heap arrays to be equal : index/length: " + i + n,
								arr[i], maxHeapOutput[n-i]);
		}
	}

	@Test
	public void testMaxHeapOrder()
	{
		int count = 0;
		int repeatCount = 5;
		while(repeatCount-->0)
		{
			System.out.println("Iter: " + ++count);
			//int arr[] = initArr(15, 1, 20, true);
			int arr[] = initArr(0);
			int n = arr.length;
			Heap maxHeap = new MaxHeap(n, arr, 0, n);
			Arrays.sort(arr);
			int maxHeapOutput[] = maxHeap.sortedArr();
			for(int i=0; i<n; i++)
				assertEquals("Max Heap arrays to be equal : index/length: " + i + n,
								arr[i], maxHeapOutput[i+1]);
		}
	}

	@Test
	public void testMaxHeapInsert() throws LimitExceededException
	{
		int repeatCount = 5;
		int count = 0;
		while(repeatCount-->0)
		{
			System.out.println("Iter: " + ++count);
			//int arr[] = initArr(6, 1, 20, true);
			int arr[] = initArr(0);
			int maxTillNow = Integer.MIN_VALUE;
			//blank heaps
			Heap maxHeap = new MaxHeap(arr.length);
			for (int elem : arr)
			{
				//minHeap.insert(elem);
				maxHeap.insert(elem);
				maxTillNow = Integer.max(maxTillNow, elem);
				assertEquals("max heap should always contain max element",
								maxTillNow, maxHeap.getTop());
			}
		}
	}

	@Test
	public void testMinHeapInsert() throws LimitExceededException
	{
		int repeatCount = 5;
		int count = 0;
		while(repeatCount-->0)
		{
			System.out.println("Iter: " + ++count);
			//int arr[] = initArr(6, 1, 20, true);
			int arr[] = initArr(0);
			int minTillNow = Integer.MAX_VALUE;
			//blank heaps
			Heap minHeap = new MinHeap(arr.length);
			for (int elem : arr)
			{
				//minHeap.insert(elem);
				minHeap.insert(elem);
				minTillNow = Integer.min(minTillNow, elem);
				assertEquals("min heap should always contain min element",
								minTillNow, minHeap.getTop());
			}
		}
	}
}
