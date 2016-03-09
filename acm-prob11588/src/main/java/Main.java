import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Main {
  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);
    StringBuilder resultBuilder = new StringBuilder();
    int numberOfCases = Integer.parseInt(scanner.nextLine().trim());

    for (int i = 0; i < numberOfCases; i++) {
      String[] valueArray = scanner.nextLine().trim().split("\\s+");

      if (valueArray.length < 4) {
        throw new RuntimeException("invalid input line detected");
      }

      int numberOfRows = Integer.parseInt(valueArray[0]);
      int numberOfColumns = Integer.parseInt(valueArray[1]);
      int high = Integer.parseInt(valueArray[2]);
      int low = Integer.parseInt(valueArray[3]);

      Map<String, ImageRegion> regionMap = new HashMap<>();

      for (int j = 0; j < numberOfRows; j++) {
        String rowLine = scanner.nextLine().trim();

        if (rowLine.length() < numberOfColumns) {
          throw new RuntimeException(String.format("invalid input line : %s", rowLine));
        }

        for (int k = 0; k < numberOfColumns; k++) {
          String id = Character.toString(rowLine.charAt(k));
          ImageRegion region = regionMap.get(id);

          if (region == null) {
            region = new ImageRegion(id);
            regionMap.put(id, region);
          }

          region.incrementCount();
        }
      }

      // now sort the regions and compute the result
      ImageRegion[] regionArray =
          regionMap.values().toArray(new ImageRegion[regionMap.values().size()]);
      Arrays.sort(regionArray);

      int sum = 0;

      if (regionArray.length > 0) {
        int maxCount = regionArray[regionArray.length - 1].getCount();

        for (int j = 0; j < regionArray.length; j++) {
          int x = regionArray[j].getCount();

          if (x == maxCount) {
            sum += high * x;
          } else {
            sum += low * x;
          }
        }
      }

      resultBuilder.append(String.format("Case %d: %d%n", i + 1, sum));
    }

    scanner.close();
    outStream.print(resultBuilder.toString());
  }

  private static class ImageRegion implements Comparable<ImageRegion> {
    private final String identifier;
    private int count;

    public ImageRegion(String identifier) {
      this.identifier = Objects.requireNonNull(identifier);
    }

    public String getIdentifier() {
      return identifier;
    }

    public int getCount() {
      return count;
    }

    public void incrementCount() {
      count++;
    }

    @Override
    public int compareTo(ImageRegion otherRegion) {
      if (count < otherRegion.count) {
        return -1;
      }

      if (count > otherRegion.count) {
        return 1;
      }

      return 0;
    }
  }
}
