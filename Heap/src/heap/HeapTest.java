package heap;

import java.util.Arrays;
import java.util.List;


public class HeapTest 
{
	public static void main(String args[])
	{
		HeapTest hp = new HeapTest();
		hp.go();
	}
	
	public void go()
	{
		String ary[] = new String[]{"Tom", "Andy", "Lisa", "Steve", "Joe", "Sally", "Alex"}; 
		String dsorted[] = new String[]{"Tom", "Steve", "Sally", "Lisa", "Joe", "Andy","Alex"};
		String asorted[] = new String[]{"Alex", "Andy", "Joe", "Lisa", "Sally", "Steve", "Tom"};
		List<String> list = Arrays.asList(ary);
		
		IHeap<String> heap = new Heap<String>();
		
		for (String item : ary)
			heap.insert(item);
		check(heap.size(), ary.length);
		
		heap.clear();
		check(heap.size(), 0);
		
		for (String item : ary)
			heap.insert(item);
		check(heap.size(), ary.length);
		
		for (int i = 0; i < ary.length; i++)
		{
			System.out.println(heap);
			check(heap.peekTop(), dsorted[i]);
			heap.removeTop();
		}
		check(heap.size(), 0);

		heap.insertAll(list);
		for (int i = 0; i < ary.length; i++)
		{
			System.out.println(heap);
			check(heap.peekTop(), dsorted[i]);
			heap.removeTop();
		}
		check(heap.size(), 0);
		
		for (String item : ary)
			heap.insert(item);
		check(heap.size(), ary.length);
		heap.setMaxHeap(false);

		for (int i = 0; i < ary.length; i++)
		{
			System.out.println(heap);
			check(heap.peekTop(), asorted[i]);
			heap.removeTop();
		}
		check(heap.size(), 0);


	}

	public void check(int i1, int i2)
	{
		if (i1 != i2)
		{
			throw new AssertionError();
		}
	}

	public void check(String s1, String s2)
	{
		if (!s1.equals(s2))
		{
			throw new AssertionError();
		}
	}
}