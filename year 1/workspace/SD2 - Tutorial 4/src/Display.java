import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Display extends JFrame implements ActionListener {
	JPanel panel;
	JButton open, play, pause, stop, close, exit;
	JLabel display;
	int state = 0;
	final int READY = 0;
	final int OPEN = 1;
	final int PLAY = 2;
	final int PAUSE = 3;

	public JButton setupButton(String text, Container c) {
		JButton button = new JButton(text);
		button.setFont(new Font("serif", Font.PLAIN, 18));
		button.setBackground(Color.WHITE);
		c.add(button);
		button.addActionListener(this);
		return button;
	}

	public Display() {
		setLayout(new GridLayout(2, 1));
		display = new JLabel("", JLabel.CENTER);
		panel = new JPanel(new GridLayout(1, 6));

		display.setFont(new Font("serif", Font.PLAIN, 36));
		display.setBackground(Color.WHITE);
		display.setOpaque(true);

		open = setupButton("Open", panel);
		play = setupButton("Play", panel);
		pause = setupButton("Pause", panel);
		stop = setupButton("Stop", panel);
		close = setupButton("Close", panel);
		exit = setupButton("Exit", panel);

		add(display);
		add(panel);

	}

	
	public void actionPerformed(ActionEvent e) {
		switch (state) {

		case READY: {
			state = 0;
			if (e.getSource() == open) {
				display.setText(open.getText());
				state = 1;
			}
			if (e.getSource() == exit) {
				System.exit(0);
			}
			break;

		}
		case OPEN: {
			if (e.getSource() == close) {
				display.setText(close.getText());
				state = 0;
			}
			if (e.getSource() == play) {
				display.setText(play.getText());
				state = 2;
			}
			if (e.getSource() == exit) {
				System.exit(0);
			}
			break;

		}
		case PAUSE: {
			if (e.getSource() == play) {
				display.setText(play.getText());
				state = 2;
			}
			if (e.getSource() == stop) {
				display.setText(stop.getText());
				state = 1;
			}
			if (e.getSource() == exit) {
				System.exit(0);
			}
			break;
		}
		case PLAY: {
			if (e.getSource() == exit) {
				System.exit(0);
			}
			if (e.getSource() == pause) {
				display.setText(pause.getText());
				state = 3;
			}
			if (e.getSource() == stop) {
				display.setText(stop.getText());
				state = 1;
			}
			break;
			}
		}
	}

}
