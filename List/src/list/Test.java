package list;

import java.io.IOException;

public class Test
{
	public static void main(String[] args) throws IOException
	{
		Test tester = new Test();
		tester.test();
	}
	
	public void test() throws IOException
	{
		System.out.println("Testing...");
		AddPolynomials ap = new AddPolynomials();
		IList<Integer, Term> p1 = null;
		IList<Integer, Term> p2 = null;
		IList<Integer, Term> ans = null;
		p1 = ap.readPolynomial("p1.1.txt");
		p2 = ap.readPolynomial("p1.2.txt");
		ans = ap.add(p1, p2);
		assert(ans.toString().equals("[3x^2, 4x, 7]"));
		System.out.println("1: " + ans.toString());
		p1 = ap.readPolynomial("p2.1.txt");
		p2 = ap.readPolynomial("p2.2.txt");
		ans = ap.add(p1, p2);
		assert(ans.toString().equals("[-3x^3, 6]"));
		System.out.println("2: " + ans.toString());
		p1 = ap.readPolynomial("p3.1.txt");
		p2 = ap.readPolynomial("p3.2.txt");
		ans = ap.add(p1, p2);
		assert(ans.toString().equals("[x^3, x^2, x]"));
		System.out.println("3: " + ans.toString());
		p1 = ap.readPolynomial("p4.1.txt");
		p2 = ap.readPolynomial("p4.2.txt");
		ans = ap.add(p1, p2);
		assert(ans.toString().equals("[11]"));
		System.out.println("4: " + ans.toString());
		p1 = ap.readPolynomial("p5.1.txt");
		p2 = ap.readPolynomial("p5.2.txt");
		ans = ap.add(p1, p2);
		assert(ans.toString().equals("[11]"));
		System.out.println("5: " + ans.toString());
		System.out.println("Success!");
	}
}