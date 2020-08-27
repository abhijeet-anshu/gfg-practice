package javaCom;

import java.util.Scanner;

class XOR
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int t =  sc.nextInt();
		while(t-->0)
		{
			int n = sc.nextInt();
			int set[] = new int[n];
			for(int i=0; i<n; i++)
			{
				set[i] = sc.nextInt();
			}
			System.out.println(GFG.maxSubarrayXOR(set, n));
		}
	}
}

class GFG
{
	final static int MAX_BIT = 32;

	public static int maxSubarrayXOR(int set[], int n)
	{
		int index = 0;

		for(int i=MAX_BIT-1; i>=0; i--)
		{
			int bitChecker_i = 1<<i;
			//check if bit set
			// and is a greater element than encountered
			// in index...n
			int maxEle = Integer.MIN_VALUE;
			int maxEleIndex = -1;

			for(int j=index; j<n; j++)
			{
				int _element = set[j];
				int _bitSet = bitChecker_i & _element;

				if( _bitSet > 0 && maxEle<_element)
				{
					maxEle = _element;
					maxEleIndex = j;
				}
			}

			if(maxEle==Integer.MIN_VALUE)
			{
				//bit not set
				// skip
				continue;
			}

			//swap maxEleIndex with index element in set
			swap(set, maxEleIndex, index);


			//unset all ith bit numbers, this makes sure this bit is returned in answer
			// by setting all others to 0
			for(int j=0; j<n; j++)
			{
				if(j==index) continue;
				int _element = set[j];
				int _bitSet = bitChecker_i & _element;

				if(_bitSet>0)
				{
					set[j] ^= maxEle; //loose the i the bit
				}
			}

			//increase index
			index++;

		} // end of loop with bits

		//set updated, answer is xor of set elements
		int answer = 0;
		for(int i=0; i<n; i++)
			answer ^= set[i];

		return  answer;

	}

	static void swap(int set[], int i, int j)
	{
		int temp = set[i];
		set[i] = set[j];
		set[j] = temp;
	}
}