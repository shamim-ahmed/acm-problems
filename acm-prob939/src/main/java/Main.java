import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
  private static final String GENE_STATUS_DOMINANT = "dominant";
  private static final String GENE_STATUS_RECESSIVE = "recessive";
  private static final String GENE_STATUS_NON_EXISTENT = "non-existent";

  public static void main(String... args) {
    processInput(System.in, System.out);
  }

  public static void processInput(InputStream inStream, PrintStream outStream) {
    Scanner scanner = new Scanner(inStream);
    Map<String, String> geneStatusMap = new TreeMap<>();
    Map<String, List<String>> parentMap = new TreeMap<>();

    // read the input
    int n = Integer.parseInt(scanner.nextLine().trim());

    for (int i = 0; i < n; i++) {
      String line = scanner.nextLine().trim();
      String[] values = line.split("\\s+");

      switch (values[1]) {
        case GENE_STATUS_DOMINANT:
        case GENE_STATUS_RECESSIVE:
        case GENE_STATUS_NON_EXISTENT: {
          geneStatusMap.put(values[0], values[1]);
          break;
        }

        default: {
          List<String> parentList = parentMap.get(values[1]);

          if (parentList == null) {
            parentList = new ArrayList<>();
            parentMap.put(values[1], parentList);
          }

          parentList.add(values[0]);
          break;
        }
      }
    }

    // Iterate over the parentMap until it is empty. The removal of entries is
    // performed within the inner loop. This approach is needed because we may
    // have data for multiple generations in input, and we cannot guarantee that
    // they will be in correct genealogical order.
    while (parentMap.size() > 0) {
      for (Iterator<Map.Entry<String, List<String>>> iter = parentMap.entrySet().iterator(); iter.hasNext();) {
        Map.Entry<String, List<String>> entry = iter.next();

        String child = entry.getKey();
        List<String> parentList = entry.getValue();

        if (parentList.size() < 2) {
          iter.remove();
          continue;
        }

        String firstParent = parentList.get(0);
        String secondParent = parentList.get(1);

        String firstGeneStatus = geneStatusMap.get(firstParent);
        String secondGeneStatus = geneStatusMap.get(secondParent);

        if (firstGeneStatus != null && secondGeneStatus != null) {
          if (firstGeneStatus.equals(GENE_STATUS_DOMINANT) && secondGeneStatus.equals(GENE_STATUS_DOMINANT)) {
            // dominant gene in both parents => dominant gene in child
            geneStatusMap.put(child, GENE_STATUS_DOMINANT);
          } else if ((firstGeneStatus.equals(GENE_STATUS_DOMINANT) && secondGeneStatus.equals(GENE_STATUS_RECESSIVE))
              || (firstGeneStatus.equals(GENE_STATUS_RECESSIVE) && secondGeneStatus.equals(GENE_STATUS_DOMINANT))) {
            // dominant gene in one parent and recessive gene in the other =>
            // dominant gene in child
            geneStatusMap.put(child, GENE_STATUS_DOMINANT);
          } else if ((firstGeneStatus.equals(GENE_STATUS_DOMINANT) && secondGeneStatus.equals(GENE_STATUS_NON_EXISTENT))
              || (firstGeneStatus.equals(GENE_STATUS_NON_EXISTENT) && secondGeneStatus.equals(GENE_STATUS_DOMINANT))) {
            // dominant gene in one parent and non-existent in the other =>
            // recessive gene in child
            geneStatusMap.put(child, GENE_STATUS_RECESSIVE);
          } else if (firstGeneStatus.equals(GENE_STATUS_RECESSIVE) && secondGeneStatus.equals(GENE_STATUS_RECESSIVE)) {
            // recessive gene in both parents => recessive gene in child
            geneStatusMap.put(child, GENE_STATUS_RECESSIVE);
          } else if ((firstGeneStatus.equals(GENE_STATUS_RECESSIVE)
              && secondGeneStatus.equals(GENE_STATUS_NON_EXISTENT))
              || (firstGeneStatus.equals(GENE_STATUS_NON_EXISTENT) && secondGeneStatus.equals(GENE_STATUS_RECESSIVE))) {
            // recessive gene in one parent and non-existent in the other =>
            // non-existent gene in child
            geneStatusMap.put(child, GENE_STATUS_NON_EXISTENT);
          } else if (firstGeneStatus.equals(GENE_STATUS_NON_EXISTENT)
              && secondGeneStatus.equals(GENE_STATUS_NON_EXISTENT)) {
            // gene is non-existent in both parents => gene is non-existent in
            // child
            geneStatusMap.put(child, GENE_STATUS_NON_EXISTENT);
          }

          iter.remove();
        }
      }
    }

    StringBuilder resultBuilder = new StringBuilder();

    for (String name : geneStatusMap.keySet()) {
      String status = geneStatusMap.get(name);
      resultBuilder.append(String.format("%s %s%n", name, status));
    }

    scanner.close();
    outStream.print(resultBuilder.toString());
  }
}