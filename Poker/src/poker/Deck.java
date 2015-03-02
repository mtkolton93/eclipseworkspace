package poker;

import java.util.ArrayList;
import java.util.Collections;

public class Deck
{
	private ArrayList<Card> deck = new ArrayList<Card>();
	
	public Deck()
	{
		for(int i = 0; i < 52; i++)
			deck.add(new Card(i));
		
		shuffle();
	}
	
	public int size()
	{
		return deck.size();
	}
	
	public void addGraphics(Poker p)
	{
		for(Card c : deck)
			c.addGraphics(p);
	}
	
	public void shuffle()
	{
		Collections.shuffle(deck);
	}
	
	public void replace(ArrayList<Card> cards)
	{
		for(Card c : cards)
			deck.add(c);
		shuffle();
		cards.clear();
	}
	
	public Card drawCard()
	{
		Card ret = deck.get(0);
		deck.remove(ret);
		return ret;
	}
}
