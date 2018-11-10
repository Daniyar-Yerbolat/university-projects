import java.awt.Color;
import java.awt.GridLayout;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Progress extends JFrame {
	private JLabel[] labels = new JLabel[10];
	private JLabel percent;

	public Progress() {
		setLayout(new GridLayout(11, 1));
		labels[0] = new JLabel("", JLabel.CENTER);
		labels[1] = new JLabel("", JLabel.CENTER);
		labels[2] = new JLabel("", JLabel.CENTER);
		labels[3] = new JLabel("", JLabel.CENTER);
		labels[4] = new JLabel("", JLabel.CENTER);
		labels[5] = new JLabel("", JLabel.CENTER);
		labels[6] = new JLabel("", JLabel.CENTER);
		labels[7] = new JLabel("", JLabel.CENTER);
		labels[8] = new JLabel("", JLabel.CENTER);
		labels[9] = new JLabel("", JLabel.CENTER);
		percent = new JLabel("", JLabel.CENTER);
		for (int x = 0; x < labels.length; x++) {
			labels[x].setBackground(Color.red);
			labels[x].setOpaque(true);
			add(labels[labels.length-1-x]);
		}

		percent.setText(0 + "%");
		percent.setOpaque(true);
		percent.setVisible(true);

		add(percent);
	}

	public void click() {
		Scanner enter = new Scanner(System.in);
		int y = 0;
		for (int x = 0; x < 10; x++) {
			enter.nextLine();
			y = y + 10;
			labels[x].setBackground(Color.green);
			percent.setText(y + "%");
		}
	}
}
