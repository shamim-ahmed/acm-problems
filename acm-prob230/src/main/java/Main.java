import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
  private static final String END_OF_INPUT_MARKER = "END";
  private static final String END_OF_OUTPUT_INDICATOR = "END";
  private static final Pattern BOOK_LINE_PATTERN = Pattern.compile("\"([^\"]+)\"\\s+by\\s+(.+)");
  private static final Pattern BORROW_LINE_PATTERN = Pattern.compile("BORROW\\s+\"([^\"]+)\"");
  private static final Pattern RETURN_LINE_PATTERN = Pattern.compile("RETURN\\s+\"([^\"]+)\"");

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    StringBuilder resultBuilder = new StringBuilder();
    Scanner scanner = new Scanner(inputStream);

    Map<String, Book> allBookMap = new HashMap<>();
    TreeSet<Book> libraryBookSet = new TreeSet<>();

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine().trim();

      if (line.equals(END_OF_INPUT_MARKER)) {
        break;
      }

      Matcher matcher = BOOK_LINE_PATTERN.matcher(line);

      if (!matcher.matches()) {
        throw new RuntimeException(String.format("invalid input line: %s", line));
      }

      String title = matcher.group(1);
      String author = matcher.group(2);
      Book book = new Book(title, author);
      allBookMap.put(title, book);
      libraryBookSet.add(book);
    }

    Set<Book> returnedBookSet = new TreeSet<>();

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine().trim();

      if (line.startsWith("BORROW")) {
        Matcher matcher = BORROW_LINE_PATTERN.matcher(line);

        if (!matcher.matches()) {
          throw new RuntimeException(String.format("invalid input line: %s", line));
        }

        String title = matcher.group(1);
        Book book = allBookMap.get(title);
        libraryBookSet.remove(book);
      } else if (line.startsWith("RETURN")) {
        Matcher matcher = RETURN_LINE_PATTERN.matcher(line);

        if (!matcher.matches()) {
          throw new RuntimeException(String.format("invalid input line: %s", line));
        }

        String title = matcher.group(1);
        Book book = allBookMap.get(title);
        returnedBookSet.add(book);
      } else if (line.equals("SHELVE")) {
        for (Book returnedBook : returnedBookSet) {
          Book previousBook = libraryBookSet.floor(returnedBook);

          if (previousBook == null) {
            resultBuilder.append(String.format("Put \"%s\" first", returnedBook.getTitle())).append("\n");
          } else {
            resultBuilder.append(
                String.format("Put \"%s\" after \"%s\"", returnedBook.getTitle(), previousBook.getTitle()))
                .append("\n");
          }

          libraryBookSet.add(returnedBook);
        }

        // clearing this list is crucial
        returnedBookSet.clear();
        resultBuilder.append(END_OF_OUTPUT_INDICATOR).append("\n");
      }
    }

    scanner.close();
    outputStream.print(resultBuilder.toString());
  }

  private static class Book implements Comparable<Book> {
    private final String title;
    private final String author;

    public Book(String title, String author) {
      this.title = Objects.requireNonNull(title);
      this.author = Objects.requireNonNull(author);
    }

    public String getTitle() {
      return title;
    }

    public String getAuthor() {
      return author;
    }

    @Override
    public int compareTo(Book otherBook) {
      int result = author.compareTo(otherBook.author);

      if (result == 0) {
        result = title.compareTo(otherBook.title);
      }

      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (!(obj instanceof Book)) {
        return false;
      }

      Book otherBook = (Book) obj;
      return title.equals(otherBook.title) && author.equals(otherBook.author);
    }

    @Override
    public int hashCode() {
      int result = 7;
      result = 17 * result + title.hashCode();
      result = 17 * result + author.hashCode();

      return result;
    }
  }
}
