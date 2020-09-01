package javaCom;

import javax.naming.LimitExceededException;
import java.util.NoSuchElementException;
import java.util.Scanner;

abstract class Heap
{
	abstract protected boolean compare(int child, int parent);
	private int _Array[];
	private int maxSize;
	private int _Length;
	private void _Swap(int _index1, int _index2)
	{
		int temp = _Array[_index1];
		_Array[_index1] = _Array[_index2];
		_Array[_index2] = temp;
	}
	public Heap(int maxSize)
	{
		this.maxSize = maxSize;
		_Array = new int[maxSize];
		_Length = 0;
	}
	public void insert(int elem) throws LimitExceededException
	{
		if(_Length>=maxSize)
			throw new LimitExceededException("limit reached for heap");
		_Array[++_Length] = elem;
		int currNode = _Length;
		while(currNode>1)
		{
			int parent = currNode>>1;
			if(compare(_Array[currNode], _Array[parent]))
				return;
			_Swap(currNode, parent);
			currNode = parent;
		}
	}
	public int countElements()
	{
		return _Length;
	}
	public void reset()
	{
		_Length = 0;
	}
	public int getTop()
	{
		if(_Length<1)
			throw new NoSuchElementException("heap is empty");
		return _Array[1];
	}
	public int extractTop()
	{
		int elem = getTop();
		_Swap(1, _Length--);
		_Heapify(1);
		return elem;
	}
	protected void _Heapify(int index)
	{
		int lChild = index<<1;
		int rChild = (index<<1)+1;
		if(lChild>_Length) return; // on leaf

		//get minimum of lChild and rChild
		//and mark it as swappable
		int _swappable = lChild;
		if(rChild<=_Length && rChild>0)
			_swappable = compare(_Array[lChild], _Array[rChild]) ? rChild : lChild;

		//check if the min element is smaller than current Node
		if(compare(_Array[_swappable], _Array[index]))
			return; //property met
		_Swap(_swappable, index);
		_Heapify(_swappable);
	}
}

class minHeap extends Heap
{
	public minHeap(int maxSize)
	{
		super(maxSize);
	}
	protected boolean compare(int child, int parent)
	{
		return child>parent;
	}
}
class maxHeap extends Heap
{
	public maxHeap(int maxSize)
	{
		super(maxSize);
	}
	protected boolean compare(int child, int parent)
	{
		return child<parent;
	}
}


public class medianOfStream
{


	private maxHeap MaxHeap;
	private minHeap MinHeap;

	static final int LIMIT_N = 1000000;
	public medianOfStream()
	{
		MinHeap = new minHeap(LIMIT_N);
		MaxHeap = new maxHeap(LIMIT_N);
	}

	private int compareHeaps()
	{
		int cnt =  MaxHeap.countElements() - MinHeap.countElements();
		if(cnt>1 || cnt <-1)
			throw new RuntimeException("invalid setup");
		return cnt;
	}

	public int getMedianOnline(int elem) throws LimitExceededException
	{
		int _compare = compareHeaps();

		switch (_compare)
		{
			case 1:
				//Max Heap has 1 more element than Min Heap
				int lTop = MaxHeap.getTop();
				if(elem<lTop)
				{
					//the element needs to go to left Heap
					//and ltop needs to be moved to right heap
					MinHeap.insert(lTop);
					MaxHeap.extractTop();
					MaxHeap.insert(elem);
				}
				else// if(elem>=lTop)
				{
					//insert to MinHeap
					MinHeap.insert(elem);
				}
				//now there are even elements
				//return average of the 2 tops
				return (MinHeap.getTop()+MaxHeap.getTop())/2;

			case 0:
				// both have equal elements
				if(MinHeap.countElements()==0) //both are empty
				{
					MinHeap.insert(elem);
					return elem;
				}
				//identify where to insert
				Heap _extraElementHeap = (elem>MaxHeap.getTop()) ? MinHeap : MaxHeap;
				_extraElementHeap.insert(elem);
				return _extraElementHeap.getTop();
			case -1:
				//Max Heap has 1 less element
				int _rTop = MinHeap.getTop();
				if(elem>_rTop)
				{
					//insert rTop into left heap
					//remove top from right heap
					//insert elem into right heap
					MaxHeap.insert(_rTop);
					MinHeap.extractTop();
					MinHeap.insert(elem);
				}
				else //elem<=rTop
				{
					MaxHeap.insert(elem);
				}
				return (MinHeap.getTop()+MaxHeap.getTop())/2;
		}

		return 0;
	}

	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);

		int t = s.nextInt();
		medianOfStream m = new medianOfStream();
		while (t-->0)
		{
			int elem =  s.nextInt();
			try
			{
				System.out.println(m.getMedianOnline(elem));
			} catch (LimitExceededException e)
			{
				System.out.println(e.getMessage());
			}
		}
		return;
	}

}
