import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
  private static final int MAX_ITERATION_COUNT = 999;
  
  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    StringBuilder resultBuilder = new StringBuilder();
    Scanner scanner = new Scanner(inputStream);
    int n = scanner.nextInt();

    for (int i = 0; i < n; i++) {
      long inputNum = scanner.nextLong();
      Result result = findResult(inputNum);
      resultBuilder.append(result.getCount()).append(" ").append(result.getPalindrome()).append("\n");
    }

    scanner.close();
    outputStream.print(resultBuilder.toString());
  }

  private static Result findResult(long inputNum) {
    int count = 0;
    String currentStr = Long.toString(inputNum);

    do {
      count++;
      String reverseStr = new StringBuilder(currentStr).reverse().toString();
      long value1 = Long.parseLong(currentStr);
      long value2 = Long.parseLong(reverseStr);
      long sum = value1 + value2;
      currentStr = Long.toString(sum);
    } while (!isPalindrome(currentStr) && count < MAX_ITERATION_COUNT);

    Result result = new Result();
    result.setCount(count);
    result.setPalindrome(currentStr);
    return result;
  }

  private static boolean isPalindrome(String inputStr) {
    char[] inputChars = inputStr.toCharArray();
    boolean result = true;
    int i = 0;
    int j = inputChars.length - 1;

    while (i < j) {
      if (inputChars[i] != inputChars[j]) {
        result = false;
        break;
      }

      i++;
      j--;
    }

    return result;
  }

  private static class Result {
    private int count;
    private String palindrome;

    public int getCount() {
      return count;
    }

    public void setCount(int count) {
      this.count = count;
    }

    public String getPalindrome() {
      return palindrome;
    }

    public void setPalindrome(String palindrome) {
      this.palindrome = palindrome;
    }
  }
}
