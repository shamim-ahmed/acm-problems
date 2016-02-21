import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
  private static final Pattern GAME_RESULT_PATTERN =
      Pattern.compile("([^#@]+)#([\\d]+)@([\\d]+)#([^#@]+)");

  private static final int POINTS_FOR_WIN = 3;
  private static final int POINTS_FOR_DRAW = 1;

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);
    StringBuilder resultBuilder = new StringBuilder();
    final int numberOfTournaments = Integer.parseInt(scanner.nextLine().trim());

    for (int i = 0; i < numberOfTournaments; i++) {
      String tournamentName = scanner.nextLine();
      final int numberOfTeams = Integer.parseInt(scanner.nextLine());
      Map<String, Team> teamMap = new HashMap<>();

      for (int j = 0; j < numberOfTeams; j++) {
        String teamName = scanner.nextLine();
        Team team = new Team(teamName);
        teamMap.put(teamName, team);
      }

      int numberOfGames = Integer.parseInt(scanner.nextLine().trim());

      for (int j = 0; j < numberOfGames; j++) {
        String line = scanner.nextLine();
        Matcher matcher = GAME_RESULT_PATTERN.matcher(line);

        if (matcher.matches()) {
          String firstTeamName = matcher.group(1);
          int firstTeamGoals = Integer.parseInt(matcher.group(2));
          int secondTeamGoals = Integer.parseInt(matcher.group(3));
          String secondTeamName = matcher.group(4);

          Team firstTeam = teamMap.get(firstTeamName);
          Team secondTeam = teamMap.get(secondTeamName);

          if (firstTeam == null || secondTeam == null) {
            continue;
          }

          if (firstTeamGoals > secondTeamGoals) {
            // first team won
            updateTeams(firstTeam, firstTeamGoals, secondTeam, secondTeamGoals);
          } else if (firstTeamGoals < secondTeamGoals) {
            // second team won
            updateTeams(secondTeam, secondTeamGoals, firstTeam, firstTeamGoals);
          } else {
            // there was a draw
            updateTeamsForDraw(firstTeam, secondTeam, firstTeamGoals);
          }
        }
      }

      // sort the teams, and compute result
      List<Team> teamList = new ArrayList<>(teamMap.values());
      Collections.sort(teamList);
      int rank = 0;

      resultBuilder.append(tournamentName).append("\n");

      for (Team team : teamList) {
        rank++;
        String line = String.format("%d) %s %dp, %dg (%d-%d-%d), %dgd (%d-%d)%n", rank,
            team.getName(), team.getPoints(), team.getNumberOfGames(), team.getNumberOfWins(),
            team.getNumberOfTies(), team.getNumberOfLosses(), team.getGoalDifference(),
            team.getNumberOfGoalsScored(), team.getNumberOfGoalsAgainst());

        resultBuilder.append(line);
      }

      // add a blank line between tournament results
      if (i < numberOfTournaments - 1) {
        resultBuilder.append("\n");
      }
    }

    scanner.close();
    outStream.print(resultBuilder.toString());
  }

  private static final void updateTeams(Team winningTeam, int winningTeamGoals, Team losingTeam,
      int losingTeamGoals) {
    // update fields fields of winning team
    winningTeam.incrementNumberOfGames();
    winningTeam.incrementNumberOfWins();
    winningTeam.addPoints(POINTS_FOR_WIN);
    winningTeam.addToNumberOfGoalsScored(winningTeamGoals);
    winningTeam.addToNumberOfGoalsAgainst(losingTeamGoals);

    // update fields of losing team
    losingTeam.incrementNumberOfGames();
    losingTeam.incrementNumberOfLosses();
    losingTeam.addToNumberOfGoalsScored(losingTeamGoals);
    losingTeam.addToNumberOfGoalsAgainst(winningTeamGoals);
  }

  private static final void updateTeamsForDraw(Team firstTeam, Team secondTeam, int numberOfGoals) {
    List<Team> teamList = Arrays.asList(firstTeam, secondTeam);

    for (Team team : teamList) {
      team.addPoints(POINTS_FOR_DRAW);
      team.incrementNumberOfGames();
      team.incrementNumberOfTies();
      team.addToNumberOfGoalsScored(numberOfGoals);
      team.addToNumberOfGoalsAgainst(numberOfGoals);
    }
  }

  private static class Team implements Comparable<Team> {
    private final String name;
    private int points;
    private int numberOfGames;
    private int numberOfWins;
    private int numberOfTies;
    private int numberOfLosses;
    private int numberOfGoalsScored;
    private int numberOfGoalsAgainst;

    public Team(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }

    public int getPoints() {
      return points;
    }

    private void addPoints(int n) {
      points += n;
    }

    public int getNumberOfGames() {
      return numberOfGames;
    }

    private void incrementNumberOfGames() {
      numberOfGames++;
    }

    public int getNumberOfWins() {
      return numberOfWins;
    }

    public void incrementNumberOfWins() {
      numberOfWins++;
    }

    public int getNumberOfTies() {
      return numberOfTies;
    }

    public void incrementNumberOfTies() {
      numberOfTies++;
    }

    public int getNumberOfLosses() {
      return numberOfLosses;
    }

    public void incrementNumberOfLosses() {
      numberOfLosses++;
    }

    public int getNumberOfGoalsScored() {
      return numberOfGoalsScored;
    }

    public void addToNumberOfGoalsScored(int n) {
      numberOfGoalsScored += n;
    }

    public int getNumberOfGoalsAgainst() {
      return numberOfGoalsAgainst;
    }

    public void addToNumberOfGoalsAgainst(int n) {
      numberOfGoalsAgainst += n;
    }

    public int getGoalDifference() {
      return numberOfGoalsScored - numberOfGoalsAgainst;
    }

    @Override
    public int compareTo(Team otherTeam) {
      // comparison based on points
      if (points < otherTeam.points) {
        return 1;
      }

      if (points > otherTeam.points) {
        return -1;
      }

      // comparison based on wins
      if (numberOfWins < otherTeam.numberOfWins) {
        return 1;
      }

      if (numberOfWins > otherTeam.numberOfWins) {
        return -1;
      }

      // comparison based on goal difference
      int goalDifference = getGoalDifference();
      int otherGoalDifference = otherTeam.getGoalDifference();

      if (goalDifference < otherGoalDifference) {
        return 1;
      }

      if (goalDifference > otherGoalDifference) {
        return -1;
      }

      // comparison based on most goals scored
      if (numberOfGoalsScored < otherTeam.numberOfGoalsScored) {
        return 1;
      }

      if (numberOfGoalsScored > otherTeam.numberOfGoalsScored) {
        return -1;
      }

      // comparison based on less games played.
      // Note the change in logic here.
      if (numberOfGames < otherTeam.numberOfGames) {
        return -1;
      }

      if (numberOfGames > otherTeam.numberOfGames) {
        return 1;
      }

      // comparison based on lexicographic order (but case insensitive)
      return name.toLowerCase().compareTo(otherTeam.name.toLowerCase());
    }
  }
}
