import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
  private static final int DIMENSION = 3;
  private static final int TOKENS_PER_LINE = DIMENSION * DIMENSION;
  private static final String[] INDEX_COMBINATIONS = { "012", "021", "102", "120", "201", "210" };
  private static final String[] COLOR_COMBINATIONS = { "BGC", "BCG", "GBC", "GCB", "CBG", "CGB" };

  public static void main(String... args) {
    Scanner scanner = null;

    try {
      scanner = new Scanner(System.in);

      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        int[][] matrix = parseLine(line);

        if (matrix == null) {
          continue;
        }

        String result = findSolution(matrix);
        System.out.println(result);
      }
    } finally {
      if (scanner != null) {
        scanner.close();
      }
    }
  }

  private static String findSolution(int[][] values) {
    final int sum = getSum(values);
    int[] moves = new int[INDEX_COMBINATIONS.length];

    for (int index = 0; index < INDEX_COMBINATIONS.length; index++) {
      String combination = INDEX_COMBINATIONS[index];
      int moveCount = sum;

      for (int i = 0; i < combination.length(); i++) {
        int j = Integer.parseInt(Character.toString(combination.charAt(i)));
        moveCount -= values[i][j];
      }

      moves[index] = moveCount;
    }

    int minCount = moves[0];
    String resultColors = COLOR_COMBINATIONS[0];

    for (int i = 1; i < moves.length; i++) {
      if (moves[i] < minCount || (moves[i] == minCount && COLOR_COMBINATIONS[i].compareTo(resultColors) < 0)) {
        minCount = moves[i];
        resultColors = COLOR_COMBINATIONS[i];
      }
    }

    return String.format("%s %d", resultColors, minCount);
  }

  private static int getSum(int[][] values) {
    int sum = 0;

    for (int i = 0; i < DIMENSION; i++) {
      for (int j = 0; j < DIMENSION; j++) {
        sum += values[i][j];
      }
    }

    return sum;
  }

  private static int[][] parseLine(String line) {
    StringTokenizer tokenizer = new StringTokenizer(line, " ", false);

    if (tokenizer.countTokens() != TOKENS_PER_LINE) {
      return null;
    }

    int[][] values = new int[DIMENSION][DIMENSION];

    for (int i = 0; i < DIMENSION; i++) {
      for (int j = 0; j < DIMENSION; j++) {
        values[i][j] = Integer.parseInt(tokenizer.nextToken());
      }
    }

    return values;
  }
}
