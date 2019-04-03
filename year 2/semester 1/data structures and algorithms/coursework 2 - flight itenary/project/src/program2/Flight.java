package program2;

import java.util.Arrays;

public class Flight {
	private String planeID;
	private int takeoff_day;
	private int takeoff_month;
	private int takeoff_year;
	private int takeoff_hour;
	private int takeoff_minute;
	private int arrival_day;
	private int arrival_month;
	private int arrival_year;
	private int arrival_hour;
	private int arrival_minute;
	
	private Time time;

	public Flight(String planeID, String takeoff, String arrival) {
		this.planeID = planeID;

		// DD-MM-YY-HHMM format
		String[] takeoff_array, arrival_array;
		// split string into array
		takeoff_array = takeoff.split("-");
		arrival_array = arrival.split("-");

		// 0,1 indexes of substring
		takeoff_hour = Integer.valueOf(takeoff_array[3].substring(0, 2));
		// 2,3 indexes of substring
		takeoff_minute = Integer.valueOf(takeoff_array[3].substring(2, 4));
		
		arrival_hour = Integer.valueOf(arrival_array[3].substring(0, 2));
		arrival_minute = Integer.valueOf(arrival_array[3].substring(2, 4));

		takeoff_day = Integer.valueOf(takeoff_array[0]);
		takeoff_month = Integer.valueOf(takeoff_array[1]);
		takeoff_year = Integer.valueOf(takeoff_array[2]);
		arrival_day = Integer.valueOf(arrival_array[0]);
		arrival_month = Integer.valueOf(arrival_array[1]);
		arrival_year = Integer.valueOf(arrival_array[2]);
		
		time = new Time();

	}

	public String getPlaneID() {
		return planeID;
	}

	public int[] getArrival() {
		int[] arrival = { arrival_day, arrival_month, arrival_year, arrival_hour, arrival_minute };
		return arrival;
	}

	public int[] getTakeOff() {
		int[] takeoff = { takeoff_day, takeoff_month, takeoff_year, takeoff_hour, takeoff_minute };
		return takeoff;
	}

	public int[] duration() {
		int[] difference = time.getDifference(getTakeOff(), getArrival());
		return difference;

	}

}
