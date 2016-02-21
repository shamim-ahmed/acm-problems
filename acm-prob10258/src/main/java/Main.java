import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {
  private static final String CORRECT_STATUS = "C";
  private static final String INCORRECT_STATUS = "I";
  private static final int PENALTY_FOR_INCORRECT_SUBMISSION = 20;

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);
    StringBuilder resultBuilder = new StringBuilder();
    final int numberOfCases = Integer.parseInt(scanner.nextLine().trim());

    // discard first blank line
    scanner.nextLine();

    for (int i = 0; i < numberOfCases; i++) {
      Map<Integer, Team> teamMap = new HashMap<>();
      String line = scanner.nextLine().trim();

      while (!isBlank(line)) {
        String[] strArray = line.split("\\s+");
        int teamNumber = Integer.parseInt(strArray[0]);
        int problemNumber = Integer.parseInt(strArray[1]);
        int submissionTime = Integer.parseInt(strArray[2]);
        String status = strArray[3];

        // create and store team when necessary
        Team team = teamMap.get(teamNumber);

        if (team == null) {
          team = new Team(teamNumber);
          teamMap.put(teamNumber, team);
        }

        team.updateForSubmission(problemNumber, submissionTime, status);

        // read the next line
        if (scanner.hasNextLine()) {
          line = scanner.nextLine().trim();
        } else {
          line = null;
        }
      }

      // sort the teams and compute result
      List<Team> teamList = new ArrayList<>(teamMap.values());
      Collections.sort(teamList);

      for (Team team : teamList) {
        resultBuilder.append(String.format("%d %d %d%n", team.getNumber(), team.getSolutionCount(),
            team.getPenaltyPoints()));
      }

      if (i < numberOfCases - 1) {
        resultBuilder.append("\n");
      }
    }

    scanner.close();
    outStream.print(resultBuilder.toString());
  }

  private static boolean isBlank(String line) {
    return line == null || line.trim().equals("");
  }

  private static class Team implements Comparable<Team> {
    private final int number;
    private int solutionCount;
    private int penaltyPoints;
    private Map<Integer, Integer> incorrectSubmissionMap = new HashMap<>();
    private Set<Integer> solvedProblemSet = new TreeSet<>();

    public Team(int number) {
      this.number = number;
    }

    public int getNumber() {
      return number;
    }

    public int getSolutionCount() {
      return solutionCount;
    }

    public int getPenaltyPoints() {
      return penaltyPoints;
    }

    public void updateForSubmission(int problemNumber, int submissionTime, String status) {
      if (solvedProblemSet.contains(problemNumber)) {
        return;
      }

      if (CORRECT_STATUS.equals(status)) {
        Integer c = incorrectSubmissionMap.remove(problemNumber);

        if (c == null) {
          c = 0;
        }

        penaltyPoints += c * PENALTY_FOR_INCORRECT_SUBMISSION + submissionTime;
        solutionCount++;
        solvedProblemSet.add(problemNumber);
      } else if (INCORRECT_STATUS.equals(status)) {
        Integer c = incorrectSubmissionMap.get(problemNumber);

        if (c == null) {
          c = 0;
        }

        c++;
        incorrectSubmissionMap.put(problemNumber, c);
      }
    }

    @Override
    public int compareTo(Team otherTeam) {
      if (solutionCount < otherTeam.solutionCount) {
        return 1;
      }

      if (solutionCount > otherTeam.solutionCount) {
        return -1;
      }

      if (penaltyPoints < otherTeam.penaltyPoints) {
        return -1;
      }

      if (penaltyPoints > otherTeam.penaltyPoints) {
        return 1;
      }

      if (number < otherTeam.number) {
        return -1;
      }

      if (number > otherTeam.number) {
        return 1;
      }

      return 0;
    }
  }
}
