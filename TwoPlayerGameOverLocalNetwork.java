import java.net.ServerSocket;
import java.net.Socket;

public class TwoPlayerGameOverLocalNetwork implements TicTacToeGame,
        Runnable {

    private static final int PORT = 8888;@Override
    public void play() {

        System.out.println("Welcome to Two Player Tic-Tac-Toe! Let's begin the game.");
        try {
            Thread.sleep(3000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Waiting for players to connect...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            Socket player1Socket = serverSocket.accept();
            System.out.println("Player 1 joined.");
            Player p1 = new Player(player1Socket);
            Socket player2Socket = serverSocket.accept();
            System.out.println("Player 2 joined.");
            Player p2 = new Player(player2Socket);

            p1.out.println("Welcome to the game, Player 1! Please enter your name: ");
            String p1Name = p1. in .readLine();
            p1.setName(p1Name);

            p2.out.println("Welcome to the game, Player 2! Please enter your name: ");

            String p2Name = p2. in .readLine();
            p2.setName(p2Name);

            p1.out.println(p1Name + ", do you want to be X or O? ");
            p1.out.flush();
            char player1Choice = p1. in .readLine().charAt(0);
            while (player1Choice != 'X' && player1Choice != 'O') {
                p1.out.println("Invalid choice. Please enter either X or O");
                p1.out.flush();

                player1Choice = p1. in .readLine().charAt(0);
            }
            p1.setChoice(player1Choice);
            char player2Choice = (player1Choice == 'X') ? 'O': 'X';
            p2.setChoice(player2Choice);
            p2.out.println(p2.name + ", you will be " + p2.choice);

            p1.out.println("Who will play first? " + p1.name + " (1) or " + p2.name + " (2)? ");
            p1.out.flush();

            int firstPlayer = Integer.parseInt(p1. in .readLine());

            char[][] board = Utility.new_board();

            Player currentPlayer = (firstPlayer == 1) ? p1: p2;
            Player otherPlayer = (firstPlayer == 1) ? p2: p1;

            while (true) {
                Utility.render(board, currentPlayer.out);
                Utility.render(board, otherPlayer.out);

                System.out.println("It is " + currentPlayer.name + "'s turn");
                currentPlayer.out.println("Please make your move, " + currentPlayer.name + " (row col): ");
                currentPlayer.out.flush();

                String[] moveInput = currentPlayer.in.readLine().split(" ");
                int[] move = new int[2];

                try {
                    move[0] = Integer.parseInt(moveInput[0]);
                    move[1] = Integer.parseInt(moveInput[1]);
                } catch(NumberFormatException e) {
                    currentPlayer.out.println("Invalid move. Please try again: ");
                    currentPlayer.out.flush();
                    continue;
                }

                while (!Utility.is_valid_move(board, move)) {
                    currentPlayer.out.println("Invalid move. Please try again: ");
                    currentPlayer.out.flush();

                    moveInput = currentPlayer.in.readLine().split(" ");

                    try {
                        move[0] = Integer.parseInt(moveInput[0]);
                        move[1] = Integer.parseInt(moveInput[1]);
                    } catch(NumberFormatException e) {
                        currentPlayer.out.println("Invalid move. Please try again: ");
                        currentPlayer.out.flush();
                    }
                }

                Utility.make_move(board, move, currentPlayer.choice);
                otherPlayer.out.println("Opponent's move: " + move[0] + " " + move[1]);
                otherPlayer.out.println("Current board: ");
                otherPlayer.out.println();
                Utility.render(board, otherPlayer.out);
                otherPlayer.out.flush();

                char winner = Utility.get_winner(board);
                if (winner != ' ') {
                    Utility.render(board, currentPlayer.out);
                    Utility.render(board, otherPlayer.out);
                    currentPlayer.out.println("Game over! " + currentPlayer.name + " wins!");
                    currentPlayer.out.flush();
                    otherPlayer.out.println("Game over! " + currentPlayer.name + " wins!");
                    otherPlayer.out.println();
                    otherPlayer.out.println("Better luck next time!");
                    otherPlayer.out.flush();
                    break;
                }

                Player temp = currentPlayer;
                currentPlayer = otherPlayer;
                otherPlayer = temp;
            }



        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        play();
    }
}