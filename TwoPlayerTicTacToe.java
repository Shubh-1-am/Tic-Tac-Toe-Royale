import java.util.Scanner;

public class TwoPlayerTicTacToe implements TicTacToeGame {

    @Override
    public void play() {
        System.out.println("Welcome to Two Player Tic-Tac-Toe! Let's begin the game.");
        try {
            Thread.sleep(3000); // sleep for 3 seconds
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        // Prompt players for their names
        Scanner input = new Scanner(System. in );
        System.out.print("Player 1, please enter your name: ");
        String player1Name = input.nextLine();
        System.out.print("Player 2, please enter your name: ");
        String player2Name = input.nextLine();

        // Prompt players for their choice of X or O
        System.out.print(player1Name + ", do you want to be X or O? ");
        char player1Choice = input.next().charAt(0);
        while (player1Choice != 'X' && player1Choice != 'O') {
            System.out.println("Invalid choice. Please enter either X or O");
            player1Choice = input.next().charAt(0);
        }
        char player2Choice = (player1Choice == 'X') ? 'O': 'X';
        System.out.println(player2Name + ", you will be " + player2Choice);

        // Ask who will play first
        System.out.print("Who will play first? " + player1Name + " (1) or " + player2Name + " (2)? ");
        int firstPlayer = input.nextInt();

        // Create a new board
        char[][] board = Utility.new_board();

        // Game loop
        char currentPlayer = (firstPlayer == 1) ? player1Choice: player2Choice;
        while (true) {
            // Render the board
            Utility.render(board);

            // Get the move
            String currentPlayerName = (currentPlayer == player1Choice) ? player1Name: player2Name;
            System.out.println("It is " + currentPlayerName + "'s turn");
            int[] move = Utility.get_move();

            // Check if the move is valid
            while (!Utility.is_valid_move(board, move)) {
                System.out.println("Invalid move. Please try again.");
                move = Utility.get_move();
            }

            // Make the move
            Utility.make_move(board, move, currentPlayer);

            // Check if the game is over
            char winner = Utility.get_winner(board);
            if (winner != ' ') {
                Utility.render(board);
                System.out.println();
                Utility.display_message(winner, currentPlayerName);
                break;
            }
            // Switch the current player
            currentPlayer = (currentPlayer == player1Choice) ? player2Choice: player1Choice;
        }

    }

}