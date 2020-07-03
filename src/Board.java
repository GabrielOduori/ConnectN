
public class Board {

	private int boardX;
	private int boardY;
	private char[][] board;

	public Board(int boardX, int boardY) {
		this.boardX = boardX;
		this.boardY = boardY;
		this.board = new char[boardX][boardY];
		for (int i = 0; i < boardX; i++) {
			for (int j = 0; j < boardY; j++) {
				board[i][j] = '.';
			}
		}
	}

	public void printBoard() {
		for (int i = 0; i < boardX; i++) {
			for (int j = 0; j < boardY; j++) {
				System.out.print(board[i][j] + "  ");

			}
			System.out.println();
		}
	}

	public int getMaxLength() {
		return boardX;
	}

	public int getMaxWidth() {
		return boardY;
	}

	/*
	 * isValid Checks if the input is within the range of the board's length and width.
	 */
	public boolean isValid(int x, int y) {
		if (x >= 0 && y >= 0 && x < boardX && y < boardY)
			return true;
		return false;
	}

	public char getStatus(int x, int y) {
		if (!isValid(x, y))
			return '0';
		return board[x][y];
	}

	public void setStatus(int x, int y, char color) {
		if (!isValid(x, y))
			return;
		board[x][y] = color;

	}

}
