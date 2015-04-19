import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    StringBuilder resultBuilder = new StringBuilder();
    Scanner scanner = new Scanner(inputStream);
    int numberOfCases = Integer.parseInt(scanner.nextLine());

    for (int k = 1; k <= numberOfCases; k++) {
      resultBuilder.append(String.format("Case #%d:%n", k));
      String inputLine = scanner.nextLine();
      inputLine = inputLine.replaceAll("[,!?\\(\\)\\.\\s]+", "");
      char[] inputChars = inputLine.toCharArray();

      if (!isPerfectSquare(inputChars.length) || !isPalindrome(inputChars)) {
        resultBuilder.append("No magic :(\n");
      } else {
        int n = (int) Math.sqrt(inputChars.length);
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        StringBuilder sb4 = new StringBuilder();

        for (int j = 0; j < n; j++) {
          for (int i = 0; i < n; i++) {
            int index = i * n + j;
            sb2.append(inputChars[index]);
          }
        }

        for (int i = n - 1; i >= 0; i--) {
          for (int j = n - 1; j >= 0; j--) {
            int index = i * n + j;
            sb3.append(inputChars[index]);
          }
        }

        for (int j = n - 1; j >= 0; j--) {
          for (int i = n - 1; i >= 0; i--) {
            int index = i * n + j;
            sb4.append(inputChars[index]);
          }
        }

        String inputLine2 = sb2.toString();
        String inputLine3 = sb3.toString();
        String inputLine4 = sb4.toString();

        if (inputLine.equals(inputLine2) && inputLine2.equals(inputLine3) && inputLine3.equals(inputLine4)) {
          resultBuilder.append(n).append("\n");
        } else {
          resultBuilder.append("No magic :(\n");
        }
      }
    }

    scanner.close();
    outputStream.print(resultBuilder.toString());
  }

  private static boolean isPalindrome(char[] inputChars) {
    int i = 0;
    int j = inputChars.length - 1;
    boolean result = true;

    while (i < j) {
      if (inputChars[i] != inputChars[j]) {
        result = false;
        break;
      }
      
      i++;
      j--;
    }

    return result;
  }

  private static boolean isPerfectSquare(int n) {
    int r = (int) Math.sqrt(n);
    return n == r * r;
  }
}
