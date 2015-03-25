import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
  private static final String[] SONG_WORDS = { "Happy", "birthday", "to", "you", "Happy", "birthday", "to", "you",
      "Happy", "birthday", "to", "Rujia", "Happy", "birthday", "to", "you" };

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    StringBuilder resultBuilder = new StringBuilder();
    Scanner scanner = new Scanner(inputStream);
    final int n = Integer.parseInt(scanner.nextLine());
    String[] nameArray = new String[n];

    for (int i = 0; i < n; i++) {
      nameArray[i] = scanner.nextLine();
    }
    
    // print all names at least once
    int i = 0;
    int j = 0;
    
    while (i < n) {
      resultBuilder.append(String.format("%s: %s%n", nameArray[i], SONG_WORDS[j]));
      i++;
      j = (j + 1) % SONG_WORDS.length;
    }

    // now ensure that the song is finished properly    
    i = 0;
    
    while (j < SONG_WORDS.length) {
      resultBuilder.append(String.format("%s: %s%n", nameArray[i], SONG_WORDS[j]));
      i = (i + 1) % n;
      j++;
    }
    
    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
}
