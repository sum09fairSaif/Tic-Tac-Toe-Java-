public class TicTacToeJava {
    private String[][] ticTacToeBoard;
    private boolean[][] isPosOccupied;
    private static int numRoundsPlayed = 0;

    public TicTacToeJava() {
        this.ticTacToeBoard = new String[3][3];
        this.isPosOccupied = new boolean[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.ticTacToeBoard[i][j] = " ";
                this.isPosOccupied[i][j] = false;
            }
        }
        numRoundsPlayed++;
    }

    // Use 0-based indexing for row and col
    public boolean placeMark(int row, int col, String mark) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3) {
            if (!this.isPosOccupied[row][col]) {
                this.ticTacToeBoard[row][col] = mark;
                this.isPosOccupied[row][col] = true;
                return true;
            }
            return false;
        }
        return false;
    }

    private String solveHorizontal() {
        for (int i = 0; i < 3; i++) {
            if (!this.ticTacToeBoard[i][0].equals(" ") &&
                this.ticTacToeBoard[i][0].equals(this.ticTacToeBoard[i][1]) &&
                this.ticTacToeBoard[i][1].equals(this.ticTacToeBoard[i][2])) {
                return this.ticTacToeBoard[i][0];
            }
        }
        return "N";
    }

    private String solveVertical() {
        for (int j = 0; j < 3; j++) {
            if (!this.ticTacToeBoard[0][j].equals(" ") &&
                this.ticTacToeBoard[0][j].equals(this.ticTacToeBoard[1][j]) &&
                this.ticTacToeBoard[1][j].equals(this.ticTacToeBoard[2][j])) {
                return this.ticTacToeBoard[0][j];
            }
        }
        return "N";
    }

    private String solveDiagonal() {
        if (!this.ticTacToeBoard[0][0].equals(" ") &&
            this.ticTacToeBoard[0][0].equals(this.ticTacToeBoard[1][1]) &&
            this.ticTacToeBoard[1][1].equals(this.ticTacToeBoard[2][2])) {
            return this.ticTacToeBoard[0][0];
        }
        if (!this.ticTacToeBoard[0][2].equals(" ") &&
            this.ticTacToeBoard[0][2].equals(this.ticTacToeBoard[1][1]) &&
            this.ticTacToeBoard[1][1].equals(this.ticTacToeBoard[2][0])) {
            return this.ticTacToeBoard[1][1];
        }
        return "N";
    }

    public String solve() {
        String winner;
        if (!(winner = solveHorizontal()).equals("N")) {
            return winner;
        } else if (!(winner = solveVertical()).equals("N")) {
            return winner;
        } else if (!(winner = solveDiagonal()).equals("N")) {
            return winner;
        }
        return "N";
    }

    public String toString() {
        String stringReprBoard = "";
        for (int i = 0; i < this.ticTacToeBoard.length; i++) {
            stringReprBoard += "|";
            for (int j = 0; j < this.ticTacToeBoard[0].length; j++) {
                stringReprBoard += this.ticTacToeBoard[i][j] + "|";
            }
            stringReprBoard += "\n";
        }
        return stringReprBoard;
    }

    public void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.ticTacToeBoard[i][j] = " ";
                this.isPosOccupied[i][j] = false;
            }
        }
    }

    public boolean isFull() {
        for (int i = 0; i < this.ticTacToeBoard.length; i++) {
            for (int j = 0; j < this.ticTacToeBoard[0].length; j++) {
                if (!this.isPosOccupied[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getNumRoundsPlayed() {
        return numRoundsPlayed;
    }

    public String[][] getBoard() {
        return this.ticTacToeBoard;
    }
}




    