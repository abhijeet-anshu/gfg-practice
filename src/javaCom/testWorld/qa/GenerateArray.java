package qa;

import java.util.HashSet;
import java.util.Random;

public class GenerateArray
{
	static int repeatCount  = 5;
	public static void main(String[] args)
	{
		while(repeatCount-->0)
		{
			int[] myArray = (new GenerateArray(20, 20, 0)).generateArray(true);
			for (int abc : myArray)
				System.out.print(abc + " ");
			for (int abc : myArray)
				System.out.print("\n------In " + abc);
			System.out.println();
			System.out.println();
			System.out.println();
		}
	}
	/*
	toggle with above to generate random sequences
	*/

	//max size
	private int N;
	private int MaxVal;
	private int MinVal;

	public GenerateArray()
	{
		N = 100;
		MaxVal = 250;
		MinVal = 0;
	}

	public GenerateArray(int n, int maxVal, int minVal)
	{
		N = n;
		MaxVal = maxVal;
		MinVal = minVal;
	}


	/**
	 *
	 * @param min inclusive
	 * @param max exclusive
	 * @return
	 */
	public static int getRandomNumber(int min, int max)
	{
		int length = max-min;
		if(length<0) throw new RuntimeException("max should be >= min");
		return (new Random()).nextInt(length) + min;
	}

	public int[] generateArray(boolean unique)
	{

		int[] Sequence = new int[this.N];

		HashSet<Integer> alreadyStored = new HashSet<Integer>();

		for(int i=0; i<this.N; i++)
		{
			int val;

			while(true)
			{
				val = getRandomNumber(MinVal, MaxVal);
				if(!unique) break;
				if(!alreadyStored.contains(val))
				{
					alreadyStored.add(val);
					break;
				}
			}
			Sequence[i] = val;
		}
		return Sequence;

	}

}