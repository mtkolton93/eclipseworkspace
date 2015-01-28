package consoleBlackjack;

import java.util.Scanner;

public class ConsoleBlackjack
{
	static Hand player;
	static Hand house;
	static Blackjack blackjack;
	static Scanner keyboard = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		String playAgain = "y";
		
		while(playAgain.equals("y"))
		{
			newGame();
			play();
			chooseWinner();
			System.out.println("Press y to play again, or any key to exit: ");
			playAgain = keyboard.next();
		}
	}

	private static void newGame()
	{
		blackjack = new Blackjack();
		player = blackjack.getPlayer();
		house = blackjack.getHouse();
	}

	private static void play()
	{
		while(player.getScore() < 21 && house.getScore() < 21)
		{
			boolean playerStay = false;
			boolean houseStay = false;
			
			if(player.size() > 7)
			{
				System.out.println("Maximum number of cards reached. You stay");
				break;
			}
			
			readHands();
			
			if(!playerStay)
			{
				System.out.println("Would you like to (h)it or (s)tay?");
				if(getPlayerInput(keyboard.next()))
				{
					blackjack.hit(player);
				}
				else
				{
					System.out.println("You stay");
					playerStay = true;
				}
			}
			if(!houseStay)
			{
				if(getHouseInput())
				{
					blackjack.hit(house);
				}
				else
				{
					houseStay = true;
				}
			}
		}
	}
	
	private static void chooseWinner()
	{
		readHands();
	}
	
	private static void readHands()
	{
		System.out.println("House hand: " + house.toString(true));
		System.out.println("Your hand: " + player.toString(false));
	}
	
	private static boolean getPlayerInput(String s)
	{
		if(s.equals("h"))
		{
			return true;
		}
		else if(s.equals("s"))
		{
			return false;
		}
		else
		{
			System.out.println("Invalid input! Please enter h or s");
			return getPlayerInput(keyboard.next());
		}
	}
	
	private static boolean getHouseInput()
	{
		if(house.getScore() < 17)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
