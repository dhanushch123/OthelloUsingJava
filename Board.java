package Othello;
import java.util.Arrays;


public class Board {
	// the board we are going to use is  8 x 8
	private char board[][];
	private char p1Symbol,p2Symbol;
	private int Totalcount=4,p1count=2,p2count=2;
	public static final int PLAYER1WINS = 1;
	public static final int PLAYER2WINS = 2;
	public static final int DRAW = 3;
	public boolean isFull = false;
	
	public Board(char player1Symbol,char player2Symbol)
	{
		board = new char[8][8];
		for(char i[] : board)
		{
			Arrays.fill(i, '-');
		}
		board[3][3] = player1Symbol;
		board[3][4] = player2Symbol;
		board[4][3] = player2Symbol;
		board[4][4] = player1Symbol;
		this.p1Symbol = player1Symbol;
		this.p2Symbol = player2Symbol;
	}
	// we have to write the move function make moves whether it is valid or invalid
	// then we can declare winner only if the board is full .if the board is full we will call another method for result
	public boolean move(int x,int y,char symbol)
	{
		// check whether the position is valid and occupied or not
		if(x < 0 || y < 0 || x >= 8 || y >= 8 || board[x][y] != '-')
		{
			return false;
		}
		// find the opponent su=ymbol
		char OpponentSymbol = p1Symbol == symbol ? p2Symbol : p1Symbol;
		//Explore all directions
		boolean validMove = false;
		int directions[][] = { {-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1} };
		int count = 0;
		for(int[] dir : directions)
		{
			int i = x + dir[0];
			int j =  y + dir[1];
			boolean hasOpponentBetween = false;
			// check for the opponents in the next cell
			while(i >= 0 && j >= 0 && i < 8 && j < 8 && board[i][j] == OpponentSymbol)
			{
				i += dir[0];
				j += dir[1];
				hasOpponentBetween = true;
				
				
				
			}
			// the last one should be of currnt playes symbol black white white white black
			// so that we can able to flip the disks
			
			if(hasOpponentBetween && i >= 0 && j >= 0 && i < 8 && j < 8 && board[i][j] == symbol)
			{
				i -= dir[0];
				j -= dir[1];
				validMove = true;
				while(i != x || j != y)
				{
					board[i][j] = symbol;
					i -= dir[0];
					j -= dir[1];
					count++;
					
					
					
							
				}
			}
			
			
		}
		if(validMove)
		{
			board[x][y] = symbol;
			
			if(symbol == p1Symbol)
			{
				p1count += count;
				p2count -= count;
				p1count++;
			}
			else
			{
				p2count += count;
				p1count -= count;
				p2count++;
			}
			Totalcount = p1count + p2count;
			if(Totalcount == 64)
			{
				isFull = true;
			}
			
			
		}
		return validMove;
		
	}
	public int status()
	{
		if(p1count > p2count)
		{
			return PLAYER1WINS;
		}
		else if(p2count > p1count)
		{
			return PLAYER2WINS;
		}
		else
		{
			return DRAW;
		}
	}
	
	public int getP1Count()
	{
		return this.p1count;
	}
	public int getP2Count()
	{
		return this.p2count;
	}
	
	
	
	
	public void printBoard()
	{
		System.out.print("  ");
		for(int i = 0;i<8;i++)
		{
			System.out.print(i+" ");
		}
		System.out.println();
		int c = 0;
		for(char i[] : board)
		{
			System.out.print(c+" ");
			c++;
			for(char j : i)
			{
				System.out.print(j+" ");
			}
			System.out.println();
		}
	}
	
	
	

}
