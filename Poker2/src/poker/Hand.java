package poker;

import java.util.ArrayList;
import java.awt.Graphics;

public class Hand
{
	protected int score = 0;
	protected int x; //position of first card
	protected int y;
	ArrayList<Card> cards = new ArrayList<Card>();
		
	public void paint(Graphics g) //house-player specific
	{}
	
	public void setScore(int s)
	{
		score = s;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public int handSize()
	{
		return cards.size();
	}
	
	public void setPosition(int xPos, int yPos)
	{
		x = xPos;
		y = yPos;
	}
	
	public void addCard(Card c)
	{
		cards.add(c);
	}
	
	public void swapAll(Deck d)
	{
		
	}
	
	public boolean contains(int i)
	{
		for(Card c : cards)
		{
			if(c.getValue() == i)
				return true;
		}
		
		return false;
	}
	
	public int lowest()
	{
		int ret = 15;
		for(Card c : cards)
		{
			if(c.getValue() < ret)
				ret = c.getValue();
		}
		
		return ret;
	}
	
	public int highest()
	{
		int ret = 0;
		for(Card c : cards)
		{
			if(c.getValue() > ret)
				ret = c.getValue();
		}
		
		return ret;
	}	
}