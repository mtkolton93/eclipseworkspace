package poker;

import javax.swing.JPanel;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Poker extends JPanel
{
	ArrayList<Card> discards = new ArrayList<Card>();
	
	public Poker()
	{}
	
	public void discard(Card c)
	{
		discards.add(c);
	}
}
