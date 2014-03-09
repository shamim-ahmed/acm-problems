import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
  public static void main(String... args) {
    Scanner scanner = new Scanner(System.in);
    int rfpCount = 0;
    boolean done = false;

    while (!done) {
      String startingLine = scanner.nextLine();
      StringTokenizer tokenizer = new StringTokenizer(startingLine, " ", false);
      int n = Integer.parseInt(tokenizer.nextToken());
      int p = Integer.parseInt(tokenizer.nextToken());

      if (n == 0 && p == 0) {
        done = true;
        continue;
      }

      rfpCount++;

      // this is the blank line between a pair of RFP outputs
      if (rfpCount > 1) {
        System.out.println();
      }

      // skip lines containing the name of the requirements
      for (int i = 1; i <= n; i++) {
        scanner.nextLine();
      }

      List<ProposalData> proposalList = new ArrayList<>();

      // read each proposal specification one by one
      for (int i = 1; i <= p; i++) {
        String proposalName = scanner.nextLine();
        StringTokenizer proposalTokenizer = new StringTokenizer(scanner.nextLine(), " ", false);
        BigDecimal cost = new BigDecimal(proposalTokenizer.nextToken());
        int numberOfRequirementsMet = Integer.parseInt(proposalTokenizer.nextToken());
        proposalList.add(new ProposalData(proposalName, cost, numberOfRequirementsMet));

        // skip lines containing the names of met requirements for a particular
        // proposal
        for (int j = 1; j <= numberOfRequirementsMet; j++) {
          scanner.nextLine();
        }
      }

      // sort the proposals
      Collections.sort(proposalList);
      ProposalData winningProposal = proposalList.get(0);

      System.out.printf("RFP #%d%n", rfpCount);
      System.out.printf("%s%n", winningProposal.getProposalName());
    }

    scanner.close();
  }

  private static class ProposalData implements Comparable<ProposalData> {
    private final String proposalName;
    private final BigDecimal cost;
    private final int numberOfRequirementsMet;

    public ProposalData(String proposalName, BigDecimal cost, int numberOfRequirementsMet) {
      this.proposalName = proposalName;
      this.cost = cost;
      this.numberOfRequirementsMet = numberOfRequirementsMet;
    }

    public String getProposalName() {
      return proposalName;
    }

    @Override
    public int compareTo(ProposalData otherData) {
      if (numberOfRequirementsMet > otherData.numberOfRequirementsMet) {
        return -1;
      }

      if (numberOfRequirementsMet < otherData.numberOfRequirementsMet) {
        return 1;
      }

      // if we come here, then numberOfRequirementsMet has the same value for
      // both objects
      int costCompValue = cost.compareTo(otherData.cost);

      if (costCompValue < 0) {
        return -1;
      }

      if (costCompValue > 0) {
        return 1;
      }

      return 0;
    }
  }
}
