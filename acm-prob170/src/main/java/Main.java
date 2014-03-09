import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
  private static final int RANK_COUNT = 13;
  private static final String INPUT_END_MARKER = "#";

  private enum Suite {
    HEART, DIAMOND, SPADE, CLUBS;

    @Override
    public String toString() {
      return super.toString().substring(0, 1);
    }
  }

  private enum Rank {
    ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(
        13);

    private final int value;

    Rank(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }

    @Override
    public String toString() {
      String result = null;

      switch (value) {
        case 1:
        case 10:
        case 11:
        case 12:
        case 13:
          result = super.toString().substring(0, 1);
          break;
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
        case 8:
        case 9:
          result = Integer.toString(value);
          break;
      }

      return result;
    }
  }

  public static void main(String... args) {
    Scanner scanner = new Scanner(System.in);
    String line = scanner.nextLine();

    while (!line.equals(INPUT_END_MARKER)) {
      // process one deck of cards
      Stack<Card> cardStack = new Stack<Card>();

      for (int i = 0; i < 4; i++) {
        StringTokenizer tokenizer = new StringTokenizer(line, " ", false);

        while (tokenizer.hasMoreTokens()) {
          String token = tokenizer.nextToken();
          Card card = convertToCard(token);
          cardStack.push(card);
        }

        line = scanner.nextLine();
      }

      processInput(cardStack);
    }

    // final cleanup
    scanner.close();
  }

  private static void processInput(Stack<Card> cardStack) {
    List<List<Card>> deck = new ArrayList<List<Card>>(RANK_COUNT + 1);

    for (int i = 0; i <= RANK_COUNT; i++) {
      if (i == 0) {
        deck.add(null);
      } else {
        deck.add(new ArrayList<Card>());
      }
    }

    for (int i = 1, n = cardStack.size(); i <= n; i++) {
      int p = i % RANK_COUNT;

      if (p == 0) {
        p = RANK_COUNT;
      }

      Card currentCard = cardStack.pop();
      deck.get(p).add(currentCard);
    }

    List<Card> pile = deck.get(RANK_COUNT);
    Card card = pile.remove(pile.size() - 1);
    boolean done = false;
    int count = 1;

    while (!done) {
      card.setExposed(true);
      int r = card.getRank().getValue();
      pile = deck.get(r);

      if (isPileExposed(pile)) {
        done = true;
        continue;
      }

      Card newCard = pile.remove(pile.size() - 1);
      pile.add(0, card);
      card = newCard;
      count++;
    }

    System.out.printf("%02d,%s%n", count, card);
  }

  private static boolean isPileExposed(List<Card> cards) {
    for (Card card : cards) {
      if (!card.isExposed()) {
        return false;
      }
    }

    return true;
  }

  private static Card convertToCard(String str) {
    Rank rank = getRank(str.charAt(0));
    Suite suite = getSuite(str.charAt(1));

    return new Card(rank, suite);
  }

  private static Rank getRank(char r) {
    Rank rank = null;

    switch (r) {
      case 'A':
        rank = Rank.ACE;
        break;
      case '2':
        rank = Rank.TWO;
        break;
      case '3':
        rank = Rank.THREE;
        break;
      case '4':
        rank = Rank.FOUR;
        break;
      case '5':
        rank = Rank.FIVE;
        break;
      case '6':
        rank = Rank.SIX;
        break;
      case '7':
        rank = Rank.SEVEN;
        break;
      case '8':
        rank = Rank.EIGHT;
        break;
      case '9':
        rank = Rank.NINE;
        break;
      case 'T':
        rank = Rank.TEN;
        break;
      case 'J':
        rank = Rank.JACK;
        break;
      case 'Q':
        rank = Rank.QUEEN;
        break;
      case 'K':
        rank = Rank.KING;
        break;
      default:
        throw new RuntimeException(String.format("Invalid rank %s", Character.toString(r)));
    }

    return rank;
  }

  private static Suite getSuite(char s) {
    Suite suite = null;

    switch (s) {
      case 'H':
        suite = Suite.HEART;
        break;
      case 'D':
        suite = Suite.DIAMOND;
        break;
      case 'S':
        suite = Suite.SPADE;
        break;
      case 'C':
        suite = Suite.CLUBS;
        break;
      default:
        throw new RuntimeException(String.format("Invalid suite %s", Character.toString(s)));
    }

    return suite;
  }

  private static class Card {
    private final Rank rank;
    private final Suite suite;
    private boolean exposed;

    public Card(Rank rank, Suite suite) {
      this.suite = suite;
      this.rank = rank;
      exposed = false;
    }

    public boolean isExposed() {
      return exposed;
    }

    public void setExposed(boolean exposed) {
      this.exposed = exposed;
    }

    public Suite getSuite() {
      return suite;
    }

    public Rank getRank() {
      return rank;
    }

    @Override
    public String toString() {
      return rank.toString().concat(suite.toString());
    }
  }
}
