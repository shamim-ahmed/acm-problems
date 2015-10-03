import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    Scanner scanner = new Scanner(inputStream);
    StringBuilder resultBuilder = new StringBuilder();

    while (true) {
      final int n = scanner.nextInt();
      final int p = scanner.nextInt();

      if (n == 0 && p == 0) {
        break;
      }

      boolean[] soldierFlags = new boolean[n + 1];

      for (int k = 1; k < soldierFlags.length; k++) {
        soldierFlags[k] = true;
      }

      for (int k = 0; k < p; k++) {
        int lower = scanner.nextInt();
        int upper = scanner.nextInt();

        for (int r = lower; r <= upper; r++) {
          soldierFlags[r] = false;
        }

        int i = lower - 1;
        while (i >= 1 && !soldierFlags[i]) {
          i--;
        }

        int j = upper + 1;
        while (j < soldierFlags.length && !soldierFlags[j]) {
          j++;
        }

        resultBuilder.append(i >= 1 ? i : "*").append(" ")
            .append(j < soldierFlags.length ? j : "*").append("\n");
      }

      resultBuilder.append("-").append("\n");
    }

    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
}
