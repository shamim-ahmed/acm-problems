import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
  private static final String REPORT = "report";
  private static final Pattern DONATE_PATTERN = Pattern.compile("donate[\\s]+(\\d+)");
  
  public static void main(String... args) {
    processInput(System.in, System.out);
  }
  
  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    StringBuilder resultBuilder = new StringBuilder();
    Scanner scanner = new Scanner(inputStream);
    final int n = Integer.parseInt(scanner.nextLine());
    int sum = 0;
    
    for (int i = 0; i < n; i++) {
      String line = scanner.nextLine().trim();
      
      if (line.equals(REPORT)) {
        resultBuilder.append(sum).append("\n");
      } else {
        Matcher matcher = DONATE_PATTERN.matcher(line);
        
        if (matcher.matches()) {
          int amount = Integer.parseInt(matcher.group(1));
          sum += amount;
        } else {
          throw new RuntimeException(String.format("Invalid input: %s", line));
        } 
      }
    }
    
    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
}
