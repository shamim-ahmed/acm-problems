import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
  private static final int NUMBER_OF_CARDS = 52;
  private static final Card[] DECK_OF_CARDS = new Card[NUMBER_OF_CARDS];
  private static final Pattern CARD_PATTERN = Pattern.compile("[2-9TJQKA][CDHS]");
  private static final String LINE_SEPARATOR = "\n";

  static {
    for (int i = 0; i < DECK_OF_CARDS.length; i++) {
      DECK_OF_CARDS[i] = new Card();
    }
  }

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    StringBuilder resultBuilder = new StringBuilder();
    Scanner scanner = new Scanner(inputStream);

    // read the number of test cases
    int n = scanner.nextInt();

    // now read the card specifications for each test case
    for (int i = 0; i < n; i++) {
      Card[] currentDeck = new Card[NUMBER_OF_CARDS];

      for (int j = 0; j < NUMBER_OF_CARDS; j++) {
        String str = scanner.next(CARD_PATTERN);
        char r = str.charAt(0);
        char s = str.charAt(1);

        Card card = DECK_OF_CARDS[j];
        card.setRank(r);
        card.setSuit(s);

        currentDeck[j] = card;
      }

      String res = process(currentDeck, i + 1);
      resultBuilder.append(res).append(LINE_SEPARATOR);
    }

    outputStream.print(resultBuilder.toString());
    scanner.close();
  }

  private static String process(Card[] currentDeck, int caseNumber) {
    int y = 0;
    final int M = 25;

    int startIndex = NUMBER_OF_CARDS - M;
    int i = startIndex;

    for (int n = 0; n < 3; n++) {
      Card card = currentDeck[--i];
      int x = getValue(card);
      y += x;
      i -= (10 - x);
    }

    for (int j = 0; j < M; j++) {
      currentDeck[i + j] = currentDeck[startIndex + j];
    }

    Card resultCard = currentDeck[y - 1];
    String result = String.format("Case %d: %s", caseNumber, resultCard);
    return result;
  }

  private static int getValue(Card card) {
    int value = 0;
    char r = card.getRank();

    switch (r) {
      case '2':
      case '3':
      case '4':
      case '5':
      case '6':
      case '7':
      case '8':
      case '9':
        value = r - '0';
        break;
      case 'T':
      case 'J':
      case 'Q':
      case 'K':
      case 'A':
        value = 10;
        break;
      default:
        throw new RuntimeException(String.format("invalid rank : %c", r));
    }

    return value;
  }

  private static class Card {
    private char rank;
    private char suit;

    public char getRank() {
      return rank;
    }

    public void setRank(char rank) {
      this.rank = rank;
    }

    public char getSuit() {
      return suit;
    }

    public void setSuit(char suit) {
      this.suit = suit;
    }

    @Override
    public String toString() {
      return String.format("%c%c", rank, suit);
    }
  }
}
