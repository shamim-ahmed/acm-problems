import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
  public static void main(String... args) {
    Scanner scanner = null;

    try {
      scanner = new Scanner(System.in);

      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        StringTokenizer tokenizer = new StringTokenizer(line, " ", false);

        if (tokenizer.countTokens() != 2) {
          continue;
        }

        int p = Integer.parseInt(tokenizer.nextToken());
        int q = Integer.parseInt(tokenizer.nextToken());

        int m, n;

        if (p < q) {
          m = p;
          n = q;
        } else {
          m = q;
          n = p;
        }

        int max = 0;

        for (int i = m; i <= n; i++) {
          int length = getCycleLength(i);

          if (length > max) {
            max = length;
          }
        }

        System.out.printf("%d %d %d%n", p, q, max);
      }
    } finally {
      if (scanner != null) {
        scanner.close();
      }
    }
  }

  private static int getCycleLength(int n) {
    int length = 1;

    while (n != 1) {
      length++;

      if (n % 2 == 0) {
        n /= 2;
      } else {
        n = 3 * n + 1;
      }
    }

    return length;
  }
}
