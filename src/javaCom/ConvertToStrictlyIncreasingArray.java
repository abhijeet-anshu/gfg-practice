/*
https://practice.geeksforgeeks.org/problems/convert-to-strictly-increasing-array/0
 */

package javaCom;


import java.util.Scanner;

public class ConvertToStrictlyIncreasingArray
{
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		int t = s.nextInt();
		while (t-->0)
		{
			int n = s.nextInt();
			int arr[] = new int[n];
			for(int i=0; i<n; i++)
				arr[i] = s.nextInt();
			System.out.println(minOperation(arr));
		}
	}

	private static int minOperation(int[] arr)
	{
		int n = arr.length;
		int[] LIS = new int[n];
		int maxLIS = 0;
		for(int i=0; i<n; i++)
			LIS[i] = 1;
		//arr[i] .. arr[j]
		for(int i=1; i<n; i++)
		{
			for(int j=0; j<i; j++)
			{
				if(arr[i]>arr[j] && (i-j) <= arr[i] -arr[j])
					LIS[i] = Integer.max(1+LIS[j], LIS[i]);
			}
			maxLIS = Math.max(LIS[i], maxLIS);

		}
		return n - maxLIS;
	}
}
