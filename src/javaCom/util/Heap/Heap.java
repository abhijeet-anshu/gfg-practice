package javaCom.util.Heap;

import javax.naming.LimitExceededException;
import java.util.NoSuchElementException;

public abstract class Heap
{
	protected final static int NOT_FOUND = -1;

	protected int[] _Array;
	protected int _Length;
	protected int _MaxHeapSize;

	Heap()
	{
		_MaxHeapSize = 0;
		_Array = new int[0];
	}

	protected Heap(int maxHeapSize)
	{
		_Length = 0;
		this._MaxHeapSize = maxHeapSize;
		_Array = new int[1+maxHeapSize];
	}

	protected Heap(int maxHeapSize, int inpArray[], int arrSt, int arrEn)
	{
		this(maxHeapSize);
		for(int i=arrSt; i<arrEn; i++)
			_Array[++_Length] = inpArray[i];
		buildHeap();
	}

	public void buildHeap()
	{
		for(int i=(_Length/2); i>=1; i--)
			_Heapify(i);
	}
	public void resetHeap()
	{
		_Length = 0;
	}
	protected int _GetParent(int node)
	{
		if(node<=1) return NOT_FOUND;
		return node/2;
	}
	protected int _GetLeftChild(int node)
	{
		int leftChild = node<<1;
		return isValidNode(leftChild) ?leftChild : NOT_FOUND;
	}
	protected int _GetRightChild(int node)
	{
		int rightChild = 1+ (node<<1);
		return isValidNode(rightChild) ?rightChild : NOT_FOUND;
	}
	protected abstract boolean _Compare(int child, int parent);
	protected void _Swap(int aIndex, int bIndex)
	{
		int temp = _Array[aIndex];
		_Array[aIndex] = _Array[bIndex];
		_Array[bIndex] = temp;
	}
	public void insert(int element) throws LimitExceededException
	{
		if (_Length == _MaxHeapSize)
		{
			throw new LimitExceededException("heap full");
		}
		_Length++;

		int currNode = _Length;
		_Array[currNode] = element;
		//fit this
		for (int parentNode = _GetParent(currNode); parentNode != NOT_FOUND;
				 parentNode = _GetParent(parentNode))
		{
			if (_Compare(_Array[currNode], _Array[parentNode]))
			{
				//property met, end
				break;
			}

			//_Swap
			_Swap(currNode, parentNode);
			currNode = parentNode;
		}
	}

	public int extractTop() throws NoSuchElementException
	{
		int elem = getTop();
		//swap last element with top element, decrease last index length
		_Swap(1, _Length--);
		_Heapify(1 );
		return elem;
	}
	public int getTop() throws NoSuchElementException
	{
		if(_Length<1)
			throw new NoSuchElementException("getTop: no element, present in heap");
		return _Array[1];
	}
	public int getCount()
	{
		return _Length>0?_Length:0;
	}
	private boolean isValidNode(int node)
	{
		return node>=1 && node<=_Length;
	}
	private boolean isLeaf(int node)
	{
		return !isValidNode(_GetLeftChild(node));
	}
	protected void _Heapify(int node)
	{
		if(isLeaf(node)) return;

		int lChild = _GetLeftChild(node);
		int rChild = _GetRightChild(node);

		//identify the smallest(biggest) child to be changed
		int swappableChild = lChild;
		if(rChild!=NOT_FOUND)
			swappableChild = _Compare(_Array[lChild], _Array[rChild])?rChild:lChild;

		if(_Compare(_Array[swappableChild], _Array[node]))
			return;

		//perform swap
		_Swap(swappableChild, node);

		//invoke heapify
		_Heapify(swappableChild);
	}

	/**
	 * heap sort, heap should be built first
	 * Note: Empties the Heap
	 * NOTE: the output index starts from 1
	 * @return sorted array
	 */
	public int[] sortedArr()
	{
		while (_Length>0)
		{
			extractTop();
		}
		return _Array;
	}
	
}//end of HEAP class
