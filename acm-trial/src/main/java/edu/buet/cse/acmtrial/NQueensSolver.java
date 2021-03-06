package edu.buet.cse.acmtrial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueensSolver {
  private final int n;
  private final int[][] board;
  private final List<Coordinate[]> solutions;
  private static final int[] HORIZONTAL_INCREMENTS = { -1, 1 };
  private static final int[] VERTICAL_INCREMENTS = { -1, 1 };

  public NQueensSolver(int size) {
    n = size;
    board = new int[n][n];
    solutions = new ArrayList<Coordinate[]>();
  }

  public List<Coordinate[]> findSolution() {
    solve(0, new Coordinate[n]);
    return solutions;
  }

  private void solve(int row, Coordinate[] coordinates) {
    if (row == n) {
      solutions.add(Arrays.copyOf(coordinates, coordinates.length));
      return;
    }

    for (int j = 0; j < n; j++) {
      if (board[row][j] == 0) {
        occupy(row, j);
        coordinates[row] = new Coordinate(row, j);
        solve(row + 1, coordinates);
        coordinates[row] = null;
        unOccupy(row, j);
      }
    }
  }

  private void occupy(int x, int y) {
    addValue(x, y, 1);
  }

  private void unOccupy(int x, int y) {
    addValue(x, y, -1);
  }

  private void addValue(int x, int y, int value) {
    for (int i = 0; i < n; i++) {
      board[i][y] += value;
    }

    for (int j = 0; j < n; j++) {
      if (j != y) {
        board[x][j] += value;
      }
    }

    for (int dx : HORIZONTAL_INCREMENTS) {
      for (int dy : VERTICAL_INCREMENTS) {
        int i = x + dx;
        int j = y + dy;

        while (withinBoard(i, j)) {
          board[i][j] += value;
          i += dx;
          j += dy;
        }
      }
    }
  }

  private boolean withinBoard(int x, int y) {
    return x >= 0 && x < n && y >= 0 && y < n;
  }
}
