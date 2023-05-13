package unit12;

import java.util.Arrays;

public class ConnectFour {
    private int[][] board;

    public ConnectFour(int[][] board) {
        this.board = board;
    }

    /**
     * @param row
     * @param col
     * @return true if the space is not occupied
     */
    public boolean isEmpty(int row, int col) {
        if (isValid(row, col) && board[row][col] == 0) {
            return true;
        }
        return false;
    }

    /**
     * @param row
     * @param col
     * @return true if the indexes are in the board
     */
    public boolean isValid(int row, int col) {
        if (row > -1 && col > -1 && row < board.length && col < board[0].length) {
            return true;
        }
        return false;
    }

    /**
     * @return array of the possible locations for a move
     */
    public int[] getMoves() {
        int[] moves = new int[board[0].length];
        for (int col = 0; col < moves.length; col++) {
            boolean test = false;
            int row = board.length;
            while (test == false && row > 0) {
                row--;
                test = isEmpty(row, col);
            }
            if (test) {
                moves[col] = row;
            } else {
                moves[col] = -1;
            }
        }
        return moves;
    }
    
    public void makeMove(int row, int col) {
        //interfaces or somthing idk    
    }
}

class Runner {
    public static void main(String[] args) {
        int[][] board = { { 0, 0, 2, 0, 0, 0 }, { 0, 0, 1, 0, 0, 1 }, { 0, 0, 1, 0, 0, 1 }, { 0, 0, 2, 0, 1, 2 },
                { 1, 0, 1, 0, 2, 2}, { 2, 0, 1, 2, 1, 2 } };
        ConnectFour game = new ConnectFour(board);
        int[] moves = game.getMoves();
        for (int[] a : board) {
            System.out.println(Arrays.toString(a));
        }
        System.out.println("~~~~~~~~~~~~~~~~~~");
        System.out.println(Arrays.toString(moves));
    }
}
