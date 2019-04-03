import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class TestBlackwhite {
	public static void main(String[] args) {
		Blackwhite b;
		b = new Blackwhite();
		b.setSize(200, 220);
		b.setTitle("Black and white");
		b.setVisible(true);
		b.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		b.flash2();
	}
}