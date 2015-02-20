import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
  private static final int NUMBER_OF_CARDS = 52;
  private static final int NUMBER_OF_SUITS = 4;
  private static final int CARDS_PER_SUIT = NUMBER_OF_CARDS / NUMBER_OF_SUITS;
  private static final Card[] ORDERED_DECK = new Card[NUMBER_OF_CARDS];
  private static final String LINE_SEPARATOR = "\n";

  static {
    for (Suit s : Suit.values()) {
      int i = s.ordinal();

      for (Rank r : Rank.values()) {
        int j = r.ordinal();

        Card c = new Card(r, s);
        ORDERED_DECK[i * CARDS_PER_SUIT + j] = c;
      }
    }
  }

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    StringBuilder resultBuilder = new StringBuilder();

    Scanner scanner = new Scanner(inputStream);
    int numberOfCases = scanner.nextInt();

    // read the first blank line
    scanner.nextLine();

    for (int i = 0; i < numberOfCases; i++) {
      int n = scanner.nextInt();
      int[][] values = new int[n][NUMBER_OF_CARDS];

      for (int j = 0; j < n; j++) {
        for (int k = 0; k < NUMBER_OF_CARDS; k++) {
          values[j][k] = scanner.nextInt();
        }
      }

      scanner.nextLine();

      // now we have to figure out which moves to apply
      Card[][] tempDecks = new Card[2][NUMBER_OF_CARDS];
      int p = 0;
      Card[] initialDeck = ORDERED_DECK;
      Card[] resultDeck = tempDecks[p];

      String line = scanner.nextLine();

      while (!isEmpty(line)) {
        int m = Integer.parseInt(line);
        int[] moves = values[m - 1];
        applyMoves(initialDeck, resultDeck, moves);

        p = (p + 1) % 2;
        initialDeck = resultDeck;
        resultDeck = tempDecks[p];

        if (scanner.hasNextLine()) {
          line = scanner.nextLine();
        } else {
          line = null;
        }
      }

      for (int q = 0; q < initialDeck.length; q++) {
        resultBuilder.append(initialDeck[q]).append(LINE_SEPARATOR);
      }

      if (i != numberOfCases - 1) {
        resultBuilder.append(LINE_SEPARATOR);
      }
    }

    outputStream.print(resultBuilder);

    // cleanup
    scanner.close();
  }

  private static void applyMoves(Card[] initialDeck, Card[] resultDeck, int[] moves) {
    for (int j = 0; j < moves.length; j++) {
      int i = moves[j] - 1;
      resultDeck[j] = initialDeck[i];
    }
  }

  private static boolean isEmpty(String s) {
    return s == null || s.trim().equals("");
  }

  private static class Card {
    private final Rank rank;
    private final Suit suit;

    Card(Rank rank, Suit suit) {
      this.rank = rank;
      this.suit = suit;
    }

    @Override
    public String toString() {
      return String.format("%s of %s", rank, suit);
    }
  }

  private enum Rank {
    TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"), JACK("Jack"), QUEEN(
        "Queen"), KING("King"), ACE("Ace");

    private String str;

    Rank(String str) {
      this.str = str;
    }

    @Override
    public String toString() {
      return str;
    }
  }

  private enum Suit {
    CLUB("Clubs"), DIAMOND("Diamonds"), HEART("Hearts"), SPADE("Spades");

    private String str;

    Suit(String str) {
      this.str = str;
    }

    @Override
    public String toString() {
      return str;
    }
  }
}
