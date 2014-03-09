package edu.buet.cse.acmtrial;

import java.util.Arrays;
import java.util.List;

public class NQueensTrial {
  public static void main(String... args) {
	NQueensSolver solver = new NQueensSolver(8);
	List<Coordinate[]> solutions = solver.findSolution();

	for (Coordinate[] solution : solutions) {
	  System.out.println(Arrays.deepToString(solution));
	}
  }
}
