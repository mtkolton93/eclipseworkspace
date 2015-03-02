package graphicsBlackjack;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class GraphicsBlackjack extends JPanel implements ActionListener
{
	private Hand player;
	private Hand house;
	private Blackjack blackjack;
	private ImageIcon bg = new ImageIcon("images/Background.png");
	private boolean playerStay = false;
	private boolean houseStay = false;
	private JButton hit;
	private JButton stay;
	private JButton newGame;
	private int money = 600;
	private int turn = 0; //0=player, 1=house, 2=broke
	private int winner = 0; //0=nobody, 1=player, 2=house, 3=draw
	private Timer t = new Timer(800, this);

	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.setSize(818, 638);
		frame.add(new GraphicsBlackjack());
		frame.setVisible(true);
	}

	public GraphicsBlackjack()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		setLayout(null);
		newGame();
		hit = new JButton("Hit");
		hit.setSize(90, 50);
		hit.setLocation(310, 320);
		hit.addActionListener(this);
		add(hit);
		stay = new JButton("Stay");
		stay.setSize(90, 50);
		stay.setLocation(310, 390);
		stay.addActionListener(this);
		add(stay);
		newGame = new JButton("New Game");
		newGame.setSize(100, 80);
		newGame.setLocation(655, 480);
		newGame.addActionListener(this);
		add(newGame);
		setFocusable(true);
		t.start();
	}

	public void paintComponent(Graphics g)
	{
		if(turn == 1 && !houseStay)
			houseTurn();

		//Game
		bg.paintIcon(this, g, 0, 0);
		player.paint(g, false);

		//Info
		g.setFont(new Font("garamond", Font.BOLD, 24));
		g.setColor(Color.green);;
		g.drawString("Money: $" + money, 630, 250);

		//game over
		if(houseStay && playerStay)
		{
			chooseWinner();
			house.paint(g, false);
			g.setColor(new Color(0, 0, 0, 150));
			g.fillRect(0, 141, 603, 458);

			//busts
			if(house.getScore() > 21)
			{
				g.setColor(Color.red);
				g.setFont(new Font("garamond", Font.BOLD, 64));
				g.drawString("BUST!!", 200, 250);
			}
			if(player.getScore() > 21)
			{
				g.setColor(Color.red);
				g.setFont(new Font("garamond", Font.BOLD, 64));
				g.drawString("BUST!!", 200, 550);
			}

			//Show winner
			g.setFont(new Font("garamond", Font.BOLD, 72));
			g.setColor(Color.blue);
			if(winner == 1)
				g.drawString("Player Wins!!", 170, 140);
			if(winner == 2)
				g.drawString("House Wins!!", 170, 140);
			if(winner == 3)
				g.drawString("Draw!!", 200, 140);
		}
		else
		{
			house.paint(g, true);
		}
		if(turn == 2)
		{
			g.setColor(new Color(0, 0, 0, 240));
			g.fillRect(0, 141, 800, 458);
			g.setColor(Color.red);
			g.setFont(new Font("garamond", Font.BOLD, 72));
			g.drawString("YOU'RE BROKE:", 80, 200);
			g.drawString("GTFO", 260, 300);
		}
	}

	//reinitializes the game, deck, and hands
	private void newGame()
	{
		blackjack = new Blackjack();
		player = blackjack.getPlayer();
		house = blackjack.getHouse();
		playerStay = false;
		houseStay = false;
		turn = 0;

		if(winner == 1)
			money += 100;
		if(winner == 2 || winner == 0)
			money -= 100;
		winner = 0;

		if(money < 0)
			turn = 2;
	}

	private void houseTurn()
	{
		if(house.getScore() < 17 && house.size() < 5)
		{
			blackjack.hit(house);

			if(house.getScore() >= 21)
			{
				chooseWinner();
				houseStay = true;
				repaint();
			}
		}
		else
			houseStay = true;

		if(!playerStay && winner == 0)
			turn = 0;
	}

	private void chooseWinner()
	{
		//player with 21 wins regardless of house score
		if(player.getScore() == 21)
			winner = 1;
		//player bust loses regardless of house score
		else if(player.getScore() > 21)
			winner = 2;
		//house bust if player score < 21
		else if(house.getScore() > 21)
			winner = 1;
		//tie under 21
		else if(player.getScore() == house.getScore())
			winner = 3;
		//player wins by score
		else if(player.getScore() > house.getScore())
			winner = 1;
		//house wins by score
		else
			winner = 2;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		//player hits
		if(e.getSource() == hit && turn == 0)
		{
			blackjack.hit(player);

			//auto stay
			if(player.size() >= 5 || player.getScore() > 21)
				playerStay = true;
			//blackjack
			if(player.getScore() == 21)
				chooseWinner();
			//house turn
			else if(!houseStay)
				turn = 1;
		}
		//player stays
		if (e.getSource() == stay && turn == 0)
		{
			playerStay = true;
			//house turn
			if(!houseStay)
				turn = 1;
		}
		if(e.getSource() == newGame && turn < 2)
			newGame();
		//house turn timer
		if(e.getSource() == t)
		{
			if(turn == 1)
				houseTurn();
		}

		repaint();
	}
}
