/*
https://practice.geeksforgeeks.org/problems/jumping-numbers/0
 */
package javaCom;

import java.util.Arrays;
import java.util.Scanner;

public class jumpingNumbers
{
	static final int LIMIT = 3251;
	static int[] allMagicNumbers;
	static int countExces;
	static int stIndex;
	static int lastIndex;
	static
	{
		allMagicNumbers = new int[LIMIT];
		stIndex = 0;
		lastIndex = -1;
		countExces = 0;
	}

	public static void main(String[] args)
	{
		for(int n_length=1; n_length<=9; n_length++)
		{
			int _startDigit = n_length==1?0:1;
			do
			{
				allSequences(n_length, _startDigit, 0);
			}while(++_startDigit<10);
		}
		Arrays.sort(allMagicNumbers, 0, lastIndex+1);

		Scanner s = new Scanner(System.in);
		int t = s.nextInt();
		while(t-->0)
		{
			int n = s.nextInt();
			for(int i=0; i<=lastIndex;i++)
			{
				if(allMagicNumbers[i]>n)
					break;
				System.out.print(allMagicNumbers[i]+" ");
			}
			System.out.println();
		}
		/*
		System.out.println("---"+lastIndex);
		System.out.println("---"+countExces);*/

	}

	/*
		output can be 1 or 2 length array of characters
	 */
	static void allSequences(int length, int currentDigit, int inputNum)
	{
		inputNum = inputNum*10 + currentDigit;
		//store this number
		if(length==1)
		{
			if(lastIndex>=LIMIT-1)
			{
				countExces++;
				return;
			}
			allMagicNumbers[++lastIndex] = inputNum;
			return;
		}
		int[] myPossibleValues;
		if(currentDigit==0)
		{
			myPossibleValues = new int[]{1};
		}
		else if(currentDigit==9)
		{
			myPossibleValues = new int[]{8};
		}
		else
		{
			myPossibleValues = new int[]{currentDigit-1, currentDigit+1};
		}
		for(int _digits: myPossibleValues)
			allSequences(length-1, _digits, inputNum);
	}


}
