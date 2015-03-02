package consoleBlackjack;

public class Blackjack
{
	private Deck deck;
	private Hand playerHand;
	private Hand houseHand;

	public Blackjack()
	{
		deck = new Deck();
		playerHand = new Hand();
		houseHand = new Hand();

		for(int i = 0; i < 2; i++)
		{
			playerHand.addCard(deck.drawCard());
			houseHand.addCard(deck.drawCard());
		}
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
