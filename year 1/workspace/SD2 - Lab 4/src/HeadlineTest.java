import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HeadlineTest {
	public static void main(String[] args) {
		Headline headline = new Headline();
		headline.setVisible(true);
		headline.setSize(500, 500);
		headline.setTitle("Headline");
		headline.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}
