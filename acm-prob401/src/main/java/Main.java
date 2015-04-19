import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
  private static final Map<Character, Character> REVERSE_CHAR_MAP = new HashMap<>();

  static {
    REVERSE_CHAR_MAP.put('A', 'A');
    REVERSE_CHAR_MAP.put('E', '3');
    REVERSE_CHAR_MAP.put('H', 'H');
    REVERSE_CHAR_MAP.put('I', 'I');
    REVERSE_CHAR_MAP.put('J', 'L');
    REVERSE_CHAR_MAP.put('L', 'J');
    REVERSE_CHAR_MAP.put('M', 'M');
    REVERSE_CHAR_MAP.put('O', 'O');
    REVERSE_CHAR_MAP.put('S', '2');
    REVERSE_CHAR_MAP.put('T', 'T');
    REVERSE_CHAR_MAP.put('U', 'U');
    REVERSE_CHAR_MAP.put('V', 'V');
    REVERSE_CHAR_MAP.put('W', 'W');
    REVERSE_CHAR_MAP.put('X', 'X');
    REVERSE_CHAR_MAP.put('Y', 'Y');
    REVERSE_CHAR_MAP.put('Z', '5');
    REVERSE_CHAR_MAP.put('1', '1');
    REVERSE_CHAR_MAP.put('2', 'S');
    REVERSE_CHAR_MAP.put('3', 'E');
    REVERSE_CHAR_MAP.put('5', 'Z');
    REVERSE_CHAR_MAP.put('8', '8');
  }

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    StringBuilder resultBuilder = new StringBuilder();
    Scanner scanner = new Scanner(inputStream);

    while (scanner.hasNextLine()) {
      String inputLine = scanner.nextLine();
      boolean mirrored = isMirrored(inputLine);
      boolean palindrome = isPalindrome(inputLine);

      if (!palindrome && !mirrored) {
        resultBuilder.append(String.format("%s -- is not a palindrome.%n%n", inputLine));
      } else if (palindrome && !mirrored) {
        resultBuilder.append(String.format("%s -- is a regular palindrome.%n%n", inputLine));
      } else if (!palindrome && mirrored) {
        resultBuilder.append(String.format("%s -- is a mirrored string.%n%n", inputLine));
      } else if (palindrome && mirrored) {
        resultBuilder.append(String.format("%s -- is a mirrored palindrome.%n%n", inputLine));
      }
    }

    scanner.close();
    outputStream.print(resultBuilder.toString());
  }

  private static boolean isMirrored(String inputLine) {
    char[] inputChars = inputLine.toCharArray();
    
    if (inputChars.length == 1) {
      char c1 = inputChars[0];
      Character c2 = REVERSE_CHAR_MAP.get(c1);
      return c2 != null && c1 == c2.charValue();
    }
    
    boolean result = true;
    int i = 0;
    int j = inputChars.length - 1;
    
    while (i < j) {
      char c1 = inputChars[i];
      Character c2 = REVERSE_CHAR_MAP.get(inputChars[j]);

      if (c2 == null || c1 != c2.charValue()) {
        result = false;
        break;
      }

      i++;
      j--;
    }

    return result;
  }

  private static boolean isPalindrome(String inputLine) {
    char[] inputChars = inputLine.toCharArray();
    boolean result = true;
    int i = 0;
    int j = inputChars.length - 1;

    while (i < j) {
      if (inputChars[i] != inputChars[j]) {
        result = false;
        break;
      }

      i++;
      j--;
    }

    return result;
  }
}
