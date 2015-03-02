package poker;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.util.Random;

public class House extends Hand
{
	private ImageIcon hidden = new ImageIcon("images/CardBack.png");
	private boolean reveal;
	private Random RNGesus = new Random();
	
	public void paint(Graphics g)
	{
		if(reveal)
		{
			for(int i = 0; i < handSize(); i++)
			{
				cards.get(i).paint(g, x+(i*115), y);	
			}
			reveal = false;
		}
		else
		{
			for(int i = 0; i < handSize(); i++)
				hidden.paintIcon(cards.get(0).getPoker(), g, x+(i*115), y);
		}
	}
	
	public void reveal()
	{
		reveal = true;
	}
	
	public void analyze()
	{
		if(Hands.onePair(this) || Hands.twoPair(this) || Hands.threeOfAKind(this))
		{
			for(Card c : cards)
			{
				boolean odd = true;
				if(!c.isSelected())
				{
					for(Card b : cards)
					{
						if(c != b && c.getValue() == b.getValue())
							odd = false;
					}
					if(odd)
						c.select();
				}
			}
		}
		else if(score < 15)
		{
			for(Card c : cards)
			{
				if(RNGesus.nextInt(100) > 50)
					c.select();
			}
		}
	}
}
