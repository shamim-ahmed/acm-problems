import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
  private static final int NUMBER_OF_CARDS = 13;
  private static final char[] SUIT_NAMES = { 'S', 'D', 'H', 'C' };

  private static final Card[] CARDS_IN_HAND = new Card[NUMBER_OF_CARDS];
  private static final Map<Character, List<Card>> SUIT_CARD_MAP = new HashMap<>();
  private static final Map<Character, Boolean> SUIT_STOPPED_MAP = new HashMap<>();

  static {
    for (int i = 0; i < CARDS_IN_HAND.length; i++) {
      CARDS_IN_HAND[i] = new Card();
    }

    for (char s : SUIT_NAMES) {
      SUIT_CARD_MAP.put(s, new ArrayList<Card>());
    }

    for (char s : SUIT_NAMES) {
      SUIT_STOPPED_MAP.put(s, Boolean.FALSE);
    }
  }

  public static void main(String... args) {
    processInput(System.in, System.out);
  }
  
  public static void processInput(final InputStream inputStream, final PrintStream outputStream) {
    Scanner scanner = new Scanner(inputStream);

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine().trim();
      parseLine(line);
      String result = evaluateHand();
      outputStream.println(result);
    }

    scanner.close();
  }

  private static void parseLine(String line) {
    clearMaps();

    StringTokenizer tokenizer = new StringTokenizer(line, " ", false);

    if (tokenizer.countTokens() != NUMBER_OF_CARDS) {
      throw new IllegalArgumentException(String.format("invalid input %s", line));
    }

    int i = 0;

    while (tokenizer.hasMoreTokens()) {
      String token = tokenizer.nextToken();
      char r = token.charAt(0);
      char s = token.charAt(1);

      Card card = CARDS_IN_HAND[i];
      card.setRank(r);
      card.setSuit(s);
      addCardToSuitMap(card);
      i++;
    }
  }

  private static void clearMaps() {
    for (List<Card> cardList : SUIT_CARD_MAP.values()) {
      cardList.clear();
    }

    for (char s : SUIT_NAMES) {
      SUIT_STOPPED_MAP.put(s, Boolean.FALSE);
    }
  }

  private static void addCardToSuitMap(Card card) {
    char s = card.getSuit();
    List<Card> cardList = SUIT_CARD_MAP.get(s);

    if (cardList == null) {
      throw new RuntimeException(String.format("invalid suit : %c", s));
    }

    cardList.add(card);
  }

  private static String evaluateHand() {
    int value = 0;

    for (Card card : CARDS_IN_HAND) {
      char r = card.getRank();
      char s = card.getSuit();
      List<Card> cardsInSuite = SUIT_CARD_MAP.get(s);

      if (r == 'A') {
        // rule 1
        value += 4;

        // stop rule
        SUIT_STOPPED_MAP.put(s, Boolean.TRUE);
      } else if (r == 'K') {
        // rule 1
        value += 3;

        if (cardsInSuite.size() <= 1) {
          // rule 2
          value -= 1;
        } else {
          // stop rule
          SUIT_STOPPED_MAP.put(s, Boolean.TRUE);
        }
      } else if (r == 'Q') {
        // rule 1
        value += 2;

        if (cardsInSuite.size() <= 2) {
          // rule 3
          value -= 2;
        } else {
          // stop rule
          SUIT_STOPPED_MAP.put(s, Boolean.TRUE);
        }
      } else if (r == 'J') {
        // rule 1
        value += 1;

        if (cardsInSuite.size() <= 3) {
          // rule 4
          value -= 3;
        }
      }
    }

    // now apply additional rules
    int valueWithBonus = value;

    for (List<Card> cardList : SUIT_CARD_MAP.values()) {
      switch (cardList.size()) {
        case 2:
          // rule 5
          valueWithBonus += 1;
          break;
        case 1:
          // rule 6
          valueWithBonus += 2;
          break;
        case 0:
          // rule 7
          valueWithBonus += 2;
          break;
      }
    }

    // now decide what to produce as output
    String output = null;
    
    if (valueWithBonus < 14) {
      output = "PASS";
    } else {
      if (value >= 16) {
        // check for no trump option
        boolean allStopped = true;

        for (char s : SUIT_NAMES) {
          if (!SUIT_STOPPED_MAP.get(s)) {
            allStopped = false;
            break;
          }
        }

        if (allStopped) {
          output = "BID NO-TRUMP";
        }
      }
      
      if (output == null) {
        // BID option 
        // fine the suit with most cards
        char resultSuite = 0;
        int max = -1;
        
        for (char s : SUIT_NAMES) {
          int n = SUIT_CARD_MAP.get(s).size();
          
          if (n > max) {
            max = n;
            resultSuite = s;
          }
        }
        
        output = String.format("BID %s", resultSuite);
      }
    }
    
    return output;
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
