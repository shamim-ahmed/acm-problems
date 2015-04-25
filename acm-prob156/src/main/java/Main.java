import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {
  private static final String END_OF_INPUT_MARKER = "#";

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    StringBuilder resultBuilder = new StringBuilder();
    Scanner scanner = new Scanner(inputStream);
    Map<String, List<String>> wordMap = new HashMap<>();
    Set<String> ananagramSet = new TreeSet<>();

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine().trim();

      if (line.equals(END_OF_INPUT_MARKER)) {
        break;
      }

      String[] inputWords = line.split("\\s+");

      for (String word : inputWords) {
        String sortedWord = computeSortedString(word);
        List<String> wordList = wordMap.get(sortedWord);

        if (wordList == null) {
          wordList = new ArrayList<>();
        }

        wordList.add(word);
        wordMap.put(sortedWord, wordList);
      }
    }

    for (List<String> wordList : wordMap.values()) {
      if (wordList.size() == 1) {
        ananagramSet.addAll(wordList);
      }
    }

    for (String word : ananagramSet) {
      resultBuilder.append(word).append("\n");
    }

    scanner.close();
    outputStream.print(resultBuilder.toString());
  }

  private static final String computeSortedString(String inputStr) {
    char[] inputChars = inputStr.toLowerCase().toCharArray();
    Arrays.sort(inputChars);
    return String.valueOf(inputChars);
  }
}
