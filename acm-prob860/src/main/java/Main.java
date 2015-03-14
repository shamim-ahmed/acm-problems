import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
  private static final String END_OF_TEXT = "****END_OF_TEXT****";
  private static final String END_OF_INPUT = "****END_OF_INPUT****";
  private static final String WORD_SEPARATORS = ",.:;!?\"()\n\t ";

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    StringBuilder inputBuilder = new StringBuilder();
    StringBuilder resultBuilder = new StringBuilder();
    Scanner scanner = new Scanner(inputStream);

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();

      if (line.contains(END_OF_INPUT)) {
        break;
      }

      if (line.equals(END_OF_TEXT)) {
        computeEntropy(inputBuilder.toString(), resultBuilder);
        inputBuilder.delete(0, inputBuilder.length());
      } else {
        inputBuilder.append(line.toLowerCase()).append("\n");
      }
    }

    scanner.close();
    outputStream.print(resultBuilder.toString());
  }

  private static void computeEntropy(String input, StringBuilder resultBuilder) {
    StringTokenizer tokenizer = new StringTokenizer(input, WORD_SEPARATORS, false);
    int n = 0;
    Map<String, Integer> frequencyMap = new HashMap<>();

    while (tokenizer.hasMoreTokens()) {
      String word = tokenizer.nextToken();
      
      if (word.length() == 0) {
        continue;
      }
      
      n++;
      Integer freq = frequencyMap.get(word);

      if (freq == null) {
        freq = Integer.valueOf(1);
      } else {
        freq++;
      }

      frequencyMap.put(word, freq);
    }

    if (frequencyMap.size() == 0) {
      resultBuilder.append("0 0.0 0\n");
    } else {
      double sum = 0.0;

      for (int freq : frequencyMap.values()) {
        sum += freq * (Math.log10(n) - Math.log10(freq));
      }

      double et = sum / n;
      double emax = Math.log10(n);
      long erel = Math.round((et / emax) * 100.0);

      resultBuilder.append(String.format("%d %.1f %d%n", n, et, erel));
    }

  }
}
