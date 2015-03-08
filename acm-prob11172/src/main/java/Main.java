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
    final int numberOfCases = scanner.nextInt();
    
    for (int i = 0; i < numberOfCases; i++) {
      int x = scanner.nextInt();
      int y = scanner.nextInt();
            
      if (x > y) {
        resultBuilder.append(">").append("\n");
      } else if (x < y) {
        resultBuilder.append("<").append("\n");
      } else {
        resultBuilder.append("=").append("\n");
      }
    }

    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
}
