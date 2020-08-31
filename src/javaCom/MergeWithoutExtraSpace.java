/*
https://practice.geeksforgeeks.org/problems/merge-two-sorted-arrays/0
* */

package javaCom;

import java.util.Scanner;

public class MergeWithoutExtraSpace
{
	static int arr1[], arr2[];
	static
	{
		arr1 = new int[50000];
		arr2 = new int[50000];
	}

	public static void main(String[] args)
	{
		int t, n, m;

		Scanner s = new Scanner(System.in);
		t = s.nextInt();
		while (t-->0)
		{
			n = s.nextInt();
			m = s.nextInt();
			arr1 = new int[n];
			arr2 = new int[m];

			for(int i=0; i<n; i++)
				arr1[i] = s.nextInt();

			for(int i=0; i<m; i++)
				arr2[i] = s.nextInt();

			mergeSpecial(arr1, arr2, n, m);
			for(int i=0; i<n; i++)
				System.out.print(arr1[i]+" ");
			for(int i=0; i<m; i++)
				System.out.print(arr2[i]+" ");
			System.out.println();
		}
	}

	private static int nextGap(int num)
	{
		if(num<=1) return 0;
		return num/2 + num%2;
	}
	private static void mergeSpecial(int[] A, int[] B, int aLen, int bLen)
	{
		int totalLength = aLen+bLen;

		for(int gap = nextGap(totalLength); gap>0; gap = nextGap(gap))
		{
			for (int i = 0; i < totalLength; i++)
			{
				int j = i + gap;
				if (j >= totalLength) break; //does not exist

				//resolve first to right index and array
				int fIndex = (i<aLen)?i:i-aLen;
				int[] fArr = (i<aLen)? A : B;
				int fValue = fArr[fIndex];

				int sIndex = (j<aLen)?j:j-aLen;
				int[] sArr = (j<aLen)? A : B;
				int sValue = sArr[sIndex];

				if (fValue > sValue)
				{
					//swap
					sArr[sIndex] = fValue;
					fArr[fIndex] = sValue;
				}
			}
			//update currHalf

		}
		return;
	}
}
