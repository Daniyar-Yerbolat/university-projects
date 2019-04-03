package program1;

import java.util.Scanner;

import org.jgrapht.Graph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class Flights {
	private SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph;

	private String[] cities = { "Edinburgh", "Heathrow", "New York", "Sydney", "Dubai", "Kuala Lumpur",
			"Rio de Janeiro", "Auckland", "Santiago", "Frankfurt" };
	private Scanner s;

	public Flights() {
		graph = new SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		s = new Scanner(System.in);
		createVertexes();
		createEdges();
		
		listConnections();
		
		listAirports();
		input();
	}
	
	public void input() {
		System.out.println(String.format("%25s", "Please enter the start airport"));
		String start = s.nextLine();
		System.out.println(String.format("\t %-15s", start));
		System.out.println(String.format("%25s", "Please enter the destination airport"));
		String end = s.nextLine();
		System.out.println(String.format("\t %-15s", end));

		cheapestPath(start, end);
		if (s.nextLine().equals("exit")) {
			s.close();
		} else {
			input();
		}

	}
	
	private void createVertexes() {
		graph.addVertex(cities[0]);
		graph.addVertex(cities[1]);
		graph.addVertex(cities[2]);
		graph.addVertex(cities[3]);
		graph.addVertex(cities[4]);
		graph.addVertex(cities[5]);
		graph.addVertex(cities[6]);
		graph.addVertex(cities[7]);
		graph.addVertex(cities[8]);
		graph.addVertex(cities[9]);
	}

	private void createEdges() {
		DefaultWeightedEdge e1 = graph.addEdge(cities[0], cities[1]); // "Edinburgh",
		// "Heathrow"
		graph.setEdgeWeight(e1, 80);
		DefaultWeightedEdge e2 = graph.addEdge(cities[1], cities[0]);
		graph.setEdgeWeight(e2, 80);

		DefaultWeightedEdge e3 = graph.addEdge(cities[1], cities[4]); // "Edinburgh",
		// "Dubai"
		graph.setEdgeWeight(e3, 130);
		DefaultWeightedEdge e4 = graph.addEdge(cities[4], cities[1]);
		graph.setEdgeWeight(e4, 130);

		DefaultWeightedEdge e5 = graph.addEdge(cities[1], cities[3]); // "Heathrow",
		// "Sydney"
		graph.setEdgeWeight(e5, 570);
		DefaultWeightedEdge e6 = graph.addEdge(cities[3], cities[1]);
		graph.setEdgeWeight(e6, 570);

		DefaultWeightedEdge e7 = graph.addEdge(cities[4], cities[5]); // "Dubai",
		// "Kuala
		// Lumpur"
		graph.setEdgeWeight(e7, 170);
		DefaultWeightedEdge e8 = graph.addEdge(cities[5], cities[4]);
		graph.setEdgeWeight(e8, 170);

		DefaultWeightedEdge e9 = graph.addEdge(cities[4], cities[0]); // "Dubai",
		// "Edinburgh"
		graph.setEdgeWeight(e9, 190);
		DefaultWeightedEdge e10 = graph.addEdge(cities[0], cities[4]);
		graph.setEdgeWeight(e10, 190);

		DefaultWeightedEdge e11 = graph.addEdge(cities[5], cities[3]); // "Kuala
		// Lumpur",
		// "Sydney"
		graph.setEdgeWeight(e11, 150);
		DefaultWeightedEdge e12 = graph.addEdge(cities[3], cities[5]);
		graph.setEdgeWeight(e12, 150);

		DefaultWeightedEdge e13 = graph.addEdge(cities[0], cities[9]); // "Edinburgh",
		// "Frankfurt"
		graph.setEdgeWeight(e13, 90);
		DefaultWeightedEdge e14 = graph.addEdge(cities[9], cities[0]);
		graph.setEdgeWeight(e14, 90);

		DefaultWeightedEdge e15 = graph.addEdge(cities[3], cities[7]); // "Sydney",
		// "Auckland"
		graph.setEdgeWeight(e15, 120);
		DefaultWeightedEdge e16 = graph.addEdge(cities[7], cities[3]);
		graph.setEdgeWeight(e16, 120);

		DefaultWeightedEdge e17 = graph.addEdge(cities[6], cities[2]); // "Rio
		// de
		// Janeiro",
		// "New
		// York"
		graph.setEdgeWeight(e17, 430);
		DefaultWeightedEdge e18 = graph.addEdge(cities[2], cities[6]);
		graph.setEdgeWeight(e18, 430);

		DefaultWeightedEdge e19 = graph.addEdge(cities[2], cities[8]); // "New
		// York",
		// "Santiago"
		graph.setEdgeWeight(e19, 320);
		DefaultWeightedEdge e20 = graph.addEdge(cities[8], cities[2]);
		graph.setEdgeWeight(e20, 320);
	}
	
	/* ******************************************************
	* Part A *
	****************************************************** */
	
	// Edges
	public void listConnections() {
		System.out.println(String.format("%-32s %-5s", "Flight", "Cost"));
		for (String i : graph.vertexSet()) {
			for (DefaultWeightedEdge x : graph.outgoingEdgesOf(i)) {
				System.out.println(String.format("%15s %2s %-15s $%4.0f", i, "->", graph.getEdgeTarget(x),
						graph.getEdgeWeight(x)));
			}
		}
	}
	
	// Vertexes
	public void listAirports(){
		System.out.println(String.format("%25s", "The following airports are used:"));
		for (String i : graph.vertexSet()) {
			System.out.println(String.format("\t %-15s", i));
		}
	}
	
	
	/* ******************************************************
	* Part B *
	****************************************************** */
	private void cheapestPath(String start, String destination) {
		if (graph.containsVertex(start) && graph.containsVertex(destination)) {
			DijkstraShortestPath<String, DefaultWeightedEdge> path = new DijkstraShortestPath<String, DefaultWeightedEdge>(
					graph, start, destination);
			if (path.getPathEdgeList() != null) {
				start = start.trim();
				destination = destination.trim();
				double total = 0;
				int count = 1;
				System.out.println(String.format("%25s", "Shortest (i.e. cheapest ) path :"));
				for (DefaultWeightedEdge edge : path.getPathEdgeList()) {
					System.out.println(String.format("%2s. %15s -> %-15s", count, graph.getEdgeSource(edge),
							graph.getEdgeTarget(edge)));
					total = total + graph.getEdgeWeight(edge);
					count++;
				}
				System.out.println(String.format("Cost of shortest (i.e. cheapest ) path = $%-4.0f", total));
			} else {
				System.out.println("Cannot find path between 2 cities.");
				input();
			}
		} else {
			System.out.println("One or both of the cities are typed incorrectly.");
			input();
		}
	}
}
