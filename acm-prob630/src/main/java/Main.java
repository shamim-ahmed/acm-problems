import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    StringBuilder resultBuilder = new StringBuilder();
    Scanner scanner = new Scanner(inputStream);
    int dataSetCount = Integer.parseInt(scanner.nextLine());
    scanner.nextLine();

    for (int i = 0; i < dataSetCount; i++) {
      int wordCount = Integer.parseInt(scanner.nextLine());
      String[] wordsInVocabulary = new String[wordCount];
      String[] transformedWords = new String[wordCount];

      for (int j = 0; j < wordCount; j++) {
        String word = scanner.nextLine();
        wordsInVocabulary[j] = word;
        transformedWords[j] = transform(word);
      }

      String testWord = scanner.nextLine();

      while (!testWord.equals("END")) {
        List<String> anagramList = findAnagrams(testWord, wordsInVocabulary, transformedWords);
        resultBuilder.append(String.format("Anagrams for: %s%n", testWord));

        if (anagramList.isEmpty()) {
          resultBuilder.append(String.format("No anagrams for: %s%n", testWord));
        } else {
          for (int j = 0, n = anagramList.size(); j < n; j++) {
            resultBuilder.append(String.format("%3d) %s%n", j + 1, anagramList.get(j)));
          }
        }

        // read the next word
        testWord = scanner.nextLine();
      }

      if (i < dataSetCount - 1) {
        scanner.nextLine();
        resultBuilder.append("\n");
      }
    }

    scanner.close();
    outputStream.print(resultBuilder.toString());
  }

  private static List<String> findAnagrams(String word, String[] wordsInVocabulary, String[] transformedWords) {
    List<String> resultList = new ArrayList<>();
    String sortedStr = transform(word);

    for (int i = 0; i < transformedWords.length; i++) {
      String s = transformedWords[i];

      if (s.equals(sortedStr)) {
        resultList.add(wordsInVocabulary[i]);
      }
    }

    return resultList;
  }

  private static final String transform(String inputStr) {
    char[] inputChars = inputStr.toCharArray();
    Arrays.sort(inputChars);
    return String.valueOf(inputChars);
  }
}
