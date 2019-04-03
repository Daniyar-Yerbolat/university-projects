import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class ChoicesTest {
	public static void main(String[] args) throws IOException {
		Choices choices = new Choices();
		choices.setVisible(true);
		choices.setSize(500, 200);
		choices.setTitle("Choices");
		choices.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}