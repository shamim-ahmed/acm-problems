import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);
    StringBuilder resultBuilder = new StringBuilder();

    while (scanner.hasNextInt()) {
      final int n = scanner.nextInt();
      int[] values = new int[n];
      Map<Integer, Integer> counterMap = new HashMap<>();

      for (int i = 0; i < n; i++) {
        values[i] = scanner.nextInt();

        Integer count = counterMap.get(values[i]);

        if (count == null) {
          count = 1;
        } else {
          count++;
        }

        counterMap.put(values[i], count);
      }

      Arrays.sort(values);

      // the median
      int median;

      // number of values in input that satisfy the property
      int p;

      // number of all possible values of median
      int q;

      boolean even = (n % 2 == 0);

      if (even) {
        int i = n / 2 - 1;
        int j = i + 1;
        median = values[i];
        
        if (values[i] < values[j]) {
          p = counterMap.get(values[i]) + counterMap.get(values[j]);
        } else {
          p = counterMap.get(values[i]);
        }
        
        q = values[j] - values[i] + 1;
      } else {
        int i = n / 2;
        median = values[i];
        p = counterMap.get(values[i]);
        q = 1;
      }

      resultBuilder.append(String.format("%d %d %d%n", median, p, q));
    }

    scanner.close();
    outStream.print(resultBuilder.toString());
  }
}
