import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Headline extends JFrame implements ActionListener {

	JPanel jp1, jp2;
	Label[] string_of_text;
	Label display;
	JTextField input;
	final int number = 10;
	int z;

	public Label setLable(Container c) {
		Label j = new Label(" ", Label.CENTER);
		j.setFont(new Font("serif", Font.PLAIN, 18));
		j.setBackground(Color.WHITE);
		c.add(j);
		return j;

	}

	public Headline() {
		setLayout(new GridLayout(2, 1));

		string_of_text = new Label[number];
		jp1 = new JPanel(new GridLayout(1, number));
		for (int i = 0; i < number; i++) {
			string_of_text[i] = setLable(jp1);
		}

		jp2 = new JPanel(new GridLayout(1, 2));
		display = new Label("Enter Text and Press Return", Label.CENTER);
		input = new JTextField();
		jp2.add(display);
		jp2.add(input);

		add(jp1);
		add(jp2);

		input.addActionListener(this);

	}

	private void pause(long millisecs) {
		long startTime = Calendar.getInstance().getTimeInMillis();
		while (Calendar.getInstance().getTimeInMillis() - startTime < millisecs)
			;
	}

	public void place() {
		String y = input.getText();
		int x = y.length();
		for (int abc = 0; abc < y.length() + number; abc++) {

			if (abc < y.length()) {
				move();
				String text = y.substring(y.length() - x, y.length() - x + 1);
				string_of_text[9].setText(text);
				x--;
				pause(500);
			} else {
				move();
				string_of_text[9].setText("");
				pause(500);
			}
		}
	}

	public void move() {
		for (int x = 0; x < 9; x++) {
			string_of_text[x].setText(string_of_text[x + 1].getText());

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == input) {

			for (int i = 0; i < string_of_text.length; i++) {
				string_of_text[i].setText("");
			}

			input.setEnabled(false);

			place();
			input.setEnabled(true);
		}
	}

}
