import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  private static final BigInteger START_YEAR = BigInteger.valueOf(2148);
  private static final BigInteger FOUR = BigInteger.valueOf(4);
  private static final BigInteger HUNDRED = BigInteger.valueOf(100);
  private static final BigInteger FOUR_HUNDRED = BigInteger.valueOf(400);

  private static final String LEAP_YEAR_GHOST_NAME = "K. M. Iftekhar";

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);
    StringBuilder resultBuilder = new StringBuilder();
    List<Ghost> ghostList = constructGhostList();

    while (scanner.hasNextBigInteger()) {
      BigInteger year = scanner.nextBigInteger();
      boolean found = false;

      if (year.equals(BigInteger.ZERO)) {
        break;
      }

      resultBuilder.append(year.toString()).append("\n");
      BigInteger difference = year.subtract(START_YEAR);
      
      if (difference.signum() < 0) {
        // note the extra newline
        resultBuilder.append("No ghost will come in this year\n\n");
        continue;
      }

      for (Ghost ghost : ghostList) {
        BigInteger result = difference.mod(ghost.getFrequency());

        if (result.equals(BigInteger.ZERO)) {
          found = true;
          resultBuilder.append(String.format("Ghost of %s!!!%n", ghost.getName()));
        }
      }

      // now account for the leap year ghost
      if (isLeapYear(year)) {
        found = true;
        resultBuilder.append(String.format("Ghost of %s!!!%n", LEAP_YEAR_GHOST_NAME));
      }

      // output for ghost-free year
      if (!found) {
        resultBuilder.append("No ghost will come in this year\n");
      }

      resultBuilder.append("\n");
    }

    scanner.close();
    String result;

    if (resultBuilder.length() > 0) {
      result = resultBuilder.substring(0, resultBuilder.length() - 1);
    } else {
      result = resultBuilder.toString();
    }

    outStream.print(result);
  }

  private static boolean isLeapYear(BigInteger year) {
    BigInteger x = year.mod(FOUR_HUNDRED);

    if (x.equals(BigInteger.ZERO)) {
      return true;
    }

    BigInteger y = year.mod(FOUR);
    BigInteger z = year.mod(HUNDRED);

    return y.equals(BigInteger.ZERO) && !z.equals(BigInteger.ZERO);
  }

  // the returned list will contain all ghosts, except the one that comes on a
  // leap year
  private static List<Ghost> constructGhostList() {
    String[] ghostNames = { "Tanveer Ahsan", "Shahriar Manzoor", "Adrian Kugel", "Anton Maydell", "Derek Kisman",
        "Rezaul Alam Chowdhury", "Jimmy Mardell", "Monirul Hasan" };
    int[] frequencies = { 2, 5, 7, 11, 15, 20, 28, 36 };

    List<Ghost> ghostList = new ArrayList<>();

    for (int i = 0; i < ghostNames.length; i++) {
      ghostList.add(new Ghost(ghostNames[i], BigInteger.valueOf(frequencies[i])));
    }

    return ghostList;
  }

  private static class Ghost {
    private final String name;
    private final BigInteger frequency;

    public Ghost(String name, BigInteger frequencyOfVisit) {
      this.name = name;
      this.frequency = frequencyOfVisit;
    }

    public String getName() {
      return name;
    }

    public BigInteger getFrequency() {
      return frequency;
    }
  }
}