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
    
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      String[] wordArray = line.split("[^a-zA-Z]");
      int count = 0;
      
      for (String word : wordArray) {
        if (!word.trim().equals("")) {
          count++;
        }
      }
      
      resultBuilder.append(count).append("\n");
    }
    
    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
}
