import java.util.Scanner;

class TicTacToe {
    static char[][] board;

    public TicTacToe() {
        board = new char[3][3];
        initBoard();
    }

    void initBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ' ';
            }
        }
    }

    static void disBoard() {
        System.out.println("--------------");
        for (int i = 0; i < board.length; i++) {
            System.out.print("| "); 
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("--------------");
        }
    }

    static void placeMark(int row, int col, char mark) {
        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
            board[row][col] = mark;
        } else {
            System.out.println("Invalid Position");
        }
    }

    static boolean checkColWin() {
        for (int j = 0; j <= 2; j++) {
            if (board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return true;
            }
        }
        return false;
    }

    static boolean checkRowWin() {
        for (int i = 0; i <= 2; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }
        return false;
    }

    static boolean checkDiaWin() {
        return (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2])
                || (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]);
    }
}

class HumanPlayer {
    String name;
    char mark;

    HumanPlayer(String name, char mark) {
        this.name = name;
        this.mark = mark;
    }

    void makeMove() {
        Scanner scan = new Scanner(System.in);
        int row, col;
        do {
            System.out.println(name + ", enter the row and col:");
            row = scan.nextInt();
            col = scan.nextInt();
        } while (!isValidMove(row, col));

        TicTacToe.placeMark(row, col, mark);
    }

    boolean isValidMove(int row, int col) {
        return row >= 0 && row <= 2 && col >= 0 && col <= 2 && TicTacToe.board[row][col] == ' ';
    }
}

public class LaunchGame {
    public static void main(String[] args) {
        TicTacToe t = new TicTacToe();

        HumanPlayer p1 = new HumanPlayer("reddy", 'X');
        HumanPlayer p2 = new HumanPlayer("Proji", 'O');

        HumanPlayer cp = p1;

        while (true) {
            System.out.println(cp.name + " turn");
            cp.makeMove();
            TicTacToe.disBoard();

            if (TicTacToe.checkColWin() || TicTacToe.checkRowWin() || TicTacToe.checkDiaWin()) {
                System.out.println(cp.name + " has won!");
                break;
            }

            cp = (cp == p1) ? p2 : p1; 
        }
    }
}
