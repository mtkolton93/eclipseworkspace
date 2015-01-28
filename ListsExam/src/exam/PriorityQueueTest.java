package exam;

public class PriorityQueueTest 
{
	public static void main(String[] args) 
	{
		PriorityQueueTest tester = new PriorityQueueTest();
	
		PriorityQueueLL<Integer> queue = new PriorityQueueLL<Integer>();
		tester.test(queue);
	}
	
	public void test(PriorityQueueLL<Integer> queue)
	{
		System.out.println("Start tests...");
		
		check(queue.isEmpty());
		check(queue.getSize() == 0);

		System.out.println(queue);
		
		queue.enqueue(10);
		check(!queue.isEmpty());
		check(queue.getSize() == 1);

		System.out.println(queue);
		
		int i = queue.dequeue();
		check(i == 10);
		check(queue.isEmpty());
		check(queue.getSize() == 0);

		System.out.println(queue);
		
		queue.enqueue(10);
		check(!queue.isEmpty());
		check(queue.getSize() == 1);

		System.out.println(queue);
		
		i = queue.dequeue();
		check(i == 10);
		check(queue.isEmpty());
		check(queue.getSize() == 0);
		
		System.out.println(queue);
		
		check(queue.isEmpty());
		check(queue.getSize() == 0);
		
		System.out.println(queue);
		
		queue.enqueue(10);
		check(!queue.isEmpty());
		check(queue.getSize() == 1);

		System.out.println(queue);
		
		queue.enqueue(20);
		check(!queue.isEmpty());
		check(queue.getSize() == 2);

		System.out.println(queue);
		
		queue.enqueue(30);
		check(!queue.isEmpty());
		check(queue.getSize() == 3);

		System.out.println(queue);
				
		i = queue.dequeue();
		check(i == 10);
		check(!queue.isEmpty());
		check(queue.getSize() == 2);

		System.out.println(queue);
		
		i = queue.dequeue();
		check(i == 20);
		check(!queue.isEmpty());
		check(queue.getSize() == 1);

		System.out.println(queue);
		
		i = queue.dequeue();
		check(i == 30);
		check(queue.isEmpty());
		check(queue.getSize() == 0);
		
		System.out.println(queue);
		
		boolean ok = false;
		try
		{
			queue.dequeue();
		}
		catch (IllegalStateException e)
		{
			ok = true;
		}
		check(ok);

		queue.enqueue(10);
		check(!queue.isEmpty());
		check(queue.getSize() == 1);

		System.out.println(queue);
		
		queue.priorityEnqueue(100);
		check(!queue.isEmpty());
		check(queue.getSize() == 2);

		System.out.println(queue);
		
		queue.enqueue(20);
		check(!queue.isEmpty());
		check(queue.getSize() == 3);

		System.out.println(queue);
		
		queue.priorityEnqueue(200);
		check(!queue.isEmpty());
		check(queue.getSize() == 4);

		System.out.println(queue);
		
		i = queue.dequeue();
		check(i == 100);
		check(!queue.isEmpty());
		check(queue.getSize() == 3);

		System.out.println(queue);

		queue.enqueue(99);
		check(!queue.isEmpty());
		check(queue.getSize() == 4);

		System.out.println(queue);
		
		i = queue.dequeue();
		check(i == 200);
		check(!queue.isEmpty());
		check(queue.getSize() == 3);

		System.out.println(queue);
		
		i = queue.dequeue();
		check(i == 10);
		check(!queue.isEmpty());
		check(queue.getSize() == 2);

		System.out.println(queue);
		
		i = queue.dequeue();
		check(i == 20);
		check(!queue.isEmpty());
		check(queue.getSize() == 1);

		System.out.println(queue);

		i = queue.dequeue();
		check(i == 99);
		check(queue.isEmpty());
		check(queue.getSize() == 0);

		System.out.println(queue);

		System.out.println("Success!");
	}
	
	private void check(boolean test)
	{
		if (!test)
			throw new AssertionError("Test failed!");
	}
}