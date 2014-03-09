import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  private static final int SOUNDEX_CODE_LENGTH = 4;
  private static final int ASCII_VALUE_FOR_ZERO = 48;
  private static final int ASCII_VALUE_FOR_A = 65;
  private static final String EMPTY_STRING = "";
  private static final int[] CODE_VALUES = { 0, 1, 2, 3, 0, 1, 2, 0, 0, 2, 2, 4, 5, 5, 0, 1, 2, 6, 2, 3, 0, 1, 0, 2, 0,
      2 };

  public static void main(String... args) {
    List<Pair> resultList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    while (scanner.hasNextLine()) {
      String word = scanner.nextLine();
      String code = convertToSoundex(word);
      resultList.add(new Pair(word, code));
    }

    scanner.close();
    System.out.print(generateOutput(resultList));
  }

  private static final class Pair {
    private final String word;
    private final String code;

    public Pair(String word, String code) {
      this.word = word;
      this.code = code;
    }

    public String getWord() {
      return word;
    }

    public String getCode() {
      return code;
    }
  }

  private static String convertToSoundex(String word) {
    int arrayLen = word.length();

    if (arrayLen < SOUNDEX_CODE_LENGTH) {
      arrayLen = SOUNDEX_CODE_LENGTH;
    }

    char[] result = new char[arrayLen];

    int i = 0;
    int j = 0;
    final int n = word.length();
    char currentChar = 0;
    char previousChar = 0;

    while (i < n) {
      previousChar = currentChar;
      currentChar = word.charAt(i);
      int currentCode = CODE_VALUES[currentChar - ASCII_VALUE_FOR_A];

      if (i == 0) {
        result[j++] = currentChar;
      } else if (currentCode != 0) {
        int previousCode = CODE_VALUES[previousChar - ASCII_VALUE_FOR_A];

        if (currentCode != previousCode) {
          result[j++] = (char) (currentCode + ASCII_VALUE_FOR_ZERO);

          if (j == SOUNDEX_CODE_LENGTH) {
            break;
          }
        }
      }

      i++;
    }

    while (j < SOUNDEX_CODE_LENGTH) {
      result[j++] = ASCII_VALUE_FOR_ZERO;
    }

    return new String(result, 0, j);
  }

  private static String generateOutput(List<Pair> resultList) {
    StringBuilder result = new StringBuilder();
    result.append(generateRow("NAME", "SOUNDEX CODE"));

    for (Pair p : resultList) {
      result.append(generateRow(p.getWord(), p.getCode()));
    }

    result.append(String.format("%-19s%s%n", EMPTY_STRING, "END OF OUTPUT"));
    return result.toString();
  }

  private static String generateRow(String w1, String w2) {
    return String.format("%-9s%-25s%s%n", EMPTY_STRING, w1, w2);
  }
}
