package program2;

import java.util.Arrays;

public class Time {
	private final int DAYS_31 = 31;
	private final int DAYS_30 = 30;
	private final int DAYS_28 = 28;
	private final int DAYS_29 = 29;

	public int[] getDifference(int[] start, int[] end) {
		int start_day = start[0];
		int start_month = start[1];
		int start_year = start[2];
		int start_hour = start[3];
		int start_minute = start[4];

		int end_day = end[0];
		int end_month = end[1];
		int end_year = end[2];
		int end_hour = end[3];
		int end_minute = end[4];

		int difference_year = 0;
		
		if (end_year > start_year) {
			difference_year = end_year - start_year;
		} else {
			difference_year = start_year - end_year;
		}
		
		int difference_month = 0;
		int difference_day = 0;
		int difference_hour = 0;
		int difference_minute = 0;

		if (difference_year > 0) {
			difference_month = (12 - start_month) + end_month;
			difference_year--;
		} else {
			difference_month = end_month - start_month;
		}

		Integer[] months_1 = { 1, 3, 5, 7, 8, 10, 12 };
		Integer[] months_2 = { 4, 6, 9, 11 };

		if (difference_month > 0) {
			if (Arrays.asList(months_1).contains(start_month)) {
				difference_day = (DAYS_31 - start_day) + end_day;
			} else if (Arrays.asList(months_2).contains(start_month)) {
				difference_day = (DAYS_30 - start_day) + end_day;
			} else if (start_month == 2) {
				// Leap Years (Gregorian) are divisible by 4 and the century
				// must be divisible by 400.
				// Meaning there will be no leap years in 2100s, 2200s, 2300s.
				if ((start_year % 4 == 0) && ((start_year % 100 != 0) || (start_year % 400 == 0))) {
					difference_day = (DAYS_29 - start_day) + end_day;
				} else {
					difference_day = (DAYS_28 - start_day) + end_day;
				}
			}
			difference_month--;
		} else {
			difference_day = end_day - start_day;
		}

		if (difference_day > 0) {
			difference_hour = (24 - start_hour) + end_hour;
			difference_day--;
		} else {
			difference_hour = end_hour - start_hour;
		}

		if (difference_hour > 0) {
			difference_minute = (60 - start_minute) + end_minute;
			difference_hour--;
		} else {
			difference_minute = end_minute - start_minute;
		}

		while (difference_minute >= 60) {
			difference_minute = difference_minute - 60;
			difference_hour++;
		}
		while (difference_hour >= 24) {
			difference_hour = difference_hour - 24;
			difference_day++;
		}
		while (difference_day >= 31) {
			difference_day = difference_day - 31;
			difference_month++;
		}
		while (difference_month >= 12) {
			difference_month = difference_month - 12;
			difference_year++;
		}

		int[] difference = { difference_day, difference_month, difference_year, difference_hour, difference_minute };

		return difference;
	}

	public int[] add_time(int[] time1, int[] time2) {
		int total_day = time1[0] + time2[0];
		int total_month = time1[1] + time2[1];
		int total_year = time1[2] + time2[2];
		int total_hour = time1[3] + time2[3];
		int total_minute = time1[4] + time2[4];

		while (total_minute >= 60) {
			total_minute = total_minute - 60;
			total_hour++;
		}
		while (total_minute < 0) {
			total_minute = total_minute + 60;
			total_hour--;
		}
		while (total_hour >= 24) {
			total_hour = total_hour - 24;
			total_day++;
		}
		while (total_hour < 0) {
			total_hour = total_hour + 24;
			total_day--;
		}
		while (total_day >= 31) {
			total_day = total_day - 31;
			total_month++;
		}
		while (total_day < 0) {
			total_day = total_day + 31;
			total_month--;
		}
		while (total_month >= 12) {
			total_month = total_month - 12;
			total_year++;
		}
		while (total_month < 0) {
			total_month = total_month + 12;
			total_year--;
		}

		int[] total = { total_day, total_month, total_year, total_hour, total_minute };

		return total;

	}

	// checks whether time2 is greater than time1
	public boolean greatest(int[] time1, int[] time2) {

		if (time1 == null) {
			return false;
		}

		// check if time2's year is greater than time1's
		if (time2[2] > time1[2]) {
			return true;
			// if it's less, return false
		} else if (time2[2] < time1[2]) {
			return false;
			// if equal, check month ... etc
		} else {
			if (time2[1] > time1[1]) {
				return true;
			} else if (time2[1] < time1[1]) {
				return false;
			} else {
				if (time2[0] > time1[0]) {
					return true;
				} else if (time2[0] < time1[0]) {
					return false;
				} else {
					if (time2[3] > time1[3]) {
						return true;
					} else if (time2[3] < time1[3]) {
						return false;
					} else {
						if (time2[4] > time1[4]) {
							return true;
						} else if (time2[4] < time1[4]) {
							return false;
							// if all fields are equal, return true.
						} else {
							return true;
						}
					}
				}
			}
		}

	}
}
