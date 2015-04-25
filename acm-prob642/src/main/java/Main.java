import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
  private static final String SENTINEL = "XXXXXX";
  private static final String OUTPUT_GROUP_SEPARATOR = "******";
  private static final String NO_MATCH_OUTPUT = "NOT A VALID WORD";

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    StringBuilder resultBuilder = new StringBuilder();
    Scanner scanner = new Scanner(inputStream);
    TreeMap<String, String> dictionaryMap = new TreeMap<>();

    while (scanner.hasNextLine()) {
      String word = scanner.nextLine();

      if (word.equals(SENTINEL)) {
        break;
      }

      dictionaryMap.put(word, computeSortedString(word));
    }

    while (scanner.hasNextLine()) {
      String testWord = scanner.nextLine();

      if (testWord.equals(SENTINEL)) {
        break;
      }

      String testWordSorted = computeSortedString(testWord);
      List<String> matchedWords = new ArrayList<>();

      for (Map.Entry<String, String> entry : dictionaryMap.entrySet()) {
        if (testWordSorted.equals(entry.getValue())) {
          matchedWords.add(entry.getKey());
        }
      }

      if (matchedWords.isEmpty()) {
        resultBuilder.append(NO_MATCH_OUTPUT).append("\n");
      } else {
        for (String word : matchedWords) {
          resultBuilder.append(word).append("\n");
        }
      }

      resultBuilder.append(OUTPUT_GROUP_SEPARATOR).append("\n");
    }

    // read the dictionary words
    scanner.close();
    outputStream.print(resultBuilder.toString());
  }

  public static String computeSortedString(String inputStr) {
    char[] inputChars = inputStr.toCharArray();
    Arrays.sort(inputChars);
    return String.valueOf(inputChars);
  }
}
