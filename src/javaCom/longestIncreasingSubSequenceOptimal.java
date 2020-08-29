package javaCom;

import java.util.Scanner;

public class longestIncreasingSubSequenceOptimal
{


	final int N ;
	int[] LIS;	//the auxiliary array
	int[] InputArray;
	int LIS_INDEX;


	longestIncreasingSubSequenceOptimal()
	{
		N = 0;
		LIS = new int[0];
	}
	longestIncreasingSubSequenceOptimal(int[] inpArr)
	{
		this.InputArray = inpArr;
		this.N = inpArr.length;
		LIS = new int[N];
		LIS_INDEX = -1;
	}

	public int computeLISLength()
	{

		if(N==0) return 0;

		LIS[++LIS_INDEX] = InputArray[0];

		if(DEBUG)
		{
			debugLISArray();
		}


			//LIS exists from 0..LIS_INDEX
			//an element at index i would denote, a LIS of length  i + 1, ending with LIS[i]
			//the max LIS length at any point is LIS_INDEX


			/*
				https://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/
				3 cases
				1. If A[i] is smallest among all end candidates of active lists, we will start new active list of length 1.
				2. If A[i] is largest among all end candidates of active lists, we will clone the largest active list, and extend it by A[i].
				3. If A[i] is in between, we will find a list with largest end element that is smaller than A[i].
  																Clone and extend this list by A[i]. We will discard all
  																other lists of same length as that of this modified list.

				Inferring above:
				Case 1:
				if _ArrI smallest, update the LIS[0] with the new smallest

				Case 2:
				If _ArrI largest, extend the LIS_INDEX, LIS[LIS_INDEX] = _ArrI

				case 3:
				If _ArrI in between,
					find the biggest LIS[i] with value < _ArrI
					update LIS[i+1] with _ArrI
			 */

		for(int i=1; i<N; i++)
		{
			int _ArrI = InputArray[i];
			if(_ArrI<=LIS[0])
				LIS[0] = _ArrI;
			else if(_ArrI>LIS[LIS_INDEX])
				LIS[++LIS_INDEX] = _ArrI;
			else if(_ArrI==LIS[LIS_INDEX])
				LIS[LIS_INDEX] = _ArrI;
			else
				LIS[1+findJustSmallerLISIndex(_ArrI)] = _ArrI;
			if(DEBUG)
			{
				debugLISArray();
			}
		}

		return 1+LIS_INDEX;
	}

	private void debugLISArray()
	{
		if(!DEBUG) return;
		for(int i=0; i<=LIS_INDEX; i++)
			System.out.print(LIS[i] + " ");
		System.out.println();
	}

	public int[] computeLIS()
	{
	
		return new int[0];
	}

	private int findJustSmallerLISIndex(int key)
	{
		//perform b-search over LIS[0] .. LIS[LIS_INDEX]
		// such that find max index, i
		//												LIS[i]<newVal
		//												LIS[i+1]>newVal

		int left, right, mid;
		int answer = -1;
		left = 0;
		right = LIS_INDEX;
		if(key<=LIS[left] || key> LIS[right])
			throw new RuntimeException("In bsearch, solution is out of boundary");

		//b Search
		while(left<=right)
		{
			mid = (left+right)/2;
			if(key<=LIS[mid])
				right = mid-1;
			else// =>(key>LIS[mid])
			{
				if(key<=LIS[mid+1])
					return mid;
				left = mid+1;
			}
		}
		throw new RuntimeException(" no solution ");
	}
	static boolean DEBUG = false;
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		int t = s.nextInt();
		int[] inpArr;
		int n;
		while(t-->0)
		{
			n = s.nextInt();
			inpArr = new int[n];

			for(int i=0; i<n; i++)
				inpArr[i] = s.nextInt();

			System.out.println(
				new longestIncreasingSubSequenceOptimal(inpArr).computeLISLength());

		}
	}

}
