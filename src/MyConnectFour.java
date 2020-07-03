import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Random;

public class MyConnectFour {

	private BufferedReader input;
	private char[][] board;
	private int boardX = 6;
	private int boardY = 7;
	private int totalPlayers = 2;

	public MyConnectFour() {
		board = new char[boardX][boardY];
		for (int i = 0; i < boardX; i++) {
			for (int j = 0; j < boardY; j++) {
				board[i][j] = '.';
			}

		}
		input = new BufferedReader(new InputStreamReader(System.in));
		playGame();
	}
	// getPlayer() helps in knowing who is playing.

	private char getPlayer(int player) {
		if (player == 0)
			return 'r';
		if (player == 1)
			return 'y';
		return '#';
	}

	private void playGame() {
		System.out.println("Welcome to Connect 4");
		System.out.println("There are 2 players red and yellow");
		System.out.println("Player 1 is Red, Player 2 is Yellow");
		System.out.println("To play the game type in the number of the column you want to drop you counter in");
		System.out.println("A player wins by connecting 4 counters in a row - vertically, horizontally or diagonally");
		System.out.println("");
		printBoard();
		int playerTurn = 0;

		boolean win = false;
		while (!win) {
			// Print out which player is playing.
			System.out.println("Player turn : " + (playerTurn + 1));
			// Prompt to add in the coordinates to play. This is a human player
			int userInput[] = { 1, 2 };
			if (playerTurn == 0)
				//The first player will always be a human player.
				userInput = getUserInput();

			//If not the first player, its the bots turn to play the game.
			else {
				Random rand = new Random();
				userInput[0] = rand.nextInt(boardX) + 1;
				userInput[1] = rand.nextInt(boardY) + 1;

			}
			userInput[0]--;
			userInput[1]--;
			char playerSymb = getPlayer(playerTurn);
			placeCounter(playerSymb, userInput);
			playerTurn = (playerTurn + 1) % totalPlayers;
			
			/*
			 * Modularised all the check wining conditions from the code that was originally
			 * available. This may have moved a couple of lines as reported in the bug
			 * report
			 */
			if (checkHorizontal(userInput[0], userInput[1], playerSymb)
					|| checkVertical(userInput[0], userInput[1], playerSymb)
					|| checkDiagonalLeft(userInput[0], userInput[1], playerSymb)
					|| checkDiagonalRight(userInput[0], userInput[1], playerSymb)) {
				win = true;
				System.out.println("Player:- " + playerTurn + " won!!");
			}
			printBoard();

		}

	}

	
	private int[] getUserInput() {
		try {

			String s1 = input.readLine();
			String s2 = input.readLine();
			int m1 = Integer.parseInt(s1);
			int m2 = Integer.parseInt(s2);
			int userinput[] = { m1, m2 };
			return userinput;
		} catch (IOException e) {

		}
		return null;
	}

	private void printBoard() {
		for (int i = 0; i < boardX; i++) {
			for (int j = 0; j < boardY; j++) {
				System.out.print(board[i][j] + "  ");

			}
			System.out.println();
		}
	}

	/*
	 * isValid Checks if the input is valid and is within the range of the board
	 */
	private boolean isValid(int x, int y) {
		if (x >= 0 && y >= 0 && x < boardX && y < boardY)
			return true;
		return false;
	}

	/*
	 * placeCounter Places token to a position on the board.
	 */
	private void placeCounter(char player, int position[]) {
		int x = position[0];
		int y = position[1];
		if (isValid(x, y) && board[x][y] == '.')
			board[x][y] = player;
	}
	
	/*
	 * checkDiagonalLeft
	 * Checking wining sequence diagonally from from top left corner to bottom
	 * right corner of the board and reverse.
	 */

	private boolean checkDiagonalLeft(int x, int y, char player) {
		if (!isValid(x, y))
			return false;
		int count = 0;
		while (x > 0 && y > 0) {
			x--;
			y--;
		}
		while (x < boardX && y < boardY) {
			if (board[x][y] == player) {
				count++;
				if (count >= 4)
					break;
			} else
				count = 0;
			x++;
			y++;
		}
		return count >= 4;
	}

	/*
	 * checkDiagonalRight
	 * Checking wining sequence diagonally from from top right corner to bottom
	 * left corner of the board and reverse.
	 */
	private boolean checkDiagonalRight(int x, int y, char player) {
		if (!isValid(x, y))
			return false;
		int count = 0;
		while (x > 0 && y < boardY - 1) {
			x--;
			y++;
		}
		while (x < boardX && y >= 0) {
			if (board[x][y] == player) {
				count++;
				if (count >= 4)
					break;
			} else
				count = 0;
			x++;
			y--;
		}
		return count >= 4;
	}

	/*
	 * checkVertical
	 * Check vertical runs from top to bottom of the board t check if the wining
	 * condition is met.
	 */
	private boolean checkVertical(int x, int y, char player) {
		if (!isValid(x, y))
			return false;
		int count = 0;
		for (int i = 0; i < boardX; i++) {
			if (board[i][y] == player) {
				count++;
				if (count >= 4)
					break;
			} else {
				count = 0;
			}
		}
		return count >= 4;

	}

	/* checkHorizontal
	 * Check vertical runs across the board checking if the wining condition is
	 * true.
	 */
	private boolean checkHorizontal(int x, int y, char player) {
		if (!isValid(x, y))
			return false;
		int count = 0;
		for (int j = 0; j < boardY; j++) {
			if (board[x][j] == player) {
				count++;
				if (count >= 4)
					break;
			} else {
				count = 0;
			}
		}
		return count >= 4;
	}

}
