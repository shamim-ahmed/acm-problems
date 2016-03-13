import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
  private static final Pattern INPUT_LINE_PATTERN =
      Pattern.compile("([a-zA-Z]+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)");

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);
    int n = Integer.parseInt(scanner.nextLine());

    if (n <= 0) {
      throw new RuntimeException(String.format("invalid input value : %d", n));
    }

    Person[] personArray = new Person[n];

    for (int i = 0; i < n; i++) {
      String line = scanner.nextLine();
      Matcher matcher = INPUT_LINE_PATTERN.matcher(line);

      if (!matcher.matches()) {
        throw new RuntimeException(String.format("invalid input line : %s", line));
      }

      String name = matcher.group(1);
      int day = Integer.parseInt(matcher.group(2));
      int month = Integer.parseInt(matcher.group(3));
      int year = Integer.parseInt(matcher.group(4));
      Date dateOfBirth = constructDate(day, month, year);
      personArray[i] = new Person(name, dateOfBirth);
    }

    // sort the Person objects according to birth date
    Arrays.sort(personArray);

    // The oldest person will be at the start of the sorted array
    // and the youngest person will be at the end
    StringBuilder resultBuilder = new StringBuilder();
    resultBuilder.append(personArray[personArray.length - 1].getName()).append("\n");
    resultBuilder.append(personArray[0].getName()).append("\n");

    scanner.close();
    outStream.print(resultBuilder.toString());
  }

  private static Date constructDate(int day, int month, int year) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.DAY_OF_MONTH, day);
    calendar.set(Calendar.MONTH, month);
    calendar.set(Calendar.YEAR, year);

    return calendar.getTime();
  }

  private static class Person implements Comparable<Person> {
    private final String name;
    private final Date dateOfBirth;

    public Person(String name, Date dateOfBirth) {
      this.name = Objects.requireNonNull(name);
      this.dateOfBirth = Objects.requireNonNull(dateOfBirth);
    }

    public String getName() {
      return name;
    }

    @Override
    public int compareTo(Person otherPerson) {
      return dateOfBirth.compareTo(otherPerson.dateOfBirth);
    }
  }
}
