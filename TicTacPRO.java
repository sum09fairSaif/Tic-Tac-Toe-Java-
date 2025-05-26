public class TicTacPRO {

    // Public method to get the best move: pass game instance, AI mark, human mark
    public static int[] getBestMove(TicTacToeJava game, String aiMark, String humanMark) {
        String[][] board = gameBoardCopy(game);
        int bestScore = Integer.MIN_VALUE;
        int[] move = {-1, -1};

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals(" ")) {
                    board[i][j] = aiMark;
                    int score = minimax(board, false, aiMark, humanMark);
                    board[i][j] = " ";
                    if (score > bestScore) {
                        bestScore = score;
                        move = new int[]{i, j};
                    }
                }
            }
        }

        return move;
    }

    private static int minimax(String[][] board, boolean isMaximizing, String aiMark, String humanMark) {
        String result = evaluateWinner(board);
        if (result != null) {
            if (result.equals(aiMark)) return 10;
            if (result.equals(humanMark)) return -10;
            return 0; // draw
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j].equals(" ")) {
                        board[i][j] = aiMark;
                        int score = minimax(board, false, aiMark, humanMark);
                        board[i][j] = " ";
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j].equals(" ")) {
                        board[i][j] = humanMark;
                        int score = minimax(board, true, aiMark, humanMark);
                        board[i][j] = " ";
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }

    private static String evaluateWinner(String[][] board) {
        for (int i = 0; i < 3; i++) {
            if (!board[i][0].equals(" ") && board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]))
                return board[i][0];
            if (!board[0][i].equals(" ") && board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]))
                return board[0][i];
        }

        if (!board[0][0].equals(" ") && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]))
            return board[0][0];
        if (!board[0][2].equals(" ") && board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]))
            return board[0][2];

        boolean full = true;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j].equals(" ")) full = false;

        return full ? "Draw" : null;
    }

    private static String[][] gameBoardCopy(TicTacToeJava game) {
        String[][] original = game.getBoard();
        String[][] copy = new String[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                copy[i][j] = original[i][j];
            }
        return copy;
    }
}
