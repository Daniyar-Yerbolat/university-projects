import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FontSizeTest {
	public static void main(String[] args) {
		FontSize fontsize = new FontSize();
		fontsize.setVisible(true);
		fontsize.setSize(400, 300);
		fontsize.setTitle("Font");
		fontsize.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}
