package stack;

public class StackTest 
{
	public static void main(String[] args) 
	{
		StackTest tester = new StackTest();
	
		//IStack<Integer> stack = new StackLL<Integer>();
		//tester.test(stack);

		// uncomment this code to test your stack...
		IStack<Integer> stack = new StackAry<Integer>();
		tester.test(stack);
	}
	
	public void test(IStack<Integer> stack)
	{
		System.out.println("Start tests...");
		
		// test push, pop, push, pop...
		assert(stack.toString().equals("top->"));
		assert(stack.isEmpty());
		assert(stack.getSize() == 0);

		System.out.println(stack);
		
		stack.push(10);
		assert(stack.top() == 10);
		assert(stack.toString().equals("top->10"));
		assert(!stack.isEmpty());
		assert(stack.getSize() == 1);

		System.out.println(stack);
		
		int i = stack.pop();
		assert(i == 10);
		assert(stack.toString().equals("top->"));
		assert(stack.isEmpty());
		assert(stack.getSize() == 0);

		System.out.println(stack);
		
		stack.push(10);
		assert(stack.top() == 10);
		assert(stack.toString().equals("top->10"));
		assert(!stack.isEmpty());
		assert(stack.getSize() == 1);

		System.out.println(stack);
		
		i = stack.pop();
		assert(i == 10);
		assert(stack.toString().equals("top->"));
		assert(stack.isEmpty());
		assert(stack.getSize() == 0);
		
		System.out.println(stack);
		
		// test push, push, push, pop, pop, pop...
		assert(stack.toString().equals("top->"));
		assert(stack.isEmpty());
		assert(stack.getSize() == 0);
		
		System.out.println(stack);
		
		stack.push(10);
		assert(stack.top() == 10);
		assert(stack.toString().equals("top->10"));
		assert(!stack.isEmpty());
		assert(stack.getSize() == 1);

		System.out.println(stack);
		
		stack.push(20);
		assert(stack.top() == 20);
		assert(stack.toString().equals("top->20->10"));
		assert(!stack.isEmpty());
		assert(stack.getSize() == 2);

		System.out.println(stack);
		
		stack.push(30);
		assert(stack.top() == 30);
		assert(stack.toString().equals("top->30->20->10"));
		assert(!stack.isEmpty());
		assert(stack.getSize() == 3);

		System.out.println(stack);
		
		i = stack.pop();
		assert(i == 30);
		assert(stack.toString().equals("top->20->10"));
		assert(!stack.isEmpty());
		assert(stack.getSize() == 2);

		System.out.println(stack);
		
		i = stack.pop();
		assert(i == 20);
		assert(stack.toString().equals("top->10"));
		assert(!stack.isEmpty());
		assert(stack.getSize() == 1);

		System.out.println(stack);
		
		i = stack.pop();
		assert(i == 10);
		assert(stack.toString().equals("top->"));
		assert(stack.isEmpty());
		assert(stack.getSize() == 0);
		
		System.out.println(stack);
		
		// check for pop or top on emtpy stack...
		boolean ok = false;
		try
		{
			stack.pop();
		}
		catch (IllegalStateException e)
		{
			ok = true;
		}
		assert(ok);
		
		try
		{
			stack.top();
		}
		catch (IllegalStateException e)
		{
			ok = true;
		}
		assert(ok);
		
		System.out.println("Success!");
	}
}