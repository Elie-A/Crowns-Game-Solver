# Crowns-Game-Solver

This Java program solves a **Unique Region Placement Puzzle**, where a grid is divided into different regions, 
and the objective is to place exactly one `1` in each region while adhering to specific constraints.

---

## Problem Definition

Given a **10x10** grid where each cell belongs to a labeled region (represented by a character), 
the program places `1`s and `0`s such that:

1. Each region contains exactly **one `1`**.
2. No two `1`s are adjacent, including diagonally.
3. Each row and column can have at most **one `1`**.
4. Cells not assigned a `1` must be `0`.

---

## How It Works

1. The board is initialized using predefined **region characters**.
2. A **backtracking algorithm** iterates through the board and tries placing a `1` or `0` in each cell.
3. The placement is validated based on:
   - Row & Column Constraints
   - Adjacency Rules
   - Regional Uniqueness
4. If a valid configuration is found, it prints the solution; otherwise, it states that no solution was found.

---

## Code Structure

### **1. Data Structures Used**
- **`char[][] board`**: Stores the puzzle layout, where each character represents a region.
- **`int[][] solution`**: Stores the final board with `1`s and `0`s.
- **`Map<Character, Set<int[]>> regions`**: A mapping of region characters to their respective coordinates.

### **2. Functions**
- **`initializeBoard()`**: Converts the board from `String[]` format to a `char[][]` for easier processing.
- **`initializeRegions()`**: Groups cells into sets based on their region.
- **`solve(int row, int col)`**: Implements a **backtracking** approach to try placing `1` or `0` in each cell.
- **`isValid(int row, int col, int num)`**: Checks if placing `1` at `(row, col)` is allowed.
- **`allRegionsHaveOne()`**: Ensures that each region contains exactly one `1`.
- **`printSolution()`**: Displays the solution in a readable format.

---

## Running the Program

### **Prerequisites**
- Java Development Kit (JDK) 8 or later

### **Compiling and Running**
```sh
javac Main.java
java Main
```

---

## Example Input (Board Layout)

The grid is initialized with the following layout:

```
PPPPPGGGGG
PMMPPPPGGG
MMMPGGGGOG
MMMMBBGGOG
MMMMMBGOOO
MMRMMBGOOO
MRRBJBBOOV
RRRBJBBOOV
RRTJJJBBLL
RTTTTTBLLL
```

Each **character** represents a **region**, and the goal is to place exactly **one `1`** in each unique region.

---

## Example Output (Solution)
After successful execution, the program prints a valid board configuration:

```
0 0 0 0 0 1 0 0 0 0
0 1 0 0 0 0 0 0 0 0
1 0 0 0 0 0 0 0 1 0
0 0 0 0 1 0 0 1 0 0
0 0 0 0 0 0 1 0 0 1
0 0 1 0 0 0 0 0 0 0
0 1 0 0 0 0 0 0 0 0
1 0 0 0 0 0 0 0 0 0
0 0 1 0 0 0 0 0 0 0
0 0 0 1 0 0 0 0 1 0
```

---

## Complexity Analysis

- **Board Initialization:** \(O(N^2)\)
- **Backtracking Approach:** Worst-case \(O(2^{N^2})\) (brute-force), but pruned using constraints.
- **Validation Checks:** \(O(N)\) per cell (row, column, adjacency, region).

In practice, due to the **constraint-based pruning**, the solver runs efficiently for a NxN board.

---

## Possible Enhancements

1. **Heuristic Optimization:** Prioritize regions with fewer placement options first.
2. **Parallel Processing:** Use multi-threading to divide solving efforts.
3. **GUI Integration:** Implement a visual representation of the board.

---

## Conclusion

This program effectively demonstrates **constraint-based backtracking** to solve a **region-based placement puzzle**. 
It ensures a valid solution that adheres to **uniqueness, adjacency, and row-column constraints**.

Happy coding! 🚀
