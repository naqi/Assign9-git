import java.util.Scanner;

/*
 * CS312 Assignment 9
 * A program to play connect four.
 *
 * On my honor this program is my own work:
 * <Tehreem Syed>
 *
 *  email address:tehreemsyed@utexas.edu
 *  UTEID:tfs385
 *  Section 5 digit ID:
 *  Grader name:Vicky
 *  Number of slip days used on this assignment:None
 *
 * Number of slip days I am using:
 */

public class ConnectFour {

	public static final int currentRow = 5;

	public static void main(String[] args) {
		Scanner key = new Scanner(System.in);

		intro();

		char[][] board = new char[6][7];

		System.out.print("Player 1 enter your name: ");
		String name1 = key.next();
		System.out.print("Player 2 enter your name: ");
		String name2 = key.next();
		System.out.println();

		runGame(key, board, name1, name2);
	}

	// This method shows the intro to the program
	public static void intro() {
		System.out.println("This program allows two people to play the");
		System.out.println("game of Connect four. Each player takes turns");
		System.out.println("dropping a checker in one of the open columns");
		System.out.println("on the board. The columns are numbered 1 to 7.");
		System.out.println("The first player to get four checkers in a row");
		System.out.println("horizontally, vertically, or diagonally wins");
		System.out.println("the game. If no player gets fours in a row and");
		System.out.println("and all spots are taken the game is a draw.");
		System.out.println("/nPlayer one's checkers will appear as r's and");
		System.out.println("player two's checkers will appear as b's.");
		System.out.println("Open spaces on the board will appear as .'s.\n");
	}

	// the entire game is palyed within this method
	public static void runGame(Scanner key, char[][] board, String name1,
			String name2) {
		initializeBoard(board);
		while (ifNoWin()) {

			displayBoard(board);
			char choice1 = 'r';// player 1 choice
			char choice2 = 'b';// player 2 choice
			int turn1 = 0;// player 1 column pick
			int turn2 = 0; // player 2 column pick

			turn1 = getColumn(name1, choice1, key, board);
			turn2 = getColumn(name2, choice2, key, board);

			placeChecker(turn1, board, choice1);

			placeChecker(turn2, board, choice2);

			displayBoard(board);
		}

	}

	// boolean method that returns whetehr somebody won or not
	public static boolean ifNoWin() {
		return true;

	}

	// this method prompts the user to make a choice for column num and checks
	// whther they made a valid input or not
	public static int getColumn(String name, char choice, Scanner keyboard,
			char[][] board) {

		String prompt = "";

		System.out.println(name + " it is your turn.");
		System.out.println("Your pieces are the " + choice + "'s.");

		prompt = name + ", enter the column to drop your checker: ";

		return getUserInput(name, keyboard, prompt, board);

	}

	public static int getUserInput(String name, Scanner keyboard,
			String prompt, char[][] board) {

		System.out.print(prompt);

		int selection = getInt(keyboard, prompt) - 1;

		if (inRange(5, selection, board)) {
			return selection;
		} else {
			System.out.println((selection + 1) + ", is not a valid column");
			selection = getUserInput(name, keyboard, prompt, board);
		}

		return selection;

	}

	// place the player's choice on the board
	public static void placeChecker(int turn, char[][] board, char choice) {

		board[currentRow][turn] = choice;

	}

	// displayBoard just displays the board
	public static void displayBoard(char[][] board) {
		System.out.println("Current Board");
		for (int i = 1; i <= board[0].length; i++) {
			System.out.print(i + " ");
		}
		System.out.print("  column numbers");
		System.out.println();

		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[r].length; c++) {

				System.out.print(board[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	// initializes all the elements of the array to .'s and prints the initial
	// board.change elements for each turn player takes later on
	public static void initializeBoard(char[][] board) {
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[r].length; c++) {

				board[r][c] = '.';
			}
		}
	}

	// This method will return true if r and c specify
	// a valid location in the 2d array named board.
	// I expect board to be a rectangular 2d array
	// meaning every row has the same number of columns
	public static boolean inRange(int r, int c, char[][] board) {
		return 0 <= r && r < board.length && 0 <= c && c < board[0].length;
	}

	// This method prompts the user for an int.
	// The String prompt will be printed out first.
	// The method error checks the response and continues
	// to ask for input until an int is entered.
	// I expect key is connected to System.in.
	public static int getInt(Scanner key, String prompt) {
		while (!key.hasNextInt()) {
			String notAnInt = key.nextLine();
			System.out.println(notAnInt + " is not an integer.");
			System.out.print(prompt);
		}
		int result = key.nextInt();
		key.nextLine();
		return result;
	}
}
