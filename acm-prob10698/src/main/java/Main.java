import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
  private static final Pattern MATCH_RESULT_PATTERN =
      Pattern.compile("([a-zA-Z\\-]+)\\s+(\\d+)\\s+\\-\\s+(\\d+)\\s+([a-zA-Z\\-]+)");
  
  private static final int POINTS_FOR_WIN = 3;
  private static final int POINTS_FOR_DRAW = 1;
  private static final String NOT_APPLICABLE_STR = "N/A";

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);
    StringBuilder resultBuilder = new StringBuilder();

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine().trim();
      String[] strArray = line.split("\\s+");

      int numberOfTeams = Integer.parseInt(strArray[0]);
      int numberOfMatches = Integer.parseInt(strArray[1]);

      if (numberOfTeams == 0 && numberOfMatches == 0) {
        break;
      }

      Map<String, Team> teamMap = new HashMap<>();

      for (int i = 0; i < numberOfTeams; i++) {
        String name = scanner.nextLine();
        Team team = new Team(name);
        teamMap.put(name, team);
      }

      for (int i = 0; i < numberOfMatches; i++) {
        String resultLine = scanner.nextLine();
        Matcher matcher = MATCH_RESULT_PATTERN.matcher(resultLine);

        if (matcher.matches()) {
          String firstTeamName = matcher.group(1);
          int firstTeamGoals = Integer.parseInt(matcher.group(2));
          int secondTeamGoals = Integer.parseInt(matcher.group(3));
          String secondTeamName = matcher.group(4);

          Team firstTeam = teamMap.get(firstTeamName);
          Team secondTeam = teamMap.get(secondTeamName);

          if (firstTeamGoals > secondTeamGoals) {
            updateTeams(firstTeam, firstTeamGoals, secondTeam, secondTeamGoals);
          } else if (firstTeamGoals < secondTeamGoals) {
            updateTeams(secondTeam, secondTeamGoals, firstTeam, firstTeamGoals);
          } else {
            updateTeamsForDraw(firstTeam, secondTeam, firstTeamGoals);
          }
        }
      }

      List<Team> teamList = new ArrayList<>(teamMap.values());
      Collections.sort(teamList, new TeamComparator());
      generateOutput(teamList, resultBuilder);
    }

    scanner.close();

    // remove the extra blank line, if present
    int len = resultBuilder.length();
    String result;

    if (len > 0) {
      result = resultBuilder.substring(0, len - 1);
    } else {
      result = resultBuilder.toString();
    }

    outStream.print(result);
  }

  private static void updateTeams(Team winningTeam, int winningTeamGoals, Team losingTeam,
      int losingTeamGoals) {
    winningTeam.incrementNumberOfMatchesPlayed();
    winningTeam.incrementNumberOfMatchesWon();
    winningTeam.addToNumberOfGoalsScored(winningTeamGoals);
    winningTeam.addToNumberOfGoalsSuffered(losingTeamGoals);
    winningTeam.addToNumberOfPoints(POINTS_FOR_WIN);

    losingTeam.incrementNumberOfMatchesPlayed();
    losingTeam.addToNumberOfGoalsScored(losingTeamGoals);
    losingTeam.addToNumberOfGoalsSuffered(winningTeamGoals);
  }

  private static void updateTeamsForDraw(Team firstTeam, Team secondTeam, int numberOfGoals) {
    List<Team> teamList = Arrays.asList(firstTeam, secondTeam);

    for (Team team : teamList) {
      team.incrementNumberOfMatchesPlayed();
      team.addToNumberOfGoalsScored(numberOfGoals);
      team.addToNumberOfGoalsSuffered(numberOfGoals);
      team.addToNumberOfPoints(POINTS_FOR_DRAW);
    }
  }

  private static void generateOutput(List<Team> teamList, StringBuilder resultBuilder) {
    if (teamList.size() == 0) {
      return;
    }

    Team[] teamArray = teamList.toArray(new Team[teamList.size()]);
    resultBuilder.append(String.format("%2d. %s%n", 1, teamArray[0]));

    for (int i = 1; i < teamArray.length; i++) {
      Team previousTeam = teamArray[i - 1];
      Team currentTeam = teamArray[i];

      if (previousTeam.compareTo(currentTeam) == 0) {
        resultBuilder.append(String.format("    %s%n", currentTeam));
      } else {
        resultBuilder.append(String.format("%2d. %s%n", i + 1, currentTeam));
      }
    }

    resultBuilder.append("\n");
  }

  private static class Team implements Comparable<Team> {
    private final String name;
    private int numberOfPoints;
    private int numberOfGoalsScored;
    private int numberOfGoalsSuffered;
    private int numberOfMatchesPlayed;
    private int numberOfMatchesWon;

    public Team(String name) {
      this.name = Objects.requireNonNull(name);
    }

    public String getName() {
      return name;
    }

    public int getNumberOfPoints() {
      return numberOfPoints;
    }

    public void addToNumberOfPoints(int n) {
      numberOfPoints += n;
    }

    public int getNumberOfGoalsScored() {
      return numberOfGoalsScored;
    }

    public void addToNumberOfGoalsScored(int n) {
      numberOfGoalsScored += n;
    }

    public int getNumberOfGoalsSuffered() {
      return numberOfGoalsSuffered;
    }

    public void addToNumberOfGoalsSuffered(int n) {
      numberOfGoalsSuffered += n;
    }

    public int getNumberOfMatchesPlayed() {
      return numberOfMatchesPlayed;
    }

    public void incrementNumberOfMatchesPlayed() {
      numberOfMatchesPlayed++;
    }

    public int getNumberOfMatchesWon() {
      return numberOfMatchesWon;
    }

    public void incrementNumberOfMatchesWon() {
      numberOfMatchesWon++;
    }

    public int getGoalDifference() {
      return numberOfGoalsScored - numberOfGoalsSuffered;
    }

    public String getPercentageOfEarnedPointsFormatted() {
      if (numberOfMatchesPlayed == 0) {
        return NOT_APPLICABLE_STR;
      }

      double result =
          ((double) numberOfPoints / ((double) (numberOfMatchesPlayed * POINTS_FOR_WIN))) * 100.0;

      return String.format("%3.2f", result);
    }

    @Override
    public int compareTo(Team otherTeam) {
      if (numberOfPoints < otherTeam.getNumberOfPoints()) {
        return 1;
      }

      if (numberOfPoints > otherTeam.getNumberOfPoints()) {
        return -1;
      }

      if (getGoalDifference() < otherTeam.getGoalDifference()) {
        return 1;
      }

      if (getGoalDifference() > otherTeam.getGoalDifference()) {
        return -1;
      }

      if (numberOfGoalsScored < otherTeam.getNumberOfGoalsScored()) {
        return 1;
      }

      if (numberOfGoalsScored > otherTeam.getNumberOfGoalsScored()) {
        return -1;
      }

      return 0;
    }

    @Override
    public String toString() {
      return String.format("%15s %3d %3d %3d %3d %3d %6s", name, numberOfPoints,
          numberOfMatchesPlayed, numberOfGoalsScored, numberOfGoalsSuffered, getGoalDifference(),
          getPercentageOfEarnedPointsFormatted());
    }
  }

  private static class TeamComparator implements Comparator<Team> {
    @Override
    public int compare(Team firstTeam, Team secondTeam) {
      int result = firstTeam.compareTo(secondTeam);

      if (result != 0) {
        return result;
      }

      return firstTeam.getName().toLowerCase().compareTo(secondTeam.getName().toLowerCase());
    }
  }
}
