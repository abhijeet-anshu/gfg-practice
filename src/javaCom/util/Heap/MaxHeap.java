package javaCom.util.Heap;

public class MaxHeap extends Heap
{
	@Override
	protected boolean _Compare(int child, int parent)
	{
		return child<=parent;
	}

	public MaxHeap(int maxHeapSize)
	{
		super(maxHeapSize);
	}

	public MaxHeap(int maxHeapSize, int inpArray[], int arrSt, int arrEn)
	{
		super(maxHeapSize, inpArray, arrSt, arrEn);
	}

}
