package graphicsBlackjack;

import java.util.ArrayList;
import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Hand
{
	private int score;
	private int x; //position of first card
	private int y;
	ArrayList<Card> cards = new ArrayList<Card>();
	private ImageIcon hidden = new ImageIcon("images/CardBack.png");
	
	public void addCard(Card c)
	{
		cards.add(c);
		score = score + c.getValue();
		updateAces();
	}
	
	public void paint(Graphics g, boolean house)
	{
		if(house)
		{
			hidden.paintIcon(cards.get(1).getBlackjack(), g, x, y);
		}
		else
		{
			cards.get(0).paint(g, x, y);
		}
		for(int i = 1; i < size(); i++)
		{
			//player hand
			if(y > 200)
				cards.get(i).paint(g, x+(i*110), y);
			//house hand
			else
				cards.get(i).paint(g, x-(i*110), y);
				
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
	
	public void setPosition(int xPos, int yPos)
	{
		x = xPos;
		y = yPos;
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
}