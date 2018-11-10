import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator extends JFrame implements ActionListener {
	JButton plus, minus, devide, multiply, clear;
	JLabel output;
	JTextField input;
	JPanel panel1;
	int x = 0;
	int y = 0;

	Font font = new Font("Font", Font.PLAIN, 36);

	public JButton format(String text) {
		JButton button = new JButton(text);
		button.setFont(font);
		button.setBackground(Color.WHITE);
		button.setOpaque(true);
		return button;
	}

	public Calculator() {
		setLayout(new GridLayout(3, 1));
		panel1 = new JPanel(new GridLayout(1, 5));

		plus = new JButton("+");
		minus = new JButton("-");
		devide = new JButton("/");
		multiply = new JButton("*");
		clear = new JButton("Clear");
		input = new JTextField(x + "", JTextField.CENTER);
		output = new JLabel(y + "", JLabel.CENTER);

		output.setBackground(Color.WHITE);
		output.setOpaque(true);
		output.setFont(font);
		input.setFont(font);

		add(input);
		add(panel1);
		add(output);

		panel1.add(plus);
		panel1.add(minus);
		panel1.add(devide);
		panel1.add(multiply);
		panel1.add(clear);

		plus.addActionListener(this);
		minus.addActionListener(this);
		devide.addActionListener(this);
		multiply.addActionListener(this);
		clear.addActionListener(this);
		input.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		
		x = Integer.parseInt(input.getText());
		y = Integer.parseInt(output.getText());
		/*
		 * if(){ plus.enable(false); minus.enable(false); devide.enable(false);
		 * multiply.enable(false); clear.enable(false);} else{
		 */
		if (e.getSource() == plus) {
			
			y = y + x;
			input.setText(x+"");
			output.setText(y+"");
		}
		if (e.getSource() == minus) {
			y = y - x;
			input.setText(x+"");
			output.setText(y+"");
		}
		if (e.getSource() == devide) {
			y = y / x;
			input.setText(x+"");
			output.setText(y+"");
		}
		if (e.getSource() == multiply) {
			y = y * x;
			input.setText(x+"");
			output.setText(y+"");

		}
		if (e.getSource() == clear) {
			y = 0;
			x = 0;
			input.setText(x+"");
			output.setText(y+"");
		}
	}
}
