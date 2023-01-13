import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ComputerVsPlayerTicTacToe implements TicTacToeGame {

    private int[] find_winning_move(char[][] board, char player) {
        ArrayList<int[]> winningMoves = new ArrayList<int[]>();
        // Check rows
        for (int i = 0 ;i < 3; i++){
            int emptyCell = -1;
            int emptyCount = 0;
            for (int j = 0 ; j < 3; j++){
                if (board[i][j] == ' '){
                    emptyCell = j;
                    emptyCount++;
                } else if (board[i][j] != player){
                    emptyCount = 0;
                    break;
                }
            }
            if (emptyCount == 1){
                int[] move = {i, emptyCell};
                winningMoves.add(move);
            }
        }

        // Check columns
        for (int j = 0 ;j < 3; j++){
            int emptyCell = -1;
            int emptyCount = 0;
            for (int i = 0 ; i < 3; i++){
                if (board[i][j] == ' '){
                    emptyCell = i;
                    emptyCount++;
                } else if (board[i][j] != player){
                    emptyCount = 0;
                    break;
                }
            }
            if (emptyCount == 1){
                int[] move = {emptyCell, j};
                winningMoves.add(move);
            }
        }
        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == ' '){
            int[] move = {2, 2};
            winningMoves.add(move);
        } else if (board[0][0] == player && board[2][2] == player && board[1][1] == ' '){
            int[] move = {1, 1};
            winningMoves.add(move);
        } else if (board[1][1] == player && board[2][2] == player && board[0][0] == ' '){
            int[] move = {0, 0};
            winningMoves.add(move);
        } else if (board[0][2] == player && board[1][1] == player && board[2][0] == ' '){
            int[] move = {2, 0};
            winningMoves.add(move);
        } else if (board[0][2] == player && board[2][0] == player && board[1][1] == ' '){
            int[] move = {1, 1};
            winningMoves.add(move);
        } else if (board[1][1] == player && board[2][0] == player && board[0][2] == ' '){
            int[] move = {0, 2};
            winningMoves.add(move);
        }

        if (winningMoves.size() == 0) {
            return null;
        }
        Random rand = new Random();
        int randomIndex = rand.nextInt(winningMoves.size());
        return winningMoves.get(randomIndex);
    }

    private int[] find_blocking_move(char[][] board, char player) {
        if (player == 'X'){
            return find_winning_move(board, 'O');
        } else {
            return find_winning_move(board, 'X');
        }
    }

    private int[] decide_move(char[][] board) {
        int[] winningMove = find_winning_move(board, 'X');
        if (winningMove != null) {
            return winningMove;
        }
        int[] blockingMove = find_blocking_move(board, 'X');
        if (blockingMove != null) {
            return blockingMove;
        }
        Random rand = new Random();
        int[] move = new int[2];
        List<int[]> legal_moves = Utility.get_legal_moves(board);
        int randomIndex = rand.nextInt(legal_moves.size());
        return legal_moves.get(randomIndex);
    }

    @Override
    public void play() {
        System.out.println("Get ready for an exciting Tic-Tac-Toe match against our AI opponent. May the best player win! Let's start the game!");
        try {
            Thread.sleep(3000);  // sleep for 3 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Prompt players for their names
        Scanner input = new Scanner(System.in);
        System.out.print("Player , please enter your name: ");
        String playerName = input.nextLine();


        // Prompt players for their choice of X or O
        System.out.print(playerName + ", do you want to be X or O? ");
        char playerChoice = input.next().charAt(0);
        while(playerChoice != 'X' && playerChoice != 'O'){
            System.out.println("Invalid choice. Please enter either X or O");
            playerChoice = input.next().charAt(0);
        }
        char AIChoice = (playerChoice == 'X') ? 'O' : 'X';
        System.out.println("AI" + ", will be " + AIChoice);


        // Ask who will play first
        System.out.print("Who will play first? " + playerName + " (1) or " + "AI" + " (2)? ");
        int firstPlayer = input.nextInt();


        // Create a new board
        char[][] board = Utility.new_board();

        // Game loop
        char currentPlayer = (firstPlayer == 1) ? playerChoice : AIChoice;
        while (true) {
            // Render the board
            Utility.render(board);

            // Get the move
            String currentPlayerName = (currentPlayer == playerChoice) ? playerName : "AI";
            System.out.println("It is " + currentPlayerName + "'s turn");
            int[] move;
            if (currentPlayerName == "AI") {
                move = decide_move(board);
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
                Utility.display_message(winner,currentPlayerName);
                break;
            }
            // Switch the current player
            currentPlayer = (currentPlayer == playerChoice) ? AIChoice : playerChoice;
        }

    }

    public static void main(String[] args) {
        ComputerVsPlayerTicTacToe game = new ComputerVsPlayerTicTacToe();
        game.play();
    }




}
