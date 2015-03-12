import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
  private static final String INPUT_TERMINATOR = "#";
  private static final Map<String, String> LANGUAGE_MAP = new HashMap<>();

  static {
    LANGUAGE_MAP.put("HELLO", "ENGLISH");
    LANGUAGE_MAP.put("HOLA", "SPANISH");
    LANGUAGE_MAP.put("HALLO", "GERMAN");
    LANGUAGE_MAP.put("BONJOUR", "FRENCH");
    LANGUAGE_MAP.put("CIAO", "ITALIAN");
    LANGUAGE_MAP.put("ZDRAVSTVUJTE", "RUSSIAN");
  }

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    StringBuilder resultBuilder = new StringBuilder();
    Scanner scanner = new Scanner(inputStream);
    int i = 0;
    
    while (scanner.hasNextLine()) {
      String greetingLine = scanner.nextLine().trim();
      
      if (greetingLine.equals(INPUT_TERMINATOR)) {
        break;
      }
      
      i++;
      String language = LANGUAGE_MAP.get(greetingLine);
      
      if (language == null) {
        language = "UNKNOWN";
      }
      
      resultBuilder.append(String.format("Case %d: %s%n", i, language));
    }
    
    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
}
