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
      int x = scanner.nextInt();
      int y = scanner.nextInt();
      
      if (x == -1 && y == -1) {
        break;
      }
      
      int m, n;
      
      if (x > y) {
        m = x;
        n = y;
      } else {
        m = y;
        n = x;
      }
      
      int diff = m - n;
      
      if (diff > 50) {
        diff = (n - m + 100) % 100;
      }
      
      resultBuilder.append(diff).append("\n");
    }
    
    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
}
