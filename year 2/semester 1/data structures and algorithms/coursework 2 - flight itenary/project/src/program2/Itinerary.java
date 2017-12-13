package program2;

import java.util.Arrays;
import java.util.ListIterator;
import java.util.Scanner;

import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.AsUnweightedDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.traverse.*;

public class Itinerary {
	private SimpleDirectedWeightedGraph<City, FlightEdge> graph;
	private City[] cities = { new City("Edinburgh", new int[] { 0, 0, 0, 0, 0 }),
			new City("Heathrow", new int[] { 0, 0, 0, 0, 0 }), new City("New York", new int[] { 0, 0, 0, -5, 0 }),
			new City("Sydney", new int[] { 0, 0, 0, 10, 0 }), new City("Dubai", new int[] { 0, 0, 0, 4, 0 }),
			new City("Kuala Lumpur", new int[] { 0, 0, 0, 8, 0 }),
			new City("Rio de Janeiro", new int[] { 0, 0, 0, -3, 0 }),
			new City("Auckland", new int[] { 0, 0, 0, 12, 0 }), new City("Santiago", new int[] { 0, 0, 0, -3, 0 }),
			new City("Frankfurt", new int[] { 0, 0, 0, 1, 0 }) };
	private Time time;
	private Scanner s;
	private int[] global_date = { 0, 0, 0, 0, 0 };

	public Itinerary() {
		graph = new SimpleDirectedWeightedGraph<City, FlightEdge>(FlightEdge.class);
		time = new Time();
		s = new Scanner(System.in);
		createVertexes();
		createEdges();

		listVertexes();
		input();

	}

	public City[] getCities() {
		return cities;
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
		// "Edinburgh", "Heathrow".
		FlightEdge e1 = graph.addEdge(cities[0], cities[1]);
		graph.setEdgeWeight(e1, 80);
		e1.add(new Flight("B815", "11-11-16-0630", "11-11-16-0830"));
		e1.add(new Flight("B700", "11-11-16-0730", "11-11-16-0930"));
		e1.add(new Flight("B814", "11-11-16-0830", "11-11-16-1030"));
		FlightEdge e2 = graph.addEdge(cities[1], cities[0]);
		graph.setEdgeWeight(e2, 80);
		e2.add(new Flight("B802", "11-11-16-0400", "11-11-16-0600"));
		e2.add(new Flight("B702", "11-11-16-0500", "11-11-16-0700"));
		e2.add(new Flight("B813", "11-11-16-0600", "11-11-16-0800"));

		// "Edinburgh", "Dubai".
		FlightEdge e3 = graph.addEdge(cities[1], cities[4]);
		graph.setEdgeWeight(e3, 130);
		e3.add(new Flight("B600", "11-11-16-1245", "11-11-16-1600"));
		e3.add(new Flight("B601", "11-11-16-1345", "11-11-16-1700"));
		e3.add(new Flight("B602", "11-11-16-1545", "11-11-16-1900"));
		FlightEdge e4 = graph.addEdge(cities[4], cities[1]);
		graph.setEdgeWeight(e4, 130);
		e3.add(new Flight("B603", "11-11-16-0400", "11-11-16-0700"));
		e3.add(new Flight("B604", "11-11-16-0500", "11-11-16-0800"));
		e3.add(new Flight("B605", "11-11-16-0600", "11-11-16-0900"));

		// "Heathrow", "Sydney".
		FlightEdge e5 = graph.addEdge(cities[1], cities[3]);
		graph.setEdgeWeight(e5, 570);
		e5.add(new Flight("B501", "12-11-16-0730", "13-11-16-0100"));
		e5.add(new Flight("B502", "12-11-16-1440", "13-11-16-0800"));
		e5.add(new Flight("B503", "12-11-16-2120", "13-11-16-1500"));
		FlightEdge e6 = graph.addEdge(cities[3], cities[1]);
		graph.setEdgeWeight(e6, 570);
		e6.add(new Flight("B101", "11-11-16-0730", "12-11-16-0100"));
		e6.add(new Flight("B101", "11-11-16-1430", "12-11-16-0800"));
		e6.add(new Flight("B101", "11-11-16-2130", "12-11-16-1500"));
		e6.add(new Flight("B101", "12-11-16-0730", "13-11-16-0100"));
		e6.add(new Flight("B101", "12-11-16-1430", "13-11-16-0800"));
		e6.add(new Flight("B101", "12-11-16-2130", "13-11-16-1500"));

		// "Dubai", "Kuala Lumpur".
		FlightEdge e7 = graph.addEdge(cities[4], cities[5]);
		graph.setEdgeWeight(e7, 170);
		e7.add(new Flight("B101", "11-11-16-0730", "12-11-16-0100"));
		e7.add(new Flight("B101", "11-11-16-1430", "12-11-16-0800"));
		e7.add(new Flight("B101", "11-11-16-2130", "12-11-16-1500"));
		e7.add(new Flight("B101", "12-11-16-0730", "13-11-16-0100"));
		e7.add(new Flight("B101", "12-11-16-1430", "13-11-16-0800"));
		e7.add(new Flight("B101", "12-11-16-2130", "13-11-16-1500"));
		FlightEdge e8 = graph.addEdge(cities[5], cities[4]);
		graph.setEdgeWeight(e8, 170);
		e8.add(new Flight("B101", "11-11-16-0730", "12-11-16-0100"));
		e8.add(new Flight("B101", "11-11-16-1430", "12-11-16-0800"));
		e8.add(new Flight("B101", "11-11-16-2130", "12-11-16-1500"));
		e8.add(new Flight("B101", "12-11-16-0730", "13-11-16-0100"));
		e8.add(new Flight("B101", "12-11-16-1430", "13-11-16-0800"));
		e8.add(new Flight("B101", "12-11-16-2130", "13-11-16-1500"));

		// "Dubai", "Edinburgh".
		FlightEdge e9 = graph.addEdge(cities[4], cities[0]);
		graph.setEdgeWeight(e9, 190);
		e9.add(new Flight("B101", "11-11-16-0730", "12-11-16-0100"));
		e9.add(new Flight("B101", "11-11-16-1430", "12-11-16-0800"));
		e9.add(new Flight("B101", "11-11-16-2130", "12-11-16-1500"));
		e9.add(new Flight("B101", "12-11-16-0730", "13-11-16-0100"));
		e9.add(new Flight("B101", "12-11-16-1430", "13-11-16-0800"));
		e9.add(new Flight("B101", "12-11-16-2130", "13-11-16-1500"));
		e9.add(new Flight("B101", "13-11-16-2130", "14-11-16-1500"));
		e9.add(new Flight("B101", "13-11-16-2330", "14-11-16-1700"));
		e9.add(new Flight("B101", "14-11-16-2130", "15-11-16-1500"));
		FlightEdge e10 = graph.addEdge(cities[0], cities[4]);
		graph.setEdgeWeight(e10, 190);
		e10.add(new Flight("B101", "11-11-16-0730", "12-11-16-0100"));
		e10.add(new Flight("B101", "11-11-16-1430", "12-11-16-0800"));
		e10.add(new Flight("B101", "11-11-16-2130", "12-11-16-1500"));
		e10.add(new Flight("B101", "12-11-16-0730", "13-11-16-0100"));
		e10.add(new Flight("B101", "12-11-16-1430", "13-11-16-0800"));
		e10.add(new Flight("B101", "12-11-16-2130", "13-11-16-1500"));

		// "Kuala Lumpur", "Sydney".
		FlightEdge e11 = graph.addEdge(cities[5], cities[3]);
		graph.setEdgeWeight(e11, 150);
		e11.add(new Flight("B101", "11-11-16-0730", "12-11-16-0100"));
		e11.add(new Flight("B101", "11-11-16-1430", "12-11-16-0800"));
		e11.add(new Flight("B101", "11-11-16-2130", "12-11-16-1500"));
		e11.add(new Flight("B101", "12-11-16-0730", "13-11-16-0100"));
		e11.add(new Flight("B101", "12-11-16-1430", "13-11-16-0800"));
		e11.add(new Flight("B101", "12-11-16-2130", "13-11-16-1500"));
		e11.add(new Flight("B101", "13-11-16-2130", "14-11-16-1500"));
		e11.add(new Flight("B101", "13-11-16-2330", "14-11-16-1700"));
		e11.add(new Flight("B101", "14-11-16-2130", "15-11-16-1500"));
		FlightEdge e12 = graph.addEdge(cities[3], cities[5]);
		graph.setEdgeWeight(e12, 150);
		e12.add(new Flight("B101", "11-11-16-0730", "12-11-16-0100"));
		e12.add(new Flight("B101", "11-11-16-1430", "12-11-16-0800"));
		e12.add(new Flight("B101", "11-11-16-2130", "12-11-16-1500"));
		e12.add(new Flight("B101", "12-11-16-0730", "13-11-16-0100"));
		e12.add(new Flight("B101", "12-11-16-1430", "13-11-16-0800"));
		e12.add(new Flight("B101", "12-11-16-2130", "13-11-16-1500"));

		// "Edinburgh", "Frankfurt".
		FlightEdge e13 = graph.addEdge(cities[0], cities[9]);
		graph.setEdgeWeight(e13, 90);
		e13.add(new Flight("B101", "11-11-16-0730", "12-11-16-0100"));
		e13.add(new Flight("B101", "11-11-16-1430", "12-11-16-0800"));
		e13.add(new Flight("B101", "11-11-16-2130", "12-11-16-1500"));
		e13.add(new Flight("B101", "12-11-16-0730", "13-11-16-0100"));
		e13.add(new Flight("B101", "12-11-16-1430", "13-11-16-0800"));
		e13.add(new Flight("B101", "12-11-16-2130", "13-11-16-1500"));
		FlightEdge e14 = graph.addEdge(cities[9], cities[0]);
		graph.setEdgeWeight(e14, 90);
		e14.add(new Flight("B101", "11-11-16-0730", "12-11-16-0100"));
		e14.add(new Flight("B101", "11-11-16-1430", "12-11-16-0800"));
		e14.add(new Flight("B101", "11-11-16-2130", "12-11-16-1500"));
		e14.add(new Flight("B101", "12-11-16-0730", "13-11-16-0100"));
		e14.add(new Flight("B101", "12-11-16-1430", "13-11-16-0800"));
		e14.add(new Flight("B101", "12-11-16-2130", "13-11-16-1500"));

		// "Sydney", "Auckland".
		FlightEdge e15 = graph.addEdge(cities[3], cities[7]);
		graph.setEdgeWeight(e15, 120);
		e15.add(new Flight("B101", "11-11-16-0730", "12-11-16-0100"));
		e15.add(new Flight("B101", "11-11-16-1430", "12-11-16-0800"));
		e15.add(new Flight("B101", "11-11-16-2130", "12-11-16-1500"));
		e15.add(new Flight("B101", "12-11-16-0730", "13-11-16-0100"));
		e15.add(new Flight("B101", "12-11-16-1430", "13-11-16-0800"));
		e15.add(new Flight("B101", "12-11-16-2130", "13-11-16-1500"));
		FlightEdge e16 = graph.addEdge(cities[7], cities[3]);
		graph.setEdgeWeight(e16, 120);
		e16.add(new Flight("B101", "11-11-16-0730", "12-11-16-0100"));
		e16.add(new Flight("B101", "11-11-16-1430", "12-11-16-0800"));
		e16.add(new Flight("B101", "11-11-16-2130", "12-11-16-1500"));
		e16.add(new Flight("B101", "12-11-16-0730", "13-11-16-0100"));
		e16.add(new Flight("B101", "12-11-16-1430", "13-11-16-0800"));
		e16.add(new Flight("B101", "12-11-16-2130", "13-11-16-1500"));

		// "Rio de Janeiro", "New York".
		FlightEdge e17 = graph.addEdge(cities[6], cities[2]);
		graph.setEdgeWeight(e17, 430);
		e17.add(new Flight("B101", "11-11-16-0730", "12-11-16-0100"));
		e17.add(new Flight("B101", "11-11-16-1430", "12-11-16-0800"));
		e17.add(new Flight("B101", "11-11-16-2130", "12-11-16-1500"));
		e17.add(new Flight("B101", "12-11-16-0730", "13-11-16-0100"));
		e17.add(new Flight("B101", "12-11-16-1430", "13-11-16-0800"));
		e17.add(new Flight("B101", "12-11-16-2130", "13-11-16-1500"));
		FlightEdge e18 = graph.addEdge(cities[2], cities[6]);
		graph.setEdgeWeight(e18, 430);
		e18.add(new Flight("B101", "11-11-16-0730", "12-11-16-0100"));
		e18.add(new Flight("B101", "11-11-16-1430", "12-11-16-0800"));
		e18.add(new Flight("B101", "11-11-16-2130", "12-11-16-1500"));
		e18.add(new Flight("B101", "12-11-16-0730", "13-11-16-0100"));
		e18.add(new Flight("B101", "12-11-16-1430", "13-11-16-0800"));
		e18.add(new Flight("B101", "12-11-16-2130", "13-11-16-1500"));

		// "New York", "Santiago".
		FlightEdge e19 = graph.addEdge(cities[2], cities[8]);
		graph.setEdgeWeight(e19, 320);
		e19.add(new Flight("B101", "11-11-16-0730", "12-11-16-0100"));
		e19.add(new Flight("B101", "11-11-16-1430", "12-11-16-0800"));
		e19.add(new Flight("B101", "11-11-16-2130", "12-11-16-1500"));
		e19.add(new Flight("B101", "12-11-16-0730", "13-11-16-0100"));
		e19.add(new Flight("B101", "12-11-16-1430", "13-11-16-0800"));
		e19.add(new Flight("B101", "12-11-16-2130", "13-11-16-1500"));
		FlightEdge e20 = graph.addEdge(cities[8], cities[2]);
		graph.setEdgeWeight(e20, 320);
		e20.add(new Flight("B101", "11-11-16-0730", "12-11-16-0100"));
		e20.add(new Flight("B101", "11-11-16-1430", "12-11-16-0800"));
		e20.add(new Flight("B101", "11-11-16-2130", "12-11-16-1500"));
		e20.add(new Flight("B101", "12-11-16-0730", "13-11-16-0100"));
		e20.add(new Flight("B101", "12-11-16-1430", "13-11-16-0800"));
		e20.add(new Flight("B101", "12-11-16-2130", "13-11-16-1500"));
	}

	public void input() {
		System.out.println(String.format("%25s", "Cheapest or Shortest Path?"));
		String cheap_short = s.nextLine().toLowerCase();
		System.out.println(String.format("%25s", "Please enter the start airport"));
		String start = s.nextLine();
		System.out.println(String.format("\t %-15s", start));
		System.out.println(String.format("%25s", "Please enter the destination airport"));
		String end = s.nextLine();
		System.out.println(String.format("\t %-15s", end));

		if (cheap_short != null && cheap_short.equals("shortest")) {
			shortestPath(start, end);
		} else if (cheap_short != null && cheap_short.equals("cheapest")) {
			cheapestPath(start, end);
		} else {
			input();
		}
		
		System.out.println(String.format("%25s", "Exit or Try Again?"));
		exit(s.nextLine());

	}

	private void exit(String command) {
		command = command.toLowerCase();
		if (command != null && command.equals("exit")) {
			s.close();
		} else if (command != null && command.equals("try again")){
			input();
		}
	}

	public void listConnections() {
		System.out.println(String.format("%-32s %-5s", "Flight", "Cost"));
		for (City i : graph.vertexSet()) {
			for (FlightEdge x : graph.outgoingEdgesOf(i)) {
				System.out.println(String.format("%15s %2s %-15s | $%4.0f", i, "->",
						graph.getEdgeTarget(x).getCityName(), graph.getEdgeWeight(x)));
			}
		}
	}

	public void listVertexes() {
		System.out.println(String.format("%25s", "The following airports are used:"));
		for (City i : graph.vertexSet()) {
			System.out.println(String.format("\t %-15s", i.getCityName()));
		}
	}

	private City find(String cityname) {
		for (City i : graph.vertexSet()) {
			if (i.getCityName() != null && i.getCityName().equals(cityname)) {
				return i;
			}
		}
		return null;
	}

	/* ******************************************************
	* Part D, E, F (1,2) *
	* Part C in FlightEdge
	****************************************************** */
	public void cheapestPath(String start, String destination) {
		City start_city, destination_city;
		start_city = find(start);
		destination_city = find(destination);
		if (start_city != null && destination_city != null) {
			DijkstraShortestPath<City, FlightEdge> path = new DijkstraShortestPath<City, FlightEdge>(graph, start_city,
					destination_city);
			if (path.getPathEdgeList() != null) {
				double total_price = 0;
				int[] total_in_air = { 0, 0, 0, 0, 0 };
				int[] temp = { 0, 0, 0, 0, 0 };
				int[] changeover_total = { 0, 0, 0, 0, 0 };
				int count = 1;

				System.out.println(String.format("Itenary for %-15s to %15s", start, destination));
				System.out.println(String.format(
						"%-5s | %-15s | %-10s | %-5s | %-10s | %-5s | %-5s | %-15s | %-10s | %-5s | %-10s | %-5s | %-10s",
						"Leg", "Leave", "Local", "Local", "Date (UTC)", "At", "On", "Arrive", "Local", "Local",
						"Date (UTC)", "At", "Changeover"));

				for (FlightEdge edge : path.getPathEdgeList()) {
					Flight flight = edge.nextInQueue(global_date);
					int[] start_time = flight.getTakeOff();
					int[] end_time = flight.getArrival();
					int[] changeover = { 0, 0, 0, 0, 0 };
					if ((temp[0] == 0 && temp[1] == 0 && temp[2] == 0 && temp[3] == 0 && temp[4] == 0) == false) {
						changeover = Arrays.copyOf(time.getDifference(temp, start_time), 5);
					}
					int[] localtime_start = time.add_time(start_time, graph.getEdgeSource(edge).getTimeZone());
					int[] localtime_end = time.add_time(end_time, graph.getEdgeTarget(edge).getTimeZone());
					temp = Arrays.copyOf(end_time, end_time.length);
					global_date = Arrays.copyOf(end_time, global_date.length);
					String id = flight.getPlaneID();
					System.out.println(String.format(
							"%-5s | %-15s | %-10s | %02d:%02d | %-10s | %02d:%02d | %-5s | %-15s | %-10s | %02d:%02d | %-10s | %02d:%02d | %-10s",
							count, graph.getEdgeSource(edge).getCityName(),
							localtime_start[0] + "-" + localtime_start[1] + "-" + localtime_start[2],
							localtime_start[3], localtime_start[4],
							start_time[0] + "-" + start_time[1] + "-" + start_time[2], start_time[3], start_time[4], id,
							graph.getEdgeTarget(edge).getCityName(),
							localtime_end[0] + "-" + localtime_end[1] + "-" + localtime_end[2], localtime_end[3],
							localtime_end[4], end_time[0] + "-" + end_time[1] + "-" + end_time[2], end_time[3],
							end_time[4], changeover[3] + " hours, " + changeover[4] + " minutes."));

					int[] difference = time.getDifference(start_time, end_time);

					total_in_air = Arrays.copyOf(time.add_time(difference, total_in_air), 5);
					changeover_total = Arrays.copyOf(time.add_time(changeover, changeover_total), 5);
					total_price = total_price + graph.getEdgeWeight(edge);

					count++;
				}
				System.out.println(String.format("Total journey cost = $%-4.0f", total_price));
				System.out.println(String.format("Total time in air = %2s days, %2s hours, %2s minutes.",
						total_in_air[0], total_in_air[3], total_in_air[4]));
				System.out.println(String.format("Total time waiting = %2s days, %2s hours, %2s minutes.",
						changeover_total[0], changeover_total[3], changeover_total[4]));
				int[] total_time = time.add_time(total_in_air, changeover_total);
				System.out.println(String.format("Total time = %2s days, %2s hours, %2s minutes.", total_time[0],
						total_time[3], total_time[4]));
			} else {
				System.out.println("Cannot find path between 2 cities.");
				input();
			}
		} else

		{
			System.out.println("One or both of the cities are typed incorrectly.");
			input();
		}
	}
	
	/* ******************************************************
	* Part F (4) *
	****************************************************** */
	public void shortestPath(String start, String destination) {
		City start_city, destination_city;
		start_city = find(start);
		destination_city = find(destination);
		if (start_city != null && destination_city != null) {
			AsUnweightedDirectedGraph<City, FlightEdge> graph_temp = new AsUnweightedDirectedGraph(graph);
			DijkstraShortestPath<City, FlightEdge> path = new DijkstraShortestPath<City, FlightEdge>(graph_temp,
					start_city, destination_city);
			System.out.println(String.format("Itenary for %-15s to %15s", start, destination));
			if (path.getPathEdgeList() != null) {
				for (FlightEdge edge : path.getPathEdgeList()) {
					System.out
							.println(String.format("%15s -> %-15s", graph_temp.getEdgeSource(edge).getCityName(),
									graph_temp.getEdgeTarget(edge).getCityName()));
				}
			}
		}

	}
}
