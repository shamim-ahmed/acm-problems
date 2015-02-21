import java.io.InputStream;
import java.io.PrintStream;
import java.util.Objects;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
  private static final int NUMBER_OF_CARDS = 78;
  private static final Card[] DECK_OF_CARDS = new Card[NUMBER_OF_CARDS];
  private static final String LINE_TERMINATOR = "\n";
  
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
    int n = Integer.parseInt(scanner.nextLine());
    
    for (int i = 0; i < n; i++) {
      int lineCount = Integer.parseInt(scanner.nextLine());   
      final Card[] cardsInHand = new Card[lineCount];
      
      for (int j = 0; j < lineCount; j++) {
        String name = scanner.nextLine();
        Card card = DECK_OF_CARDS[j];
        card.setName(name);
        cardsInHand[j] = card;
      }
      
      resultBuilder.append(String.format("Hand #%d%n", i + 1));
      String res = process(cardsInHand);
      resultBuilder.append(res).append(LINE_TERMINATOR);
      
      if (i < n - 1) {
        resultBuilder.append(LINE_TERMINATOR);
      }
    }
    
    outputStream.print(resultBuilder.toString());
    scanner.close();
  }
  
  private static void addCardToCurrentHand(String name, int index, Card[] currentCards) {
    
  }
  
  private static boolean isEmpty(String line) {
    return line == null || line.trim().equals("");
  }
  
  private static String process(Card[] currentCards) {
    int oudlerCount = 0;
    int sumOfPoints = 0;
    
    for (Card card : currentCards) {
      sumOfPoints += card.getValue();
                  
      if (card.isOudler()) {
        oudlerCount++;
      }
    }
    
    int requiredPoints;
    
    // Please note that all the values mentioned in problem description are multiplied by 10.
    // This is done in order to avoid floating point arithmetic
    switch (oudlerCount) {
      case 0:
        requiredPoints = 560;
        break;
      case 1:
        requiredPoints = 510;
        break;
      case 2:
        requiredPoints = 410;
        break;
      case 3:
        requiredPoints = 360;
        break;
      default:
        throw new RuntimeException(String.format("Invalid number of oudlers: %d", oudlerCount));
    }
    
    String result = null;
    String status = null;
    int difference = 0;
    
    if (sumOfPoints >= requiredPoints) {
      // the taker won 
      status = "won";
      difference = sumOfPoints - requiredPoints;
    } else {
      // the taker lost
      status = "lost";
      difference = requiredPoints - sumOfPoints;
    }
    
    if (difference % 10 == 0) {
      result = String.format("Game %s by %d point(s).", status, difference / 10);
    } else {
      result = String.format("Game %s by %.1f point(s).", status, difference / 10.0);
    }
    
    return result;
  }

  private static class Card {
    private static final String FOOL = "fool";
    private static final String TRUMP = "trumps";
    private static final String ONE = "one";
    private static final String TWENTY_ONE = "twenty-one";
    private static final String JACK = "jack";
    private static final String KNIGHT = "knight";
    private static final String QUEEN = "queen";
    private static final String KING = "king";
    
    private String name;
    private int value;
    private boolean oudler;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = Objects.requireNonNull(name);
      populateFields();
    }
    
    // Please note that all the values mentioned in problem description are multiplied by 10.
    // This is done in order to avoid floating point arithmetic.
    private void populateFields() {
      if (name.equals(FOOL)) {
        // fool is an oudler
        value = 45;
        oudler = true;
        return;
      }
      
      StringTokenizer tokenizer = new StringTokenizer(name, " ", false);
      String firstToken = tokenizer.nextToken();
      tokenizer.nextToken();
      String secondToken = tokenizer.nextToken();
      
      if (secondToken.equals(TRUMP)) {
        // we are dealing with a trump
        if (firstToken.equals(ONE) || firstToken.equals(TWENTY_ONE)) {
          // an oudler
          value = 45;
          oudler = true;
        } else {
          // an ordinary trump
          value = 5;
          oudler = false;
        }
      } else {
        // we are dealing with a card from a suit
        oudler = false;

        switch (firstToken) {
          case JACK:
            value = 15;
            break;
          case KNIGHT:
            value = 25;
            break;
          case QUEEN:
            value = 35;
            break;
          case KING:
            value = 45;
            break;
          default:
            value = 5;
            break;
        }
      }
    }

    public int getValue() {
      return value;
    }  
    
    public boolean isOudler() {
      return oudler;
    }    
    
    @Override
    public String toString() {
      return name;
    }
  }
}
