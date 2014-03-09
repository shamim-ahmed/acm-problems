import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
  private static final String EMPTY_LINE = "";
  private static final String END_MARKER = "0";
  private static final String SPACE = " ";

  public static void main(String... args) {
    Scanner scanner = new Scanner(System.in);

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();

      if (line.trim().equals(EMPTY_LINE)) {
        continue;
      }

      int[] values = parse(line);
      String result = sort(values);

      System.out.println(line);
      System.out.println(result);
    }

    scanner.close();
  }

  private static int[] parse(String line) {
    StringTokenizer tokenizer = new StringTokenizer(line, SPACE, false);
    int[] values = new int[tokenizer.countTokens()];

    for (int i = 0, n = values.length; i < n; i++) {
      values[i] = Integer.parseInt(tokenizer.nextToken());
    }

    return values;
  }

  private static String sort(int[] values) {
    if (isSorted(values)) {
      return END_MARKER;
    }

    final int start = 0;
    int end = values.length - 1;
    StringBuilder result = new StringBuilder();

    do {
      int flipIndex = findIndexOfMaxValue(values, start, end);

      if (flipIndex != start) {
        flip(values, flipIndex);
        result.append(Integer.toString(values.length - flipIndex)).append(SPACE);
      }

      flip(values, end);
      result.append(Integer.toString(values.length - end)).append(SPACE);

      end--;
    } while (!isSorted(values) && end > 0);

    result.append(END_MARKER);
    return result.toString();
  }

  private static boolean isSorted(int[] values) {
    for (int i = 0, n = values.length - 1; i < n; i++) {
      if (values[i] > values[i + 1]) {
        return false;
      }
    }

    return true;
  }

  private static int findIndexOfMaxValue(int[] values, int start, int end) {
    int result = start;

    for (int i = start + 1; i <= end; i++) {
      if (values[i] > values[result]) {
        result = i;
      }
    }

    return result;
  }

  private static void flip(int[] values, final int flipIndex) {
    int n = (flipIndex + 1) / 2;

    for (int i = 0, idx = flipIndex; i < n; i++, idx--) {
      swap(values, i, idx);
    }
  }

  private static void swap(int[] values, int i, int j) {
    int temp = values[i];
    values[i] = values[j];
    values[j] = temp;
  }
}
