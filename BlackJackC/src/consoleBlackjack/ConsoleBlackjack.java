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

	//reinitializes the game, deck, and hands
	private static void newGame()
	{
		blackjack = new Blackjack();
		player = blackjack.getPlayer();
		house = blackjack.getHouse();
	}

	//play continues until a winner is decided
	private static void play()
	{
		//when a player stays, booleans retain that decision
		boolean playerStay = false;
		boolean houseStay = false;

		while(player.getScore() < 21 && house.getScore() < 21)
		{
			//Automatically ends the game when the maximum number of cards is reached
			if(player.size() > 5)
			{
				System.out.println("Maximum number of cards reached. You stay");
				break;
			}

			readHands();

			//Player goes first
			if(!playerStay)
			{
				System.out.print("Would you like to (h)it or (s)tay?" );
				if(getPlayerInput(keyboard.next()))
				{
					blackjack.hit(player);

					//If player hits 21 or busts end the game immediately
					if(player.getScore() >= 21)
					{
						break;
					}
				}
				else
				{
					System.out.println("You stay");
					playerStay = true;
				}
			}

			//House's turn
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

	//Checks scores to print the winner
	private static void chooseWinner()
	{
		readHands();

		//player with 21 wins regardless of house score
		if(player.getScore() == 21)
		{
			System.out.println("Player wins!");
		}
		//player bust loses regardless of house score
		else if(player.getScore() > 21)
		{
			System.out.println("House wins!");
		}
		//house bust if player score < 21
		else if(house.getScore() > 21)
		{
			System.out.println("Player wins!");
		}
		//tie under 21
		else if(player.getScore() == house.getScore())
		{
			System.out.println("Draw!");
		}
		//player wins by score
		else if(player.getScore() > house.getScore())
		{
			System.out.println("Player wins!");
		}
		//house wins by score
		else
		{
			System.out.println("House wins!");
		}
	}

	private static void readHands()
	{
		System.out.println();
		System.out.println("House hand: " + house.toString(true));
		System.out.println("Your hand: " + player.toString(false));
	}

	//returns a boolean based on the player's input choice
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

	//returns a boolean based on the house's current score
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
