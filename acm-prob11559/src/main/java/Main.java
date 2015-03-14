import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    StringBuilder resultBuilder = new StringBuilder();
    Scanner scanner = new Scanner(inputStream);

    while (scanner.hasNextInt()) {
      final int numberOfParticipants = scanner.nextInt();
      final int budget = scanner.nextInt();
      final int numberOfHotels = scanner.nextInt();
      final int numberOfWeeks = scanner.nextInt();

      Hotel[] hotelArray = new Hotel[numberOfHotels];

      for (int i = 0; i < numberOfHotels; i++) {
        int weeklyRent = scanner.nextInt();

        int[] weeklyAvailability = new int[numberOfWeeks];

        for (int j = 0; j < numberOfWeeks; j++) {
          weeklyAvailability[j] = scanner.nextInt();
        }

        Hotel hotel = new Hotel();
        hotel.setWeeklyRent(weeklyRent);
        hotel.setWeeklyAvailability(weeklyAvailability);
        hotelArray[i] = hotel;
      }

      Arrays.sort(hotelArray);
      int minCost = Integer.MAX_VALUE;

      for (int i = 0; i < numberOfWeeks; i++) {
        int weeklyCost = Integer.MAX_VALUE;

        for (Hotel hotel : hotelArray) {
          int cost = hotel.getWeeklyRent() * numberOfParticipants;

          if (cost > budget) {
            // later hotels are even costlier
            break;
          }

          int availableBeds = hotel.getWeeklyAvailability()[i];

          if (availableBeds < numberOfParticipants) {
            // not enough beds. Try next one.
            continue;
          }

          weeklyCost = cost;
          break;
        }

        if (weeklyCost < minCost) {
          minCost = weeklyCost;
        }
      }

      if (minCost == Integer.MAX_VALUE) {
        resultBuilder.append("stay home\n");
      } else {
        resultBuilder.append(minCost).append("\n");
      }
    }

    scanner.close();
    outputStream.print(resultBuilder.toString());
  }

  private static class Hotel implements Comparable<Hotel> {
    private int weeklyRent;
    private int[] weeklyAvailability;

    public int getWeeklyRent() {
      return weeklyRent;
    }

    public void setWeeklyRent(int weeklyRent) {
      this.weeklyRent = weeklyRent;
    }

    public int[] getWeeklyAvailability() {
      return weeklyAvailability;
    }

    public void setWeeklyAvailability(int[] weeklyAvailability) {
      this.weeklyAvailability = weeklyAvailability;
    }

    @Override
    public int compareTo(Hotel otherHotelInfo) {
      if (weeklyRent < otherHotelInfo.weeklyRent) {
        return -1;
      }

      if (weeklyRent > otherHotelInfo.weeklyRent) {
        return 1;
      }

      return 0;
    }
  }
}
