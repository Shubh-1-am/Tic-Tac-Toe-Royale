import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MinimaxAITicTacToe {
    private int mini_max_score(char[][] board, char player) {
        char winner = Utility.get_winner(board);
        if (winner == 'X') {
            return 10;
        } else if (winner == 'O') {
            return - 10;
        } else if (winner == 'D') {
            return 0;
        }
        int bestScore = (player == 'X') ? Integer.MIN_VALUE: Integer.MAX_VALUE;
        List < int[] > legalMoves = Utility.get_legal_moves(board);

        for (int[] move: legalMoves) {
            int i = move[0];
            int j = move[1];
            board[i][j] = player;
            int score = mini_max_score(board, (player == 'X') ? 'O': 'X');

            board[i][j] = ' ';
            if (player == 'X') {
                bestScore = Math.max(bestScore, score);
            } else {
                bestScore = Math.min(bestScore, score);
            }
        }

        return bestScore;
    }

    private int[] get_best_move(char[][] board, char player) {
        List < int[] > legalMoves = Utility.get_legal_moves(board);
        ArrayList < Integer > scores = new ArrayList < Integer > ();

        for (int[] move: legalMoves) {
            int i = move[0];
            int j = move[1];
            board[i][j] = player;
            int score = mini_max_score(board, (player == 'X') ? 'O': 'X');
            board[i][j] = ' ';
            scores.add(score);
            board[i][j] = ' ';
        }
        int bestScore = (player == 'X') ? Collections.max(scores) : Collections.min(scores);
        int bestIndex = scores.indexOf(bestScore);
        return legalMoves.get(bestIndex);
    }

    public void play() {
        System.out.println("Welcome to the ultimate Tic-Tac-Toe challenge! You will be playing against our unbeatable AI. Are you ready to test your skills? Let's begin the game!");
        try {
            Thread.sleep(3000); // sleep for 3 seconds
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        // Prompt players for their names
        Scanner input = new Scanner(System. in );
        System.out.print("Player , please enter your name: ");
        String playerName = input.nextLine();

        // Prompt players for their choice of X or O
        System.out.print(playerName + ", do you want to be X or O? ");
        char playerChoice = input.next().charAt(0);
        while (playerChoice != 'X' && playerChoice != 'O') {
            System.out.println("Invalid choice. Please enter either X or O");
            playerChoice = input.next().charAt(0);
        }
        char AIChoice = (playerChoice == 'X') ? 'O': 'X';
        System.out.println("AI" + ", will be " + AIChoice);

        // Ask who will play first
        System.out.print("Who will play first? " + playerName + " (1) or " + "AI" + " (2)? ");
        int firstPlayer = input.nextInt();

        // Create a new board
        char[][] board = Utility.new_board();

        // Game loop
        char currentPlayer = (firstPlayer == 1) ? playerChoice: AIChoice;
        while (true) {
            // Render the board
            Utility.render(board);

            // Get the move
            String currentPlayerName = (currentPlayer == playerChoice) ? playerName: "AI";
            System.out.println("It is " + currentPlayerName + "'s turn");
            int[] move;
            if (currentPlayerName == "AI") {
                move = get_best_move(board, currentPlayer);
            } else {
                move = Utility.get_move();

                // Check if the move is valid
                while (!Utility.is_valid_move(board, move)) {
                    System.out.println("Invalid move. Please try again.");
                    move = Utility.get_move();
                }

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
            currentPlayer = (currentPlayer == playerChoice) ? AIChoice: playerChoice;
        }

    }

}