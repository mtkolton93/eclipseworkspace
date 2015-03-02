package consoleBlackjack;

public class Card
{
	private String name;
	private int value;
	private int number;

	public Card(int cardNumber)
	{
		number = cardNumber;
		value = number%13 + 1;

		//Determine card value/name based on number
		if(value == 1)
		{
			value = 11;
			name = "Ace";
		}
		else if(value < 11)
		{
			name = "" + value;
		}
		else if(value == 11)
		{
			name = "Jack";
			value = 10;
		}
		else if(value == 12)
		{
			name = "Queen";
			value = 10;
		}
		else if(value == 13)
		{
			name = "King";
			value = 10;
		}

		//Determine card suit based on number
		String suit = "";

		if(number/13 == 0)
		{
			suit = " of Clubs";
		}
		else if(number/13 == 1)
		{
			suit = " of Spades";
		}
		else if(number/13 == 2)
		{
			suit = " of Hearts";
		}
		else if(number/13 == 3)
		{
			suit = " of Diamonds";
		}

		name = name + suit;
	}

	public String getName()
	{
		return name;
	}

	public int getValue()
	{
		return value;
	}

	public void setValue(int v)
	{
		value = v;
	}
}
