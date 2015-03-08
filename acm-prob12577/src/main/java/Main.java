import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
  private static final String INPUT_TERMINATOR = "*";
  private static final String HAJJ_STR = "Hajj";
  private static final String UMRAH_STR = "Umrah";
  
  public static void main(String... args) {
    processInput(System.in, System.out);
  }
  
  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    Scanner scanner = new Scanner(inputStream);
    StringBuilder resultBuilder = new StringBuilder();
    int count = 0;
    
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine().trim();
      
      if (line.equals(INPUT_TERMINATOR)) {
        break;
      }
      
      count++;
      
      if (line.equals(HAJJ_STR)) {
        resultBuilder.append(String.format("Case %d: Hajj-e-Akbar%n", count));
      } else if (line.equals(UMRAH_STR)) {
        resultBuilder.append(String.format("Case %d: Hajj-e-Asghar%n", count));
      }
    }
    
    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
}
