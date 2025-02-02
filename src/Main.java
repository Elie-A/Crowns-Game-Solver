import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    private static final String[] boardStrings = {
            "PPPPPGGGGG",
            "PMMPPPPGGG",
            "MMMPGGGGOG",
            "MMMMBBGGOG",
            "MMMMMBGOOO",
            "MMRMMBGOOO",
            "MRRBJBBOOV",
            "RRRBJBBOOV",
            "RRTJJJBBLL",
            "RTTTTTBLLL"
    };

    private static final int SIZE = boardStrings.length;
    private static final char[][] board = new char[SIZE][SIZE];
    private static final int[][] solution = new int[SIZE][SIZE];
    private static final Map<Character, Set<int[]>> regions = new HashMap<>();

    public static void main(String[] args) {
        initializeBoard();
        initializeRegions();
        if (solve(0, 0)) {
            printSolution();
        } else {
            System.out.println("No solution found.");
        }
    }

    private static void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            board[i] = boardStrings[i].toCharArray();
        }
    }

    private static void initializeRegions() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                char region = board[i][j];
                regions.computeIfAbsent(region, k -> new HashSet<>()).add(new int[]{i, j});
            }
        }
    }

    private static boolean solve(int row, int col) {
        if (row == SIZE) {
            return allRegionsHaveOne(); // Ensure all regions have exactly one `1`
        }
        if (col == SIZE) return solve(row + 1, 0);
        if (solution[row][col] != 0) return solve(row, col + 1);

        for (int num = 1; num >= 0; num--) {
            if (isValid(row, col, num)) {
                solution[row][col] = num;
                if (solve(row, col + 1)) return true;
                solution[row][col] = 0;
            }
        }
        return false;
    }

    private static boolean isValid(int row, int col, int num) {
        if (num == 1) {
            // Check row and column constraints
            for (int i = 0; i < SIZE; i++) {
                if (solution[row][i] == 1 || solution[i][col] == 1) return false;
            }

            // Check adjacency constraints
            int[] dRow = {-1, -1, -1, 0, 0, 1, 1, 1};
            int[] dCol = {-1, 0, 1, -1, 1, -1, 0, 1};
            for (int i = 0; i < 8; i++) {
                int newRow = row + dRow[i];
                int newCol = col + dCol[i];
                if (newRow >= 0 && newRow < SIZE && newCol >= 0 && newCol < SIZE && solution[newRow][newCol] == 1) {
                    return false;
                }
            }

            // Check region constraint (no duplicate `1` in a region)
            char region = board[row][col];
            for (int[] cell : regions.get(region)) {
                if (solution[cell[0]][cell[1]] == 1) return false;
            }
        }
        return true;
    }

    private static boolean allRegionsHaveOne() {
        for (char region : regions.keySet()) {
            int count = 0;
            for (int[] cell : regions.get(region)) {
                if (solution[cell[0]][cell[1]] == 1) {
                    count++;
                }
            }
            if (count != 1) return false; // Ensure exactly one `1` in each region
        }
        return true;
    }

    private static void printSolution() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(solution[i][j] + " ");
            }
            System.out.println();
        }
    }
}
