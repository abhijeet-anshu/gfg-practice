/*https://practice.geeksforgeeks.org/problems/numbers-with-one-absolute-difference/0*/
package javaCom;

import java.util.Arrays;
import java.util.Scanner;

public class jumpingNumbers_SimpleVariant
{
	static final int maxIndex = 22930;
	static long[] allNums = new long[maxIndex];
	static int lastIndex = -1;
	static void initAll()
	{
		for(int _length=2; _length<13; _length++)
			for(int _Startdigit=1; _Startdigit<10; _Startdigit++)
				generateAll(0, _length, _Startdigit);
		Arrays.sort(allNums);
	}

	static void generateAll(long prevNum, int length, int myDigit)
	{
		prevNum = prevNum*10l + myDigit;
		if(length==1)
		{
			if(lastIndex==maxIndex-1)
				throw new RuntimeException("error, change the indexes");
			allNums[++lastIndex] = prevNum;
			return;
		}
		int[] allPossibilities;
		if(myDigit==0)
			allPossibilities = new int[]{1};
		else if(myDigit==9)
			allPossibilities = new int[]{8};
		else
			allPossibilities = new int[]{myDigit-1, myDigit+1};

		for(int _digit : allPossibilities)
		{
			generateAll(prevNum, length-1, _digit);
		}

	}

	public static void main(String[] args)
	{
		initAll();
		//System.out.println(lastIndex);
		//System.out.println(allNums[lastIndex]);
		Scanner s = new Scanner(System.in);
		int t=s.nextInt();
		long n;
		//int
		while (t-->0)
		{
			n = s.nextLong();
			for(long v: allNums)
			{
				if(v>n) break;
				System.out.print(v+" ");
			}
			if(n<10)
				System.out.print(-1);
			System.out.println();

			//n = s.nextInt();
		}

	}
}
