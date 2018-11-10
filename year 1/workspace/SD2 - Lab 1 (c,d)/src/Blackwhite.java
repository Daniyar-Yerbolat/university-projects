import java.awt.Color;
import java.util.Calendar;
import java.util.Scanner;

import javax.swing.JFrame;

class Blackwhite extends JFrame {
	int x = 0;

	public Blackwhite() {
		getContentPane().setBackground(Color.black);
	}

	private void pause(long millisecs) {
		long startTime = Calendar.getInstance().getTimeInMillis();
		while (Calendar.getInstance().getTimeInMillis() - startTime < millisecs)
			;
	}

	Color[] rainbow = { Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.magenta, Color.black };

	public void flash() {
		for (x = 0; x < 8; x++) {
			if (x == 7) {
				x = -1;
			} else {
				pause(500);
				getContentPane().setBackground(rainbow[x]);
				pause(500);
			}
		}
	}

	Scanner color = new Scanner(System.in);

	public void flash2() {
		for (x = 0; x < 7; x++) {
			getContentPane().setBackground(rainbow[x]);
			color.nextLine();
			if (x == 6){
				x = -1;
			}
		}

	}
}