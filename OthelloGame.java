package Othello;
import java.util.Scanner;

public class OthelloGame {
	//we need players and board to start the game
	private Player player1,player2;
	private Board board;
	Scanner sc = new Scanner(System.in);
	
	
	public static void main(String[] args)
	{
		OthelloGame O = new OthelloGame();
		
		O.startGame();
	}
	
	
	public void startGame()
	{
		// take playerInputs
		player1 = takePlayerInput(1);
		player2 = takePlayerInput(2);
		while(player1.getSymbol() == player2.getSymbol())
		{
			System.out.println("Symbol has already Taken !! Please select unique symbol ");
			player2.setSymbol(sc.next().charAt(0));
		}
		board = new Board(player1.getSymbol(),player2.getSymbol());
		boolean player1turn = true;
		// the loop should continue until the board is full
		boolean validMove = false;
		while(!board.isFull)
		{
			if(player1turn)
			{
				System.out.println("Player1's Turn Enter the position : ");
				board.printBoard();
				System.out.println("Enter x[row] : ");
				int x = sc.nextInt();
				System.out.println("Enter y[column] : ");
				int y = sc.nextInt();
				
				validMove = board.move(x,y,player1.getSymbol());
				
			}
			else
			{
				System.out.println("Player2's Turn Enter the position : ");
				board.printBoard();
				System.out.println("Enter x[row] : ");
				int x = sc.nextInt();
				System.out.println("Enter y[column] : ");
				int y = sc.nextInt();
				
				validMove = board.move(x,y,player2.getSymbol());
			}
			if(validMove)
			{
				player1turn = !player1turn;
				board.printBoard();
				validMove = false;
				System.out.println(board.getP1Count() +" " + board.getP2Count());
			}
			else
			{
				System.out.println("The move is inavlid !! Make a valid move !!");
			}
			
			
			
			
			
		}
		int status = board.status();
		switch(status)
		{
		case(Board.PLAYER1WINS):
			board.printBoard();
		    System.out.println(player1.getName() + " Score: " + board.getP1Count());
		    System.out.println(player2.getName() + " Score: " + board.getP2Count());

			System.out.println(player1.getName()+" wins !!");
		    break;
		case(Board.PLAYER2WINS):
			board.printBoard();
		System.out.println(player1.getName() + " Score: " + board.getP1Count());
	    System.out.println(player2.getName() + " Score: " + board.getP2Count());

			System.out.println(player2.getName()+" wins !!");
		    break;
		case(Board.DRAW):
			board.printBoard();
		System.out.println(player1.getName() + " Score: " + board.getP1Count());
	    System.out.println(player2.getName() + " Score: " + board.getP2Count());

			System.out.println("DRAW !!");
		}
		
		
		
		
	}
	public Player takePlayerInput(int num)
	{
		System.out.println("Enter player"+num+"'s name : ");
		String name = sc.next();
		System.out.println("Enter player"+num+"'s symbol : ");
		char symbol = sc.next().charAt(0);
		while(symbol == '\0' || symbol == ' ')
		{
			System.out.println("Symbol is invalid !! Please select a valid symbol");
			symbol = sc.next().charAt(0);
		}
		return new Player(name,symbol);
		
	}
	

}
