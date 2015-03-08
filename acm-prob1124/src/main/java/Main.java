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
    
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      resultBuilder.append(line).append("\n");
    }
    
    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
}
