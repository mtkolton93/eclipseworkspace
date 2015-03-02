package graphicsBlackjack;

import java.util.ArrayList;
import java.util.Collections;

public class Deck
{
	private ArrayList<Card> deck = new ArrayList<Card>();
	
	public Deck()
	{
		for(int i = 0; i < 52; i++)
		{
			deck.add(new Card(i));
		}
		
		shuffle();
	}
	
	public void addGraphics(GraphicsBlackjack b)
	{
		for(Card c : deck)
		{
			c.addGraphics(b);
		}
	}
	
	public void shuffle()
	{
		Collections.shuffle(deck);
	}
	
	public Card drawCard()
	{
		Card ret = deck.get(0);
		deck.remove(ret);
		return ret;
	}
}
