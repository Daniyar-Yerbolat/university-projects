import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FontSize extends JFrame implements ActionListener {
	private JLabel letter;
	private JLabel size;
	private JButton plus, minus;
	private JPanel panel;
	int x = 18;

	@Override
	public void actionPerformed(ActionEvent press) {
		if (press.getSource() == plus) {
			letter.setFont(new Font("Font", Font.PLAIN, x + 1));
			size.setText("Size: " + (x + 1) + "");
			x = x + 1;
		} else if (press.getSource() == minus) {
			letter.setFont(new Font("Font", Font.PLAIN, x - 1));
			size.setText("Size: " + (x - 1) + "");
			x = x - 1;
		}

	}

	public FontSize() {

		Font font = new Font("Font", Font.PLAIN, x);

		setLayout(new GridLayout(3, 1));
		size = new JLabel();
		letter = new JLabel("", JLabel.CENTER);
		panel = new JPanel(new GridLayout(1, 2));
		minus = new JButton("Decrease");
		plus = new JButton("Increase");

		panel.add(minus);
		panel.add(plus);

		size.setText("Size: " + x + "");
		letter.setText("X");
		letter.setFont(font);
		minus.setBackground(Color.WHITE);
		plus.setBackground(Color.WHITE);

		plus.setOpaque(true);
		panel.setOpaque(true);
		letter.setOpaque(true);
		minus.setOpaque(true);
		size.setOpaque(true);

		add(letter);
		add(size);
		add(panel);

		plus.addActionListener(this);
		minus.addActionListener(this);

	}

}