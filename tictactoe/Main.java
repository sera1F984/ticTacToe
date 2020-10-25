package tictactoe;

import java.util.Scanner;


public class Main {
    static char[][] board = new char[3][3];
    static Scanner scan = new Scanner(System.in);
    static int coordinateX, coordinateY;
    static char player = 'X';
    static boolean winner = false;


    public static void main(String[] args) {
        printBoard(setBoard(board));
        while (!winner) {
            getInput();
            printBoard(updatedBoard(board));
            winner = win(player);
            if (win(player)) {
                System.out.println(player + " wins");
                break;
            } else if (win(player) && !emptyCellsInBoard()){
                System.out.println("Draw");
                break;
            }
        }
    }


    private static char[][] setBoard(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
        return board;
    }

    public static void getInput() {
        boolean validInput = false;

        System.out.print("Enter coordinates: ");

        while (!validInput) {
            if (scan.hasNextInt()) {
                coordinateX = scan.nextInt();
             } else {
                scan.nextLine();
                System.out.println("You should enter numbers!");
                continue;
            }
            if (scan.hasNextInt()) {
                coordinateY = scan.nextInt();
            } else {
                scan.nextLine();
                System.out.println("You should enter numbers!");
                continue;
            }

            if (coordinateX < 1 || coordinateX > 3 || coordinateY < 1 || coordinateY > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if (!isEmptyBox(board)){
                    System.out.println("This cell is occupied! Choose another one!");
            } else {
                validInput = true;
                updatedBoard(board);
                playerTurn();
            }
        }
    }

    private static boolean emptyCellsInBoard() {
        for (int rij = 0; rij < board.length; rij++) {
            for (int kolom = 0; kolom < board.length; kolom++) {
                if (board[rij][kolom] == ' ') {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isEmptyBox(char[][] board) {
        return board[3-coordinateY][coordinateX - 1] == '_' || board[3-coordinateY][coordinateX - 1] == ' ';
    }

    private static boolean win(char player) {
        // horizontaal
        for (int rij = 0; rij < board.length; rij++) {
            for (int kolom = 0; kolom < board[0].length - 2; kolom++) {
                if (board[rij][kolom] == player &&
                        board[rij][kolom + 1] == player &&
                        board[rij][kolom + 2] == player) {
                    return true;
                }
            }
        }
        // verticaal
        for (int rij = 0; rij < board.length - 2; rij++) {
            for (int kolom = 0; kolom < board[0].length; kolom++) {
                if (board[rij][kolom] == player &&
                        board[rij+1][kolom] == player &&
                        board[rij+2][kolom] == player) {
                    return true;
                }
            }
        }
        //diagonaal hoog
            for (int rij = 2; rij < board.length; rij++) {
                for (int kolom = 0; kolom < board[0].length - 2; kolom++) {
                    if (board[rij][kolom] == player &&
                            player == board[rij - 1][kolom + 1] &&
                            player == board[rij - 2][kolom + 2]) {
                        return true;
                    }
                }
            }
        // diagonaal laag
        for (int rij = 0; rij < board.length - 2; rij++) {
            for (int kolom = 0; kolom < board[0].length - 2; kolom++) {
                if (board[rij][kolom] == player &&
                        player == board[rij + 1][kolom + 1] &&
                        player == board[rij + 2][kolom + 2]) {
                    return true;
                }
            }

        }
        return false;
    }

    public static void printBoard(char[][] board) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    private static char[][] updatedBoard(char[][] board) {
        char[][] updatedBoard = Main.board;
        updatedBoard[3-coordinateY][coordinateX-1] = player;
        return updatedBoard;
    }

    private static char playerTurn() {
        if (player == 'X') {
            player = 'O';
        } else {
            player = 'X';
        }
        return player;
    }
}


