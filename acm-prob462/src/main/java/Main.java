import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
  private static final int NUMBER_OF_CARDS = 13;
  private static final Card[] CARDS_IN_HAND = new Card[NUMBER_OF_CARDS];
  private static final Map<Character, List<Card>> SUIT_MAP = new HashMap<>();
  private static final char[] SUIT_NAMES = {'S', 'D', 'H', 'C'};
  
  static {
    for (int i = 0; i < CARDS_IN_HAND.length; i++) {
      CARDS_IN_HAND[i] = new Card();
    }
    
    for (char c : SUIT_NAMES) {
      SUIT_MAP.put(c, new ArrayList<Card>());
    }
  }
  
  public static void main(String... args) {
    Scanner scanner = new Scanner(System.in);
    
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      parseLine(line);
      print();
    }
    
    scanner.close();
  }
  
  private static void parseLine(String line) {
    clearSuitMap();
    
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
  
  private static void clearSuitMap() {    
    for (List<Card> cardList :  SUIT_MAP.values()) {
      cardList.clear();
    }
  }
  
  private static void addCardToSuitMap(Card card) {
    char s = card.getSuit();
    List<Card> cardList = SUIT_MAP.get(s);
    
    if (cardList == null) {
      throw new RuntimeException(String.format("invalid suit : %c", s));
    }
    
    cardList.add(card);
  }
  
  private static void print() {
    System.out.println(Arrays.deepToString(CARDS_IN_HAND));
    System.out.println(SUIT_MAP);
    System.out.println();
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
