import java.math.BigInteger;
import java.util.Scanner;

public class Main {
  private static final String INPUT_END_MARKER = "#";
  private static final String MODULUS_VALUE = "34943";

  public static void main(String... args) {
    Scanner scanner = new Scanner(System.in);

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();

      if (line.equals(INPUT_END_MARKER)) {
        break;
      }

      byte[] message = new byte[line.length() + 2];

      for (int i = 0; i < line.length(); i++) {
        message[i] = (byte) line.charAt(i);
      }

      int result = computeCrc(message);
      printCrc(result);
    }

    scanner.close();
  }

  private static int computeCrc(byte[] message) {
    BigInteger n = new BigInteger(1, message);
    BigInteger m = new BigInteger(MODULUS_VALUE, 10);

    BigInteger r = n.mod(m);

    if (r.intValue() == 0) {
      return 0;
    }

    return m.intValue() - r.intValue();
  }

  private static void printCrc(int crc) {
    StringBuilder result = new StringBuilder(Integer.toString(crc, 16).toUpperCase());

    if (result.length() < 4) {
      int k = 4 - result.length();

      for (int i = 0; i < k; i++) {
        result.insert(0, "0");
      }
    }

    String str1 = result.substring(0, 2);
    String str2 = result.substring(2, 4);
    System.out.printf("%s %s%n", str1, str2);
  }
}
