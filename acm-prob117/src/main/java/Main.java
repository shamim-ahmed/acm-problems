import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
  private static final String DEAD_END = "deadend";

  public static void main(String... args) {
	Scanner scanner = new Scanner(System.in);

	while (scanner.hasNextLine()) {
	  String line = scanner.nextLine();
	  List<String> streets = new ArrayList<String>();

	  while (!line.equals(DEAD_END)) {
		streets.add(line);
		line = scanner.nextLine();
	  }
	  
	  Map<String, List<Vertex>> adjacencyMap = new HashMap<String, List<Vertex>>();
	  int sum = 0;

	  for (String street : streets) {
		String vertex1 = Character.toString(street.charAt(0));
		String vertex2 = Character.toString(street.charAt(street.length() - 1));
		int length = street.length();
		sum += length;

		addEdge(vertex1, vertex2, length, adjacencyMap);
		addEdge(vertex2, vertex1, length, adjacencyMap);
	  }
	  
	  sum += solve(adjacencyMap);
	  System.out.println(sum);
	}

	scanner.close();
  }

  private static int solve(Map<String, List<Vertex>> adjacencyMap) {
	List<String> endPointNames = new ArrayList<String>();

	for (Map.Entry<String, List<Vertex>> entry : adjacencyMap.entrySet()) {
	  String name = entry.getKey();
	  List<Vertex> adjacentNodes = entry.getValue();

	  if (adjacentNodes.size() % 2 != 0) {
		endPointNames.add(name);
	  }
	}
	
	if (endPointNames.size() == 2) {
	  return solveUsingDijkstra(adjacencyMap, endPointNames);
	}
	
	return 0;
  }

  private static int solveUsingDijkstra(Map<String, List<Vertex>> adjacencyMap, List<String> endPointNames) {
	PriorityQueue<DijkstraNode> dijkstraQueue = new PriorityQueue<DijkstraNode>();
	Map<String, DijkstraNode> dijkstraMap = new HashMap<String, DijkstraNode>();

	for (String name : adjacencyMap.keySet()) {
	  int distance;

	  if (name.equals(endPointNames.get(0))) {
		distance = 0;
	  } else {
		distance = Integer.MAX_VALUE;
	  }

	  DijkstraNode djNode = new DijkstraNode(name, distance);
	  dijkstraMap.put(djNode.getName(), djNode);
	  
	  if (distance == 0) {
		dijkstraQueue.offer(djNode);
	  }
	}

	while (!dijkstraQueue.isEmpty()) {
	  DijkstraNode currentNode = dijkstraQueue.poll();
	  List<Vertex> adjacentVertices = adjacencyMap.get(currentNode.getName());

	  for (Vertex vertex : adjacentVertices) {
		DijkstraNode node = dijkstraMap.get(vertex.getName());
		int d = currentNode.getDistance() + vertex.getEdgeWeight();

		if (node.getDistance() > d) {
		  node.setDistance(d);
		  dijkstraQueue.offer(node);
		}
	  }
	}
	
	return dijkstraMap.get(endPointNames.get(1)).getDistance();
  }

  private static void addEdge(String v1, String v2, int length, Map<String, List<Vertex>> adjacencyMap) {
	List<Vertex> adjacentNodes = adjacencyMap.get(v1);

	if (adjacentNodes == null) {
	  adjacentNodes = new ArrayList<Vertex>();
	}

	adjacentNodes.add(new Vertex(v2, length));
	adjacencyMap.put(v1, adjacentNodes);
  }

  private static class Vertex {
	private final String name;
	private final int edgeWeight;

	public Vertex(String name, int edgeWeight) {
	  this.name = name;
	  this.edgeWeight = edgeWeight;
	}

	public String getName() {
	  return name;
	}

	public int getEdgeWeight() {
	  return edgeWeight;
	}
  }

  private static class DijkstraNode implements Comparable<DijkstraNode> {
	private final String name;
	private int distance;

	public DijkstraNode(String name, int distance) {
	  this.name = name;
	  this.distance = distance;
	}

	public String getName() {
	  return name;
	}

	public int getDistance() {
	  return distance;
	}

	public void setDistance(int distance) {
	  this.distance = distance;
	}

	@Override
	public int compareTo(DijkstraNode otherNode) {
	  if (distance < otherNode.distance) {
		return -1;
	  }

	  if (distance > otherNode.distance) {
		return 1;
	  }

	  return 0;
	}
  }
}
