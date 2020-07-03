import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/*
 * Game class is responsible for playing game
 */

public class Game {
	// TODO Auto-generated constructor stub
	private Board board;
	Player players[];
	private int totalPlayers;

	public Game(Board board, Player players[]) {
		this.board = board;
		this.players = players;
		this.totalPlayers = players.length;

	}

	public void playGame() {
		int playerTurn = 0;
		boolean win = false;
		while (!win) {
			board.printBoard();
			System.out.println();
			System.out.println("Player Playing  is #: " + (playerTurn + 1));
			int userInput[] = { 1, 2 };
			if (playerTurn == 0)
				userInput = getuserInput();
			// Add a bot player
			else {
				Random rand = new Random();
				userInput[0] = rand.nextInt(board.getMaxLength()) + 1;
				userInput[1] = rand.nextInt(board.getMaxWidth()) + 1;

			}
			userInput[0]--;
			userInput[1]--;

			char playerSymb = players[playerTurn].getColor();
			placeCounter(playerSymb, userInput);

			// Check for a winning condition
			if (checkHorizontal(userInput[0], userInput[1], playerSymb)
					|| checkVertical(userInput[0], userInput[1], playerSymb)
					|| checkDiagonalLeft(userInput[0], userInput[1], playerSymb)
					|| checkDiagonalRight(userInput[0], userInput[1], playerSymb)) {
				win = true;
				System.out.println("Player:- " + playerTurn + " won!!");
			}
			playerTurn = (playerTurn + 1) % totalPlayers;

		}
	}

	private int[] getuserInput() {
		BufferedReader inputString;
		inputString = new BufferedReader(new InputStreamReader(System.in));
		// String toReturn = null;
		try {

			String firstString = inputString.readLine();
			String secondString = inputString.readLine();
			int firstCoord = Integer.parseInt(firstString);
			int secondCoord = Integer.parseInt(secondString);
			int userInput[] = { firstCoord, secondCoord };
			return userInput;
		} catch (IOException e) {
			System.err.println(e);

		}
		return null;
	}

	/*
	 * checkDiagonalLeft checkDiagonalLeft Checking wining sequence diagonally from
	 * from top left corner to bottom right corner of the board and reverse.
	 */
	private boolean checkDiagonalLeft(int x, int y, char player) {
		if (!board.isValid(x, y))
			return false;
		int count = 0;
		while (x > 0 && y > 0) {
			x--;
			y--;
		}
		while (x < board.getMaxLength() && y < board.getMaxWidth()) {
			if (board.getStatus(x, y) == player) {
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
	 * checkDiagonalRight checkDiagonalRight Checking wining sequence diagonally
	 * from from top right corner to bottom left corner of the board and reverse.
	 */
	private boolean checkDiagonalRight(int x, int y, char player) {
		if (!board.isValid(x, y))
			return false;
		int count = 0;
		while (x > 0 && y < board.getMaxWidth() - 1) {
			x--;
			y++;
		}
		while (x < board.getMaxLength() && y >= 0) {
			if (board.getStatus(x, y) == player) {
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
	 * checkVertical checkVertical Check vertical runs from top to bottom of the
	 * board to check if the wining condition is met. If not resets the count to
	 * zero
	 */
	private boolean checkVertical(int x, int y, char player) {
		if (!board.isValid(x, y))
			return false;
		int count = 0;
		for (int i = 0; i < board.getMaxLength(); i++) {
			if (board.getStatus(i, y) == player) {
				count++;
				if (count >= 4)
					break;
			} else {
				count = 0;
			}
		}
		return count >= 4;

	}

	/*
	 * checkHorizontal checkHorizontal Check vertical runs across the board checking
	 * if the wining condition is true. If not resets the count to zero.
	 */
	private boolean checkHorizontal(int x, int y, char player) {
		if (!board.isValid(x, y))
			return false;
		int count = 0;
		for (int j = 0; j < board.getMaxWidth(); j++) {
			if (board.getStatus(x, j) == player) {
				count++;
				if (count >= 4)
					break;
			} else {
				count = 0;
			}
		}
		return count >= 4;
	}
	// Places a token into the right position on the board.

	private void placeCounter(char color, int position[]) {
		int x = position[0];
		int y = position[1];
		if (board.isValid(x, y) && board.getStatus(x, y) == '.') {
			board.setStatus(x, y, color);
		}
	}

	private int winningCondition() {
		
		BufferedReader winCondition;
		winCondition = new BufferedReader(new InputStreamReader(System.in));
		// String toReturn = null;
		try {

			String winStringtring = winCondition.readLine();
			int userCondition = Integer.parseInt(winStringtring);
			return userCondition;

		} catch (IOException e) {
			System.err.println(e);

		}
		return 0;

	}
}
