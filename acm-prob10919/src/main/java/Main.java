import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inputStream, PrintStream outputStream) {
    StringBuilder resultBuilder = new StringBuilder();
    Scanner scanner = new Scanner(inputStream);

    while (scanner.hasNextInt()) {
      int numberOfCourses = scanner.nextInt();

      if (numberOfCourses == 0) {
        break;
      }

      int numberOfCategories = scanner.nextInt();
      int[] requiredCourseCounts = new int[numberOfCategories];
      int[] actualCourseCounts = new int[numberOfCategories];

      Map<Integer, Set<Integer>> courseCategoryMap = new HashMap<>();

      for (int i = 0; i < numberOfCourses; i++) {
        int course = scanner.nextInt();
        courseCategoryMap.put(course, new TreeSet<Integer>());
      }

      for (int i = 0; i < numberOfCategories; i++) {
        int courseCountInCategory = scanner.nextInt();
        requiredCourseCounts[i] = scanner.nextInt();

        for (int j = 0; j < courseCountInCategory; j++) {
          int course = scanner.nextInt();
          Set<Integer> categorySet = courseCategoryMap.get(course);

          if (categorySet != null) {
            categorySet.add(i);
          }
        }
      }

      for (Set<Integer> categorySet : courseCategoryMap.values()) {
        for (Integer category : categorySet) {
          actualCourseCounts[category]++;
        }
      }

      String result = "yes";

      for (int i = 0; i < numberOfCategories; i++) {
        if (requiredCourseCounts[i] > actualCourseCounts[i]) {
          result = "no";
          break;
        }
      }

      resultBuilder.append(result).append("\n");
    }

    scanner.close();
    outputStream.print(resultBuilder.toString());
  }
}
