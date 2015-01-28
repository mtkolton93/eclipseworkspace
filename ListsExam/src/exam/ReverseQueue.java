package exam;

public class ReverseQueue 
{
	public static void main(String[] args) 
	{
		String[] letters = {"A","B","C","D","E","F","G"}; 
		IQueue<String> q = new QueueLL<String>();
		for (String l : letters)
			q.enqueue(l);
		
		System.out.printf("Queue before: %s%n", q);		
		reverseQueue(q);		
		System.out.printf("Queue after: %s%n", q);
	}
	
	public static void reverseQueue(IQueue<String> q)
	{
		IStack<String> s = new StackLL<String>();
		int size = q.getSize();
		for(int i = 0; i < size; i++)
		{
			s.push(q.dequeue());
		}

		for(int i = 0; i < size; i++)
		{
			q.enqueue(s.pop());
		}
	}

}