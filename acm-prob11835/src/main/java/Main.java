import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);

    StringBuilder resultBuilder = new StringBuilder();

    while (scanner.hasNextInt()) {
      int numberOfGrandPrix = scanner.nextInt();
      int numberOfPilots = scanner.nextInt();

      if (numberOfGrandPrix == 0 && numberOfPilots == 0) {
        break;
      }

      int[][] arrivalOrders = new int[numberOfGrandPrix + 1][numberOfPilots + 1];

      for (int i = 1; i <= numberOfGrandPrix; i++) {
        for (int j = 1; j <= numberOfPilots; j++) {
          arrivalOrders[i][j] = scanner.nextInt();
        }
      }

      int scoringSystemCount = scanner.nextInt();
      int[][] scoringSystems = new int[scoringSystemCount][];

      for (int i = 0; i < scoringSystems.length; i++) {
        int n = scanner.nextInt();
        scoringSystems[i] = new int[n + 1];

        for (int j = 1; j <= n; j++) {
          scoringSystems[i][j] = scanner.nextInt();
        }
      }

      // input has been read for a particular case. Now compute the result
      for (int k = 0; k < scoringSystems.length; k++) {
        int[] currentScoringSystem = scoringSystems[k];
        int[] totalScores = new int[numberOfPilots + 1];

        for (int i = 1; i <= numberOfGrandPrix; i++) {
          for (int j = 1; j <= numberOfPilots; j++) {
            int order = arrivalOrders[i][j];

            if (order < currentScoringSystem.length) {
              int score = currentScoringSystem[order];
              totalScores[j] += score;
            }
          }
        }

        String positionStr = findPositionsOfMaximumValue(totalScores);
        resultBuilder.append(positionStr).append("\n");
      }
    }

    scanner.close();
    outStream.print(resultBuilder.toString());
  }

  private static int findMax(int[] values) {
    int max = 0;

    for (int i = 1; i < values.length; i++) {
      if (max < values[i]) {
        max = values[i];
      }
    }

    return max;
  }

  private static String findPositionsOfMaximumValue(int[] values) {
    int n = findMax(values);
    StringBuilder resultBuilder = new StringBuilder();

    for (int i = 1; i < values.length; i++) {
      if (values[i] == n) {
        resultBuilder.append(i).append(" ");
      }
    }

    return resultBuilder.toString().trim();
  }
}
