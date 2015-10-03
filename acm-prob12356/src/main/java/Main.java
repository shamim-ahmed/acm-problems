import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.TreeSet;

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

      TreeSet<Integer> soldierSet = new TreeSet<>();
      
      for (int i = 1; i <= n; i++) {
        soldierSet.add(i);
      }

      for (int i = 0; i < p; i++) {
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        for (int j = a; j <= b; j++) {
          soldierSet.remove(j);
        }

        Integer left = soldierSet.lower(a);
        Integer right = soldierSet.higher(b);
        
        resultBuilder.append(left != null ? left : "*").append(" ")
            .append(right != null ? right : "*").append("\n");
      }

      resultBuilder.append("-").append("\n");
    }

    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
}
