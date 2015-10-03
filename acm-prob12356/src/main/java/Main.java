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

      int[] leftIndices = new int[n + 1];
      int[] rightIndices = new int[n + 1];

      for (int i = 1; i <= n; i++) {
        leftIndices[i] = i - 1;
        rightIndices[i] = i + 1;
      }

      leftIndices[1] = 0;
      rightIndices[n] = 0;

      for (int k = 0; k < p; k++) {
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        int u = leftIndices[a];
        int v = rightIndices[b];

        rightIndices[u] = v;
        leftIndices[v] = u;

        resultBuilder.append(u != 0 ? u : "*").append(" ").append(v != 0 ? v : "*").append("\n");
      }

      resultBuilder.append("-").append("\n");
    }

    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
}
