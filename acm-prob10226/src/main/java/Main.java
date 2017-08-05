import java.io.InputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
  private static final String EMPTY_LINE = "";

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);
    StringBuilder resultBuilder = new StringBuilder();

    int numberOfCases = Integer.parseInt(scanner.nextLine());
    scanner.nextLine();

    for (int i = 0; i < numberOfCases; i++) {
      Map<String, Integer> countMap = new TreeMap<>();
      int totalCount = 0;

      while (scanner.hasNextLine()) {
        String name = scanner.nextLine().trim();

        if (EMPTY_LINE.equals(name)) {
          // end of a test case has been detected
          break;
        }

        Integer n = countMap.get(name);

        if (n == null) {
          n = 1;
        } else {
          n = n + 1;
        }

        countMap.put(name, n);
        totalCount++;
      }

      for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
        String name = entry.getKey();
        int n = entry.getValue();
        resultBuilder.append(String.format("%s %.4f%n", name, (100.0 * n) / totalCount));
      }

      if (i < numberOfCases - 1) {
        resultBuilder.append("\n");
      }
    }

    outStream.print(resultBuilder.toString());
    scanner.close();
  }
}
