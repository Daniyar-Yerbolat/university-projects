import java.awt.Font;
import java.awt.GridLayout;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.StyledEditorKit.ItalicAction;

public class Clock extends JFrame {
	private JPanel clockdisplay;
	private JLabel hour, minute, second, am_pm;

	public Clock() {
		hour = new JLabel("H", JLabel.CENTER);
		minute = new JLabel("M", JLabel.CENTER);
		second = new JLabel("S", JLabel.CENTER);
		am_pm = new JLabel("AM/PM", JLabel.CENTER);

		Font font = new Font("Italic Serif", Font.ITALIC, 36);
		setLayout(new GridLayout(2, 1));

		clockdisplay = new JPanel(new GridLayout(1, 3));
		// Adding components to JPanel
		clockdisplay.add(hour);
		clockdisplay.add(minute);
		clockdisplay.add(second);
		// Setting properties for components
		hour.setFont(font);
		minute.setFont(font);
		second.setFont(font);
		hour.setOpaque(true);
		minute.setOpaque(true);
		second.setOpaque(true);
		// Adding components to JFrame
		// note: do at the very end
		add(clockdisplay);
		add(am_pm);

	}

	private void pause(long millisecs) {
		long starttime = Calendar.getInstance().getTimeInMillis();
		while (Calendar.getInstance().getTimeInMillis() - starttime < millisecs);
	}

	public void change() {
		while (true) {

			Calendar now = Calendar.getInstance();
			int hours = now.get(Calendar.HOUR_OF_DAY);
			int minutes = now.get(Calendar.MINUTE);
			int seconds = now.get(Calendar.SECOND);

			hour.setText(hours + "");
			minute.setText(minutes + "");
			second.setText(seconds + " ");
			pause(1000);
			
			if(hours >= 12){
				am_pm.setText("PM");
			}
			else if(hours <12){
				am_pm.setText("AM");
			}

		}
	}
}
