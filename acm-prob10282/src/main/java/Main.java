import java.io.InputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
  private static final String UNKNOWN_WORD_TRANSLATION = "eh";
  
  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);
    Map<String, String> wordMap = new TreeMap<>();

    // construct the dictionary
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine().trim();

      if (line.equals("")) {
        break;
      }

      String[] words = line.split("\\s+");
      String englishWord = words[0];
      String foreignLanguageWord = words[1];
      wordMap.put(foreignLanguageWord, englishWord);
    }

    // now translate
    StringBuilder resultBuilder = new StringBuilder();

    while (scanner.hasNextLine()) {
      String foreignLanguageWord = scanner.nextLine().trim();
      String englishWord = wordMap.get(foreignLanguageWord);
      
      if (englishWord == null) {
        englishWord = UNKNOWN_WORD_TRANSLATION;
      }
      
      resultBuilder.append(String.format("%s%n", englishWord));
    }

    scanner.close();
    outStream.print(resultBuilder.toString());
  }
}