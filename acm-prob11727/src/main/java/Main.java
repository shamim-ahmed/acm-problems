import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
  private static final int SIZE = 3;
  
  public static void main(String... args) {
    processInput(System.in, System.out);
  }
  
  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    StringBuilder resultBuilder = new StringBuilder();
    Scanner scanner = new Scanner(inputStream);
    
    final int numberOfCases = scanner.nextInt();
    final int[] values = new int[SIZE];
    
    for (int i = 1; i <= numberOfCases; i++) {
      for (int j = 0; j < SIZE; j++) {
        values[j] = scanner.nextInt();
      }
      
      Arrays.sort(values);
      resultBuilder.append(String.format("Case %d: %d%n", i, values[1]));
    }

    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
}
