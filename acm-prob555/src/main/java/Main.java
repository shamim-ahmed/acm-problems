import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
  private static final int NUMBER_OF_CARDS = 52;
  private static final int NUMBER_OF_PLAYERS = 4;
  private static final String INPUT_TERMINATOR = "#";
  private static final Player[] PLAYER_ARRAY = Player.values();

  private static final Card[] CARD_DECK = new Card[NUMBER_OF_CARDS];
  private static final Map<Player, List<Card>> CARD_MAP = new HashMap<>();
  
  static {
    for (int i = 0; i < CARD_DECK.length; i++) {
      CARD_DECK[i] = new Card();
    }
    
    for (Player p : Player.values()) {  
      CARD_MAP.put(p, new ArrayList<Card>());
    }
  }
  
  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    Scanner scanner = new Scanner(inputStream);

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine().trim();

      if (line.equals(INPUT_TERMINATOR)) {
        break;
      }
      
      clearHands();
      char c = line.charAt(0);

      String firstLine = scanner.nextLine();
      String secondLine = scanner.nextLine();
      String combinedLine = firstLine + secondLine;
      
      int playerIndex = getPlayerFromChar(c).ordinal();
      int i = 0;
      int cardIndex = 0;
      
      while (i < 2 * NUMBER_OF_CARDS) {
        playerIndex = (playerIndex + 1) % NUMBER_OF_PLAYERS;
        Player player = PLAYER_ARRAY[playerIndex];
        List<Card> cardList = CARD_MAP.get(player);
        
        // determine the suit and rank for the current position
        char s = combinedLine.charAt(i++);
        char r = combinedLine.charAt(i++);
        Suit suit = getSuitFromChar(s);
        Rank rank = getRankFromChar(r);
        
        // deal the card to a player
        Card card = CARD_DECK[cardIndex++];
        card.setSuit(suit);
        card.setRank(rank);
        cardList.add(card);
      }
      
     
      for (Player player : Player.values()) {
        List<Card> cardList = CARD_MAP.get(player);
        Collections.sort(cardList);
     
        StringBuilder resultBuilder = new StringBuilder();
        
        for (Card card : cardList) {
          resultBuilder.append(card.toString()).append(" ");
        }
        
        outputStream.printf("%s: %s%n", player, resultBuilder.toString().trim());
      }
    }
    
    scanner.close();
  }
  
  private static void clearHands() {
    for (List<Card> cardList : CARD_MAP.values()) {
      cardList.clear();
    }
  }
   
  private static Suit getSuitFromChar(char s) {
    Suit suit = null;
    
    switch (s) {
      case 'C':
        suit = Suit.CLUB;
        break;
      case 'D':
        suit = Suit.DIAMOND;
        break;
      case 'S':
        suit = Suit.SPADE;
        break;
      case 'H':
        suit = Suit.HEART;
        break;
      default:
        throw new IllegalArgumentException(String.format("Invalid suit : %c", s));
    }
    
    return suit;
  }
  
  private static Rank getRankFromChar(char r) {
    Rank rank = null;
    
    switch (r) {
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
      case 'A':
        rank = Rank.ACE;
        break;
      default:
        throw new IllegalArgumentException(String.format("Invalid rank : %c", r));
    }
    
    return rank;
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
    private Suit suit;
    private Rank rank;

    public Suit getSuit() {
      return suit;
    }

    public void setSuit(Suit suit) {
      this.suit = suit;
    }

    public Rank getRank() {
      return rank;
    }

    public void setRank(Rank rank) {
      this.rank = rank;
    }
    
    @Override
    public int compareTo(Card otherCard) {
      int result = suit.ordinal() - otherCard.suit.ordinal();
      
      if (result == 0) {
        result = rank.ordinal() - otherCard.rank.ordinal();
      }
      
      return result;
    }

    @Override
    public String toString() {
      return String.format("%s%s", suit, rank);
    }
  }
  
  private enum Player {
    S, W, N, E
  }

  private enum Suit {
    CLUB("C"), DIAMOND("D"), SPADE("S"), HEART("H");

    private String value;

    Suit(String value) {
      this.value = value;
    }
    
    @Override
    public String toString() {
      return value;
    }
  }

  private enum Rank {
    TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), TEN("T"), 
    JACK("J"), QUEEN("Q"), KING("K"), ACE("A");

    private String value;

    Rank(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return value;
    }
  }
}
