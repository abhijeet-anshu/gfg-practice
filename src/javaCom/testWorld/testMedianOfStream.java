import javaCom.median.*;
import org.junit.Test;
import qa.GenerateArray;

import javax.naming.LimitExceededException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class testMedianOfStream
{
	int[] testData;
	int[] breaks;
	static  int MAX_VAL = 100000000;
	static  int MIN_VAL = - MAX_VAL;

	void initData(int arr_size, int testSize)
	{
		boolean unique = GenerateArray.getRandomNumber(0, 2)==0;
		testData = new GenerateArray(arr_size, MAX_VAL, MIN_VAL-arr_size).generateArray(unique);
		//sets everything to 0
		breaks = new GenerateArray(arr_size, 1, 0).generateArray(false);
		testSize = Math.min(arr_size, testSize);
		int[] _breakNums = new GenerateArray(testSize, arr_size, 0).generateArray(true);
		for(int elem: _breakNums)
			breaks[elem] = 1;
	}
	@Test
	public void smallTest() throws LimitExceededException
	{
		int repeat = 5;
		//MAX_VAL = 50;
		//MIN_VAL = 0;
		while(repeat-->0)
		{
			initData(1000, 1000);
			validation();
		}
	}

	@Test
	public void largeTest() throws LimitExceededException
	{
		int repeat = 5;
		//MAX_VAL = 50;
		//MIN_VAL = 0;
		while(repeat-->0)
		{
			initData(1000000, 1000);
			validation();
		}
	}

	private void validation() throws LimitExceededException
	{
		List<Integer> streamData = new ArrayList<Integer>();
		medianOfStream _medianOfStream = new medianOfStream();
		medianOfStreamUsingPQ _medianOfStreamPQ = new medianOfStreamUsingPQ();
		for(int myIndex = 0; myIndex<testData.length; myIndex++)
		{
			streamData.add(testData[myIndex]);
			int _median = _medianOfStream.getMedianOnline(testData[myIndex]);
			int _median2 = _medianOfStreamPQ.getMedianOnline(testData[myIndex]);
			if(breaks[myIndex]!=1)
				continue;
			{
				streamData.sort((a, b) -> a.compareTo(b));
				int _ActualMedian = Integer.MIN_VALUE;
				int _Sz = streamData.size();
				if (_Sz % 2 == 0)
				{
					_Sz = _Sz >> 1;
					_ActualMedian = (streamData.get(_Sz) + streamData.get(_Sz - 1)) / 2;
				} else
				{
					_Sz = (_Sz - 1) >> 1;
					_ActualMedian = streamData.get(_Sz);
				}
				assertEquals(_ActualMedian, _median);
				assertEquals(_ActualMedian, _median2);
			}
		}
	}

}
