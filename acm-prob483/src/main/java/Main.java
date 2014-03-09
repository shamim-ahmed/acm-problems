import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
  private static final String SPACE_STR = " ";

  public static void main(String... args) {
    Scanner scanner = new Scanner(System.in);

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      StringTokenizer tokenizer = new StringTokenizer(line, SPACE_STR, true);

      while (tokenizer.hasMoreTokens()) {
        String token = tokenizer.nextToken();

        if (token.equals(SPACE_STR)) {
          System.out.print(SPACE_STR);
        } else {
          StringBuilder sb = new StringBuilder(token);
          System.out.print(sb.reverse());
        }
      }

      System.out.println();
    }

    scanner.close();
  }
}
