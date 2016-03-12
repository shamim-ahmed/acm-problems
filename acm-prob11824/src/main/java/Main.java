import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
  private static final BigInteger TWO = new BigInteger("2");
  private static final BigInteger TOTAL_BUDGET = new BigInteger("5000000");
  private static final String EXPENSIVE_PROPERTY_MSG = "Too expensive";

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);
    StringBuilder resultBuilder = new StringBuilder();
    BigInteger numberOfCases = scanner.nextBigInteger();

    for (int i = 0, n = numberOfCases.intValue(); i < n; i++) {
      List<BigInteger> priceList = new ArrayList<>();
      BigInteger p = scanner.nextBigInteger();

      while (!p.equals(BigInteger.ZERO)) {
        priceList.add(p);
        p = scanner.nextBigInteger();
      }

      // now sort the prices in descending order and compute the sum
      Collections.sort(priceList, Collections.reverseOrder());

      BigInteger sum = BigInteger.ZERO;
      int j = 1;

      for (BigInteger price : priceList) {
        sum = sum.add(price.pow(j));
        j++;
      }

      sum = sum.multiply(TWO);

      if (sum.compareTo(TOTAL_BUDGET) > 0) {
        resultBuilder.append(EXPENSIVE_PROPERTY_MSG).append("\n");
      } else {
        resultBuilder.append(sum.toString()).append("\n");
      }
    }

    scanner.close();
    outStream.print(resultBuilder.toString());
  }
}
