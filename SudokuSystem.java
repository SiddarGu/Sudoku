import java.util.*;

public class SudokuSystem {
    private int[][] data;


    protected SudokuSystem(int[][] tdr) {
        this.data = tdr;
    }

    protected SudokuSystem() {
        data = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                data[i][j] = 0;
            }
        }
    }

    private int[] nextEntry() {
        int[] coord = new int[2];

        for (int i = 0; i < 9;i++) {
            for (int j = 0; j < 9;j++) {
                if (data[i][j] == 0) {
                    coord[0] = i;
                    coord[1] = j;
                    return coord;
                }
            }
        }
        return new int[]{-1, -1};
    }

    // back-tracking algorithm
    protected boolean solve() {
        // if the Sudoku is finished
        if (!hasEmptyEntry()) {
            return true;
        } else {
            // check input
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (data[i][j] != 0 && hasDuplicate(i, j, data[i][j])) {
                        return false;
                    }
                }
            }

            // find the first empty entry
            int[] coord = nextEntry();
            int row = coord[0], col = coord[1];


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

    protected boolean generate() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                data[i][j] = 0;
            }
        }

        return genAux();
    }

    private List<Integer> getShuffledArr() {
        List<Integer> arr = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            arr.add(i);
        }
        Collections.shuffle(arr, new Random());

        return arr;
    }

    private boolean genAux() {

        if (!hasEmptyEntry()) {
            return true;
        } else {
            // find the first empty entry
            int[] coord = nextEntry();
            int row = coord[0], col = coord[1];

            List<Integer> arr = getShuffledArr();

            for (int curr : arr) {
                if (!hasDuplicate(row, col, curr)) {
                    data[row][col] = curr;

                    if (genAux()) {
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
    protected boolean hasEmptyEntry() {
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
                sb.append(data[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    protected boolean check() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int num = data[i][j];

                for (int k = 0; k < 9; k++) {
                    if (k != i && data[k][j] == num) {
                        System.out.println(1);
                        return false;
                    }
                    if (k != j && data[i][k] == num) {
                        System.out.println(2);
                        return false;
                    }
                }

                int hIndex = i - i % 3;
                int vIndex = j - j % 3;

                for (int row = hIndex; row < hIndex + 3; row++) {
                    for (int col = vIndex; col < vIndex + 3; col++) {
                        if (row != i || col != j) {
                            if (data[row][col] == num) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    protected int getEntry(int x, int y) {
        return data[x][y];
    }
}
