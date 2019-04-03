import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DisplayTest {
	public static void main(String[] args) {
		Display display = new Display();
		display.setVisible(true);
		display.setSize(500, 250);
		display.setTitle("Display");
		display.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}
