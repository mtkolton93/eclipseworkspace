package consoleBlackjack;

import java.util.ArrayList;

public class Hand
{
	private int score;
	ArrayList<Card> cards = new ArrayList<Card>();

	public void addCard(Card c)
	{
		cards.add(c);
		score = score + c.getValue();
		updateAces();
	}

	public void printHand()
	{
		System.out.print("Your hand: ");
		for(Card c : cards)
		{
			System.out.println(c.getName() + " ");
		}
	}

	public int getScore()
	{
		return score;
	}

	public int size()
	{
		return cards.size();
	}

	public void updateAces()
	{
		for(Card c : cards)
		{
			if(c.getValue() == 11 && score > 21)
			{
				c.setValue(1);
				score = score - 10;
			}
		}
	}

	public String toString(boolean house)
	{
		StringBuffer sb = new StringBuffer();

		if(house)
		{
			sb.append("???");
		}
		else
		{
			sb.append(cards.get(0).getName());
		}

		for(int i = 1; i < cards.size(); i++)
		{
			sb.append(", " + cards.get(i).getName());
		}

		if(score > 21)
		{
			sb.append(" (BUST!)");
		}

		return sb.toString();
	}
}

