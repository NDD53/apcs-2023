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

    public int[][] makeMove() {
        int[] plays = getMoves();
        int col = findBestMove();
        int[][] duplicate = board.clone();
        duplicate[plays[col]][col] = 1;
        return duplicate;
    }

    public void setBoard(int[][] newBoard) {
        board = newBoard;
    }

    /**
     * @return the column of the best move
     */
    public int findBestMove() {
        int[] plays = getMoves();
        int max = Integer.MIN_VALUE;
        int col = Integer.MIN_VALUE;
        for (int i = 0; i < plays.length; i++) {
            if (plays[i] != -1) {
                if (getPlayScore(plays[i], i) > max) {
                    max = getPlayScore(plays[i], i);
                    col = i;
                }
            }
        }
        return col;
    }

    public int getPlayScore(int row, int col) {
        int[][] duplicate = board.clone();
        duplicate[row][col] = 1;
        return getBoardScore(duplicate);
    }

    public int getBoardScore(int[][] theBoard) {
        if (fourInARow(theBoard)) {
            return Integer.MAX_VALUE;
        }
        int score = 0;
        score += getConsecutive(theBoard, 1, 1);
        score -= getConsecutive(theBoard, 2, 1);
        score += getConsecutive(theBoard, 1, 2) * 4;
        score -= getConsecutive(theBoard, 2, 2) * 4;
        score += getConsecutive(theBoard, 1, 2) * 16;
        score -= getConsecutive(theBoard, 2, 2) * 16;
        return score;
    }

    /**
     * @param theBoard
     * @return true if there is a four in a row of 1s
     */
    public boolean fourInARow(int[][] theBoard) {
        // top bottom
        for (int row = 0; row < theBoard.length - 3; row++) {
            for (int col = 0; col < theBoard[0].length; col++) {
                boolean test = false;
                for (int i = 0; i < 4; i++) {
                    if (theBoard[row + i][col] != 1) {
                        test = false;
                        break;
                    }
                }
                if (test) {
                    return true;
                }
            }
        }
        // left right
        for (int row = 0; row < theBoard.length; row++) {
            for (int col = 0; col < theBoard[0].length - 3; col++) {
                boolean test = false;
                for (int i = 0; i < 4; i++) {
                    if (theBoard[row][col + 1] != 1) {
                        test = false;
                        break;
                    }
                }
                if (test) {
                    return true;
                }
            }
        }
        // bottom left to top right
        for (int row = 3; row < theBoard.length; row++) {
            for (int col = 0; col < theBoard[0].length - 3; col++) {
                boolean test = false;
                for (int i = 0; i < 4; i++) {
                    if (theBoard[row - 1][col + 1] != 1) {
                        test = false;
                        break;
                    }
                }
                if (test) {
                    return true;
                }
            }
        }
        // bottom right to top left
        for (int row = 0; row < theBoard.length - 3; row++) {
            for (int col = 0; col < theBoard[0].length - 3; col++) {
                boolean test = false;
                for (int i = 0; i < 4; i++) {
                    if (theBoard[row + 1][col + 1] != 1) {
                        test = false;
                        break;
                    }
                }
                if (test) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * very untested
     * 
     * @param theBoard
     * @param player   - either 1 or 2
     * @param number   - the desired number of consecutive 1s or 2s followed by an
     *                 empty space
     * @return - the count of consecutive 1s or 2s followed by an empty space
     */
    public int getConsecutive(int[][] theBoard, int player, int number) {
        int count = 0;
        // bottom up
        for (int row = number; row < theBoard.length; row++) {
            for (int col = 0; col < theBoard[0].length; col++) {
                boolean test = false;
                for (int i = 0; i < number; i++) {
                    if (theBoard[row - i][col] != player) {
                        test = false;
                        break;
                    }
                }
                if (test && theBoard[row - number][col] == 0) {
                    count++;
                }
            }
        }
        // right to left
        for (int row = 0; row < theBoard.length; row++) {
            for (int col = number; col < theBoard[0].length; col++) {
                boolean test = false;
                for (int i = 0; i < number; i++) {
                    if (theBoard[row][col - i] != player) {
                        test = false;
                        break;
                    }
                }
                if (test && theBoard[row][col - number] == 0) {
                    count++;
                }
            }
        }
        // left to right
        for (int row = 0; row < theBoard.length; row++) {
            for (int col = 0; col < theBoard[0].length - number; col++) {
                boolean test = false;
                for (int i = 0; i < number; i++) {
                    if (theBoard[row][col + i] != player) {
                        test = false;
                        break;
                    }
                }
                if (test && theBoard[row][col + number] == 0) {
                    count++;
                }
            }
        }
        // top left to bottom right
        for (int row = 0; row < theBoard.length - number; row++) {
            for (int col = 0; col < theBoard[0].length - number; col++) {
                boolean test = false;
                for (int i = 0; i < number; i++) {
                    if (theBoard[row + i][col + i] != player) {
                        test = false;
                        break;
                    }
                }
                if (test && theBoard[row + number][col + number] == 0) {
                    count++;
                }
            }
        }
        // top right to bottom left
        for (int row = 0; row < theBoard.length - number; row++) {
            for (int col = number; col < theBoard[0].length; col++) {
                boolean test = false;
                for (int i = 0; i < number; i++) {
                    if (theBoard[row + i][col - i] != player) {
                        test = false;
                        break;
                    }
                }
                if (test && theBoard[row + number][col - number] == 0) {
                    count++;
                }
            }
        }
        // bottom right to top left
        for (int row = number; row < theBoard.length; row++) {
            for (int col = number; col < theBoard[0].length; col++) {
                boolean test = false;
                for (int i = 0; i < number; i++) {
                    if (theBoard[row - i][col - i] != player) {
                        test = false;
                        break;
                    }
                }
                if (test && theBoard[row - number][col - number] == 0) {
                    count++;
                }
            }
        }
        // bottom left to top right
        for (int row = number; row < theBoard.length; row++) {
            for (int col = 0; col < theBoard[0].length - number; col++) {
                boolean test = false;
                for (int i = 0; i < number; i++) {
                    if (theBoard[row - i][col + i] != player) {
                        test = false;
                        break;
                    }
                }
                if (test && theBoard[row - number][col + number] == 0) {
                    count++;
                }
            }
        }
        return count;
    }
}

class Runner {
    public static void main(String[] args) {
        int[][] board = { { 0, 0, 2, 0, 0, 0 }, { 0, 0, 1, 0, 0, 1 }, { 0, 0, 1, 0, 0, 1 }, { 0, 0, 2, 0, 1, 2 },
                { 1, 0, 1, 0, 2, 2 }, { 2, 0, 2, 2, 1, 2 } };
        ConnectFour game = new ConnectFour(board);
        for (int[] a : board) {
            System.out.println(Arrays.toString(a));
        }
        System.out.println("~~~~~~~~~~~~~~~~~~");
        int[][] newBoard = game.makeMove();
        for (int[] a : newBoard) {
            System.out.println(Arrays.toString(a));
        }
    }
}
