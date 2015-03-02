package graphicsBlackjack;

public class Blackjack
{	
	private Deck deck;
	private Hand playerHand;
	private Hand houseHand;
	
	public Blackjack()
	{
		deck = new Deck();
		playerHand = new Hand();
		playerHand.setPosition(25, 455);
		houseHand = new Hand();
		houseHand.setPosition(465, 160);
		
		for(int i = 0; i < 2; i++)
		{
			playerHand.addCard(deck.drawCard());
			houseHand.addCard(deck.drawCard());
		}
	}
	
	public void addGraphics(GraphicsBlackjack b)
	{
		deck.addGraphics(b);
	}
	
	public void hit(Hand h)
	{
		h.addCard(deck.drawCard());
	}
	
	public Hand getPlayer()
	{
		return playerHand;
	}
	
	public Hand getHouse()
	{
		return houseHand;
	}
}
