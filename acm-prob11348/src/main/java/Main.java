import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
  private static final String CASE_FORMAT_STR = "Case %d: ";
  private static final String NUMBER_FORMAT_STR = "%.6f";

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);
    int numberOfCases = scanner.nextInt();
    StringBuilder resultBuilder = new StringBuilder();

    for (int i = 1; i <= numberOfCases; i++) {
      int numberOfFriends = scanner.nextInt();
      Map<Integer, List<Integer>> stampToPersonMap = new HashMap<>();

      // find the owner(s) for each kind of stamp
      for (int j = 1; j <= numberOfFriends; j++) {
        int numberOfStamps = scanner.nextInt();

        for (int k = 1; k <= numberOfStamps; k++) {
          int stampType = scanner.nextInt();
          putInMap(stampToPersonMap, stampType, j);
        }
      }

      // now consider only those stamps for which there is only one owner
      Map<Integer, List<Integer>> personToStampMap = new TreeMap<>();
      int count = 0;

      for (Map.Entry<Integer, List<Integer>> entry : stampToPersonMap.entrySet()) {
        if (entry.getValue().size() == 1) {
          putInMap(personToStampMap, entry.getValue().get(0), entry.getKey());
          count++;
        }
      }

      generateFormattedResult(i, numberOfFriends, count, personToStampMap, resultBuilder);
    }

    scanner.close();
    outStream.print(resultBuilder.toString());
  }

  private static void putInMap(Map<Integer, List<Integer>> map, Integer key, Integer value) {
    List<Integer> valueList = map.get(key);

    if (valueList == null) {
      valueList = new ArrayList<>();
      map.put(key, valueList);
    }

    valueList.add(value);
  }

  private static void generateFormattedResult(int inputSetNo, int numberOfFriends, int totalCount,
      Map<Integer, List<Integer>> personToStampMap, StringBuilder resultBuilder) {

    resultBuilder.append(String.format(CASE_FORMAT_STR, inputSetNo));

    for (int i = 1; i <= numberOfFriends; i++) {
      List<Integer> stampList = personToStampMap.get(i);
      double val = 0.0;

      if (stampList != null) {
        val = (100.0 * stampList.size()) / totalCount;
      }

      resultBuilder.append(String.format(NUMBER_FORMAT_STR, val));

      if (i < numberOfFriends) {
        resultBuilder.append(" ");
      }
    }

    resultBuilder.append("\n");
  }
}
