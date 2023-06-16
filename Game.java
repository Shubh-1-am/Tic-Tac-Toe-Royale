import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        System.out.println("Welcome to Tic-Tac-Toe! Please select a game:");
        System.out.println("1. Two Player Tic-Tac-Toe: A game where two players play against each other on the same device.");
        System.out.println("2. Computer Tic-Tac-Toe: A game where you play against computer using simple AI, it's impossible to beat computer.");
        System.out.println("3. Minimax Tic-Tac-Toe: A game where you play against computer using advanced AI based on minimax algorithm, it's also impossible to beat computer.");
        System.out.println("4. Two Player Tic-Tac-Toe over network: A game where two players play against each other over network.");
        System.out.println("5. Exit");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();

        if (choice == 1) {
            TwoPlayerTicTacToe game = new TwoPlayerTicTacToe();
            game.play();
        } else if (choice == 2) {
            BasicAITicTacToe game = new BasicAITicTacToe();
            game.play();
        } else if (choice == 3) {
            MinimaxAITicTacToe game = new MinimaxAITicTacToe();
            game.play();
        } else if (choice == 4) {
            TwoPlayerGameOverLocalNetwork game = new TwoPlayerGameOverLocalNetwork();
            game.play();
        } else if (choice == 5) {
            System.out.println("Thank you for playing Tic-Tac-Toe!");
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }
}