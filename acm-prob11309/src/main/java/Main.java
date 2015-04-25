import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    StringBuilder resultBuilder = new StringBuilder();
    Scanner scanner = new Scanner(inputStream);
    int n = Integer.parseInt(scanner.nextLine());

    for (int i = 0; i < n; i++) {
      String inputStr = scanner.nextLine();
      String[] inputComponents = inputStr.split(":");
      int hour = Integer.parseInt(inputComponents[0]);
      int minute = Integer.parseInt(inputComponents[1]);
      Time resultTime = computeTime(hour, minute);
      resultBuilder.append(resultTime).append("\n");
    }

    scanner.close();
    outputStream.print(resultBuilder.toString());
  }

  private static Time computeTime(int h, int m) {
    if (h == 0) {
      m = m + 1;

      while (!isPalindrome(Integer.toString(m))) {
        if (m == 60) {
          h = 1;
          m = 1;
        } else {
          m = m + 1;
        }
      }
    } else {
      if (h <= 9) {
        boolean done = false;

        while (!done) {
          m = m + 1;

          if (m == 60) {
            m = 0;
            h = h + 1;

            if (h == 10) {
              m = 1;
              done = true;
              continue;
            }
          }

          if (lastDigitMatches(h, m)) {
            done = true;
          }
        }
      } else {
        int targetMinute = reverseDigits(h);

        if (targetMinute > m && targetMinute <= 60) {
          m = targetMinute;
        } else {
          h = h + 1;

          if (h == 24) {
            h = 0;
          } else if (h >= 16 && h <= 19) {
            h = 20;
          }

          m = reverseDigits(h);
        }
      }
    }

    Time result = new Time(h, m);
    return result;
  }

  private static boolean lastDigitMatches(int h, int m) {
    String minuteStr = Integer.toString(m);
    int digit = minuteStr.charAt(minuteStr.length() - 1) - '0';

    return digit == h;
  }

  private static boolean isPalindrome(String inputStr) {
    if (inputStr.length() > 2) {
      throw new IllegalArgumentException(String.format("invalid input %s", inputStr));
    }

    if (inputStr.length() == 1) {
      return true;
    }

    return inputStr.charAt(0) == inputStr.charAt(1);
  }

  private static int reverseDigits(int number) {
    if (number < 0 || number >= 100) {
      throw new IllegalArgumentException(String.format("invalid number %s", number));
    }

    if (number <= 9) {
      return number;
    }

    char[] digits = Integer.toString(number).toCharArray();
    char c = digits[0];
    digits[0] = digits[1];
    digits[1] = c;

    return Integer.parseInt(String.valueOf(digits));
  }

  private static class Time {
    private final int hour;
    private final int minute;

    public Time(int hour, int minute) {
      this.hour = hour;
      this.minute = minute;
    }

    public String toString() {
      return String.format("%02d:%02d", hour, minute);
    }
  }
}
