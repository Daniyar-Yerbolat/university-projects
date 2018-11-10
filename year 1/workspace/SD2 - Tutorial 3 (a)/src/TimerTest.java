import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TimerTest {
	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.setVisible(true);
		timer.setSize(400, 300);
		timer.setTitle("Timer");
		timer.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}
