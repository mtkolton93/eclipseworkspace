package poker;

import java.awt.Graphics;

public class Player extends Hand
{
	private int money = 500;
	private int bet;
	
	//If only life were so simple
	public int getMoney()
	{
		return money;
	}
	
	public void placeBet(int b)
	{
		bet = b;
		money = money-bet;
	}
	
	public void payOut()
	{
		money = money+bet*2;
	}
	
	@Override
	public void paint(Graphics g)
	{
		for(int i = 0; i < handSize(); i++)
		{
			cards.get(i).paint(g, x+(i*115), y);	
		}
	}
}
