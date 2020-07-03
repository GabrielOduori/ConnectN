

class Main {
	  public static void main(String[] args) {
		  gameIntro.startPage();
		  Player player1 = new Player("Player1",'r');
		  Player player2 = new Player("Player2",'y');
		  Player botPlayer = new Player("BotPlayer", 'g');
		  Player players[]= {player1,player2, botPlayer};
		  Board board = new Board(5,7);
		  Game game = new Game(board, players);
		  game.playGame();

//		  new MyConnectFour();
	
		 
	  }
	}