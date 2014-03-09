import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
  private static final Map<Character, Character> characterMap = new HashMap<>();

  static {
    // row 1
    characterMap.put('1', '`');
    characterMap.put('2', '1');
    characterMap.put('3', '2');
    characterMap.put('4', '3');
    characterMap.put('5', '4');
    characterMap.put('6', '5');
    characterMap.put('7', '6');
    characterMap.put('8', '7');
    characterMap.put('9', '8');
    characterMap.put('0', '9');
    characterMap.put('-', '0');
    characterMap.put('=', '-');

    // row 2
    characterMap.put('W', 'Q');
    characterMap.put('E', 'W');
    characterMap.put('R', 'E');
    characterMap.put('T', 'R');
    characterMap.put('Y', 'T');
    characterMap.put('U', 'Y');
    characterMap.put('I', 'U');
    characterMap.put('O', 'I');
    characterMap.put('P', 'O');
    characterMap.put('[', 'P');
    characterMap.put(']', '[');
    characterMap.put('\\', ']');

    // row 3
    characterMap.put('S', 'A');
    characterMap.put('D', 'S');
    characterMap.put('F', 'D');
    characterMap.put('G', 'F');
    characterMap.put('H', 'G');
    characterMap.put('J', 'H');
    characterMap.put('K', 'J');
    characterMap.put('L', 'K');
    characterMap.put(';', 'L');
    characterMap.put('\'', ';');

    // row 4
    characterMap.put('X', 'Z');
    characterMap.put('C', 'X');
    characterMap.put('V', 'C');
    characterMap.put('B', 'V');
    characterMap.put('N', 'B');
    characterMap.put('M', 'N');
    characterMap.put(',', 'M');
    characterMap.put('.', ',');
    characterMap.put('/', '.');
  }

  public static void main(String... args) {
    Scanner scanner = new Scanner(System.in);

    while (scanner.hasNextLine()) {
      String input = scanner.nextLine();
      System.out.println(convert(input));
    }

    scanner.close();
  }

  private static String convert(String input) {
    StringBuilder result = new StringBuilder();

    for (char c : input.toCharArray()) {
      if (c == ' ') {
        result.append(c);
      } else {
        result.append(characterMap.get(c));
      }
    }

    return result.toString();
  }
}
