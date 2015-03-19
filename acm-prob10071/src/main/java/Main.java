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
    
    while (scanner.hasNextInt()) {
      int v = scanner.nextInt();
      int t = scanner.nextInt();
      int result = 2 * v * t;
      resultBuilder.append(result).append("\n");
    }
    
    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
}
