import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestClock {
	public static void main(String[] args) {
		Clock clock = new Clock();
		clock.setSize(600,200);
		clock.setTitle("Clock");
		clock.setVisible(true);
		clock.change();
		clock.addWindowListener(new WindowAdapter() {
			public void closeWindow(WindowEvent e){System.exit(0);}
		});
	}
}
