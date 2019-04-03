package program2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import org.jgrapht.graph.DefaultWeightedEdge;

/* ******************************************************
* Part C *
****************************************************** */
public class FlightEdge extends DefaultWeightedEdge {
	private static final long serialVersionUID = 1L;
	private Queue<Flight> queue;
	private Time time;

	public FlightEdge() {
		super();
		queue = new LinkedList<Flight>();
		time = new Time();
	}

	public void add(Flight flight) {
		queue.add(flight);
	}

	public void removeLast() {
		queue.remove();
	}

	public Flight nextInQueue(int[] globaldate) {
		removeObsolete(globaldate);
		return queue.peek();
	}

	public Iterator<Flight> iterate() {
		return queue.iterator();
	}

	public void removeObsolete(int[] globaldate) {
		// if globaldate is greater than or equal to the next item in queue,
		// then element is removed.

		if (queue.peek() != null) {
			while (time.greatest(queue.peek().getTakeOff(), globaldate)) {
				queue.remove();
			}
		}
	}

}
