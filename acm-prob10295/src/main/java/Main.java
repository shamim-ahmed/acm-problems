import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
  private static final String JOB_DESCRIPTION_TERMINATOR = ".";
  
  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);
    
    String numberLine = scanner.nextLine().trim();
    String[] valueArray = numberLine.split("\\s+");
    int numberOfWordsInDictionary = Integer.parseInt(valueArray[0]);
    int numberOfJobDescriptions = Integer.parseInt(valueArray[1]);
    Map<String, Integer> hayPointDictionary = new HashMap<>();
    
    for (int i = 0; i < numberOfWordsInDictionary; i++) {
      String line = scanner.nextLine().trim();
      String[] wordSpecs = line.split("\\s+");
      String word = wordSpecs[0];
      Integer value = Integer.valueOf(wordSpecs[1]);
      hayPointDictionary.put(word, value);
    }
    
    StringBuilder resultBuilder = new StringBuilder();
    
    for (int i = 0; i < numberOfJobDescriptions; i++) {
      int sum = 0;
      String line = scanner.nextLine().trim();
      
      while (!line.equals(JOB_DESCRIPTION_TERMINATOR)) {
        String[] wordArray = line.split("\\s+");
        
        for (String word : wordArray) {
          Integer value = hayPointDictionary.get(word);
          
          if (value != null) {
            sum += value;
          }
        }
        
        // read the next line from job description
        line = scanner.nextLine().trim();
      }
      
      // done with the current job description
      resultBuilder.append(sum).append("\n");
    }
    
    scanner.close();
    outStream.print(resultBuilder.toString());
  }
}