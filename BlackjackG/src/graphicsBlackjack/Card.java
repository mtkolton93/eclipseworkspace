package graphicsBlackjack;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Card
{
	private static ImageIcon[] icons = {
		new ImageIcon("images/clubs/AceOfClubs.png"), 
		new ImageIcon("images/clubs/2OfClubs.png"),
		new ImageIcon("images/clubs/3OfClubs.png"),
		new ImageIcon("images/clubs/4OfClubs.png"),
		new ImageIcon("images/clubs/5OfClubs.png"),
		new ImageIcon("images/clubs/6OfClubs.png"),
		new ImageIcon("images/clubs/7OfClubs.png"),
		new ImageIcon("images/clubs/8OfClubs.png"),
		new ImageIcon("images/clubs/9OfClubs.png"),
		new ImageIcon("images/clubs/10OfClubs.png"),
		new ImageIcon("images/clubs/JackOfClubs.png"),
		new ImageIcon("images/clubs/QueenOfClubs.png"),
		new ImageIcon("images/clubs/KingOfClubs.png"),
		new ImageIcon("images/spades/AceOfSpades.png"),
		new ImageIcon("images/spades/2OfSpades.png"),
		new ImageIcon("images/spades/3OfSpades.png"),
		new ImageIcon("images/spades/4OfSpades.png"),
		new ImageIcon("images/spades/5OfSpades.png"),
		new ImageIcon("images/spades/6OfSpades.png"),
		new ImageIcon("images/spades/7OfSpades.png"),
		new ImageIcon("images/spades/8OfSpades.png"),
		new ImageIcon("images/spades/9OfSpades.png"),
		new ImageIcon("images/spades/10OfSpades.png"),
		new ImageIcon("images/spades/JackOfSpades.png"),
		new ImageIcon("images/spades/QueenOfSpades.png"),
		new ImageIcon("images/spades/KingOfSpades.png"),
		new ImageIcon("images/hearts/AceOfHearts.png"), 
		new ImageIcon("images/hearts/2OfHearts.png"), 
		new ImageIcon("images/hearts/3OfHearts.png"), 
		new ImageIcon("images/hearts/4OfHearts.png"), 
		new ImageIcon("images/hearts/5OfHearts.png"), 
		new ImageIcon("images/hearts/6OfHearts.png"), 
		new ImageIcon("images/hearts/7OfHearts.png"), 
		new ImageIcon("images/hearts/8OfHearts.png"), 
		new ImageIcon("images/hearts/9OfHearts.png"), 
		new ImageIcon("images/hearts/10OfHearts.png"), 
		new ImageIcon("images/hearts/JackOfHearts.png"), 
		new ImageIcon("images/hearts/QueenOfHearts.png"), 
		new ImageIcon("images/hearts/KingOfHearts.png"), 
		new ImageIcon("images/diamonds/AceOfDiamonds.png"), 
		new ImageIcon("images/diamonds/2OfDiamonds.png"), 
		new ImageIcon("images/diamonds/3OfDiamonds.png"), 
		new ImageIcon("images/diamonds/4OfDiamonds.png"), 
		new ImageIcon("images/diamonds/5OfDiamonds.png"), 
		new ImageIcon("images/diamonds/6OfDiamonds.png"), 
		new ImageIcon("images/diamonds/7OfDiamonds.png"), 
		new ImageIcon("images/diamonds/8OfDiamonds.png"), 
		new ImageIcon("images/diamonds/9OfDiamonds.png"), 
		new ImageIcon("images/diamonds/10OfDiamonds.png"), 
		new ImageIcon("images/diamonds/JackOfDiamonds.png"), 
		new ImageIcon("images/diamonds/QueenOfDiamonds.png"), 
		new ImageIcon("images/diamonds/KingOfDiamonds.png"), 
	};
	
	private String name;
	private GraphicsBlackjack game;
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
	
	public void paint(Graphics g, int x, int y)
	{
		icons[number].paintIcon(game, g, x, y);
	}
	
	public void addGraphics(GraphicsBlackjack b)
	{
		game = b;
	}
	
	public GraphicsBlackjack getBlackjack()
	{
		return game;
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
