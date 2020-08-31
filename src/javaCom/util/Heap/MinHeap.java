package javaCom.util.Heap;

public class MinHeap
				extends Heap
{
	public MinHeap(int maxHeapSize, int inpArray[], int arrSt, int arrEn)
	{
		super(maxHeapSize, inpArray, arrSt, arrEn);
	}

	public MinHeap(int maxHeapSize)
	{
		super(maxHeapSize);
	}

	@Override
	protected boolean _Compare(int child, int parent)
	{
		return child>parent;
	}


}
