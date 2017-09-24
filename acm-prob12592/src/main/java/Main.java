import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);
    Map<String, String> sloganMap = new HashMap<>();
    int n = Integer.parseInt(scanner.nextLine());

    for (int i = 0; i < n; i++) {
      String firstLine = scanner.nextLine();
      String secondLine = scanner.nextLine();
      sloganMap.put(firstLine, secondLine);
    }

    StringBuilder resultBuilder = new StringBuilder();
    int q = Integer.parseInt(scanner.nextLine());

    for (int i = 0; i < q; i++) {
      String firstLine = scanner.nextLine();
      String secondLine = sloganMap.get(firstLine);
      resultBuilder.append(secondLine).append("\n");
    }

    scanner.close();
    outStream.print(resultBuilder.toString());
  }
}
