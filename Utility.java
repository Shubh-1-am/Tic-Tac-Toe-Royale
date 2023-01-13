import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utility {

    public static char[][] new_board() {
        char[][] board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
        return board;
    }
    public static void render(char[][] board){
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_GREEN = "\u001B[32m";

        System.out.println("   0   1   2");
        for (int i = 0; i < 3; i++){
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++){
                if (board[i][j] == 'X'){
                    System.out.print(" "+ANSI_BLUE + board[i][j] + ANSI_RESET+" ");
                }
                else if (board[i][j] == 'O'){
                    System.out.print(" "+ANSI_GREEN + board[i][j] + ANSI_RESET+" ");
                }
                else{
                    System.out.print("   ");
                }
                if (j != 2){
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i != 2){
                System.out.println("  ---+---+---");
            }
        }
    }
    public static int[] get_move(){
        Scanner input = new Scanner(System.in);
        int[] move = new int[2];
        System.out.print("Enter your move (row, column): ");
        move[0] = input.nextInt();
        move[1] = input.nextInt();
        return move;
    }

    public static boolean is_valid_move(char[][] board, int[] move){
        if (move[0] < 0 || move[0] > 2 || move[1] < 0 || move[1] > 2){
            return false;
        }
        else if (board[move[0]][move[1]] != ' '){
            return false;
        }
        else{
            return true;
        }
    }

    public static void make_move(char[][] board, int[] move, char player) {
        board[move[0]][move[1]] = player;
    }
    public static boolean is_draw(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static char get_winner(char[][] board) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
                return board[i][0];
            }
        }

        for (int j = 0; j < 3; j++) {
            if (board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != ' ') {
                return board[0][j];
            }
        }

        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') {
            return board[0][0];
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ') {
            return board[0][2];
        }

        if (is_draw(board)){
            return 'D';
        }

        return ' ';
    }

    public static void display_message(char winner, String playerName) {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_YELLOW = "\u001B[33m";
        if (winner == 'X') {
            System.out.println(ANSI_BLUE + playerName +"(X) won " + "\uD83D\uDC7E" + ANSI_RESET);
        } else if (winner == 'O') {
            System.out.println(ANSI_GREEN + playerName + "(O) won " + "\uD83D\uDC9A" + ANSI_RESET);
        } else if (winner == 'D') {
            System.out.println(ANSI_YELLOW + "Match Draw " + "\uD83D\uDC9B" + ANSI_RESET);
        }
    }

    public static List<int[]> get_legal_moves(char[][] board) {
        List<int[]> legalMoves = new ArrayList<int[]>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    int[] move = {i, j};
                    legalMoves.add(move);
                }
            }
        }

        return legalMoves;
    }
}
