package javaCom;

import java.util.HashMap;
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

		printArray(LIS, LIS_INDEX+1, LOG_TRACE);

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

			printArray(LIS, LIS_INDEX+1, LOG_TRACE);
		}

		return 1+LIS_INDEX;
	}



	private static void printArray(int[] arr, int length, int _logLevel)
	{
		if(!isEnabled(_logLevel)) return;
		for(int i=0; i<length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	private static void printArray(int[] arr, int[] inp, int length, int _logLevel)
	{
		if(!isEnabled(_logLevel)) return;
		for(int i=0; i<length; i++)
			System.out.print(inp[arr[i]] + " ");
		System.out.println();
	}

	public int[] computeLIS()
	{
		if(N==0)
			return new int[0];
		//in LIS, store the index of the value from Input Array
		int[] Predecessor = new int[N];
		LIS[++LIS_INDEX] = 0;
		Predecessor[0] = Integer.MIN_VALUE;
		printArray(LIS, InputArray, 1+LIS_INDEX, LOG_TRACE);
		for(int i=1; i<N; i++)
		{
			int x = InputArray[i];
			int minLIS = InputArray[LIS[0]];
			int maxLIS = InputArray[LIS[LIS_INDEX]];

			if(x<=minLIS)
			{
				//begin a new LIS
				LIS[0] = i;
				Predecessor[i] =  Integer.MIN_VALUE;
			}
			else if(x>maxLIS)
			{
				//clone the largest LIS and extend it with x
				LIS[++LIS_INDEX] = i;
				Predecessor[i] = LIS[LIS_INDEX-1];
			}
			else if(x==maxLIS)
			{
				LIS[LIS_INDEX] = i;
				Predecessor[i] = LIS[LIS_INDEX-1];
			}
			else
			{
				//lies in between (0, LIS_INDEX) non inclusive
				int lastIndex = findJustSmallerLISIndex(x, false);
				Predecessor[i] = LIS[lastIndex];
				LIS[lastIndex+1] = i;
			}
			printArray(LIS, InputArray,  1+LIS_INDEX, LOG_TRACE);
		}

		int _LIS_ARR[] = new int[1+LIS_INDEX];
		_LIS_ARR[LIS_INDEX] = readLIS(LIS_INDEX, false);
		int _lastIndex = LIS[LIS_INDEX];
		for(int index=LIS_INDEX-1; index>=0; index--)
		{
			_lastIndex = Predecessor[_lastIndex];
			if(_lastIndex==Integer.MIN_VALUE)
			{
				throw new RuntimeException("Invalid Computation");
			}
			_LIS_ARR[index] = InputArray[_lastIndex];
		}
		printArray(_LIS_ARR, 1+LIS_INDEX, LOG_INFO);
		return _LIS_ARR;
	}
	private int readLIS(int index, boolean direct)
	{
		int _lisV = LIS[index];
		return direct?_lisV:InputArray[_lisV];
	}

	private int findJustSmallerLISIndex(int key)
	{
		return findJustSmallerLISIndex(key, true);
	}
	private int findJustSmallerLISIndex(int key, boolean direct)
	{
		//perform b-search over LIS[0] .. LIS[LIS_INDEX]
		// such that find max index, i
		//												LIS[i]<newVal
		//												LIS[i+1]>newVal

		int left, right, mid;
		int answer = -1;
		left = 0;
		right = LIS_INDEX;
		if(key<=readLIS(left, direct) || key> readLIS(right, direct))
			throw new RuntimeException("In bsearch, solution is out of boundary");

		//b Search
		while(left<=right)
		{
			mid = (left+right)/2;
			if(key<=readLIS(mid, direct))
				right = mid-1;
			else// =>(key>LIS[mid])
			{
				if(key<=readLIS(mid+1, direct))
					return mid;
				left = mid+1;
			}
		}
		throw new RuntimeException(" no solution ");
	}

	static int LOG_LEVEL;
	static final int LOG_FATAL = 0;
	static final int LOG_INFO = 1;
	static final int LOG_TRACE = 2;

	static boolean isEnabled(int _level)
	{
		return LOG_LEVEL>=_level;
	}

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
			LOG_LEVEL = LOG_INFO;
			System.out.println(
				new longestIncreasingSubSequenceOptimal(inpArr).computeLISLength());
			new longestIncreasingSubSequenceOptimal(inpArr).computeLIS();
		}
	}

}
