import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ProgressTest {
	public static void main(String[] args) {
		Progress test = new Progress();
		test.setSize(300, 600);
		test.setVisible(true);
		test.setTitle("Progress");
		test.click();
		test.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}
}
