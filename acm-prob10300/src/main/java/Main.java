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
    final int numberOfCases = scanner.nextInt();

    for (int i = 0; i < numberOfCases; i++) {
      long sum = 0L;
      int numberOfFarmers = scanner.nextInt();

      for (int j = 0; j < numberOfFarmers; j++) {
        long area = scanner.nextLong();
        // ignore number of animals
        scanner.nextLong();
        long friendlinessFactor = scanner.nextLong();
        sum += area * friendlinessFactor;
      }

      resultBuilder.append(sum).append("\n");
    }

    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
}
