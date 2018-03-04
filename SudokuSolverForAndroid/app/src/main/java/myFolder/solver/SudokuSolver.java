package solver;

public class SudokuSolver {
    int rows;
    int cols;
    public int[][] sudokuMatrix;

    /**
     * Creates an instance of solver.SudokuSolver and fills
     * the sudokuMatrix with zeros.
     * @param rows number of rows in sudokuMatrix.
     * @param cols number of columns in sudokuMatrix.
     */
    public SudokuSolver(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.sudokuMatrix = new int[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                sudokuMatrix[r][c] = 0;
            }
        }

    }

    public static void main(String[] args){
        SudokuSolver solver = new SudokuSolver(9, 9);
        solver.sudokuMatrix[0][1] = 4;
        solver.sudokuMatrix[0][2] = 6;
        solver.sudokuMatrix[0][5] = 5;
        solver.sudokuMatrix[0][6] = 7;
        solver.sudokuMatrix[1][3] = 9;
        solver.sudokuMatrix[2][1] = 9;
        solver.sudokuMatrix[2][5] = 1;
        solver.sudokuMatrix[2][8] = 6;
        solver.sudokuMatrix[3][6] = 9;
        solver.sudokuMatrix[4][1] = 3;
        solver.sudokuMatrix[5][0] = 4;
        solver.sudokuMatrix[5][3] = 5;
        solver.sudokuMatrix[5][4] = 2;
        solver.sudokuMatrix[5][8] = 8;
        solver.sudokuMatrix[6][1] = 8;
        solver.sudokuMatrix[6][7] = 7;
        solver.sudokuMatrix[7][0] = 5;
        solver.sudokuMatrix[7][1] = 7;
        solver.sudokuMatrix[7][3] = 3;
        solver.sudokuMatrix[7][7] = 8;
        solver.sudokuMatrix[7][8] = 2;
        solver.sudokuMatrix[8][0] = 2;
        solver.sudokuMatrix[8][6] = 3;


        System.out.print(solver.solve(1, 0, 0));
        for (int r = 0; r < solver.rows; r++){
            System.out.println();
            for (int c = 0; c < solver.cols; c++){
                System.out.print(solver.sudokuMatrix[r][c] + " ");
            }
        }
    }

    public void printMatrix() {
        for (int r = 0; r < rows; r++) {
            System.out.println();
            for (int c = 0; c < cols; c++) {
                System.out.print(sudokuMatrix[r][c] + " ");
            }
        }
    }

    /**
     * Checks if the value already is in the row.
     * @param value the value checked for.
     * @param row the row checked in.
     * @return true if the value is in the row, otherwise false.
     */
    public boolean isInRow(int value, int row) {

        for(int c = 0; c < cols; c++) {
            if(sudokuMatrix[row][c] == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the value already is in the column
     * @param value the value searched for
     * @param col the column searched in
     * @return true if the value is in the column, otherwise false.
     */
    public boolean isInColumn(int value, int col) {
        for(int r = 0; r < rows; r++) {
            if(sudokuMatrix[r][col] == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the value already is in the quadrant
     * @param value the value searched for
     * @param row the row index of the mid in the quadrant
     * @param col the coloumn index of the mid in the quadrant
     * @return true if the value is in the quadrant
     */
    public boolean isInQuadrant(int value, int row, int col) {
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                if(sudokuMatrix[row + i][col + j] == value){
                   // System.out.println(row+i + " " + col+j);
                    return true;
                }
            }
        }
        return false;

    }

    /**
     * Checks if all indexes of the sudokuMatrix contains a value other than zero
     * @return true if all indexes contains a value other than zero
     */
    public boolean isSolved() {
        boolean done = true;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++){
                if (sudokuMatrix[r][c] == 0) done = false;
            }
        }
        return done;
    }

    /**
     * Uses isInRow, isInColumn and is inQuadrant to check if
     * the value can be placed at the index row, col in sudokuMatrix.
     * @param value the value
     * @param row the row index
     * @param col the column index
     * @return false if isInRow, isInColumn or isInQuadrant returns true, otherwise false.
     */
    public boolean isAllowed(int value, int row, int col) {
        int q1 = 0;
        int q2 = 0;
        for (int r = 3; r <= rows; r = r + 3){
            for (int c = 3; c <= cols; c = c + 3){
                if (row < r && row > r - 4 && col < c && col > c - 4 && q1 == 0) {
                    q1 = r - 2; q2 = c - 2;
                   // System.out.println(q1 + "," + q2);
                }
            }
        }
        if (isInQuadrant(value, q1, q2) || isInRow(value, row) || isInColumn(value, col)) return false;
        else return true;
    }

    public boolean check() {
        boolean allowed = true;
        int temp;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (sudokuMatrix[r][c] != 0) {
                    temp = sudokuMatrix[r][c];
                    sudokuMatrix[r][c] = 0;
                    if (!isAllowed(temp, r, c)) allowed = false;
                    sudokuMatrix[r][c] = temp;
                }
            }
        }
        return allowed;
    }

    public boolean solve() {
        if (!check()) return false;
        return solve(1, 0, 0);
    }

    /**
     * Solves sudoku
     * @param value the value to be placed
     * @param row the row index in sudokuMatrix
     * @param col the column index in sudokuMatrix
     * @return true if the sudoku is solved, otherwise false.
     */
    private boolean solve(int value, int row, int col){
        int testValue = value;
        if(isSolved()) return true;
        if(col == 9){
            row++;
            col = 0;
        }
        if (sudokuMatrix[row][col] != 0) {
            int temp = sudokuMatrix[row][col];
            sudokuMatrix[row][col] = 0;
            if (!isAllowed(temp, row, col)) {
                System.out.println(row + "," + col +" "+temp);
                sudokuMatrix[row][col] = temp;
                return false;        //hänger sig på första förbestämda element som inte passar.
            }
            sudokuMatrix[row][col] = temp;
            return solve(value, row, col+1);
        }

        while (testValue <= 9) {
           // System.out.println(row + "," + col + " " + testValue);
            if (isAllowed(testValue, row, col)) {
                sudokuMatrix[row][col] = testValue;
                if (solve(value, row, col + 1)) {
                    return true;
                }

            }
            testValue++;
        }
        sudokuMatrix[row][col] = 0;
        return false;
    }
}
