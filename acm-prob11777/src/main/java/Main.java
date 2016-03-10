import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
  private static final int NUMBERS_PER_LINE = 7;
  private static final int NUMBER_OF_CLASS_TESTS = 3;

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);
    StringBuilder resultBuilder = new StringBuilder();

    int numberOfCases = scanner.nextInt();

    for (int i = 1; i <= numberOfCases; i++) {
      int[] scoreArray = new int[NUMBERS_PER_LINE];

      for (int j = 0; j < scoreArray.length; j++) {
        scoreArray[j] = scanner.nextInt();
      }

      int sum = 0;

      for (int j = 0; j < scoreArray.length - NUMBER_OF_CLASS_TESTS; j++) {
        sum += scoreArray[j];
      }

      // sort the class test values only and compute the average of the higher scores
      Arrays.sort(scoreArray, scoreArray.length - NUMBER_OF_CLASS_TESTS, scoreArray.length);
      int classTestAverage = (scoreArray[scoreArray.length - 2] + scoreArray[scoreArray.length - 1]) / 2;
      sum += classTestAverage;

      String letterGrade = computeLetterGrade(sum);
      resultBuilder.append(String.format("Case %d: %s%n", i, letterGrade));
    }

    scanner.close();
    outStream.print(resultBuilder.toString());
  }

  private static String computeLetterGrade(int marks) {
    String result = null;

    if (marks >= 90) {
      result = "A";
    } else if (marks >= 80 && marks < 90) {
      result = "B";
    } else if (marks >= 70 && marks < 80) {
      result = "C";
    } else if (marks >= 60 && marks < 70) {
      result = "D";
    } else {
      result = "F";
    }

    return result;
  }
}
