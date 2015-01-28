package exam;

public class DeleteNegatives 
{
	public static void main(String[] args) 
	{
		int[] nums = {-3, 2, -1, 4, 5, -2, -10, 6, 7, -43}; 
		IQueue<Integer> q = new QueueLL<Integer>();
		for (int i : nums)
			q.enqueue(i);

		System.out.printf("Queue before: %s%n", q);
		
		deleteNegatives(q);
		
		System.out.printf("Queue after: %s%n", q);
	}
	
	public static void deleteNegatives(IQueue<Integer> q)
	{
		int size = q.getSize();
		for(int i = 0; i < size; i++)
		{
			int x = q.dequeue();
			if(x > 0)
			{
				q.enqueue(x);
			}
		}
	}
}
