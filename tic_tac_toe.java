import java.util.Scanner;

class tic_tac_toe {
    static Scanner sc = new Scanner(System.in);

    public static void printBoard(char[][] arr) {
        System.out.println("current board");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean checkWin(char[][] board, char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player)
                return true;
        }
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player)
                return true;
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }

        return false;
    }

    public static boolean checkDraw(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '_')
                    return false; // no draw
            }
        }
        return true;
    }

    public static void twoPlayerMode(char[][] board) {

        System.out.println("The positions are from 1,2.....9" + "\nplayer1-->X \nplayer2-->O");
        char currentPlayer = 'X';
        while (true) {
            printBoard(board);
            System.out.print("Player " + currentPlayer + " enter position (1-9): ");
            int pos = sc.nextInt();
            int rowPos = (pos - 1) / 3;
            int colPos = (pos - 1) % 3;
            if (board[rowPos][colPos] == '_') {
                board[rowPos][colPos] = currentPlayer;
                if (checkWin(board, currentPlayer)) {
                    System.out.println("Player " + currentPlayer + " won!");
                    break;
                }
                if (checkDraw(board)) {
                    System.out.println("Its a draw");
                    break;
                }
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            } else {
                System.out.println("Position already occupied");
            }
        }
    }

    public static void vsBot(char[][] board) {

        System.out.println("The positions are from 1,2.....9" + "\nplayer-->X \nBot-->O");
        char currentPlayer = 'X';
        while (true) {
            printBoard(board);
            System.out.print("Player " + currentPlayer + " enter position (1-9): ");
            if (currentPlayer == 'X') {
                int pos = sc.nextInt();
                int rowPos = (pos - 1) / 3;
                int colPos = (pos - 1) % 3;
                if (board[rowPos][colPos] == '_') {
                    board[rowPos][colPos] = currentPlayer;
                } else {
                    System.out.println("Position already occupied");
                }
            } else {
                System.out.println("bots turn");
                while (true) {
                    int pos = (int) (Math.random() * 9) + 1;
                    int rowPos = (pos - 1) / 3;
                    int colPos = (pos - 1) % 3;
                    if (board[rowPos][colPos] == '_') {
                        board[rowPos][colPos] = 'O';
                        break;
                    }
                }
            }
            if (checkWin(board, currentPlayer)) {
                printBoard(board);
                System.out.println((currentPlayer == 'X' ? "You" : "Bot") + " won!");
                break;
            }

            if (checkDraw(board)) {
                printBoard(board);
                System.out.println("It's a draw!");
                break;
            }

            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    public static void main(String[] args) {

        char[][] board = {
                { '_', '_', '_' },
                { '_', '_', '_' },
                { '_', '_', '_' } };
        printBoard(board);
        System.out.println("select mode (vsBot/2player mode) : (0/1)");
        int mode = sc.nextInt();
        if(mode == 1) twoPlayerMode(board);
        if(mode == 0) vsBot(board);

        sc.close();
    }
}