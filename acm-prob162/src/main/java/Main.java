import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
  private static final String INPUT_TERMINATOR = "#";

  private static class Card {
    private char suite;
    private char rank;

    public char getSuite() {
      return suite;
    }

    public void setSuite(char suite) {
      this.suite = suite;
    }

    public char getRank() {
      return rank;
    }

    public void setRank(char rank) {
      this.rank = rank;
    }

    public boolean isFaceCard() {
      return rank == 'J' || rank == 'Q' || rank == 'K' || rank == 'A';
    }

    @Override
    public String toString() {
      return String.format("%c%c", suite, rank);
    }
  }

  private static final Card[][] DECK_OF_CARDS = new Card[4][13];

  static {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 13; j++) {
        DECK_OF_CARDS[i][j] = new Card();
      }
    }
  }

  public static void main(String... args) {
    Scanner scanner = new Scanner(System.in);

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine().trim();

      if (line.equals(INPUT_TERMINATOR)) {
        break;
      }

      // we have already read the first line of the deck
      parseLine(line, 0);

      // let us read the next three lines of the deck
      for (int i = 1; i < 4; i++) {
        line = scanner.nextLine().trim();
        parseLine(line, i);
      }

      // at this point, we have read the whole deck. So we can start the game.
      playWithDeck();
    }

    scanner.close();
  }

  private static void parseLine(String line, int i) {
    StringTokenizer tokenizer = new StringTokenizer(line, " ", false);
    int j = 0;

    while (tokenizer.hasMoreTokens()) {
      String token = tokenizer.nextToken();
      char s = token.charAt(0);
      char r = token.charAt(1);
      Card card = DECK_OF_CARDS[i][j];
      card.setSuite(s);
      card.setRank(r);
      j++;
    }
  }

  private static void playWithDeck() {
    // TODO implement it properly
    printDeck();
  }

  private static void printDeck() {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 13; j++) {
        System.out.printf("%s ", DECK_OF_CARDS[i][j]);
      }
      System.out.println();
    }
  }
}
