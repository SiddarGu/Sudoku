import java.util.*;

public class SudokuSystem {
    private int[][] data;

    /*
    public static void main(String[] args) {
        System.out.println("This is a Sudoku solving machine.");
        System.out.println("Use 0 to represent empty entry.");

        int[][] sudoku = new int[9][9];

        Scanner scan = new Scanner(System.in);

        // iterate over rows
        for (int i = 0; i < 9; i++) {
            System.out.println("Please enter row " + (i + 1) + "\n");
            String row = scan.nextLine();

            // iterate over entries in the specific row
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = Character.getNumericValue(row.charAt(j));
            }

        }
        SudokuSystem system = new SudokuSystem(sudoku);

        if (system.solve()) {
            System.out.println("The Sudoku is solvable:\n");

        } else {
            System.out.println("This Sudoku has no solution.");
        }
        System.out.println(system.toString());
    }
    */

    public SudokuSystem(int[][] tdr) {
        this.data = tdr;
        this.solve();
    }

    // back-tracking algorithm
    public boolean solve() {
        // if the Sudoku is finished
        if (!hasEmptyEntry()) {
            return true;
        } else {
            // find the first empty entry
            int row = -1, col = -1;

            for (int i = 0; i < 9;i++) {
                for (int j = 0; j < 9;j++) {
                    if (data[i][j] == 0) {
                        row = i;
                        col = j;
                        break;
                    }
                }
            }

            // possible solutions
            for (int i = 1; i < 10; i++) {
                // validation
                if (!hasDuplicate(row,col,i)) {
                    // assign value
                    data[row][col] = i;

                    // recursion
                    if (solve()) {
                        return true;
                    } else {
                        // clear the cell
                        data[row][col] = 0;
                    }
                }
            }
        }
        return false;
    }

    // returns true if the number has duplicates in its row, col or 3x3 box
    private boolean hasDuplicate(int row, int col, int num) {
        // checks for row and col
        for (int i = 0; i < 9; i++) {
            if (data[row][i] == num || data[i][col] == num) {
                return true;
            }
        }

        // indexing
        int hIndex = row - row % 3;
        int vIndex = col - col % 3;

        // checks for 3x3 box
        for (int i = hIndex; i < hIndex + 3; i++) {
            for (int j = vIndex; j < vIndex + 3; j++) {
                if (data[i][j] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    // returns true if the Sudoku has an empty entry
    private boolean hasEmptyEntry() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (data[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(data[i][j] + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public int[][] getData() {
        return data;
    }

    public int getEntry(int x, int y) {
        return data[x][y];
    }
}
