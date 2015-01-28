package topSpin;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;

@SuppressWarnings("serial")
public class TopSpin extends JPanel implements MouseListener, KeyListener
{
	ArrayList<Integer> numbers = new ArrayList<Integer>();
	ImageIcon image = new ImageIcon("images/image.png");
	ImageIcon winOverlay = new ImageIcon("images/winOverlay.png");
	boolean win = false;
	Button left;
	Button right;
	Button skipLeft;
	Button flip;
	Button skipRight;
	Button solve16;
	Button solve;
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.setSize(816, 638);
		frame.add(new TopSpin());
		frame.setVisible(true);
	}
	
	public TopSpin()
	{
		addKeyListener(this);
		addMouseListener(this);
		setFocusable(true);
		
		for(int i = 1; i < 21; i++)
		{
			numbers.add(i);
		}
		
		left = new Button(this);
		left.setIcon("images/left.png");
		left.setPosition(177, 166);
		left.setSize(94, 63);
		right = new Button(this);
		right.setIcon("images/right.png");
		right.setPosition(514, 166);
		right.setSize(94, 63);
		skipLeft = new Button(this);
		skipLeft.setIcon("images/skipLeft.png");
		skipLeft.setPosition(157, 86);
		skipLeft.setSize(94, 63);
		skipRight = new Button(this);
		skipRight.setIcon("images/skipRight.png");
		skipRight.setPosition(524, 86);
		skipRight.setSize(94, 63);
		flip = new Button(this);
		flip.setIcon("images/flip.png");
		flip.setPosition(297, 191);
		flip.setSize(200, 189);
		solve16 = new Button(this);
		solve16.setIcon("images/solve16.png");
		solve16.setPosition(621, 30);
		solve16.setSize(149, 56);
		solve = new Button(this);
		solve.setIcon("images/solve.png");
		solve.setPosition(30, 30);
		solve.setSize(149, 56);
		
		Collections.shuffle(numbers);
	}
	
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, 800, 600);
		image.paintIcon(this, g, 0, 0);
		left.paint(g);
		right.paint(g);
		skipLeft.paint(g);
		skipRight.paint(g);
		flip.paint(g);
		solve.paint(g);
		solve16.paint(g);
		
		g.setColor(Color.black);
		g.setFont(new Font("Arial Black", Font.PLAIN, 18));
		g.drawString(numbers.get(0).toString(), 304, 291);
		g.drawString(numbers.get(1).toString(), 359, 294);
		g.drawString(numbers.get(2).toString(), 413, 293);
		g.drawString(numbers.get(3).toString(), 469, 293);
		g.drawString(numbers.get(4).toString(), 528, 302);
		g.drawString(numbers.get(5).toString(), 578, 333);
		g.drawString(numbers.get(6).toString(), 605, 385);
		g.drawString(numbers.get(7).toString(), 605, 442);
		g.drawString(numbers.get(8).toString(), 579, 491);
		g.drawString(numbers.get(9).toString(), 527, 518);
		g.drawString(numbers.get(10).toString(), 465, 523);
		g.drawString(numbers.get(11).toString(), 409, 524);
		g.drawString(numbers.get(12).toString(), 356, 524);
		g.drawString(numbers.get(13).toString(), 300, 521);
		g.drawString(numbers.get(14).toString(), 244, 521);
		g.drawString(numbers.get(15).toString(), 195, 493);
		g.drawString(numbers.get(16).toString(), 164, 444);
		g.drawString(numbers.get(17).toString(), 165, 387);
		g.drawString(numbers.get(18).toString(), 195, 335);
		g.drawString(numbers.get(19).toString(), 246, 304);
		
		if(win)
		{
			winOverlay.paintIcon(this, g, 0, 0);
		}
	}
	
	public void moveLeft()
	{
		int temp = numbers.get(0);
		numbers.remove(0);
		numbers.add(temp);
	}
	
	public void moveRight()
	{
		ArrayList<Integer> temp = new ArrayList<Integer>();
		temp.add(numbers.get(19));
		for(int i = 0; i < 19; i++)
		{
			temp.add(numbers.get(i));
		}
		numbers = temp;
	}
	
	public void skipRight()
	{
		flip();
		moveLeft();
		flip();
		moveRight();
		flip();
		moveLeft();
		flip();
		moveRight();
	}
	
	public void skipLeft()
	{
		flip();
		moveRight();
		flip();
		moveLeft();
		flip();
		moveRight();
		flip();
		moveLeft();
	}
	
	public void flip()
	{
		int temp = numbers.get(0);
		numbers.set(0, numbers.get(3));
		numbers.set(3, temp);
		
		temp = numbers.get(1);
		numbers.set(1, numbers.get(2));
		numbers.set(2, temp);
	}
	
	public void autoSolve16()
	{
		for(int i = 0; i < 16; i++)
		{
			numbers.set(i, i+1);
		}

		numbers.set(16, 18);
		numbers.set(17, 20);
		numbers.set(18, 19);
		numbers.set(19, 17);
	}
	
	public void autoSolve()
	{
		for(int i = 0; i < 20; i++)
		{
			numbers.set(i, i+1);
		}
	}
	
	public void checkForWin()
	{
		boolean temp = true;
		
		//Find 1: that's where it starts checking
		int startIndex = numbers.indexOf(1);
		
		//Starting from 1, check if the next number in the list is 1 greater
		for(int i = startIndex; i < 19; i++)
		{
			//if not, make temp false
			if(numbers.get(i+1) != (numbers.get(i) + 1))
			{
				temp = false;
			}
		}
		
		//If temp is still true, wrap around and check the rest of the numbers the same way
		if(temp)
		{
			for(int i = 0; i < startIndex-1; i++)
			{
				if(numbers.get(i+1) != (numbers.get(i) + 1))
				{
					temp = false;
				}
			}
		}
		
		win = temp;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if(left.contains(e.getX(), e.getY()) && !win)
		{
			moveLeft();
		}
		if(right.contains(e.getX(), e.getY()) && !win)
		{
			moveRight();
		}
		if(skipLeft.contains(e.getX(), e.getY()) && !win)
		{
			skipLeft();
		}
		if(skipRight.contains(e.getX(), e.getY()) && !win)
		{
			skipRight();
		}
		if(flip.contains(e.getX(), e.getY()) && !win)
		{
			flip();
			checkForWin();
		}
		if(solve16.contains(e.getX(), e.getY()) && !win)
		{
			autoSolve16();
		}
		if(solve.contains(e.getX(), e.getY()) && !win)
		{
			autoSolve();
		}
		repaint();
	}
	@Override
	public void mouseEntered(MouseEvent e){}
	@Override
	public void mouseExited(MouseEvent e){}
	@Override
	public void mousePressed(MouseEvent e){}
	@Override
	public void mouseReleased(MouseEvent e){}

	
	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			moveLeft();
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			moveRight();
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			flip();
		}
		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
		{
			skipLeft();
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			skipRight();
		}
		repaint();
	}
	@Override
	public void keyReleased(KeyEvent e){}
	@Override
	public void keyTyped(KeyEvent e){}
}
