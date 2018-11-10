import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Choices extends JFrame implements ActionListener {
	JPanel panel1, panel2;
	JLabel score1, score2;
	JLabel question;
	JButton answer1, answer2, answer3, answer4;
	String[] question_text = new String[4];
	String[][] answer_text = new String[4][4];
	int i = 0;
	int correct = 0;

	public JButton setButton(Container c) {
		JButton j = new JButton(" ");
		j.setFont(new Font("serif", Font.PLAIN, 18));
		j.setOpaque(true);
		j.setBackground(Color.WHITE);
		c.add(j);
		j.addActionListener(this);
		return j;
	}

	public void readFile() throws IOException {
		File file = new File("questions.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line;
		int l = 0;
		while ((line = br.readLine()) != null) {
			question_text[l] = line;
			l++;
		}
		br.close();
	}

	public void writeAnswers() throws IOException {
		File file = new File("answers.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line;
		int l = 0;
		while ((line = br.readLine()) != null) {
			answer_text[l][0] = line;
			answer_text[l][1] = br.readLine();
			answer_text[l][2] = br.readLine();
			answer_text[l][3] = br.readLine();
			l++;
		}
		br.close();
	}

	public Choices() throws IOException {

		readFile();
		writeAnswers();

		setLayout(new GridLayout(3, 1));
		question = new JLabel("", JLabel.CENTER);
		question.setBackground(Color.WHITE);
		score1 = new JLabel("", JLabel.CENTER);
		score1.setBackground(Color.WHITE);
		score2 = new JLabel("", JLabel.CENTER);
		score2.setBackground(Color.WHITE);
		JPanel panel2 = new JPanel(new GridLayout(1, 2));
		panel2.add(score1);
		panel2.add(score2);
		score();

		JPanel panel1 = new JPanel(new GridLayout(2, 2));
		answer1 = setButton(panel1);
		answer2 = setButton(panel1);
		answer3 = setButton(panel1);
		answer4 = setButton(panel1);

		changeText();

		add(panel2);
		add(question);
		add(panel1);

	}

	public void score() {
		score1.setText("Questions: " + i + "/" + question_text.length);
		score2.setText("Correct Answers: " + correct);
	}

	public void changeText() {
		if (i <= 3) {
			answer1.setText(answer_text[i][0]);
			answer2.setText(answer_text[i][1]);
			answer3.setText(answer_text[i][2]);
			answer4.setText(answer_text[i][3]);
			question.setText(question_text[i]);
			i++;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (i) {
		case 1: {
			if (e.getSource() == answer1) {
				correct = correct + 1;
				changeText();
				score();
			}
			if (e.getSource() == answer2) {
				changeText();
				score();
			}
			if (e.getSource() == answer3) {
				changeText();
				score();
			}
			if (e.getSource() == answer4) {
				changeText();
				score();
			}
			break;
		}
		case 2: {
			if (e.getSource() == answer1) {
				changeText();
				score();
			}
			if (e.getSource() == answer2) {
				correct = correct + 1;
				changeText();
				score();
			}
			if (e.getSource() == answer3) {
				changeText();
				score();
			}
			if (e.getSource() == answer4) {
				changeText();
				score();
			}
			break;
		}
		case 3: {
			if (e.getSource() == answer1) {
				changeText();
				score();
			}
			if (e.getSource() == answer2) {
				changeText();
				score();
			}
			if (e.getSource() == answer3) {
				correct = correct + 1;
				changeText();
				score();
			}
			if (e.getSource() == answer4) {
				changeText();
				score();
			}
			break;

		}
		case 4: {
			if (e.getSource() == answer1) {
				changeText();
				score();
				answer1.setEnabled(false);
				answer2.setEnabled(false);
				answer3.setEnabled(false);
				answer4.setEnabled(false);
			}
			if (e.getSource() == answer2) {
				changeText();
				score();
				answer1.setEnabled(false);
				answer2.setEnabled(false);
				answer3.setEnabled(false);
				answer4.setEnabled(false);
			}
			if (e.getSource() == answer3) {
				changeText();
				score();
				answer1.setEnabled(false);
				answer2.setEnabled(false);
				answer3.setEnabled(false);
				answer4.setEnabled(false);
			}
			if (e.getSource() == answer4) {
				correct = correct + 1;
				changeText();
				score();
				answer1.setEnabled(false);
				answer2.setEnabled(false);
				answer3.setEnabled(false);
				answer4.setEnabled(false);
			}
			break;

		}
		}

	}
}
