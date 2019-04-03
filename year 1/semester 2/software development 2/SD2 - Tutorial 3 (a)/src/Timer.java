import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Timer extends JFrame implements ActionListener {
	JButton plus, minus, go;
	Label time;
	JPanel panel;
	int x = 0;

	public Timer() {
		setLayout(new GridLayout(3, 1));

		Font font = new Font("Font", Font.PLAIN, 36);

		plus = new JButton("+");
		minus = new JButton("-");
		time = new Label("", JLabel.CENTER);
		time.setText(0 + "");

		go = new JButton("GO");
		panel = new JPanel(new GridLayout(1, 2));

		panel.add(minus);
		panel.add(plus);

		plus.setOpaque(true);
		minus.setOpaque(true);
		go.setOpaque(true);

		plus.setBackground(Color.WHITE);
		time.setBackground(Color.WHITE);
		go.setBackground(Color.WHITE);
		minus.setBackground(Color.WHITE);

		plus.setFont(font);
		time.setFont(font);
		go.setFont(font);
		minus.setFont(font);

		add(time);
		add(panel);
		add(go);

		plus.addActionListener(this);
		minus.addActionListener(this);
		go.addActionListener(this);

	}

	void pause(long millisecs) {
		long current = Calendar.getInstance().getTimeInMillis();
		while (Calendar.getInstance().getTimeInMillis() - current < millisecs)
			;
	}

	@Override
	public void actionPerformed(ActionEvent press) {

		if (press.getSource() == plus) {
			time.setText((x + 1) + "");
			x = x + 1;

		} else if (press.getSource() == minus) {

			if (x <= 0) {
				x = 0;
			} else {
				time.setText((x - 1) + "");
				x = x - 1;

			}
		}
		if (press.getSource() == go) {
			x = Integer.parseInt(time.getText());
			while (x > 0) {
				x--;
				pause(1000);
				time.setText(x + "");

			}
		}

	}
}
