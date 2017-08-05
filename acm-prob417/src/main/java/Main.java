import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);
    StringBuilder resultBuilder = new StringBuilder();
    Map<String, Integer> wordMap = initializeMap();

    while (scanner.hasNextLine()) {
      String word = scanner.nextLine();
      Integer n = wordMap.get(word);

      if (n == null) {
        n = 0;
      }

      resultBuilder.append(n).append("\n");
    }

    scanner.close();
    outStream.print(resultBuilder.toString());
  }

  private static Map<String, Integer> initializeMap() {
    Map<String, Integer> wordMap = new HashMap<>();
    List<String> inputList = new ArrayList<>();

    for (char c = 'a'; c <= 'z'; c++) {
      inputList.add(Character.toString(c));
    }

    addWordsToMap(wordMap, inputList);

    for (int i = 1; i <= 4; i++) {
      List<String> resultList = generateNextWords(inputList);
      addWordsToMap(wordMap, resultList);
      inputList = resultList;
    }

    return wordMap;
  }

  private static List<String> generateNextWords(List<String> inputList) {
    List<String> resultList = new ArrayList<>();

    for (String word : inputList) {
      int n = word.length();
      char lastChar = word.charAt(n - 1);

      for (char c = (char) (lastChar + 1); c <= 'z'; c++) {
        StringBuilder sb = new StringBuilder();
        sb.append(word).append(c);
        resultList.add(sb.toString());
      }
    }

    return resultList;
  }

  private static void addWordsToMap(Map<String, Integer> wordMap, List<String> wordList) {
    int count = wordMap.size();

    for (String word : wordList) {
      count++;
      wordMap.put(word, count);
    }
  }
}
