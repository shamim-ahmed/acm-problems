import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
  private static final int NUMBER_OF_CARDS = 52;
  private static final int NUMBER_OF_PLAYERS = 4;
  private static final int CARDS_PER_HAND = NUMBER_OF_CARDS / NUMBER_OF_PLAYERS;
  private static final String INPUT_TERMINATOR = "#";
  private static final String LINE_SEPARATOR = "\n";
  private static final Map<Character, Integer> SUIT_VALUE_MAP = new HashMap<>();
  private static final Map<Character, Integer> RANK_VALUE_MAP = new HashMap<>();

  private static final Card[][] BRIDGE_HANDS = new Card[NUMBER_OF_PLAYERS][CARDS_PER_HAND];

  static {
    SUIT_VALUE_MAP.put('C', 0);
    SUIT_VALUE_MAP.put('D', 1);
    SUIT_VALUE_MAP.put('S', 2);
    SUIT_VALUE_MAP.put('H', 3);

    RANK_VALUE_MAP.put('2', 2);
    RANK_VALUE_MAP.put('3', 3);
    RANK_VALUE_MAP.put('4', 4);
    RANK_VALUE_MAP.put('5', 5);
    RANK_VALUE_MAP.put('6', 6);
    RANK_VALUE_MAP.put('7', 7);
    RANK_VALUE_MAP.put('8', 8);
    RANK_VALUE_MAP.put('9', 9);
    RANK_VALUE_MAP.put('T', 10);
    RANK_VALUE_MAP.put('J', 11);
    RANK_VALUE_MAP.put('Q', 12);
    RANK_VALUE_MAP.put('K', 13);
    RANK_VALUE_MAP.put('A', 14);

    for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
      for (int j = 0; j < CARDS_PER_HAND; j++) {
        BRIDGE_HANDS[i][j] = new Card();
      }
    }
  }

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    StringBuilder resultBuilder = new StringBuilder();
    Scanner scanner = new Scanner(inputStream);

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine().trim();

      if (line.equals(INPUT_TERMINATOR)) {
        break;
      }

      char c = line.charAt(0);

      String firstLine = scanner.nextLine();
      String secondLine = scanner.nextLine();
      String combinedLine = firstLine + secondLine;

      int playerIndex = getPlayerFromChar(c).ordinal();

      for (int k = 0; k < 4; k++) {
        playerIndex = (playerIndex + 1) % NUMBER_OF_PLAYERS;
        Card[] currentHand = BRIDGE_HANDS[playerIndex];
        int cardIndex = 0;

        for (int i = 2 * k; i < 2 * NUMBER_OF_CARDS; i += 8) {
          char s = combinedLine.charAt(i);
          char r = combinedLine.charAt(i + 1);
          Card card = currentHand[cardIndex++];
          card.setSuit(s);
          card.setRank(r);
        }
      }

      for (Player player : Player.values()) {
        Card[] handOfCards = BRIDGE_HANDS[player.ordinal()];
        Arrays.sort(handOfCards);

        resultBuilder.append(player.toString()).append(": ");

        for (int i = 0; i < handOfCards.length; i++) {
          resultBuilder.append(handOfCards[i].getSuit()).append(handOfCards[i].getRank());

          if (i != handOfCards.length - 1) {
            resultBuilder.append(' ');
          }
        }

        resultBuilder.append(LINE_SEPARATOR);
      }
    }

    outputStream.print(resultBuilder.toString());
    scanner.close();
  }

  private static Player getPlayerFromChar(char p) {
    Player player = null;

    switch (p) {
      case 'S':
        player = Player.S;
        break;
      case 'W':
        player = Player.W;
        break;
      case 'N':
        player = Player.N;
        break;
      case 'E':
        player = Player.E;
        break;
      default:
        throw new IllegalArgumentException(String.format("invalid player : %c", p));
    }

    return player;
  }

  private static class Card implements Comparable<Card> {
    private char suit;
    private char rank;

    public char getSuit() {
      return suit;
    }

    public void setSuit(char suit) {
      this.suit = suit;
    }

    public char getRank() {
      return rank;
    }

    public void setRank(char rank) {
      this.rank = rank;
    }

    @Override
    public int compareTo(Card otherCard) {
      int result = SUIT_VALUE_MAP.get(suit) - SUIT_VALUE_MAP.get(otherCard.suit);

      if (result == 0) {
        result = RANK_VALUE_MAP.get(rank) - RANK_VALUE_MAP.get(otherCard.rank);
      }

      return result;
    }
  }

  private enum Player {
    S, W, N, E
  }
}
