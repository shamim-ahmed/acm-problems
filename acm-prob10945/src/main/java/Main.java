import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
  private static final String END_OF_INPUT_INDICATOR = "DONE";
  
  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    StringBuilder resultBuilder = new StringBuilder();
    Scanner scanner = new Scanner(inputStream);
    
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      
      if (line.equals(END_OF_INPUT_INDICATOR)) {
        break;
      }
      
      line = line.toLowerCase();
      line = line.replaceAll("[\\.,?!\\s]+", "");
      char[] lineChars = line.toCharArray();
      int i = 0;
      int j = lineChars.length - 1;
      boolean result = true;
      
      while (i < j) {
        if (lineChars[i] != lineChars[j]) {
          result = false;
          break;
        }
        
        i++;
        j--;
      }
      
      if (result) {
        resultBuilder.append("You won't be eaten!\n");
      } else {
        resultBuilder.append("Uh oh..\n");
      } 
    }
    
    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
}
