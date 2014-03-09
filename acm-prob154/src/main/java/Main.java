import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
  private static final int BIN_COUNT = 5;
  private static final String COMMA = ",";
  private static final String BLOCK_END_MARKER_LETTER = "e";
  private static final String INPUT_END_MARKER = "#";

  public static void main(String... args) {
    Scanner scanner = new Scanner(System.in);

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();

      if (line.equals(INPUT_END_MARKER)) {
        break;
      }

      List<String[]> dataForCities = new ArrayList<String[]>();

      do {
        StringTokenizer tokenizer = new StringTokenizer(line, COMMA, false);

        if (tokenizer.countTokens() != BIN_COUNT) {
          throw new RuntimeException(String.format("Invalid input: %s", line));
        }

        String[] data = new String[tokenizer.countTokens()];

        for (int i = 0, n = data.length; i < n; i++) {
          data[i] = tokenizer.nextToken();
        }

        Arrays.sort(data);
        dataForCities.add(data);
        line = scanner.nextLine();
      } while (!line.startsWith(BLOCK_END_MARKER_LETTER));

      int cityIndex = findCity(dataForCities);
      System.out.println(cityIndex);
    }

    scanner.close();
  }

  private static int findCity(List<String[]> dataForCities) {
    Map<Integer, Integer> moveCountMap = new HashMap<Integer, Integer>();
    final int n = dataForCities.size();

    for (int i = 0; i < n; i++) {
      int sum = 0;

      for (int j = 0; j < n; j++) {
        if (j == i) {
          continue;
        }

        sum += countMismatches(dataForCities.get(i), dataForCities.get(j));
      }

      moveCountMap.put(i + 1, sum);
    }

    int result = 0;
    int min = Integer.MAX_VALUE;

    for (Map.Entry<Integer, Integer> entry : moveCountMap.entrySet()) {
      if (entry.getValue() < min) {
        min = entry.getValue();
        result = entry.getKey();
      }
    }

    return result;
  }

  private static int countMismatches(String[] p, String[] q) {
    int count = 0;

    for (int i = 0; i < p.length; i++) {
      if (!p[i].equals(q[i])) {
        count++;
      }
    }

    return count;
  }
}
